package lesson4Classical;


import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.internal.runtime.StatefulKnowledgeSession;


public class DrlCustomTask implements WorkItemHandler {

    private StatefulKnowledgeSession session;

    public DrlCustomTask(StatefulKnowledgeSession session) {
        this.session = session;
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        System.out.println("coucou");
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        System.out.println("coucou");
    }
}