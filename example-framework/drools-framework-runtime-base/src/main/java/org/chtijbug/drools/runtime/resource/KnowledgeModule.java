package org.chtijbug.drools.runtime.resource;

import org.chtijbug.drools.entity.history.EventCounter;
import org.chtijbug.drools.entity.history.knowledge.KnowledgeBaseAddResourceEvent;
import org.chtijbug.drools.runtime.DroolsChtijbugException;
import org.chtijbug.drools.runtime.listener.HistoryListener;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Throwables.propagate;

public class KnowledgeModule {

    private final String groupId;
    private final String artifactId;
    private final String version;
    private final KieServices kieServices;
    private String ruleBaseName;
    private final KieResources kieResources;
    private final KieFileSystem kieFileSystem;
    private final KieRepository kieRepository;
    private final HistoryListener historyListener;
    private final EventCounter sharedCounter;

    private final Long ruleBaseId;
    private ReleaseId releaseId;
    private boolean fileBaseModule = false;

    private WorkbenchClient workbenchClient;

    public KnowledgeModule(Long ruleBaseId, HistoryListener historyListener, String groupId, String artifactId, String version, EventCounter sharedCounter) {
        this.ruleBaseId = ruleBaseId;
        this.historyListener = historyListener;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.kieServices = KieServices.Factory.get();
        this.kieRepository = kieServices.getRepository();
        this.kieResources = kieServices.getResources();
        this.kieFileSystem = kieServices.newKieFileSystem();
        this.sharedCounter = sharedCounter;
    }

    public KnowledgeModule(String ruleBaseName, HistoryListener historyListener, EventCounter sharedCounter) {
        this.ruleBaseName = ruleBaseName;
        this.historyListener = historyListener;
        this.groupId = null;
        this.artifactId = null;
        this.version = null;
        this.kieServices = KieServices.Factory.get();
        this.kieRepository = kieServices.getRepository();
        this.kieResources = kieServices.getResources();
        this.kieFileSystem = kieServices.newKieFileSystem();
        this.sharedCounter = sharedCounter;
        this.ruleBaseId = 1L;
    }

    public KnowledgeModule(Long ruleBaseId, HistoryListener historyListener, EventCounter sharedCounter) {
        this.ruleBaseId = ruleBaseId;
        this.historyListener = historyListener;
        this.groupId = null;
        this.artifactId = null;
        this.version = null;
        this.kieServices = KieServices.Factory.get();
        this.kieRepository = kieServices.getRepository();
        this.kieResources = kieServices.getResources();
        this.kieFileSystem = kieServices.newKieFileSystem();
        this.sharedCounter = sharedCounter;
    }

    public void addAllFiles(List<FileKnowledgeResource> files) {
        for (FileKnowledgeResource file : files) {
            addRuleFile(groupId + ".rules", file);
        }
    }

    public void addRuleFile(String packageName, FileKnowledgeResource ruleResource) {
        this.fileBaseModule = true;

        packageName = packageName.replace(".", "/");
        String resourcePath = "src/main/resources/" + packageName + "/" + ruleResource.getPath();
        kieFileSystem.write(resourcePath, ruleResource.getResource());
        if (historyListener != null)
            try {
                historyListener.fireEvent(
                        new KnowledgeBaseAddResourceEvent(
                                sharedCounter.next(), new Date(), this.ruleBaseId, ruleResource));
            } catch (DroolsChtijbugException e) {
                throw propagate(e);
            }
    }

    public KieContainer build() {

        this.releaseId = kieServices.newReleaseId(groupId, artifactId, version);
        if (fileBaseModule) {
            this.kieFileSystem.generateAndWritePomXML(releaseId);
            KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
            kb.buildAll();
            if (kb.getResults().hasMessages(Message.Level.ERROR)) {
                throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
            }
        }
        return this.kieServices.newKieContainer(releaseId);
    }

    public KieContainer buildFromClassPath(ClassLoader classLoader) {
        return this.kieServices.getKieClasspathContainer(classLoader);
    }
    public KieContainer buildFromClassPath() {

        return this.kieServices.getKieClasspathContainer();
    }

    public void addWorkbenchResource(String workbenchUrl, String username, String password) {
        try (WorkbenchClient client = new WorkbenchClient(workbenchUrl, username, password)) {
            this.workbenchClient = client;
            Resource resource = kieServices.getResources().newInputStreamResource(client.getWorkbenchResource(this));
            this.kieRepository.addKieModule(resource);
            if (historyListener != null)
                try {
                    WorkbenchKnowledgeResource workbenchRuleResource = new WorkbenchKnowledgeResource(workbenchUrl, this.groupId, this.artifactId, this.version);
                    historyListener.fireEvent(
                            new KnowledgeBaseAddResourceEvent(
                                    sharedCounter.next(), new Date(), this.ruleBaseId,
                                    workbenchRuleResource));
                } catch (DroolsChtijbugException e) {
                    throw propagate(e);
                }
        }
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public WorkbenchClient getWorkbenchClient() {
        return workbenchClient;
    }
}
