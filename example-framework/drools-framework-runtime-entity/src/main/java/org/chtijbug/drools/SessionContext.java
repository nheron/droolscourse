package org.chtijbug.drools;

import org.chtijbug.drools.logging.*;

import java.util.ArrayList;
import java.util.Date;
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

    private String groupID;

    private String artefactID;

    private String version;

    private String containerId;

    private String serverName;

    private Date startTime;

    private Date stopTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getArtefactID() {
        return artefactID;
    }

    public void setArtefactID(String artefactID) {
        this.artefactID = artefactID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SessionContext{");
        sb.append("groupID='").append(groupID).append('\'');
        sb.append(", artefactID='").append(artefactID).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", containerId='").append(containerId).append('\'');
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", startTime=").append(startTime);
        sb.append(", stopTime=").append(stopTime);
        sb.append('}');
        return sb.toString();
    }
}
