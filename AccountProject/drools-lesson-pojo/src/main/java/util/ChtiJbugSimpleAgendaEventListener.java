package util;

import org.kie.api.event.rule.*;

public class ChtiJbugSimpleAgendaEventListener implements AgendaEventListener {
    public void matchCreated(MatchCreatedEvent event) {
        System.out.println("The rule "
                + event.getMatch().getRule().getName()
                + " can be fired in agenda");
    }

    public void matchCancelled(MatchCancelledEvent event) {
        System.out.println("The rule "
                + event.getMatch().getRule().getName()
                + " cannot b in agenda");
    }

    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        System.out.println("The rule "
                + event.getMatch().getRule().getName()
                + " will be fired");
    }

    public void afterMatchFired(AfterMatchFiredEvent event) {
        System.out.println("The rule "
                + event.getMatch().getRule().getName()
                + " has be fired");
    }

    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {

    }

    public void agendaGroupPushed(AgendaGroupPushedEvent event) {

    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {

    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {

    }

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {

    }

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {

    }
}
