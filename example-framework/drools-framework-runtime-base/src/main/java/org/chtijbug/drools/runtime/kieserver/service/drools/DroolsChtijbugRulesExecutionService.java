/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chtijbug.drools.runtime.kieserver.service.drools;

import org.chtijbug.drools.ChtijbugObjectRequest;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.kieserver.extension.KieServerAddOnElement;
import org.chtijbug.drools.kieserver.extension.KieServerListenerDefinition;
import org.chtijbug.drools.runtime.DroolsChtijbugException;
import org.chtijbug.drools.runtime.RuleBasePackage;
import org.chtijbug.drools.runtime.RuleBaseSession;
import org.chtijbug.drools.runtime.impl.RuleBaseSingleton;
import org.chtijbug.drools.runtimeevent.MessageHandlerResolver;
import org.kie.api.KieBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Direct rules execution service that allow use of typed objects instead of string only
 */
public class DroolsChtijbugRulesExecutionService {
    private static final Logger logger = LoggerFactory.getLogger(DroolsChtijbugRulesExecutionService.class);

    private RuleBaseSingleton ruleBaseSingleton;
    private MessageHandlerResolver messageHandlerResolver;
    private int MaxNumberRulesToExecute = 20000;
    private KieServerAddOnElement kieServerAddOnElement = null;

    public static DroolsChtijbugRulesExecutionService createService(KieBase kiebase,int MaxNumberRulesToExecute) {
        RuleBaseSingleton ruleBasePackage = new RuleBaseSingleton(kiebase, MaxNumberRulesToExecute, new ChtijbugHistoryListener());
        return new DroolsChtijbugRulesExecutionService(ruleBasePackage);
    }

    public DroolsChtijbugRulesExecutionService(RuleBaseSingleton ruleBaseSingleton) {
        this.ruleBaseSingleton = ruleBaseSingleton;
        this.messageHandlerResolver = new MessageHandlerResolver();
        this.MaxNumberRulesToExecute = ruleBaseSingleton.getMaxNumberRuleToExecute();
    }

    public RuleBaseSingleton getRuleBaseSingleton() {
        return ruleBaseSingleton;
    }

    public ChtijbugObjectRequest FireAllRulesAndStartProcess(ChtijbugObjectRequest chtijbugObjectRequest, String processID, int sessionMaxNumberRulesToExecute) {
        return this.FireAllRulesAndStartProcess( chtijbugObjectRequest, processID, null, sessionMaxNumberRulesToExecute);
    }

    public ChtijbugObjectRequest FireAllRulesAndStartProcess( ChtijbugObjectRequest chtijbugObjectRequest, String processID) {
        return this.FireAllRulesAndStartProcess(chtijbugObjectRequest, processID, null);
    }

    public KieServerAddOnElement getKieServerAddOnElement() {
        return kieServerAddOnElement;
    }

    public ChtijbugObjectRequest FireAllRulesAndStartProcess(ChtijbugObjectRequest chtijbugObjectRequest, String processID, String sessionName, int sessionMaxNumberRulesToExecute) {

        Object result = null;

        try {
            if (messageHandlerResolver==null){
                logger.error("DroolsChtijbugRulesExecutionService.FireAllRulesAndStartProcess.messageHandlerResolver is null");
            }
            if (chtijbugObjectRequest==null){
                logger.error("DroolsChtijbugRulesExecutionService.FireAllRulesAndStartProcess.chtijbugObjectRequest is null");
            }else if( chtijbugObjectRequest.getObjectRequest()==null){
                logger.error("DroolsChtijbugRulesExecutionService.FireAllRulesAndStartProcess.chtijbugObjectRequest.getObjectRequest is null");
            }
            if (chtijbugObjectRequest.getObjectRequest().getClass()!= null
                && chtijbugObjectRequest.getObjectRequest().getClass().getClassLoader() != null) {
                messageHandlerResolver.setClassLoader(chtijbugObjectRequest.getObjectRequest().getClass().getClassLoader());
            }
             if (ruleBaseSingleton != null) {
                Date startTime = new Date();
                ChtijbugHistoryListener chtijbugHistoryListener=null;
                if (!chtijbugObjectRequest.isDisableLogging()) {
                    chtijbugHistoryListener = new ChtijbugHistoryListener();
                }
                RuleBaseSession session = ruleBaseSingleton.createRuleBaseSession(sessionMaxNumberRulesToExecute, chtijbugHistoryListener);
                result = session.fireAllRulesAndStartProcess(chtijbugObjectRequest.getObjectRequest(), processID);
                session.dispose();
                Date stopTime = new Date();
                List<HistoryEvent> events=new ArrayList<>();
                if (chtijbugHistoryListener!= null){
                    events = chtijbugHistoryListener.getHistoryEventLinkedList();
                }
                 List<HistoryEvent> toto = session.getHistoryContainer().getListHistoryEvent();
                SessionContext sessionContext = this.messageHandlerResolver.getSessionFromHistoryEvent(events);
              //  sessionContext.setGroupID(kci.getResource().getReleaseId().getGroupId());
               // sessionContext.setArtefactID(kci.getResource().getReleaseId().getArtifactId());
              //  sessionContext.setVersion(kci.getResource().getReleaseId().getVersion());
              //  sessionContext.setContainerId(kci.getContainerId());
              //  String serverName = System.getProperty("org.kie.server.id");
              //  if (serverName!= null){
              //      sessionContext.setServerName(serverName);
              //  }
                sessionContext.setStartTime(startTime);
                sessionContext.setStopTime(stopTime);
                chtijbugObjectRequest.setSessionLogging(sessionContext);
                chtijbugObjectRequest.setObjectRequest(result);
                logger.debug("Returning OK response with content '{}'", chtijbugObjectRequest.getObjectRequest());

            }
            return chtijbugObjectRequest;

        } catch (DroolsChtijbugException e) {
           logger.error("FireAllRulesAndStartProcess",e);
        }

        throw new IllegalStateException("Unable to execute command " + chtijbugObjectRequest.getObjectRequest());

    }

    public ChtijbugObjectRequest FireAllRulesAndStartProcess( ChtijbugObjectRequest chtijbugObjectRequest, String processID, String sessionName) {

        return this.FireAllRulesAndStartProcess( chtijbugObjectRequest, processID, sessionName, this.MaxNumberRulesToExecute);
    }
}
