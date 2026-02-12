package org.chtijbug.drools.runtime.test;

import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.entity.DroolsFactObjectAttribute;
import org.chtijbug.drools.entity.history.HistoryEvent;
import org.chtijbug.drools.entity.history.fact.DeletedFactHistoryEvent;
import org.chtijbug.drools.entity.history.fact.InsertedFactHistoryEvent;
import org.chtijbug.drools.entity.history.fact.UpdatedFactHistoryEvent;
import org.chtijbug.drools.runtime.*;
import org.chtijbug.drools.runtime.impl.RuleBaseSingleton;
import org.chtijbug.drools.runtime.listener.HistoryListener;
import org.junit.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

/**
 * Created by IntelliJ IDEA.
 * Date: 26/09/12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class FactHandlerListernerTestTest {
    static RuleBaseSession session;
    static RuleBasePackage ruleBasePackage;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @Before
    public void justBefore() {
        //  insert1File = FileKnowledgeResource.createDRLClassPathResource("insert1.drl");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        session.dispose();

    }

    @Test
    public void test1ObjectInserted() throws Exception {
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        ruleBasePackage =  RuleHelper.createKB("fibonacci.drl",new TestHistoryListener());
        session = ruleBasePackage.createRuleBaseSession(1000,new TestHistoryListener());
        Fibonacci newObject = new Fibonacci(0);
        session.insertObject(newObject);
        Assert.assertEquals(session.getHistoryContainer().getListHistoryEvent().size(), 2);
        HistoryEvent event = session.getHistoryContainer().getListHistoryEvent().get(1);
        if (event instanceof InsertedFactHistoryEvent) {
            Assert.assertEquals(1, 1);
            Assert.assertEquals(session.getRuleBaseID(), event.getRuleBaseID());
            Assert.assertEquals(session.getSessionId(), event.getSessionId());
            InsertedFactHistoryEvent insertedFactHistoryEvent = (InsertedFactHistoryEvent) event;
            DroolsFactObject droolsFactObject = insertedFactHistoryEvent.getInsertedObject();
            for (DroolsFactObjectAttribute droolsFactObjectAttribute : droolsFactObject.getListfactObjectAttributes()) {
                if (droolsFactObjectAttribute.getAttributeName().equals("value")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "-1");
                } else if (droolsFactObjectAttribute.getAttributeName().equals("sequence")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "0");
                }
            }
        }
    }

    @Test
    public void test1ObjectUpdated() throws Exception {
        ruleBasePackage =  RuleHelper.createKB("fibonacci.drl",null);
        session = ruleBasePackage.createRuleBaseSession(1000,new TestHistoryListener());
        Fibonacci newObject = new Fibonacci(0);
        session.insertObject(newObject);
        newObject.setValue(100);
        session.updateObject(newObject);
        Assert.assertEquals(session.getHistoryContainer().getListHistoryEvent().size(), 3);
        HistoryEvent event = session.getHistoryContainer().getListHistoryEvent().get(2);
        if (event instanceof UpdatedFactHistoryEvent) {
            Assert.assertEquals(1, 1);
            Assert.assertEquals(session.getRuleBaseID(), event.getRuleBaseID());
            Assert.assertEquals(session.getSessionId(), event.getSessionId());
            UpdatedFactHistoryEvent updatedFactHistoryEvent = (UpdatedFactHistoryEvent) event;
            DroolsFactObject droolsFactObject = updatedFactHistoryEvent.getObjectOldValue();
            for (DroolsFactObjectAttribute droolsFactObjectAttribute : droolsFactObject.getListfactObjectAttributes()) {
                if (droolsFactObjectAttribute.getAttributeName().equals("value")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "-1");
                } else if (droolsFactObjectAttribute.getAttributeName().equals("sequence")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "0");
                }
            }
            DroolsFactObject droolsFactObject2 = updatedFactHistoryEvent.getObjectNewValue();
            Assert.assertEquals(droolsFactObject2.getObjectVersion(), 1);
            for (DroolsFactObjectAttribute droolsFactObjectAttribute : droolsFactObject2.getListfactObjectAttributes()) {
                if (droolsFactObjectAttribute.getAttributeName().equals("value")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "100");
                } else if (droolsFactObjectAttribute.getAttributeName().equals("sequence")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "0");
                }
            }
        }
    }

    @Test
    public void retracted() throws Exception {
        ruleBasePackage = RuleHelper.createKB("fibonacci.drl",null);
        session = ruleBasePackage.createRuleBaseSession(1000,new TestHistoryListener());
        Fibonacci newObject = new Fibonacci(0);
        session.insertObject(newObject);
        session.retractObject(newObject);
        Assert.assertEquals(session.getHistoryContainer().getListHistoryEvent().size(), 3);
        HistoryEvent event = session.getHistoryContainer().getListHistoryEvent().get(2);
        if (event instanceof DeletedFactHistoryEvent) {
            Assert.assertEquals(1, 1);
            Assert.assertEquals(session.getRuleBaseID(), event.getRuleBaseID());
            Assert.assertEquals(session.getSessionId(), event.getSessionId());
            DeletedFactHistoryEvent deletedFactHistoryEvent = (DeletedFactHistoryEvent) event;
            DroolsFactObject droolsFactObject = deletedFactHistoryEvent.getDeletedObject();
            for (DroolsFactObjectAttribute droolsFactObjectAttribute : droolsFactObject.getListfactObjectAttributes()) {
                if (droolsFactObjectAttribute.getAttributeName().equals("value")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "-1");
                } else if (droolsFactObjectAttribute.getAttributeName().equals("sequence")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "0");
                }
            }
        }
    }

    @Test
    public void test1RuleFired() throws Exception {
        ruleBasePackage =  RuleHelper.createKB("fibonacci.drl",null);
        session = ruleBasePackage.createRuleBaseSession(1000,new TestHistoryListener());
        Fibonacci newObject = new Fibonacci(1);
        session.insertObject(newObject);
        session.fireAllRules();
        Assert.assertEquals(session.listRules().size(), 1);
        Assert.assertEquals(session.listLastVersionObjects().size(), 1);

        for (DroolsFactObject droolsFactObject : session.listLastVersionObjects()) {
            Assert.assertEquals(droolsFactObject.getObjectVersion(), 1);
            Assert.assertEquals(droolsFactObject.getFullClassName(), "org.chtijbug.drools.runtime.test.Fibonacci");
            for (DroolsFactObjectAttribute droolsFactObjectAttribute : droolsFactObject.getListfactObjectAttributes()) {
                if (droolsFactObjectAttribute.getAttributeName().equals("value")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "1");
                } else if (droolsFactObjectAttribute.getAttributeName().equals("sequence")) {
                    Assert.assertEquals(droolsFactObjectAttribute.getAttributeValue(), "1");
                }
            }
        }


    }


}



