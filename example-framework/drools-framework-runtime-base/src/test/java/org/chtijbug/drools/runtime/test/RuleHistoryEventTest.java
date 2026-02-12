package org.chtijbug.drools.runtime.test;

import org.chtijbug.drools.entity.DroolsRuleObject;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.entity.history.rule.AfterRuleFiredHistoryEvent;
import org.chtijbug.drools.entity.history.rule.AfterRuleFlowActivatedHistoryEvent;
import org.chtijbug.drools.entity.history.rule.AfterRuleFlowDeactivatedHistoryEvent;
import org.chtijbug.drools.entity.history.rule.BeforeRuleFiredHistoryEvent;
import org.chtijbug.drools.runtime.DroolsChtijbugException;
import org.chtijbug.drools.runtime.RuleBasePackage;
import org.chtijbug.drools.runtime.RuleBaseSession;
import org.chtijbug.drools.runtime.impl.RuleBaseSingleton;
import org.chtijbug.drools.runtime.listener.HistoryListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/02/14
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class RuleHistoryEventTest {


    @Before
    public void justBefore() {
        //   fibonacciFile =   FileKnowledgeResource.createDRLClassPathResource("fibonacci.drl");
        //  ruleflow2File = FileKnowledgeResource.createDRLClassPathResource("ruleflow2.drl");
        //  ruleFlowProcess2File = FileKnowledgeResource.createDRLClassPathResource("RuleFlowProcess2.bpmn2");
    }

    @Test
    public void KnowledgeBaseFireAllRules() throws DroolsChtijbugException {

        final List<HistoryEvent> historyEvents = new ArrayList<>();
        HistoryListener historyListener = new HistoryListener() {
            @Override
            public void fireEvent(HistoryEvent newHistoryEvent) throws DroolsChtijbugException {
                historyEvents.add(newHistoryEvent);
            }
            @Override
            public void setDetails(Boolean details) {
            }
            @Override
            public boolean withDetails() {
                return true;
            }
        };
        RuleBasePackage
        ruleBasePackage =  RuleHelper.createKB("fibonacci.drl",historyListener);
        Long rulePackageID = 1L;

        RuleBaseSession ruleBaseSession1 = ruleBasePackage.createRuleBaseSession();
        Fibonacci fibonacci = new Fibonacci(1);
        ruleBaseSession1.insertObject(fibonacci);
        ruleBaseSession1.fireAllRules();
        Assert.assertTrue(historyEvents.size() == 8);
        Assert.assertTrue(historyEvents.get(4) instanceof BeforeRuleFiredHistoryEvent);
        BeforeRuleFiredHistoryEvent beforeRuleFiredHistoryEvent = (BeforeRuleFiredHistoryEvent) historyEvents.get(4);
        assertThat(beforeRuleFiredHistoryEvent.getSessionId()).isEqualTo(1);
        assertThat(beforeRuleFiredHistoryEvent.getTypeEvent()).isEqualTo(HistoryEvent.TypeEvent.Rule);
        DroolsRuleObject droolsRuleObject = beforeRuleFiredHistoryEvent.getRule();
        assertThat(droolsRuleObject.getRuleName()).isEqualTo("Bootstrap");
        assertThat(droolsRuleObject.getRulePackageName()).isEqualTo("org.chtijbug.drools.runtime.test");

        Assert.assertTrue(historyEvents.get(6) instanceof AfterRuleFiredHistoryEvent);
        AfterRuleFiredHistoryEvent afterRuleFiredHistoryEvent = (AfterRuleFiredHistoryEvent) historyEvents.get(6);
        assertThat(afterRuleFiredHistoryEvent.getSessionId()).isEqualTo(1);
        assertThat(afterRuleFiredHistoryEvent.getTypeEvent()).isEqualTo(HistoryEvent.TypeEvent.Rule);
        DroolsRuleObject droolsRuleObject2 = afterRuleFiredHistoryEvent.getRule();
        assertThat(droolsRuleObject2.getRuleName()).isEqualTo("Bootstrap");
        assertThat(droolsRuleObject2.getRulePackageName()).isEqualTo("org.chtijbug.drools.runtime.test");
        assertThat(afterRuleFiredHistoryEvent.getRuleInstanceId()).isEqualTo(1);
    }

    @Test
    public void KnowledgeBaseFireAllRulesRuleFlowGroup() throws DroolsChtijbugException {

        final List<HistoryEvent> historyEvents = new ArrayList<HistoryEvent>();
        HistoryListener historyListener = new HistoryListener() {
            @Override
            public void fireEvent(HistoryEvent newHistoryEvent) throws DroolsChtijbugException {
                historyEvents.add(newHistoryEvent);
            }
            @Override
            public void setDetails(Boolean details) {
            }
            @Override
            public boolean withDetails() {
                return true;
            }
        };
        RuleBasePackage ruleBasePackage =  RuleHelper.createKBProcess("ruleflow2.drl","RuleFlowProcess2.bpmn2",historyListener);
        Long rulePackageID = 1L;

        RuleBaseSession ruleBaseSession1 = ruleBasePackage.createRuleBaseSession();
        Fibonacci fibonacci = new Fibonacci(0);
        ruleBaseSession1.insertObject(fibonacci);
        ruleBaseSession1.startProcess("P1");
        ruleBaseSession1.fireAllRules();
        assertThat(historyEvents).hasSize(37);
        assertThat(historyEvents.get(8)).isInstanceOfAny(AfterRuleFlowActivatedHistoryEvent.class);
        AfterRuleFlowActivatedHistoryEvent afterRuleFlowActivatedHistoryEvent = (AfterRuleFlowActivatedHistoryEvent) historyEvents.get(8);
        assertThat(afterRuleFlowActivatedHistoryEvent.getSessionId()).isEqualTo(1);
        assertThat(afterRuleFlowActivatedHistoryEvent.getTypeEvent()).isEqualTo(HistoryEvent.TypeEvent.RuleFlowGroup);
        assertThat(afterRuleFlowActivatedHistoryEvent.getDroolsRuleFlowGroupObject().getName()).isEqualTo("Group1");

        assertThat(historyEvents.get(11)).isInstanceOfAny(AfterRuleFiredHistoryEvent.class);
        AfterRuleFiredHistoryEvent afterRuleFiredHistoryEvent = (AfterRuleFiredHistoryEvent) historyEvents.get(11);
        assertThat(afterRuleFiredHistoryEvent.getSessionId()).isEqualTo(1);
        assertThat(afterRuleFiredHistoryEvent.getTypeEvent()).isEqualTo(HistoryEvent.TypeEvent.Rule);
        DroolsRuleObject droolsRuleObject2 = afterRuleFiredHistoryEvent.getRule();
        assertThat(droolsRuleObject2.getRuleName()).isEqualTo("Account group1");
        assertThat(droolsRuleObject2.getRulePackageName()).isEqualTo("org.chtijbug.drools.runtime.test");
        assertThat(afterRuleFiredHistoryEvent.getRuleInstanceId()).isEqualTo(1);


        assertThat(historyEvents.get(19)).isInstanceOf(AfterRuleFlowDeactivatedHistoryEvent.class);
        AfterRuleFlowDeactivatedHistoryEvent afterRuleFlowDeactivatedHistoryEvent = (AfterRuleFlowDeactivatedHistoryEvent) historyEvents.get(19);
        assertThat(afterRuleFlowDeactivatedHistoryEvent.getSessionId()).isEqualTo(1);
        assertThat(afterRuleFlowDeactivatedHistoryEvent.getTypeEvent()).isEqualTo(HistoryEvent.TypeEvent.RuleFlowGroup);
        assertThat(afterRuleFlowDeactivatedHistoryEvent.getDroolsRuleFlowGroupObject().getName()).isEqualTo("Group2");


    }


}
