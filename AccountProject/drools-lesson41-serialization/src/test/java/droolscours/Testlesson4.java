package droolscours;

import droolscours.util.OutputDisplay;
import lesson41.StopWorkItemHandler;
import org.drools.core.process.instance.WorkItemHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import serialization.FileKieSessionLoader;
import util.KnowledgeSessionHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;


public class Testlesson4 {
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
	public void testRuleFlow1() {
		String tmpDirsLocation = System.getProperty("java.io.tmpdir");
		File kieFile = new File(tmpDirsLocation+"/account.bin");
        try {
            kieFile.createNewFile();
			StopWorkItemHandler workItemHandler =new StopWorkItemHandler();
			FileKieSessionLoader fileKieSessionLoader = new FileKieSessionLoader(kieFile);
			sessionStatefull = KnowledgeSessionHelper
					.getStatefulKnowledgeSessionForJBPM(kieContainer, "lesson40-session-serialization",
							"Human Task", workItemHandler);
			OutputDisplay display = new OutputDisplay();
			sessionStatefull.setGlobal("showResult", display);
			Account a = new Account();
			sessionStatefull.insert(a);
			sessionStatefull.startProcess("P1_serialization");
			System.out.println("Wait for task Validation");
			System.out.println("Serialization");
			TimeUnit.SECONDS.sleep(2);
			fileKieSessionLoader.save(sessionStatefull);
			sessionStatefull = null;
			KieSession newKieSession=fileKieSessionLoader.load();
			System.out.println("Restore session");
			newKieSession.setGlobal("showResult", display);
			newKieSession.getWorkItemManager().completeWorkItem(workItemHandler.getWorkItem().getId(), null);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //	sessionStatefull.fireAllRules();
	}


}
