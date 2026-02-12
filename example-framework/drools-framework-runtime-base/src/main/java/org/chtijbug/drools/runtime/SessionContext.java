package org.chtijbug.drools.runtime;

import org.chtijbug.drools.logging.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nheron on 11/06/15.
 */
public class SessionContext {

    private SessionExecution sessionExecution;

    private ProcessExecution processExecution;


    private List<RuleflowGroup> ruleflowGroups = new ArrayList<>();

    private RuleExecution ruleExecution;

    private FireAllRulesExecution fireAllRulesExecution;

    private Fact fact;

    public SessionExecution getSessionExecution() {
        return sessionExecution;
    }

    public void setSessionExecution(SessionExecution sessionExecution) {
        this.sessionExecution = sessionExecution;
    }

    public ProcessExecution getProcessExecution() {
        return processExecution;
    }

    public void setProcessExecution(ProcessExecution processExecution) {
        this.processExecution = processExecution;
    }

    public List<RuleflowGroup> getRuleflowGroups() {
        return ruleflowGroups;
    }

    public void setRuleflowGroups(List<RuleflowGroup> ruleflowGroups) {
        this.ruleflowGroups = ruleflowGroups;
    }

    public RuleflowGroup findRuleFlowGroup(String name) {
        RuleflowGroup result = null;
        for (RuleflowGroup r : ruleflowGroups) {
            if (r.getRuleflowGroup() != null && r.getRuleflowGroup().equals(name)) {
                result = r;
            }

        }

        return result;
    }

    public RuleExecution getRuleExecution() {
        return ruleExecution;
    }

    public void setRuleExecution(RuleExecution ruleExecution) {
        this.ruleExecution = ruleExecution;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public FireAllRulesExecution getFireAllRulesExecution() {
        return fireAllRulesExecution;
    }

    public void setFireAllRulesExecution(FireAllRulesExecution fireAllRulesExecution) {
        this.fireAllRulesExecution = fireAllRulesExecution;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SessionContext{");
        sb.append("sessionExecution=").append(sessionExecution);
        sb.append(", processExecution=").append(processExecution);
        sb.append(", ruleExecution=").append(ruleExecution);
        sb.append(", fireAllRulesExecution=").append(fireAllRulesExecution);
        sb.append(", fact=").append(fact);
        sb.append('}');
        return sb.toString();
    }
}
