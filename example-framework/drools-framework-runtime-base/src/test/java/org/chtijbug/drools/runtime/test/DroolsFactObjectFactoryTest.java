/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chtijbug.drools.runtime.test;

import org.chtijbug.drools.entity.DroolsFactObject;
import org.chtijbug.drools.runtime.*;
import org.junit.*;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * @author nheron
 */
public class DroolsFactObjectFactoryTest {

    public DroolsFactObjectFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestIntrosepct() {
        TestPojoOne underTest = new TestPojoOne();
        DroolsFactObject fact = DroolsFactObjectFactory.createFactObject(underTest, 1);
        assertTrue(fact.getListfactObjectAttributes().size() == 7);

    }


    public class TestPojoOne {
        private Long at01 = new Long(12);
        private String at02 = "hello";
        private Integer at03 = new Integer("12");
        private BigDecimal at04 = new BigDecimal(12.04);
        private int at10 = 1;
        private short at11 = 1;
        private double at12 = 12.99;

        public Long getAt01() {
            return at01;
        }

        public void setAt01(Long at01) {
            this.at01 = at01;
        }

        public String getAt02() {
            return at02;
        }

        public void setAt02(String at02) {
            this.at02 = at02;
        }

        public Integer getAt03() {
            return at03;
        }

        public void setAt03(Integer at03) {
            this.at03 = at03;
        }

        public BigDecimal getAt04() {
            return at04;
        }

        public void setAt04(BigDecimal at04) {
            this.at04 = at04;
        }

        public int getAt10() {
            return at10;
        }

        public void setAt10(int at10) {
            this.at10 = at10;
        }

        public short getAt11() {
            return at11;
        }

        public void setAt11(short at11) {
            this.at11 = at11;
        }

        public double getAt12() {
            return at12;
        }

        public void setAt12(double at12) {
            this.at12 = at12;
        }

    }
}
