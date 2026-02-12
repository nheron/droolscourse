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
import com.rits.cloning.ObjenesisInstantiationStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.chtijbug.drools.common.reflection.ReflectionUtils;
import org.chtijbug.drools.entity.*;
import org.chtijbug.drools.entity.history.EventCounter;
import org.chtijbug.drools.entity.history.HistoryContainer;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.entity.history.fact.InsertedByReflectionFactEndHistoryEvent;
import org.chtijbug.drools.entity.history.fact.InsertedByReflectionFactStartHistoryEvent;
import org.chtijbug.drools.entity.history.session.*;
import org.chtijbug.drools.runtime.DroolsChtijbugException;
import org.chtijbug.drools.runtime.DroolsFactObjectFactory;
import org.chtijbug.drools.runtime.RuleBaseSession;
import org.chtijbug.drools.runtime.listener.HistoryListener;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.jbpm.workflow.core.node.RuleSetNode;
import org.jbpm.workflow.instance.node.*;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

//import org.drools.core.definitions.rule.impl.RuleImpl;

/**
 * @author nheron
 */
public class RuleBaseStatefulSession implements RuleBaseSession {
    /**
     * Class Logger
     */
    private static Logger logger = LoggerFactory.getLogger(RuleBaseStatefulSession.class);
    /**
     * All objects inserted into the session as fact
     */
    private final Map<FactHandle, Object> listObject;
    private final Map<Object, FactHandle> listFact;
    private final Map<Object, List<DroolsFactObject>> listFactObjects;
    private final HistoryContainer historyContainer;
    /**
     * All the
     */
    private final Map<String, DroolsRuleObject> listRules;
    private final Map<String, DroolsProcessObject> processList;
    private final Map<String, DroolsProcessInstanceObject> processInstanceList;
    /**
     * The wrapped Drools KnowledgeSession
     */
    private KieSession knowledgeSession = null;
    // Listeners can be dispose ...
    private FactHandlerListener factListener;
    private RuleHandlerListenerInterface ruleHandlerListener;
    private ProcessHandlerListener processHandlerListener;
    private int maxNumberRuleToExecute;

    private XStream xstream = new XStream(new JettisonMappedXmlDriver());
    private Long ruleBaseID;
    private Long sessionId;

    private HistoryListener historyListener;
    private EventCounter eventCounter = EventCounter.newCounter();
    private Cloner cloner = new Cloner(new ObjenesisInstantiationStrategy());

    public RuleBaseStatefulSession(Long ruleBaseID, Long sessionId, KieSession knowledgeSession, int maxNumberRuleToExecute, HistoryListener historyListener) throws DroolsChtijbugException {
        this.ruleBaseID = ruleBaseID;
        this.sessionId = sessionId;
        this.knowledgeSession = knowledgeSession;
        this.maxNumberRuleToExecute = maxNumberRuleToExecute;

        this.historyContainer = new HistoryContainer(sessionId, historyListener);
        this.listFactObjects = new HashMap<>();
        this.listFact = new HashMap<>();
        this.listObject = new HashMap<>();
        this.listRules = new HashMap<>();
        this.processList = new HashMap<>();
        this.processInstanceList = new HashMap<>();

        this.historyListener = historyListener;
        if (this.historyListener != null && this.historyListener.withDetails()) {
            this.factListener = new FactHandlerListener(this,cloner);
            this.ruleHandlerListener = new RuleHandlerListener(this,cloner);
            this.processHandlerListener = new ProcessHandlerListener(this,cloner);
            knowledgeSession.addEventListener(factListener);
            knowledgeSession.addEventListener(ruleHandlerListener);
            knowledgeSession.addEventListener(processHandlerListener);
            SessionCreatedEvent sessionCreatedEvent = new SessionCreatedEvent(eventCounter.next(), this.ruleBaseID, this.sessionId);
            this.addHistoryElement(sessionCreatedEvent);
        }else{
            this.ruleHandlerListener = new SimpleRuleHandlerListener(this,cloner);
            knowledgeSession.addEventListener(ruleHandlerListener);
        }

    }

    public int getMaxNumberRuleToExecute() {
        return maxNumberRuleToExecute;
    }

    public DroolsProcessInstanceObject getDroolsProcessInstanceObject(ProcessInstance processInstance) {

        DroolsProcessInstanceObject droolsProcessInstanceObject = processInstanceList.get(Long.toString(processInstance.getId()));
        if (droolsProcessInstanceObject == null) {
            DroolsProcessObject droolsProcessObject = processList.get(processInstance.getProcess().getId());

            if (droolsProcessObject == null) {
                droolsProcessObject = DroolsProcessObject.createDroolsProcessObject(processInstance.getProcess().getId(),
                        processInstance.getProcess().getName(),
                        processInstance.getProcess().getPackageName(), processInstance.getProcess().getType(), processInstance.getProcess().getVersion());
                processList.put(processInstance.getProcess().getId(), droolsProcessObject);
            }

            droolsProcessInstanceObject = DroolsProcessInstanceObject.createDroolsProcessInstanceObject(String.valueOf(processInstance.getId()), droolsProcessObject);
            processInstanceList.put(droolsProcessInstanceObject.getId(), droolsProcessInstanceObject);
        }
        return droolsProcessInstanceObject;
    }

    public DroolsNodeInstanceObject getDroolsNodeInstanceObject(NodeInstance nodeInstance) {
        DroolsNodeType nodeType = DroolsNodeType.Other;
        String ruleFlowGroupName = null;
        if (nodeInstance instanceof StartNodeInstance) {
            nodeType = DroolsNodeType.StartNode;
        } else if (nodeInstance instanceof RuleSetNodeInstance) {
            nodeType = DroolsNodeType.RuleNode;
            RuleSetNode ruleSetNode = this.getRuleSetNode(nodeInstance);
            if (ruleSetNode != null) {
                ruleFlowGroupName = ruleSetNode.getRuleFlowGroup();
            }
        } else if (nodeInstance instanceof SplitInstance) {
            nodeType = DroolsNodeType.SplitNode;
        } else if (nodeInstance instanceof JoinInstance) {
            nodeType = DroolsNodeType.JoinNode;
        } else if (nodeInstance instanceof EndNodeInstance) {
            nodeType = DroolsNodeType.EndNode;
        }
        DroolsProcessInstanceObject droolsProcessInstanceObject = processInstanceList.get(Long.toString(nodeInstance.getProcessInstance().getId()));
        if (droolsProcessInstanceObject == null) {
            droolsProcessInstanceObject = this.getDroolsProcessInstanceObject(nodeInstance.getProcessInstance());
        }

        DroolsNodeInstanceObject droolsNodeInstanceObject = droolsProcessInstanceObject.getDroolsNodeInstanceObjet(String.valueOf(nodeInstance.getId()));
        if (droolsNodeInstanceObject == null) {
            DroolsNodeObject droolsNodeObject = DroolsNodeObject.createDroolsNodeObject(String.valueOf(nodeInstance.getNode().getId()), nodeType);
            droolsProcessInstanceObject.getProcess().addDroolsNodeObject(droolsNodeObject);
            droolsNodeObject.setRuleflowGroupName(ruleFlowGroupName);
            droolsNodeInstanceObject = DroolsNodeInstanceObject.createDroolsNodeInstanceObject(String.valueOf(nodeInstance.getId()), droolsNodeObject);
            droolsProcessInstanceObject.addDroolsNodeInstanceObject(droolsNodeInstanceObject);
        }


        return droolsNodeInstanceObject;
    }

    public DroolsRuleObject getDroolsRuleObject(Rule rule) {
        DroolsRuleObject droolsRuleObject = listRules.get(rule.toString());
       RuleImpl ruleInstance = (RuleImpl) rule;
        if (droolsRuleObject == null) {
            droolsRuleObject = DroolsRuleObject.createDroolRuleObject(rule.getName(), rule.getPackageName());
            droolsRuleObject.setRuleFlowGroup(ruleInstance.getRuleFlowGroup());
            addDroolsRuleObject(droolsRuleObject);
        }

        return droolsRuleObject;
    }

    public void addDroolsRuleObject(DroolsRuleObject droolsRuleObject) {
        listRules.put(droolsRuleObject.getRulePackageName() + droolsRuleObject.getRuleName(), droolsRuleObject);
    }

    public DroolsFactObject getLastFactObjectVersion(Object searchO) {
        int lastVersion = listFactObjects.get(searchO).size() - 1;
        DroolsFactObject result = getFactObjectVersion(searchO, lastVersion);
        try {
            result.updateRealObjectFromJSON();
        } catch (ClassNotFoundException e) {
            logger.error("getLastFactObjectVersion",e);
        } catch (IOException e) {
            logger.error("getLastFactObjectVersion",e);
        }
        return result;
    }

    public DroolsFactObject getFactObjectVersion(Object search0, int version) {
        return listFactObjects.get(search0).get(version);
    }

    public DroolsFactObject getLastFactObjectVersionFromFactHandle(FactHandle factToFind) {

        Object searchObject = this.listObject.get(factToFind);
        if (searchObject == null) {
            return null;
        }

        List<DroolsFactObject> facto = listFactObjects.get(searchObject);

        if (facto == null) {
            logger.error("List of FactObject can not be null for FactHandle {}", factToFind);
            return null;
        }

        int lastVersion = facto.size() - 1;
        return listFactObjects.get(searchObject).get(lastVersion);
    }

    @Override
    public HistoryContainer getHistoryContainer() {
        return historyContainer;
    }

    @Override
    public String getHistoryContainerXML() {
        String result = null;
        if (historyContainer != null) {
            xstream.setMode(XStream.NO_REFERENCES);
            result = xstream.toXML(historyContainer);
        }
        return result;
    }

    @Override
    public Collection<DroolsFactObject> listLastVersionObjects() {
        Collection<DroolsFactObject> list = new ArrayList<>();
        for (Object o : this.listFact.keySet()) {
            FactHandle factHandle = this.listFact.get(o);
            list.add(this.getLastFactObjectVersionFromFactHandle(factHandle));

        }
        return list;
    }

    @Override
    public String listLastVersionObjectsXML() {
        String result = null;
        Collection<DroolsFactObject> list = this.listLastVersionObjects();
        if (list != null) {
            xstream.setMode(XStream.NO_REFERENCES);
            result = xstream.toXML(list);
        }
        return result;
    }

    public void setData(FactHandle f, Object o, DroolsFactObject fObject) {

        Object objectSearch = listObject.containsKey(f);
        listFact.remove(objectSearch);

        listObject.put(f, o);
        listFact.put(o, f);

        if (!listFactObjects.containsKey(o)) {
            List<DroolsFactObject> newList = new LinkedList<>();
            newList.add(fObject);
            listFactObjects.put(o, newList);
        } else {
            listFactObjects.get(o).add(fObject);
        }
    }

    public void unsetData(FactHandle f, Object o) {
        listObject.remove(f);
        listFact.remove(o);
    }

    @Override
    public void dispose() {
        if (knowledgeSession!= null) {
            knowledgeSession.removeEventListener(factListener);
            knowledgeSession.removeEventListener(ruleHandlerListener);
            knowledgeSession.removeEventListener(processHandlerListener);
            for (FactHandle f : listObject.keySet()) {
                knowledgeSession.delete(f);
            }

            factListener = null;
            ruleHandlerListener = null;
            processHandlerListener = null;
            knowledgeSession.dispose();
            knowledgeSession = null;
            if (this.historyListener != null) {

                try {
                    SessionDisposedEvent sessionDisposedEvent = new SessionDisposedEvent(eventCounter.next(), this.ruleBaseID, this.sessionId);
                    this.addHistoryElement(sessionDisposedEvent);
                } catch (Exception e) {
                    logger.error("Exception in calling historyEvent", e);

                }

            }
        }
    }

    @Override
    public void insertObject(Object newObject) {
        FactHandle newFactHandle = this.knowledgeSession.insert(newObject);
        listFact.put(newObject, newFactHandle);
    }

    @Override
    public void insertByReflection(Object newObject) throws DroolsChtijbugException {
        // Avoid inserting java.* classes

        if (newObject.getClass().getPackage().getName().startsWith("java.")) {
            return;
        }
        if (this.historyListener != null) {
            DroolsFactObject topDroolsObject = DroolsFactObjectFactory.createFactObject(newObject);
            InsertedByReflectionFactStartHistoryEvent insertedByReflectionFactStartHistoryEvent = new InsertedByReflectionFactStartHistoryEvent(eventCounter.next(), topDroolsObject, this.ruleBaseID, this.sessionId);
            this.addHistoryElement(insertedByReflectionFactStartHistoryEvent);
        }
        //____ First insert the root object
        insertObject(newObject);
        //____ Then foreach getters insert item by reflection
        for (Method method : newObject.getClass().getMethods()) {
            //____ only manage getters
            if (!ReflectionUtils.IsGetter(method)) {
                continue;
            }
            Object getterValue;
            try {
                getterValue = method.invoke(newObject, (Object[]) null);
            } catch (Exception e) {
                throw new DroolsChtijbugException(DroolsChtijbugException.insertByReflection, "getterValue = method.invoke(newObject, (Object[]) null);", e);
            }
            if (getterValue == null)
                continue;
            //____ If returned value is not a collection, insert it in the ksession
            if (!(getterValue instanceof Iterable)) {
                this.insertByReflection(getterValue);
            } else {
                Iterable<?> iterable = (Iterable) getterValue;
                for (Object item : iterable) {
                    this.insertByReflection(item);
                }
            }
        }
        if (this.historyListener != null) {
            InsertedByReflectionFactEndHistoryEvent insertedByReflectionFactEndHistoryEvent = new InsertedByReflectionFactEndHistoryEvent(eventCounter.next(), this.ruleBaseID, this.sessionId);
            this.addHistoryElement(insertedByReflectionFactEndHistoryEvent);
        }
    }

    @Override
    public void setGlobal(String identifier, Object value) {
        this.knowledgeSession.setGlobal(identifier, value);
    }

    @Override
    public void updateObject(Object updatedObject) {
        FactHandle factToUpdate = listFact.get(updatedObject);
        this.knowledgeSession.update(factToUpdate, updatedObject);
    }

    @Override
    public void retractObject(Object oldObject) {
        FactHandle factToDelete = listFact.get(oldObject);
        this.knowledgeSession.delete(factToDelete);
    }

    @Override
    public Object fireAllRulesAndStartProcess(Object inputObject, String processName) throws DroolsChtijbugException {
        DroolsFactObject inputDroolsObject = null;
        DroolsFactObject outputDroolsObject = null;
        Object inputObjectClone;
        if (inputObject != null) {
            this.insertByReflection(inputObject);
            inputObject.getClass().getClassLoader();
            Thread currentThread = Thread.currentThread();
            ClassLoader old = currentThread.getContextClassLoader();
            currentThread.setContextClassLoader(inputObject.getClass().getClassLoader());

            inputObjectClone=cloner.deepClone(inputObject);
            currentThread.setContextClassLoader(old);
            inputDroolsObject = DroolsFactObjectFactory.createFactObject(inputObjectClone);
        }
        if (processName != null && processName.length() > 0) {
            this.startProcess(processName);
        }
        this.fireAllRules();
        if (inputDroolsObject != null) {
            outputDroolsObject = DroolsFactObjectFactory.createFactObject(inputObject);
        }

        if (this.historyListener != null) {
            SessionFireAllRulesAndStartProcess sessionFireAllRulesAndStartProcess = new SessionFireAllRulesAndStartProcess(eventCounter.next(), this.ruleBaseID, this.sessionId, inputDroolsObject, outputDroolsObject);
            this.addHistoryElement(sessionFireAllRulesAndStartProcess);
        }
        return inputObject;
    }

    @Override
    public void fireAllRules() throws DroolsChtijbugException {
        if (this.historyListener != null) {
            SessionFireAllRulesBeginEvent sessionFireAllRulesBeginEvent = new SessionFireAllRulesBeginEvent(eventCounter.next(), this.ruleBaseID, this.sessionId);
            this.addHistoryElement(sessionFireAllRulesBeginEvent);
        }
        long startTime = System.currentTimeMillis();
        long beforeNumberRules = ruleHandlerListener.getNbRuleFired();
        try {
            this.knowledgeSession.fireAllRules();
        } catch (Exception e) {
            throw new DroolsChtijbugException(DroolsChtijbugException.fireAllRules, "", e);
        }

        long stopTime = System.currentTimeMillis();
        long afterNumberRules = ruleHandlerListener.getNbRuleFired();
        if (ruleHandlerListener.isMaxNumerExecutedRulesReached()) {
            if (this.historyListener != null) {
                SessionFireAllRulesMaxNumberReachedEvent sessionFireAllRulesMaxNumberReachedEvent = new SessionFireAllRulesMaxNumberReachedEvent(eventCounter.next(), ruleHandlerListener.getNbRuleFired(), maxNumberRuleToExecute, this.ruleBaseID, this.sessionId);
                this.addHistoryElement(sessionFireAllRulesMaxNumberReachedEvent);
            }
            throw new DroolsChtijbugException(DroolsChtijbugException.MaxNumberRuleExecutionReached, "nbRulesExecuted" + afterNumberRules + " and MaxNumberRules for the session is set to " + maxNumberRuleToExecute, null);
        }
        if (this.historyListener != null) {
            SessionFireAllRulesEndEvent sessionFireAllRulesEndEvent = new SessionFireAllRulesEndEvent(eventCounter.next(), this.ruleBaseID, this.sessionId, stopTime - startTime, afterNumberRules - beforeNumberRules);
            this.addHistoryElement(sessionFireAllRulesEndEvent);
        }
    }

    @Override
    public void startProcess(String processName) {
        if (this.historyListener != null) {
            try {
                SessionStartProcessBeginEvent sessionStartProcessBeginEvent = new SessionStartProcessBeginEvent(eventCounter.next(), processName, this.ruleBaseID, this.sessionId);
                this.addHistoryElement(sessionStartProcessBeginEvent);
            } catch (Exception e) {
                logger.error("Exception in calling historyEvent", e);

            }

        }
        ProcessInstance processInstance = this.knowledgeSession.startProcess(processName);
        if (this.historyListener != null) {
            try {
                SessionStartProcessEndEvent sessionStartProcessEndEvent = new SessionStartProcessEndEvent(eventCounter.next(), processName, this.ruleBaseID, this.sessionId, processInstance.getProcessId());
                this.addHistoryElement(sessionStartProcessEndEvent);
            } catch (Exception e) {
                logger.error("Exception in calling historyEvent", e);

            }
        }
    }

    @Override
    public Collection<DroolsRuleObject> listRules() {
        return listRules.values();
    }

    @Override
    public int getNumberRulesExecuted() {
        int result = 0;
        if (this.ruleHandlerListener != null) {
            result = this.ruleHandlerListener.getNbRuleFired();
        }
        return result;
    }

    public void addHistoryElement(HistoryEvent newHistoryElement) {
        this.historyContainer.addHistoryElement(this.ruleBaseID, this.sessionId, newHistoryElement);
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getRuleBaseID() {
        return ruleBaseID;
    }

    @Override
    public KieSession getKnowledgeSession() {
        return knowledgeSession;
    }

    @Override
    public Collection<? extends java.lang.Object> getObjects(ObjectFilter objectFilter) {
        return this.knowledgeSession.getObjects(objectFilter);
    }

    @Override
    public void completeWorkItem(long processId, Map<String, Object> vars) {
        if (this.knowledgeSession != null && this.knowledgeSession.getWorkItemManager() != null) {
            this.knowledgeSession.getWorkItemManager().completeWorkItem(processId, vars);
        }
    }

    @Override
    public void abortWorkItem(long processId) {
        if (this.knowledgeSession != null && this.knowledgeSession.getWorkItemManager() != null) {
            this.knowledgeSession.getWorkItemManager().abortWorkItem(processId);
        }
    }

    @Override
    public void registerWorkItemHandler(String workItemName, WorkItemHandler workItemHandler) {
        if (this.knowledgeSession != null && this.knowledgeSession.getWorkItemManager() != null) {
            this.knowledgeSession.getWorkItemManager().registerWorkItemHandler(workItemName, workItemHandler);
        }
    }

    @Override
    public ProcessInstance startProcess(String processName, Map<String, Object> vars) {
        if (this.knowledgeSession != null && this.knowledgeSession.getWorkItemManager() != null) {
            return this.knowledgeSession.startProcess(processName, vars);
        }
        return null;
    }

    private RuleSetNode getRuleSetNode(NodeInstance nodeInstance) {
        RuleSetNode ruleSetNode = null;
        if (nodeInstance instanceof RuleSetNodeInstance) {
            if (nodeInstance.getNode() instanceof RuleSetNode) {
                ruleSetNode = (RuleSetNode) nodeInstance.getNode();
            }
        }
        return ruleSetNode;
    }

    public Long nextEventId() {
        return this.eventCounter.next();
    }

    @Override
    public Object fireAllRulesAndStartProcessWithParam(Object inputObject, String processName) throws DroolsChtijbugException {

        DroolsFactObject inputDroolsObject = null;
        DroolsFactObject outputDroolsObject = null;
        Object inputObjectClone;
        if (inputObject != null) {
            this.insertByReflection(inputObject);
            inputObjectClone=cloner.deepClone(inputObject);
            inputDroolsObject = DroolsFactObjectFactory.createFactObject(inputObjectClone);
        }
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("inputObject", inputObject);
        if (processName != null && processName.length() > 0) {
            this.startProcess(processName, maps);
        }
        this.fireAllRules();
        if (inputDroolsObject != null) {
            outputDroolsObject = DroolsFactObjectFactory.createFactObject(inputObject);
        }
        if (this.historyListener != null) {
            SessionFireAllRulesAndStartProcess sessionFireAllRulesAndStartProcess = new SessionFireAllRulesAndStartProcess(eventCounter.next(), this.ruleBaseID, this.sessionId, inputDroolsObject, outputDroolsObject);
            this.addHistoryElement(sessionFireAllRulesAndStartProcess);
        }
        return inputObject;

    }
}
