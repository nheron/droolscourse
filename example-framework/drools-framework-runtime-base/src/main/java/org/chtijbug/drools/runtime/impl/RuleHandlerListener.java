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

//import org.drools.core.common.InternalFactHandle;
//import org.drools.core.reteoo.InitialFactImpl;


/**
 * @author nheron
 */
public class RuleHandlerListener extends DefaultAgendaEventListener implements  RuleHandlerListenerInterface{
    /**
     * Class logger
     */
    private static Logger logger = LoggerFactory.getLogger(RuleHandlerListener.class);
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

    public RuleHandlerListener(RuleBaseStatefulSession ruleBaseSession, Cloner cloner) {
        this.ruleBaseSession = ruleBaseSession;
        this.maxNumberRuleToExecute = ruleBaseSession.getMaxNumberRuleToExecute();
        this.cloner=cloner;
    }

    public boolean isMaxNumerExecutedRulesReached() {
        return maxNumerExecutedRulesReached;
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        logger.debug(">>beforeActivationFired", event);
        try {
            if (event instanceof BeforeActivationFiredEventImpl) {
                Match match = event.getMatch();
                List<? extends FactHandle> listFact = match.getFactHandles();
                //____ Getting the Rule object summary from the session
                DroolsRuleObject droolsRuleObject = ruleBaseSession.getDroolsRuleObject(match.getRule());
                //____ Creating the specific History event for history managment
                BeforeRuleFiredHistoryEvent newBeforeRuleEvent = new BeforeRuleFiredHistoryEvent(this.ruleBaseSession.nextEventId(), this.nbRuleFired + 1, droolsRuleObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
                //____ Adding all objects info contained in the Activation object into the history Events
                for (FactHandle h : listFact) {
                    if (h instanceof DefaultFactHandle) {
                        InternalFactHandle defaultFactHandle = (InternalFactHandle) h;
                        //System.out.println(defaultFactHandle.toString());
                        Object object = cloner.deepClone(defaultFactHandle.getObject());
                        DroolsFactObject sourceFactObject = ruleBaseSession.getLastFactObjectVersionFromFactHandle(h);
                        if (sourceFactObject==null){
                            try {
                                if (object instanceof List){
                                    List lst = (List)object;
                                    List clonedList=new ArrayList();
                                    for (Object l : lst){
                                        clonedList.add(cloner.deepClone(l));
                                    }
                                    sourceFactObject = new DroolsFactObject(clonedList.toArray(), 1);
                                }else if (object instanceof InitialFactImpl){
                                    //InitialFactImpl initialFact = (InitialFactImpl)object;
                                    // Do Nothing for the time
                                }
                                else {
                                    sourceFactObject = new DroolsFactObject(object, 1);
                                }
                            } catch (IOException e) {
                                logger.info("Object not put as JSON possible"+object.toString());
                            }
                        }
                        newBeforeRuleEvent.getWhenObjects().add(sourceFactObject);

                    } else {
                        DroolsFactObject sourceFactObject = ruleBaseSession.getLastFactObjectVersionFromFactHandle(h);
                        newBeforeRuleEvent.getWhenObjects().add(sourceFactObject);

                    }
                }
                //_____ Add Event into the History Container
                ruleBaseSession.addHistoryElement(newBeforeRuleEvent);
            }
        } finally {
            logger.debug("<<beforeActivationFired");
        }
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        logger.debug(">>afterActivationFired", event);
        try {
            //____ Increment the global rule fired count
            nbRuleFired++;
            Match match = event.getMatch();
            //____ Getting the Rule Object Summary from the session
            DroolsRuleObject droolsRuleObject = ruleBaseSession.getDroolsRuleObject(match.getRule());

            //____ Creating the specific "After Rule Fired" History Event
            AfterRuleFiredHistoryEvent newAfterRuleEvent = new AfterRuleFiredHistoryEvent(this.ruleBaseSession.nextEventId(), this.nbRuleFired, droolsRuleObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            ruleBaseSession.addHistoryElement(newAfterRuleEvent);
            //____ If the max number rule able to be executed threshold is raised, stop the session execution
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
            logger.debug("nbre RDG Fired ==> ", nbRuleFired);
        } finally {
            logger.debug("<<afterActivationFired");
        }
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        logger.debug(">>afterRuleFlowGroupActivated", ruleFlowGroupActivatedEvent);
        try {
            String ruleFlowGroupName = null;
            //____ Filling the event with the rule flow group name activated
            if (ruleFlowGroupActivatedEvent.getRuleFlowGroup() != null && ruleFlowGroupActivatedEvent.getRuleFlowGroup().getName() != null) {
                ruleFlowGroupName = ruleFlowGroupActivatedEvent.getRuleFlowGroup().getName();
            }
            DroolsRuleFlowGroupObject droolsRuleFlowGroupObject = new DroolsRuleFlowGroupObject(this.nbRuleFlowGroupUsed + 1, ruleFlowGroupName);
            //____ Creating history event
            AfterRuleFlowActivatedHistoryEvent afterRuleFlowActivatedHistoryEvent = new AfterRuleFlowActivatedHistoryEvent(this.ruleBaseSession.nextEventId(), droolsRuleFlowGroupObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            //____ Adding into the History container
            ruleBaseSession.addHistoryElement(afterRuleFlowActivatedHistoryEvent);
        } finally {
            logger.debug("<<afterRuleFlowGroupActivated");
        }
    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        logger.debug(">>afterRuleFlowGroupDeactivated", ruleFlowGroupDeactivatedEvent);
        try {
            String ruleFlowGroupName = null;
            if (ruleFlowGroupDeactivatedEvent.getRuleFlowGroup() != null && ruleFlowGroupDeactivatedEvent.getRuleFlowGroup().getName() != null) {
                ruleFlowGroupName = ruleFlowGroupDeactivatedEvent.getRuleFlowGroup().getName();
            }
            this.nbRuleFlowGroupUsed++;
            DroolsRuleFlowGroupObject droolsRuleFlowGroupObject = new DroolsRuleFlowGroupObject(this.nbRuleFlowGroupUsed, ruleFlowGroupName);
            //____ Creating history event
            AfterRuleFlowDeactivatedHistoryEvent afterRuleFlowGroupDeactivated = new AfterRuleFlowDeactivatedHistoryEvent(this.ruleBaseSession.nextEventId(), droolsRuleFlowGroupObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            //_____ Adding the event to the History container
            ruleBaseSession.addHistoryElement(afterRuleFlowGroupDeactivated);
        } finally {
            logger.debug("<<afterRuleFlowGroupDeactivated");
        }
    }

    public int getNbRuleFired() {
        return nbRuleFired;
    }

}
