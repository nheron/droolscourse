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
package org.chtijbug.drools.runtimeevent.impl.rule;


import com.rits.cloning.Cloner;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.entity.history.rule.BeforeRuleFiredHistoryEvent;
import org.chtijbug.drools.logging.*;
import org.chtijbug.drools.runtimeevent.AbstractMemoryEventHandlerStrategy;

public class BeforeRuleFiredEventStrategy implements AbstractMemoryEventHandlerStrategy {

    @Override
    public void handleMessageInternally(HistoryEvent historyEvent, SessionContext sessionContext,Cloner cloner) {
        BeforeRuleFiredHistoryEvent beforeRuleFiredHistoryEvent = (BeforeRuleFiredHistoryEvent) historyEvent;
        RuleExecution ruleExecution = new RuleExecution();
        sessionContext.setRuleExecution(ruleExecution);
        if (beforeRuleFiredHistoryEvent.getRule().getRuleFlowGroup() != null && beforeRuleFiredHistoryEvent.getRule().getRuleFlowGroup().length() > 0) {
            String ruleFlowName = beforeRuleFiredHistoryEvent.getRule().getRuleFlowGroup();
            RuleflowGroup ruleflowGroup = sessionContext.findRuleFlowGroup(ruleFlowName);
            if (ruleflowGroup == null) {
                ruleflowGroup = new RuleflowGroup();
                ruleflowGroup.setRuleflowGroup(beforeRuleFiredHistoryEvent.getRule().getRuleFlowGroup());
                sessionContext.getRuleflowGroups().add(ruleflowGroup);
                sessionContext.getProcessExecution().getRuleflowGroups().add(ruleflowGroup);
            }

            ruleflowGroup.getRuleExecutionList().add(ruleExecution);
        } else {
            SessionExecution sessionExecution = sessionContext.getSessionExecution();
            sessionExecution.getRuleExecutions().add(ruleExecution);
        }
        ruleExecution.setStartDate(beforeRuleFiredHistoryEvent.getDateEvent());
        ruleExecution.setRuleName(beforeRuleFiredHistoryEvent.getRule().getRuleName());
        ruleExecution.setPackageName(beforeRuleFiredHistoryEvent.getRule().getRulePackageName());
        ruleExecution.setStartEventID(beforeRuleFiredHistoryEvent.getEventID());
        for (DroolsFactObject droolsFactObject : beforeRuleFiredHistoryEvent.getWhenObjects()) {
            if (droolsFactObject != null) {
                Fact fact = new Fact();
                fact.setRealFact(cloner.deepClone(droolsFactObject.getRealObject()));
                fact.setFactType(FactType.WHEN);
                fact.setObjectVersion(droolsFactObject.getObjectVersion());
                fact.setFullClassName(droolsFactObject.getFullClassName());
                fact.setEventid(beforeRuleFiredHistoryEvent.getEventID());
                ruleExecution.getWhenFacts().add(fact);
            }
        }

    }

    @Override
    public boolean isEventSupported(HistoryEvent historyEvent) {

        return historyEvent instanceof BeforeRuleFiredHistoryEvent;
    }


}
