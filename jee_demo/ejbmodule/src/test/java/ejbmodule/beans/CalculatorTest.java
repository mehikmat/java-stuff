package ejbmodule.beans;

import ejbmodules.beans.CalculatorBean;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {
    private static EJBContainer ejbContainer;
    private CalculatorBean calculator;

    @BeforeClass
    public static void startTheContainer() {
        ejbContainer = EJBContainer.createEJBContainer();
    }

    @Before
    public void lookupABean() throws NamingException {
        Object object = ejbContainer.getContext().lookup("java:global/ejbmodule/CalculatorBean");
        assertTrue(object instanceof CalculatorBean);
        calculator = (CalculatorBean) object;
    }

    @AfterClass
    public static void stopTheContainer() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }

    /**
     * Test Add method
     */
    @Test
    public void testAdd() {

        assertEquals(10, calculator.add(4, 6));

    }

    /**
     * Test Subtract method
     */
    @Test
    public void testSubtract() {

        assertEquals(-2, calculator.subtract(4, 6));

    }

    /**
     * Test Multiply method
     */
    @Test
    public void testMultiply() {

        assertEquals(24, calculator.multiply(4, 6));

    }

    /**
     * Test Divide method
     */
    @Test
    public void testDivide() {

        assertEquals(2, calculator.divide(12, 6));

    }

    /**
     * Test Remainder method
     */
    @Test
    public void testRemainder() {

        assertEquals(4, calculator.remainder(46, 6));

    }

}