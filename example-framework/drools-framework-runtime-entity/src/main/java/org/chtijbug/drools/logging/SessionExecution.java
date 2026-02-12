/*
 * Copyright 2014 Pymma Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chtijbug.drools.logging;


import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SessionExecution {


    private Long id;
    private Long sessionId;
    private Date startDate;
    private Date endDate;
    private Long startEventID;
    private Long stopEventID;

    private PlatformRuntimeMode platformRuntimeMode = PlatformRuntimeMode.Debug;


    private SessionExecutionStatus sessionExecutionStatus;

    private List<RuleExecution> ruleExecutions = new ArrayList<>();

    private List<ProcessExecution> processExecutions = new ArrayList<>();

    private List<FireAllRulesExecution> fireAllRulesExecutions = new ArrayList<>();

    private List<Fact> facts = new ArrayList<Fact>();
    private Date processingStartDate;
    private Date processingStopDate;

    public SessionExecution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getStartEventID() {
        return startEventID;
    }

    public void setStartEventID(Long startEventID) {
        this.startEventID = startEventID;
    }

    public Long getStopEventID() {
        return stopEventID;
    }

    public void setStopEventID(Long stopEventID) {
        this.stopEventID = stopEventID;
    }

    public SessionExecutionStatus getSessionExecutionStatus() {
        return sessionExecutionStatus;
    }

    public void setSessionExecutionStatus(SessionExecutionStatus sessionExecutionStatus) {
        this.sessionExecutionStatus = sessionExecutionStatus;
    }

    public List<FireAllRulesExecution> getFireAllRulesExecutions() {
        return fireAllRulesExecutions;
    }

    public void setFireAllRulesExecutions(List<FireAllRulesExecution> fireAllRulesExecutions) {
        this.fireAllRulesExecutions = fireAllRulesExecutions;
    }

    public List<RuleExecution> getRuleExecutions() {
        return ruleExecutions;
    }

    public void setRuleExecutions(List<RuleExecution> ruleExecutions) {
        this.ruleExecutions = ruleExecutions;
    }

    public List<ProcessExecution> getProcessExecutions() {
        return processExecutions;
    }

    public void setProcessExecutions(List<ProcessExecution> processExecutions) {
        this.processExecutions = processExecutions;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public java.util.Collection<Fact> getFactsByType(final FactType factType) {
        if (this.getFacts().isEmpty())
            return Lists.newArrayList();
        return Collections2.filter(this.getFacts(), fact -> fact.getFactType().equals(factType));
    }

    public Date getProcessingStartDate() {
        return processingStartDate;
    }

    public void setProcessingStartDate(Date processionStartDate) {
        this.processingStartDate = processionStartDate;
    }

    public Date getProcessingStopDate() {
        return processingStopDate;
    }

    public void setProcessingStopDate(Date processingStopDate) {
        this.processingStopDate = processingStopDate;
    }

    public PlatformRuntimeMode getPlatformRuntimeMode() {
        return platformRuntimeMode;
    }

    public void setPlatformRuntimeMode(PlatformRuntimeMode platformRuntimeMode) {
        this.platformRuntimeMode = platformRuntimeMode;
    }
}
