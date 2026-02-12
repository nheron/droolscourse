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
 * @author nheron
 */
public class DroolsNodeObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2149698078767524188L;
    protected String id;
    private DroolsNodeType nodeType = DroolsNodeType.Other;

    private String ruleflowGroupName;

    /**
     *
     */
    public DroolsNodeObject() {
    }

    public DroolsNodeObject(String id, DroolsNodeType nodeType) {
        this.id = id;
        this.nodeType = nodeType;
    }

    protected DroolsNodeObject(String id) {
        this.id = id;
        this.nodeType = DroolsNodeType.Other;
    }

    public static DroolsNodeObject createDroolsNodeObject(String id, DroolsNodeType nodeType) {
        return new DroolsNodeObject(id, nodeType);
    }

    public String getId() {
        return id;
    }

    public DroolsNodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(DroolsNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public String getRuleflowGroupName() {
        return ruleflowGroupName;
    }

    public void setRuleflowGroupName(String ruleflowGroupName) {
        if (ruleflowGroupName != null && ruleflowGroupName.length() > 0) {
            this.nodeType = DroolsNodeType.RuleNode;
        }
        this.ruleflowGroupName = ruleflowGroupName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DroolsNodeObject other = (DroolsNodeObject) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("DroolsNodeObject");
        sb.append("{id='").append(id).append('\'');
        sb.append("nodeType='").append(nodeType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
