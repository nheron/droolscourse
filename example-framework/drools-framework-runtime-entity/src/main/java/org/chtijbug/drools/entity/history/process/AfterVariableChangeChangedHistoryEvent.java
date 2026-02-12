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

import org.chtijbug.drools.entity.DroolsJbpmVariableObject;
import org.chtijbug.drools.entity.DroolsProcessObject;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/02/14
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class AfterVariableChangeChangedHistoryEvent extends ProcessHistoryEvent {

    private DroolsJbpmVariableObject oldValue;
    private DroolsJbpmVariableObject newValue;
    private DroolsProcessObject droolsProcessObject;

    public AfterVariableChangeChangedHistoryEvent() {
    }

    public AfterVariableChangeChangedHistoryEvent(Long eventID, DroolsProcessObject droolsProcessObject, Long ruleBaseId, Long sessionId, DroolsJbpmVariableObject oldValue, DroolsJbpmVariableObject newValue) {
        super(eventID, ruleBaseId, sessionId);
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.droolsProcessObject = droolsProcessObject;
    }

    public DroolsJbpmVariableObject getOldValue() {
        return oldValue;
    }

    public void setOldValue(DroolsJbpmVariableObject oldValue) {
        this.oldValue = oldValue;
    }

    public DroolsJbpmVariableObject getNewValue() {
        return newValue;
    }

    public void setNewValue(DroolsJbpmVariableObject newValue) {
        this.newValue = newValue;
    }

    public DroolsProcessObject getDroolsProcessObject() {
        return droolsProcessObject;
    }

    public void setDroolsProcessObject(DroolsProcessObject droolsProcessObject) {
        this.droolsProcessObject = droolsProcessObject;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AfterVariableChangeChangedHistoryEvent{");
        sb.append("oldValue=").append(oldValue);
        sb.append(", newValue=").append(newValue);
        sb.append(", droolsProcessObject=").append(droolsProcessObject);
        sb.append('}');
        return sb.toString();
    }
}
