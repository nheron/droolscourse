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
package org.chtijbug.drools.entity;

import java.io.Serializable;


public class DroolsJbpmVariableObject implements Serializable {
    private final transient Object variableValue;
    private String variableId;
    private String variableInstanceId;

    public DroolsJbpmVariableObject() {
        this.variableValue = null;
    }

    public DroolsJbpmVariableObject(String variableId, String variableInstanceId, Object variableValue) {
        this.variableId = variableId;
        this.variableInstanceId = variableInstanceId;
        this.variableValue = variableValue;
    }

    public String getVariableInstanceId() {
        return variableInstanceId;
    }

    public void setVariableInstanceId(String variableInstanceId) {
        this.variableInstanceId = variableInstanceId;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    public Object getVariableValue() {
        return variableValue;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DroolsJbpmVariableObject{");
        sb.append("variableId='").append(variableId).append('\'');
        sb.append(", variableValue=").append(variableValue);
        sb.append('}');
        return sb.toString();
    }
}
