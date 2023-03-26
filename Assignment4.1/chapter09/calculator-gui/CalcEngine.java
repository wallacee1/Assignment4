/**
 * The main part of the calculator doing the calculations.
 * 
 * @author: (none yet)
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
    // Put instance variables here.
    private int displayValue;
    private char previousOperator;
    private int leftOperand;
    private boolean resetDisplayValue;
    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        displayValue = 0;
        previousOperator = ' ';
        leftOperand = 0;
        resetDisplayValue = false;
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public int getDisplayValue()
    {
        return displayValue;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number)
    {
        displayValue = 0;
        displayValue = displayValue * 10 + number;
        resetDisplayValue = false;
    }

    /**
     * The 'plus' button was pressed. 
     */
    public void plus()
    {
        applyPreviousOperator();
        previousOperator = '+';
        resetDisplayValue = true;
    }

    /**
     * The 'minus' button was pressed.
     */
    public void minus()
    {
        applyPreviousOperator();
        previousOperator = '-';
        resetDisplayValue = true;
    }

    /**
     * The '=' button was pressed.
     */
    public void equals()
    {
        if(previousOperator == '+') {
            displayValue = leftOperand + displayValue;
        }
        else {
            displayValue = leftOperand - displayValue;
        }
        leftOperand = 0;
        previousOperator = ' ';
    }
    
    private void applyPreviousOperator()
    {
        if (previousOperator == '+') {
            leftOperand += displayValue;
        }
        else if (previousOperator == '-') {
            leftOperand -= displayValue;
        }
        else {
            leftOperand = displayValue;
        }
    }

    /**
     * The 'C' (clear) button was pressed.
     */
    public void clear()
    {
        displayValue = 0;
        previousOperator = ' ';
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "";
    }

    /**
     * @return The author of this engine. This string is displayed as it is,
     * so it should say something like "Written by H. Simpson".
     */
    public String getAuthor()
    {
        return "";
    }

    /**
     * @return The version number of this engine. This string is displayed as 
     * it is, so it should say something like "Version 1.1".
     */
    public String getVersion()
    {
        return "";
    }
}
