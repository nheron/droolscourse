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
package org.chtijbug.drools.runtimeevent.impl.fact;


import com.rits.cloning.Cloner;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.entity.history.fact.UpdatedFactHistoryEvent;
import org.chtijbug.drools.logging.Fact;
import org.chtijbug.drools.logging.FactType;
import org.chtijbug.drools.logging.RuleExecution;
import org.chtijbug.drools.logging.SessionExecution;
import org.chtijbug.drools.runtimeevent.AbstractMemoryEventHandlerStrategy;


public class UpdatedFactEventStrategy implements AbstractMemoryEventHandlerStrategy {


    @Override
    public void handleMessageInternally(HistoryEvent historyEvent, SessionContext sessionContext,Cloner cloner) {
        UpdatedFactHistoryEvent updatedFactHistoryEvent = (UpdatedFactHistoryEvent) historyEvent;
        Fact factOldValue = new Fact();
        factOldValue.setFullClassName(updatedFactHistoryEvent.getObjectOldValue().getFullClassName());
        factOldValue.setObjectVersion(updatedFactHistoryEvent.getObjectOldValue().getObjectVersion());
        factOldValue.setRealFact(cloner.deepClone(updatedFactHistoryEvent.getObjectOldValue().getRealObject()));
        factOldValue.setModificationDate(updatedFactHistoryEvent.getDateEvent());
        factOldValue.setFactType(FactType.UPDATED_OLDVALUE);
        factOldValue.setEventid(updatedFactHistoryEvent.getEventID());
        Fact factNewValue = new Fact();
        factNewValue.setFullClassName(updatedFactHistoryEvent.getObjectNewValue().getFullClassName());
        factNewValue.setObjectVersion(updatedFactHistoryEvent.getObjectNewValue().getObjectVersion());
        factNewValue.setRealFact(cloner.deepClone(updatedFactHistoryEvent.getObjectNewValue().getRealObject()));
        factNewValue.setModificationDate(updatedFactHistoryEvent.getDateEvent());
        factNewValue.setFactType(FactType.UPDATED_NEWVALUE);
        factNewValue.setEventid(updatedFactHistoryEvent.getEventID());
        RuleExecution existingInSessionRuleExecution = null;
        if (updatedFactHistoryEvent.getRuleName() == null) {  // updated from a session
            SessionExecution sessionExecution = sessionContext.getSessionExecution();
            sessionExecution.getFacts().add(factOldValue);
            sessionExecution.getFacts().add(factNewValue);
        } else {   // updated from a rule that is not in a ruleflow/process
            existingInSessionRuleExecution = sessionContext.getRuleExecution();
            existingInSessionRuleExecution.getThenFacts().add(factOldValue);
            existingInSessionRuleExecution.getThenFacts().add(factNewValue);
        }

    }

    @Override
    public boolean isEventSupported(HistoryEvent historyEvent) {

        return historyEvent instanceof UpdatedFactHistoryEvent;
    }

}
