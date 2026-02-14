package loyalty;

import loyalty.domains.*;
import org.chtijbug.drools.ChtijbugObjectRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLoyalty {
    private static LoyaltyService loyaltyService;
    @BeforeClass
    public static void beforeClass() {
        loyaltyService = new LoyaltyService();
        loyaltyService.initService();
    }

    @Before
    public void setUp() {
        System.out.println("-----------Before-------------");
    }
    @After
    public void tearDown() {
        System.out.println("-----------After-----------");
    }

    @Test
    public void testRuleOneFactWithoutFact() {
        Provider provider = new Provider("Evian","France");
        Ticket ticket = new Ticket();
        Product product = new Product("Evian","Evian",provider);
        Price evianPrice = new Price(5.0F,Currency.Euro);
        product.setPrice(evianPrice);
        ticket.addLine(product,5.0F,10L);
        ticket = loyaltyService.calculate(ticket);
        System.out.println(ticket);
    }
}
