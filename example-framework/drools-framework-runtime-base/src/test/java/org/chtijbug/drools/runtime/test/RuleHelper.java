package org.chtijbug.drools.runtime.test;

import org.chtijbug.drools.runtime.RuleBasePackage;
import org.chtijbug.drools.runtime.impl.RuleBaseSingleton;
import org.chtijbug.drools.runtime.listener.HistoryListener;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;

public class RuleHelper {

    public static RuleBasePackage createKB(String fileName, HistoryListener historyListener){
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource( fileName),
                ResourceType.DRL );
        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages( pkgs );
        return  RuleBaseSingleton.createkb(kbase,1000,historyListener);

    }
    public static RuleBasePackage createKBProcess(String fileName,String processName, HistoryListener historyListener){
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource( fileName),
                ResourceType.DRL );
        kbuilder.add(ResourceFactory.newClassPathResource( processName),
                ResourceType.BPMN2 );
        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addPackages( pkgs );
        return  RuleBaseSingleton.createkb(kbase,1000,historyListener);

    }
}
