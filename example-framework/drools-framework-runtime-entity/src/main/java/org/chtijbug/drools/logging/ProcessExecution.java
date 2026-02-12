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


public class ProcessExecution {


    private String processName;


    private Date startDate;
    private Date endDate;


    private ProcessExecutionStatus processExecutionStatus;

    private Long startEventID;

    private Long stopEventID;


    private String ProcessInstanceId;

    private String processPackageName;

    private String processVersion;

    private String processType;

    private String processId;


    private LinkedList<RuleflowGroup> ruleflowGroups = new LinkedList<RuleflowGroup>();


    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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


    public ProcessExecutionStatus getProcessExecutionStatus() {
        return processExecutionStatus;
    }

    public void setProcessExecutionStatus(ProcessExecutionStatus processExecutionStatus) {
        this.processExecutionStatus = processExecutionStatus;
    }

    public String getProcessInstanceId() {
        return ProcessInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        ProcessInstanceId = processInstanceId;
    }

    public String getProcessPackageName() {
        return processPackageName;
    }

    public void setProcessPackageName(String processPackageName) {
        this.processPackageName = processPackageName;
    }

    public String getProcessVersion() {
        return processVersion;
    }

    public void setProcessVersion(String processVersion) {
        this.processVersion = processVersion;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public LinkedList<RuleflowGroup> getRuleflowGroups() {
        return ruleflowGroups;
    }

    public void setRuleflowGroups(LinkedList<RuleflowGroup> ruleflowGroups) {
        this.ruleflowGroups = ruleflowGroups;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProcessRuntime{");
        sb.append(", processName='").append(processName).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", processRuntimeStatus=").append(processExecutionStatus);
        sb.append(", startEventID=").append(startEventID);
        sb.append(", ProcessInstanceId='").append(ProcessInstanceId).append('\'');
        sb.append(", processPackageName='").append(processPackageName).append('\'');
        sb.append(", processVersion='").append(processVersion).append('\'');
        sb.append(", processType='").append(processType).append('\'');
        sb.append(", processId='").append(processId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessExecution)) return false;

        ProcessExecution that = (ProcessExecution) o;

        if (!processName.equals(that.processName)) return false;
        if (!startDate.equals(that.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processName.hashCode();
        result = 31 * result + startDate.hashCode();
        return result;
    }

}
