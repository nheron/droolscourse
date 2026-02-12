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
package org.chtijbug.drools.runtimeevent.impl.knowledgeSession;


import com.rits.cloning.Cloner;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.entity.history.session.SessionFireAllRulesBeginEvent;
import org.chtijbug.drools.logging.FireAllRulesExecution;
import org.chtijbug.drools.logging.FireAllRulesExecutionStatus;
import org.chtijbug.drools.logging.SessionExecution;
import org.chtijbug.drools.runtimeevent.AbstractMemoryEventHandlerStrategy;

public class KnowledgeSessionFireAllRulesBeginEventStrategy implements AbstractMemoryEventHandlerStrategy {

    @Override
    public void handleMessageInternally(HistoryEvent historyEvent, SessionContext sessionContext, Cloner cloner) {
        SessionFireAllRulesBeginEvent sessionFireAllRulesBeginEvent = (SessionFireAllRulesBeginEvent) historyEvent;

        SessionExecution existingSessionRutime = sessionContext.getSessionExecution();

        FireAllRulesExecution fireAllRulesExecution = new FireAllRulesExecution();
        fireAllRulesExecution.setStartEventID(sessionFireAllRulesBeginEvent.getEventID());
        fireAllRulesExecution.setStartDate(sessionFireAllRulesBeginEvent.getDateEvent());
        fireAllRulesExecution.setFireAllRulesExecutionStatus(FireAllRulesExecutionStatus.STARTED);
        existingSessionRutime.getFireAllRulesExecutions().add(fireAllRulesExecution);
        sessionContext.setFireAllRulesExecution(fireAllRulesExecution);
    }

    @Override
    public boolean isEventSupported(HistoryEvent historyEvent) {

        return historyEvent instanceof SessionFireAllRulesBeginEvent;
    }
}
