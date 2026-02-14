package lesson41;

import org.kie.api.runtime.process.WorkItem;
import org.drools.core.process.instance.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class StopWorkItemHandler implements WorkItemHandler {

    public static StopWorkItemHandler stopTask;

    private WorkItem workItem;

    private WorkItemManager manager;

    public StopWorkItemHandler() {
        stopTask = this;

    }

    public WorkItemManager getManager() {
        return manager;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        this.workItem = workItem;
        this.manager = manager;
        System.out.println("executeWorkItem id="+workItem.getId()+" processinstanceid="+workItem.getProcessInstanceId());


    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("abortWorkItem id="+workItem.getId()+" processinstanceid="+workItem.getProcessInstanceId());
    }
}
