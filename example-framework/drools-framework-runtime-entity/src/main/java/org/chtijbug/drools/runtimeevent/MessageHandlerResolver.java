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
package org.chtijbug.drools.runtimeevent;

import com.rits.cloning.Cloner;
import com.rits.cloning.ObjenesisInstantiationStrategy;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.runtimeevent.impl.fact.*;
import org.chtijbug.drools.runtimeevent.impl.knowledgeSession.*;
import org.chtijbug.drools.runtimeevent.impl.process.*;
import org.chtijbug.drools.runtimeevent.impl.rule.AfterRuleFiredEventStrategy;
import org.chtijbug.drools.runtimeevent.impl.rule.AfterRuleflowGroupActivatedEventStrategy;
import org.chtijbug.drools.runtimeevent.impl.rule.AfterRuleflowGroupDeactivatedEventStrategy;
import org.chtijbug.drools.runtimeevent.impl.rule.BeforeRuleFiredEventStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class MessageHandlerResolver {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerResolver.class);

    @Resource
    private List<AbstractMemoryEventHandlerStrategy> allMemoryStrategies = new ArrayList<>();

    private ClassLoader classLoader;

    public MessageHandlerResolver() {
        allMemoryStrategies.add(new DeleteFactEventStrategy());
        allMemoryStrategies.add(new InsertedByRelectionFactEndEventStrategy());
        allMemoryStrategies.add(new InsertedByRelectionFactStartEventStrategy());
        allMemoryStrategies.add(new InsertedFactEventStrategy());
        allMemoryStrategies.add(new UpdatedFactEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionCreateEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionDisposeEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionFireAllRulesAndStartProcessEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionFireAllRulesBeginEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionFireAllRulesEndEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionFireAllRulesMaxRulesEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionProcessBeginEventStrategy());
        allMemoryStrategies.add(new KnowledgeSessionProcessEndEventStrategy());
        allMemoryStrategies.add(new AfterNodeInstanceTriggeredEventStrategy());
        allMemoryStrategies.add(new AfterNodeLeftEventStrategy());
        allMemoryStrategies.add(new AfterProcessEndHistoryEventStrategy());
        allMemoryStrategies.add(new AfterProcessStartEventStrategy());
        allMemoryStrategies.add(new AfterVariableChangeEventStrategy());
        allMemoryStrategies.add(new BeforeNodeInstanceTriggeredEventStrategy());
        allMemoryStrategies.add(new BeforeNodeLeftEventStrategy());
        allMemoryStrategies.add(new BeforeProcessEndEventStrategy());
        allMemoryStrategies.add(new BeforeProcessStartEventStrategy());
        allMemoryStrategies.add(new BeforeVariableChangeEventStrategy());
        allMemoryStrategies.add(new AfterRuleFiredEventStrategy());
        allMemoryStrategies.add(new AfterRuleflowGroupActivatedEventStrategy());
        allMemoryStrategies.add(new AfterRuleflowGroupDeactivatedEventStrategy());
        allMemoryStrategies.add(new BeforeRuleFiredEventStrategy());

    }

    public SessionContext getSessionFromHistoryEvent(List<HistoryEvent> historyEvents) {
        Thread currentThread = Thread.currentThread();
        ClassLoader old = currentThread.getContextClassLoader();
        currentThread.setContextClassLoader(classLoader);
        SessionContext sessionContext = new SessionContext();
        Cloner cloner=new Cloner(new ObjenesisInstantiationStrategy());
        for (HistoryEvent historyEvent : historyEvents) {
            AbstractMemoryEventHandlerStrategy strategy = this.resolveMessageHandlerMemory(historyEvent);
            if (strategy != null) {
                try {
                    strategy.handleMessageInternally(historyEvent, sessionContext,cloner);
                }catch (Exception e){
                    logger.error("MessageHandle for class" + historyEvent.getClass().toString(), historyEvent, e);
                }
            }
        }

        sessionContext.getRuleflowGroups().clear();
        currentThread.setContextClassLoader(old);
        return sessionContext;
    }

    public AbstractMemoryEventHandlerStrategy resolveMessageHandlerMemory(HistoryEvent historyEvent) {
        for (AbstractMemoryEventHandlerStrategy strategy : allMemoryStrategies) {
            if (strategy.isEventSupported(historyEvent))
                return strategy;
        }
        return null;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader=classLoader;
    }
}
