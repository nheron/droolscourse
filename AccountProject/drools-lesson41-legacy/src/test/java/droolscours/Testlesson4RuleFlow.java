package droolscours;

import droolscours.util.OutputDisplay;
import lesson4Classical.DrlCustomTask;
import org.drools.core.impl.EnvironmentFactory;
import org.drools.core.impl.RuleBaseFactory;
import org.drools.kiesession.rulebase.InternalKnowledgeBase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.WorkItemHandlerOption;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.internal.utils.ChainedProperties;
import org.kie.kogito.process.bpmn2.BpmnProcess;
import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.kie.memorycompiler.resources.ResourceReader;
import util.KnowledgeSessionHelper;

import java.util.Properties;


public class Testlesson4RuleFlow {
	static KieContainer kieContainer;
	KieSession sessionStatefull = null;

	@BeforeClass
	public static void beforeClass() {

        kieContainer = KnowledgeSessionHelper.createRuleBase();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("------------Before------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("------------After ------------");
	}
	
	

	@Test
	public void testRuleFlow4() {

		sessionStatefull = KnowledgeSessionHelper
				.getKogitoProcessSession(kieContainer, "lesson4aClassical");

		Account a = new Account();
		sessionStatefull.insert(a);
        sessionStatefull.startProcess("processLesson4Classical");
		sessionStatefull.fireAllRules();

	}

	
}
