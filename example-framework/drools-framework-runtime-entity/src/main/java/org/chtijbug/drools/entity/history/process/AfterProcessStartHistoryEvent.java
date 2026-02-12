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
package org.chtijbug.drools.entity.history.process;

import org.chtijbug.drools.entity.DroolsProcessInstanceObject;

/**
 * @author nheron
 */
public class AfterProcessStartHistoryEvent extends ProcessHistoryEvent {


    /**
     *
     */
    public AfterProcessStartHistoryEvent() {
    }

    public AfterProcessStartHistoryEvent(Long eventID, DroolsProcessInstanceObject processInstance, Long ruleBaseId, Long sessionId) {
        super(eventID, ruleBaseId, sessionId);
        this.processInstance = processInstance;
    }

    public DroolsProcessInstanceObject getProcessInstance() {
        return processInstance;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AfterProcessStartHistoryEvent{");
        sb.append("processInstance=").append(processInstance);
        sb.append('}');
        return sb.toString();
    }
}
