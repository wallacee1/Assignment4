
/**
 * Write a description of class Instructor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Instructor extends Person
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class Instructor
     */
    public Instructor(String name, String phoneNumber)
    {
        // initialise instance variables
        super(name, phoneNumber);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Instructor(String name)
    {
        // put your code here
        super(name);
    }
    
    @Override
    public String toString()
    {
        return getName();
    }
}
