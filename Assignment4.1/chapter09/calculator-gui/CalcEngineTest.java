

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CalcEngineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalcEngineTest
{
    private CalcEngine engine;
    /**
     * Default constructor for test class CalcEngineTest
     */
    public CalcEngineTest()
    {
        engine = new CalcEngine();
    }
    
    /**
     * Test both addition and subtraction.
     */
    public void testAll()
    {
        engine.clear();
    }
    
    public void testComplex()
    {
        System.out.println("Testing the multi-digit operation.");
        System.out.println("44 + 65 - 116 is: " + testMultiDigit());
        boolean passed = testMultiDigit() == -7;
        if (passed) {
            System.out.println("All tests passed.");
        }
        else {
            System.out.println("Testing failed.");
        }
        System.out.println("Expected results: -7");
        System.out.println("Actual results: " + testMultiDigit());
        System.out.println("_____________________________");
    }
    
    /**
     * Test multidigits.
     * 
     * @return the result of 44 + 65 - 116.
     */
    public int testMultiDigit()
    {
        engine.clear();
        engine.numberPressed(4);
        engine.numberPressed(4);
        engine.plus();
        engine.numberPressed(6);
        engine.numberPressed(5);
        engine.minus();
        engine.numberPressed(1);
        engine.numberPressed(1);
        engine.numberPressed(6);
        engine.equals();
        return engine.getDisplayValue();
    }
    
    /**
     * Test plus
     * @return addition of 3 + 4.
     */
    public int testPlus()
    {
        engine.clear();
        engine.numberPressed(3);
        engine.plus();
        engine.numberPressed(4);
        engine.equals();
        return engine.getDisplayValue();
    }
    
    /**
     * Test minus
     * @return calculation of 9 - 4.
     */
    public int testMinus()
    {
        engine.clear();
        engine.numberPressed(9);
        engine.minus();
        engine.numberPressed(4);
        engine.equals();
        return engine.getDisplayValue();
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testALl()
    {
        CalcEngine calcEngi1 = new CalcEngine();
    }
}

