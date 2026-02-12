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

import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.entity.DroolsRuleObject;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/02/12
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class BeforeRuleFiredHistoryEvent extends RuleHistoryEvent {
    protected ArrayList<DroolsFactObject> whenObjects;

    /**
     *
     */
    public BeforeRuleFiredHistoryEvent() {
    }

    public BeforeRuleFiredHistoryEvent(Long eventID, int ruleInstanceID, DroolsRuleObject rule, Long ruleBaseId, Long sessionId) {

        super(eventID, ruleInstanceID, rule, ruleBaseId, sessionId);
        this.whenObjects = new ArrayList<DroolsFactObject>();
    }

    public ArrayList<DroolsFactObject> getWhenObjects() {
        return whenObjects;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(super.toString() + "\n");

        str.append("When objects :\n");
        for (DroolsFactObject fact : whenObjects) {
            if (fact != null) {
                str.append("**" + fact.toString() + "\n");
            }
        }
        return str.toString();
    }
}
