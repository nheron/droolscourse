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
package org.chtijbug.drools.entity.history.rule;

import org.chtijbug.drools.entity.DroolsRuleObject;
import org.chtijbug.drools.entity.history.HistoryEvent;

import java.util.Date;

/**
 * @author nheron
 */
public class RuleHistoryEvent extends HistoryEvent {

    private static final long serialVersionUID = 7433690026159716847L;
    protected DroolsRuleObject rule;
    /**
     * ruleInstanceID
     * Each rule that is executed in a sessoin gets a ruleInstanceID
     */
    private int ruleInstanceId;

    /**
     *
     */
    public RuleHistoryEvent() {
    }

    public RuleHistoryEvent(Long eventID, int ruleInstanceId, DroolsRuleObject rule, Long ruleBaseId, Long sessionId) {

        super(eventID, new Date(), HistoryEvent.TypeEvent.Rule);
        this.ruleInstanceId = ruleInstanceId;
        this.rule = rule;
        this.setRuleBaseID(ruleBaseId);
        this.setSessionId(sessionId);
    }

    public DroolsRuleObject getRule() {
        return rule;
    }

    public void setRule(DroolsRuleObject rule) {
        this.rule = rule;
    }

    public int getRuleInstanceId() {
        return ruleInstanceId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RuleHistoryEvent{");
        sb.append("rule=").append(rule);
        sb.append(", ruleInstanceId=").append(ruleInstanceId);
        sb.append('}');
        return sb.toString();
    }
}
