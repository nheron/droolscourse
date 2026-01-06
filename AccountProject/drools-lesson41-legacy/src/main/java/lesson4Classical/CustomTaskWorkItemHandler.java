package lesson4Classical;

import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.kogito.Application;
import org.kie.kogito.internal.process.workitem.*;
import org.kie.kogito.process.workitems.impl.DefaultKogitoWorkItemHandler;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;


public class CustomTaskWorkItemHandler extends DefaultKogitoWorkItemHandler implements KogitoWorkItemHandler {
    private StatefulKnowledgeSession session;

    public CustomTaskWorkItemHandler(StatefulKnowledgeSession session) {
        super();
        this.session = session;
    }

    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        System.out.println("Hello from the custom work item definition.");
        System.out.println("Passed parameters:");

        for (String parameter : workItem.getParameters().keySet()) {
            System.out.println(parameter + " = " + workItem.getParameters().get(parameter));
        }

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("Result", "Message Returned from Work Item Handler");
        manager.completeWorkItem(workItem.getStringId(), results);
    }


    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        System.err.println("Error happened in the custom work item definition.");
    }


    public Optional<WorkItemTransition> activateWorkItemHandler(KogitoWorkItemManager manager, KogitoWorkItemHandler handler, KogitoWorkItem workitem, WorkItemTransition transition) {
        return Optional.empty();
    }

    public Optional<WorkItemTransition> completeWorkItemHandler(KogitoWorkItemManager manager, KogitoWorkItemHandler handler, KogitoWorkItem workitem, WorkItemTransition transition) {
        return java.util.Optional.of(this.workItemLifeCycle.newTransition("complete", workitem.getPhaseStatus(), null));

    }

    public Optional<WorkItemTransition> abortWorkItemHandler(KogitoWorkItemManager manager, KogitoWorkItemHandler handler, KogitoWorkItem workitem, WorkItemTransition transition) {
        System.err.println("Error happened in the custom work item definition.");

        return Optional.empty();
    }

    public Optional<WorkItemTransition> skipWorkItemHandler(KogitoWorkItemManager manager, KogitoWorkItemHandler handler, KogitoWorkItem workitem, WorkItemTransition transition) {
        return Optional.empty();
    }
}