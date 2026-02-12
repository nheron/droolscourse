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
 * @author Bertrand Gressier
 */
public class DroolsRuleObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -716077698281963299L;

    protected String ruleName;
    protected String rulePackageName;
    protected String ruleFlowGroup;

    /**
     *
     */
    public DroolsRuleObject() {
    }

    /**
     * @param ruleName        - the rule name added to the knowledge base
     * @param rulePackageName - the package of the rule
     */
    protected DroolsRuleObject(String ruleName, String rulePackageName) {
        this.ruleName = ruleName;
        this.rulePackageName = rulePackageName;
    }

    public static DroolsRuleObject createDroolRuleObject(String ruleName, String rulePackageName) {
        return new DroolsRuleObject(ruleName, rulePackageName);
    }

    /**
     * @return the ruleName
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * @return the rulePackageName
     */
    public String getRulePackageName() {
        return rulePackageName;
    }

    public String getRuleFlowGroup() {
        return ruleFlowGroup;
    }

    public void setRuleFlowGroup(String ruleFlowGroup) {
        this.ruleFlowGroup = ruleFlowGroup;
    }

    /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
    @Override
    public String toString() {
        return "Rule object with name :" + getRuleName();
    }
}
