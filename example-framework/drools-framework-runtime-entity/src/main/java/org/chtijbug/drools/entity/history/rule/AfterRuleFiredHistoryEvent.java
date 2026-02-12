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

/**
 * @author nheron
 */
public class AfterRuleFiredHistoryEvent extends RuleHistoryEvent {

    /**
     *
     */
    private static final long serialVersionUID = -8587421328193577240L;

    /**
     *
     */
    public AfterRuleFiredHistoryEvent() {
    }

    public AfterRuleFiredHistoryEvent(Long eventID, int ruleInstanceID, DroolsRuleObject rule, Long ruleBaseId, Long sessionId) {

        super(eventID, ruleInstanceID, rule, ruleBaseId, sessionId);
    }


    /*
      * (non-Javadoc)
      *
      * @see org.chtijbug.drools.entity.history.HistoryEvent#toString()
      */
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(super.toString() + "\n");

        str.append("End Rule :\n");
        return str.toString();
    }

}
