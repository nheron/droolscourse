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
package org.chtijbug.drools.runtime.impl;

import org.chtijbug.drools.entity.history.EventCounter;
import org.chtijbug.drools.entity.history.knowledge.*;
import org.chtijbug.drools.kieserver.extension.KieServerAddOnElement;
import org.chtijbug.drools.runtime.DroolsChtijbugException;
import org.chtijbug.drools.runtime.RuleBasePackage;
import org.chtijbug.drools.runtime.RuleBaseSession;
import org.chtijbug.drools.runtime.listener.HistoryListener;
import org.chtijbug.drools.runtime.resource.FileKnowledgeResource;
import org.chtijbug.drools.runtime.resource.KnowledgeModule;
import org.chtijbug.drools.runtime.resource.WorkbenchClient;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import static com.google.common.base.Throwables.propagate;

/**
 * @author Bertrand Gressier
 */
public class RuleBaseSingleton implements RuleBasePackage {
    /**
     * default rule threshold
     */
    public static int DEFAULT_RULE_THRESHOLD = 2000;
    /**
     * Class Logger
     */
    private static Logger logger = LoggerFactory.getLogger(RuleBaseSingleton.class);
    /**
     * unique ID of the RuleBase in the JVM
     */
    protected EventCounter eventCounter = EventCounter.newCounter();
    protected EventCounter sessionCounter = EventCounter.newCounter();

    private KieBase kieBase;
    /**
     * Max rule to be fired threshold.
     */
    private int maxNumberRuleToExecute = DEFAULT_RULE_THRESHOLD;
    /**
     * Semaphore used to void concurrent access to the singleton
     */
    private Semaphore lockKbase = new Semaphore(1);
    /**
     * History Listener
     */
    private HistoryListener historyListener = null;

    public static RuleBaseSingleton createkb(KieBase kieBase, int maxNumberRulesToExecuter,HistoryListener historyListener){
        return new RuleBaseSingleton(kieBase,maxNumberRulesToExecuter,historyListener);
    }

    public RuleBaseSingleton(KieBase kieBase, int maxNumberRulesToExecuter,HistoryListener historyListener) {
        this.kieBase = kieBase;
        this.maxNumberRuleToExecute = maxNumberRulesToExecuter;
        this.historyListener = historyListener;

    }

    public int getMaxNumberRuleToExecute() {
        return maxNumberRuleToExecute;
    }

    @Override
    public RuleBaseSession createRuleBaseSession() throws DroolsChtijbugException {
        logger.debug(">>createRuleBaseSession");
        try {
            //____ Creating new Rule Base Session using default rule threshold
            return this.createRuleBaseSession(this.maxNumberRuleToExecute);
        } finally {
            logger.debug("<<createRuleBaseSession");
        }
    }

    @Override
    public RuleBaseSession createRuleBaseSession(int maxNumberRulesToExecute) throws DroolsChtijbugException {
        return this.createRuleBaseSession(maxNumberRulesToExecute, this.historyListener);
    }


    @Override
    public RuleBaseSession createRuleBaseSession(int maxNumberRulesToExecute, HistoryListener sessionHistoryListener) throws DroolsChtijbugException {
        logger.debug(">>createRuleBaseSession number Rules to execute {}", maxNumberRulesToExecute);
        RuleBaseSession newRuleBaseSession = null;
        try {
            if (kieBase != null) {
                //____ Acquire semaphore
                try {
                    lockKbase.acquire();
                } catch (Exception e) {
                    throw new DroolsChtijbugException(DroolsChtijbugException.KbaseAcquire, "", e);
                }
                //_____ Now we can create a new stateful session using KnowledgeBase
                KieSession newDroolsSession = null;

                newDroolsSession = this.kieBase.newKieSession();
                Long sessionId = this.sessionCounter.next();
                if (sessionHistoryListener != null) {
                    KnowledgeBaseCreateSessionEvent knowledgeBaseCreateSessionEvent = new KnowledgeBaseCreateSessionEvent(eventCounter.next(), new Date(),1L);
                    knowledgeBaseCreateSessionEvent.setSessionId(sessionId);
                    sessionHistoryListener.fireEvent(knowledgeBaseCreateSessionEvent);
                }

                //_____ Wrapping the knowledge Session
                newRuleBaseSession = new RuleBaseStatefulSession(1L, sessionId, newDroolsSession, maxNumberRulesToExecute, sessionHistoryListener);
                //_____ Release semaphore
            } else {
                throw new DroolsChtijbugException(DroolsChtijbugException.KbaseNotInitialised, "", null);
            }
            //____ return the wrapped KnowledgeSession
            return newRuleBaseSession;
        } finally {

            lockKbase.release();
            logger.debug("<<createRuleBaseSession", newRuleBaseSession);
        }
    }





    @Override
    public HistoryListener getHistoryListener() {
        return historyListener;
    }

    @Override
    public void dispose() {
        if (this.historyListener != null) {
            try {
                this.historyListener.fireEvent(new KnowledgeBaseDisposeEvent(eventCounter.next(), new Date(), 1L));
            } catch (DroolsChtijbugException e) {
                throw propagate(e);
            }
        }
        this.kieBase = null;
    }





    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RuleBaseSingleton{");

        sb.append('}');
        return sb.toString();
    }
}
