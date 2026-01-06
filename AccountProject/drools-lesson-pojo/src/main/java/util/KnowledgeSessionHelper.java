package util;

import org.drools.core.impl.EnvironmentFactory;
import org.drools.core.impl.RuleBaseFactory;
import org.drools.kiesession.rulebase.InternalKnowledgeBase;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.process.*;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.utils.ChainedProperties;

import java.util.Properties;

public class KnowledgeSessionHelper {

    public static KieContainer createRuleBase() {

        KieServices ks = KieServices.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        return kieContainer;
    }

    public static StatelessKieSession getStatelessKnowledgeSession(KieContainer kieContainer, String sessionName) {
        StatelessKieSession kSession = kieContainer.newStatelessKieSession(sessionName);

        return kSession;
    }

    public static KieSession getStatefulKnowledgeSession(KieContainer kieContainer, String sessionName) {
      
    	KieSession kSession = kieContainer.newKieSession(sessionName);
     
    	return kSession;
    }

    public static KieSession getStatefulKnowledgeSessionWithCallback(
            KieContainer kieContainer, String sessionName) {
        KieSession session = getStatefulKnowledgeSession(kieContainer, sessionName);
        session.addEventListener(new ChtiJbugSimpleRuleRuntimeEventListener());
        session.addEventListener(new ChtiJbugSimpleAgendaEventListener() );


        return session;
    }


    public static KieSession getKogitoProcessSession( KieContainer kieContainer, String kbaseName){

        KieBase kieBase = kieContainer.getKieBase(kbaseName);
        Properties props = new Properties();
       // props.setProperty("drools.workItemHandlers", "CustomTaskWorkItemHandlers.conf");
        props.setProperty("drools.workItemHandlers", "DrlCustomTaskWorkItemHandlers.conf");
       // props.setProperty("drools.workItemManagerFactory", "org.jbpm.process.workitem.builtin.DefaultKogitoWorkItemHandlerFactory");
        ClassLoader cl = ((InternalKnowledgeBase)kieBase).getConfiguration().getClassLoader();
        KieSessionConfiguration config = RuleBaseFactory.newKnowledgeSessionConfiguration(ChainedProperties.getChainedProperties(cl).addProperties(props), cl);
        KieSession ksession = kieBase.newKieSession(config, EnvironmentFactory.newEnvironment());

        ksession.addEventListener(new ChtiJbugSimpleRuleRuntimeEventListener());
        ksession.addEventListener(new ChtiJbugSimpleAgendaEventListener() );
        ksession.addEventListener(new ChtiJbugSimpleProcessEventListener() );
        return ksession;
    }

    public static KieSession getStatefulKnowledgeSessionForJBPM(
            KieContainer kieContainer, String sessionName) {
        KieSession session = getStatefulKnowledgeSessionWithCallback(kieContainer, sessionName);

        session.addEventListener(new ChtiJbugSimpleProcessEventListener() );
        return session;
    }


}
