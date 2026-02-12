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
import org.chtijbug.drools.entity.DroolsJbpmVariableObject;
import org.chtijbug.drools.entity.DroolsNodeInstanceObject;
import org.chtijbug.drools.entity.DroolsProcessInstanceObject;
import org.chtijbug.drools.entity.DroolsProcessObject;
import org.chtijbug.drools.entity.history.process.*;
import org.kie.api.event.process.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nheron
 */
public class ProcessHandlerListener implements ProcessEventListener {
    /**
     * Class logger
     */
    private static Logger logger = LoggerFactory.getLogger(ProcessHandlerListener.class);
    /**
     * The knowledge session sending event
     */
    private final RuleBaseStatefulSession ruleBaseSession;

    private Cloner cloner;
    public ProcessHandlerListener(RuleBaseStatefulSession ruleBaseSession, Cloner cloner) {
        this.ruleBaseSession = ruleBaseSession;
        this.cloner = cloner;
    }

    @Override
    public void beforeProcessStarted(ProcessStartedEvent event) {
        logger.debug(">>beforeProcessStarted", event);
        try {
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            BeforeProcessStartHistoryEvent beforeProcessStart = new BeforeProcessStartHistoryEvent(this.ruleBaseSession.nextEventId(), droolsProcessInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            ruleBaseSession.addHistoryElement(beforeProcessStart);
        } finally {
            logger.debug("<<beforeProcessStarted");
        }
    }


    @Override
    public void afterProcessStarted(ProcessStartedEvent event) {
        logger.debug(">>afterProcessStarted", event);
        try {

            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            AfterProcessStartHistoryEvent afterProcessStart = new AfterProcessStartHistoryEvent(this.ruleBaseSession.nextEventId(), droolsProcessInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            ruleBaseSession.addHistoryElement(afterProcessStart);
        } finally {
            logger.debug("<<afterProcessStarted");
        }
    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        logger.debug(">>beforeProcessCompleted", event);
        try {
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            BeforeProcessEndHistoryEvent beforeProcessEndHistoryEvent = new BeforeProcessEndHistoryEvent(this.ruleBaseSession.nextEventId(), droolsProcessInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            ruleBaseSession.addHistoryElement(beforeProcessEndHistoryEvent);
        } finally {
            logger.debug("<<beforeProcessCompleted");
        }
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent event) {
        logger.debug(">>afterProcessCompleted", event);
        try {
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            AfterProcessEndHistoryEvent AfterProcessStart = new AfterProcessEndHistoryEvent(this.ruleBaseSession.nextEventId(), droolsProcessInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            ruleBaseSession.addHistoryElement(AfterProcessStart);
        } finally {
            logger.debug("<<afterProcessCompleted");
        }
    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        logger.debug(">>beforeNodeTriggered", event);
        try {
            DroolsNodeInstanceObject droolsNodeInstanceObject = ruleBaseSession.getDroolsNodeInstanceObject(event.getNodeInstance());
            BeforeNodeInstanceTriggeredHistoryEvent beforeNodeInstanceTriggeredHistoryEvent = new BeforeNodeInstanceTriggeredHistoryEvent(this.ruleBaseSession.nextEventId(), droolsNodeInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            beforeNodeInstanceTriggeredHistoryEvent.setProcessInstance(droolsProcessInstanceObject);
            ruleBaseSession.addHistoryElement(beforeNodeInstanceTriggeredHistoryEvent);
        } finally {
            logger.debug("<<beforeNodeTriggered");
        }
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        logger.debug(">>afterNodeTriggered", event);
        try {
            DroolsNodeInstanceObject droolsNodeInstanceObject = ruleBaseSession.getDroolsNodeInstanceObject(event.getNodeInstance());
            AfterNodeInstanceTriggeredHistoryEvent afterNodeInstanceTriggeredHistoryEvent = new AfterNodeInstanceTriggeredHistoryEvent(this.ruleBaseSession.nextEventId(), droolsNodeInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            afterNodeInstanceTriggeredHistoryEvent.setProcessInstance(droolsProcessInstanceObject);
            ruleBaseSession.addHistoryElement(afterNodeInstanceTriggeredHistoryEvent);
        } finally {
            logger.debug("<<afterNodeTriggered");
        }
    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        logger.debug(">>beforeNodeLeft", event);
        try {

            DroolsNodeInstanceObject droolsNodeInstanceObject = ruleBaseSession.getDroolsNodeInstanceObject(event.getNodeInstance());
            BeforeNodeLeftHistoryEvent afterHistoryEvent = new BeforeNodeLeftHistoryEvent(this.ruleBaseSession.nextEventId(), droolsNodeInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            afterHistoryEvent.setProcessInstance(droolsProcessInstanceObject);
            ruleBaseSession.addHistoryElement(afterHistoryEvent);
        } finally {
            logger.debug("<<beforeNodeLeft");
        }
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        logger.debug(">>afterNodeLeft", event);
        try {

            DroolsNodeInstanceObject droolsNodeInstanceObject = ruleBaseSession.getDroolsNodeInstanceObject(event.getNodeInstance());
            AfterNodeLeftHistoryEvent afterHistoryEvent = new AfterNodeLeftHistoryEvent(this.ruleBaseSession.nextEventId(), droolsNodeInstanceObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId());
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            afterHistoryEvent.setProcessInstance(droolsProcessInstanceObject);

            ruleBaseSession.addHistoryElement(afterHistoryEvent);
        } finally {
            logger.debug("<<afterNodeLeft");
        }
    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        logger.debug(">>beforeVariableChanged", event);
        try {

            DroolsJbpmVariableObject oldValue = new DroolsJbpmVariableObject(event.getVariableId(), event.getVariableInstanceId(), event.getOldValue());
            DroolsProcessObject droolsProcessObject = new DroolsProcessObject(String.valueOf(event.getProcessInstance().getId()), event.getProcessInstance().getProcessName(), event.getProcessInstance().getProcess().getPackageName(), event.getProcessInstance().getProcess().getType(), event.getProcessInstance().getProcess().getVersion());
            BeforeVariableChangeChangedHistoryEvent beforeVariableChangeChangedHistoryEvent = new BeforeVariableChangeChangedHistoryEvent(this.ruleBaseSession.nextEventId(), droolsProcessObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId(), oldValue);
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            beforeVariableChangeChangedHistoryEvent.setProcessInstance(droolsProcessInstanceObject);
            ruleBaseSession.addHistoryElement(beforeVariableChangeChangedHistoryEvent);
        } finally {
            logger.debug("<<beforeVariableChanged");
        }
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        logger.debug(">>afterVariableChanged", event);
        try {

            DroolsJbpmVariableObject oldValue = new DroolsJbpmVariableObject(event.getVariableId(), event.getVariableInstanceId(), event.getOldValue());
            DroolsJbpmVariableObject newValue = new DroolsJbpmVariableObject(event.getVariableId(), event.getVariableInstanceId(), event.getNewValue());
            DroolsProcessObject droolsProcessObject = new DroolsProcessObject(String.valueOf(event.getProcessInstance().getId()), event.getProcessInstance().getProcessName(), event.getProcessInstance().getProcess().getPackageName(), event.getProcessInstance().getProcess().getType(), event.getProcessInstance().getProcess().getVersion());
            AfterVariableChangeChangedHistoryEvent afterVariableChangeChangedHistoryEvent = new AfterVariableChangeChangedHistoryEvent(this.ruleBaseSession.nextEventId(), droolsProcessObject, this.ruleBaseSession.getRuleBaseID(), this.ruleBaseSession.getSessionId(), oldValue, newValue);
            DroolsProcessInstanceObject droolsProcessInstanceObject = ruleBaseSession.getDroolsProcessInstanceObject(event.getProcessInstance());
            afterVariableChangeChangedHistoryEvent.setProcessInstance(droolsProcessInstanceObject);
            ruleBaseSession.addHistoryElement(afterVariableChangeChangedHistoryEvent);
        } finally {
            logger.debug("<<afterVariableChanged");
        }
    }


}
