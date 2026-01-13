package calculation;


import es.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import util.KnowledgeSessionHelper;

@SuppressWarnings("restriction")
public class TestExpertSystem {

    static KieContainer kieContainer;
    StatelessKieSession sessionStateless = null;
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
        System.out.println("------------After------------");
    }



    @Test
    public void testFirstOne() {
        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessionForJBPM(kieContainer, "ksession-rules");
        Request request = new Request("N1") ;
        sessionStatefull.insert(request);
        sessionStatefull.fireAllRules();

    }

}
