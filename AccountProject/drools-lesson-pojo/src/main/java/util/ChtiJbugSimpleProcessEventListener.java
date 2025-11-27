package util;

import org.kie.api.event.process.*;

public class ChtiJbugSimpleProcessEventListener implements ProcessEventListener {
    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeProcessStarted(ProcessStartedEvent arg0) {
        System.out.println("Process Name " + arg0.getProcessInstance().getProcessName() + " will be started");


    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
        System.out.println("Process Name " + arg0.getProcessInstance().getProcessName() + " will be stopped");

    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println("Node Name " + arg0.getNodeInstance().getNodeName() + " has been started");


    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {

        System.out.println("Node Name " + arg0.getNodeInstance().getNodeName() + " will be left");


    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent arg0) {
        System.out.println("Process Name " + arg0.getProcessInstance().getProcessName() + " was started");
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent arg0) {
        System.out.println("Process Name " + arg0.getProcessInstance().getProcessName() + " was stopped");


    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent arg0) {
        System.out.println("Node Name " + arg0.getNodeInstance().getNodeName() + " has been quit");
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
        System.out.println("Node Name " + arg0.getNodeInstance().getNodeName() + " has been left");
    }
}
