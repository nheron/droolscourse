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
package org.chtijbug.drools.entity.history;

import org.chtijbug.drools.runtime.DroolsChtijbugException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author nheron
 */
public class HistoryEvent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6640538290066213804L;
    protected Date dateEvent;
    protected TypeEvent typeEvent;
    private Long eventID;
    private Long ruleBaseID;
    private Long sessionId;
    private DroolsChtijbugException droolsChtijbugException;
    private ArrayList<KnowledgeResource> knowledgeResources = new ArrayList<KnowledgeResource>();
    private ClassLoader businessClassLoader;
    /**
     * Mandatory for GWT Serialization
     */
    public HistoryEvent() {
    }

    public void setBusinessClassLoader(ClassLoader businessClassLoader) {
        this.businessClassLoader = businessClassLoader;
    }

    public HistoryEvent(Long eventID, Date dateEvent, TypeEvent typeEvent) {

        this.eventID = eventID;
        this.dateEvent = dateEvent;
        this.typeEvent = typeEvent;
    }

    public ArrayList<KnowledgeResource> getKnowledgeResources() {
        return knowledgeResources;
    }

    public void setKnowledgeResources(ArrayList<KnowledgeResource> knowledgeResources) {
        this.knowledgeResources = knowledgeResources;
    }

    public DroolsChtijbugException getDroolsChtijbugException() {
        return droolsChtijbugException;
    }

    public void setDroolsChtijbugException(DroolsChtijbugException droolsChtijbugException) {
        this.droolsChtijbugException = droolsChtijbugException;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getRuleBaseID() {
        return ruleBaseID;
    }

    public void setRuleBaseID(Long ruleBaseID) {
        this.ruleBaseID = ruleBaseID;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }


    public enum TypeEvent {
        Fact, Rule, BPMN, RuleFlowGroup, KnowledgeBaseSingleton, Session
    }
}
