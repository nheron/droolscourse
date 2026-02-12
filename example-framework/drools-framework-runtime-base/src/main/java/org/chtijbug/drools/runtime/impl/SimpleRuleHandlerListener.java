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

import com.rits.cloning.Cloner;
import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.entity.DroolsRuleFlowGroupObject;
import org.chtijbug.drools.entity.DroolsRuleObject;
import org.chtijbug.drools.entity.history.rule.AfterRuleFiredHistoryEvent;
import org.chtijbug.drools.entity.history.rule.AfterRuleFlowActivatedHistoryEvent;
import org.chtijbug.drools.entity.history.rule.AfterRuleFlowDeactivatedHistoryEvent;
import org.chtijbug.drools.entity.history.rule.BeforeRuleFiredHistoryEvent;
import org.chtijbug.drools.entity.history.session.SessionFireAllRulesMaxNumberReachedEvent;
import org.drools.core.common.DefaultFactHandle;
import org.drools.core.common.InternalFactHandle;
import org.drools.core.event.rule.impl.BeforeActivationFiredEventImpl;
import org.drools.core.reteoo.InitialFactImpl;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author nheron
 */
public class SimpleRuleHandlerListener extends DefaultAgendaEventListener implements RuleHandlerListenerInterface {
    /**
     * Class logger
     */
    private static Logger logger = LoggerFactory.getLogger(SimpleRuleHandlerListener.class);
    /**
     * The Knowledge sessions sending events
     */
    private final RuleBaseStatefulSession ruleBaseSession;
    /**
     * The rule fired count
     */
    private int nbRuleFired = 0;
    /**
     * the RuleFLowGroup count
     */
    private int nbRuleFlowGroupUsed = 0;
    /**
     * The rule fire limit
     */
    private int maxNumberRuleToExecute;

    private Cloner cloner;

    /**
     * IfMaxNumberRulewasReached
     */
    private boolean maxNumerExecutedRulesReached = false;

    public SimpleRuleHandlerListener(RuleBaseStatefulSession ruleBaseSession, Cloner cloner) {
        this.ruleBaseSession = ruleBaseSession;
        this.maxNumberRuleToExecute = ruleBaseSession.getMaxNumberRuleToExecute();
        this.cloner = cloner;
    }

    @Override
    public boolean isMaxNumerExecutedRulesReached() {
        return maxNumerExecutedRulesReached;
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {

    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        logger.debug(">>afterActivationFired", event);
        nbRuleFired++;
        Match match = event.getMatch();
        //____ Getting the Rule Object Summary from the session
        DroolsRuleObject droolsRuleObject = ruleBaseSession.getDroolsRuleObject(match.getRule());

        //____ Creating the specific "After Rule Fired" History Event
        AfterRuleFiredHistoryEvent newAfterRuleEvent = new AfterRuleFiredHistoryEvent(this.ruleBaseSession.nextEventId(), this.nbRuleFired, droolsRuleObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
        ruleBaseSession.addHistoryElement(newAfterRuleEvent);

        if (nbRuleFired >= maxNumberRuleToExecute) {
            logger.warn(String.format("%d rules have been fired. This is the limit.", maxNumberRuleToExecute));
            logger.warn("The session execution will be stop");
            KieRuntime runtime = event.getKieRuntime();
            this.maxNumerExecutedRulesReached = true;
            //(int eventID, int sessionId, int numberOfRulesExecuted, int maxNumberOfRulesForSession)
            SessionFireAllRulesMaxNumberReachedEvent sessionFireAllRulesMaxNumberReachedEvent = new SessionFireAllRulesMaxNumberReachedEvent(this.ruleBaseSession.nextEventId(), nbRuleFired, maxNumberRuleToExecute, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            ruleBaseSession.addHistoryElement(sessionFireAllRulesMaxNumberReachedEvent);
            runtime.halt();
        }
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        logger.debug(">>afterRuleFlowGroupActivated", ruleFlowGroupActivatedEvent);

    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

    }

    @Override
    public int getNbRuleFired() {
        return nbRuleFired;
    }

}
