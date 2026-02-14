package loyalty;
import loyalty.domains.Ticket;
import org.chtijbug.drools.ChtijbugObjectRequest;
import org.chtijbug.drools.runtime.RuleBasePackage;
import org.chtijbug.drools.runtime.impl.RuleBaseSingleton;
import org.chtijbug.drools.runtime.kieserver.service.drools.ChtijbugHistoryListener;
import org.chtijbug.drools.runtime.kieserver.service.drools.DroolsChtijbugRulesExecutionService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

public class LoyaltyService {
    private DroolsChtijbugRulesExecutionService droolsChtijbugRulesExecutionService = null;
    public void initService(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        droolsChtijbugRulesExecutionService = DroolsChtijbugRulesExecutionService.createService(ks.getKieClasspathContainer().getKieBase("loyalty"),100000);
    }

    public Ticket calculate(Ticket ticket){
        if (droolsChtijbugRulesExecutionService==null){
            this.initService();
        }
        ChtijbugObjectRequest chtijbugObjectRequest = new ChtijbugObjectRequest();
        chtijbugObjectRequest.setObjectRequest(ticket);
        chtijbugObjectRequest.setDisableLogging(false);

        chtijbugObjectRequest = droolsChtijbugRulesExecutionService.FireAllRulesAndStartProcess(chtijbugObjectRequest,"P1",100000);

        return ticket;
    }
}
