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

/**
 * Created by IntelliJ IDEA.
 * Date: 23/11/12
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class DroolsRuleFlowGroupObject implements Serializable {
    private int ruleFlowInstanceID;
    private String name;

    public DroolsRuleFlowGroupObject() {
    }

    public DroolsRuleFlowGroupObject(int ruleFlowInstanceID, String name) {
        this.ruleFlowInstanceID = ruleFlowInstanceID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("DroolsRuleFlowGroupObject");
        sb.append("{ruleFlowInstanceID=").append(ruleFlowInstanceID);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
