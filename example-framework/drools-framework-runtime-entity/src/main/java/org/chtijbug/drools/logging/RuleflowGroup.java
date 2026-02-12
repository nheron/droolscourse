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

import java.util.Date;
import java.util.LinkedList;


public class RuleflowGroup {


    private String ruleflowGroup;


    private LinkedList<RuleExecution> ruleExecutionList = new LinkedList<>();


    private Date startDate;

    private Date endDate;

    private Long startEventID;

    private Long stopEventID;


    private RuleflowGroupStatus ruleflowGroupStatus;


    public String getRuleflowGroup() {
        return ruleflowGroup;
    }

    public void setRuleflowGroup(String ruleflowGroup) {
        this.ruleflowGroup = ruleflowGroup;
    }


    public LinkedList<RuleExecution> getRuleExecutionList() {
        return ruleExecutionList;
    }

    public void setRuleExecutionList(LinkedList<RuleExecution> ruleExecutionList) {
        this.ruleExecutionList = ruleExecutionList;
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

    public RuleflowGroupStatus getRuleflowGroupStatus() {
        return ruleflowGroupStatus;
    }

    public void setRuleflowGroupStatus(RuleflowGroupStatus ruleflowGroupStatus) {
        this.ruleflowGroupStatus = ruleflowGroupStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuleflowGroup)) return false;

        RuleflowGroup that = (RuleflowGroup) o;


        if (!ruleflowGroup.equals(that.ruleflowGroup)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruleflowGroup.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RuleflowGroupRuntime{");
        sb.append(", ruleflowGroup='").append(ruleflowGroup).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", ruleflowGroupRuntimeStatus=").append(ruleflowGroupStatus);
        sb.append('}');
        return sb.toString();
    }
}
