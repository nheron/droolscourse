package calculation;



import decisiontable.Request;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.decisiontable.InputType;
import org.drools.template.parser.DataListener;
import org.drools.template.parser.TemplateDataListener;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import util.KnowledgeSessionHelper;

import java.util.ArrayList;
import java.util.List;

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
