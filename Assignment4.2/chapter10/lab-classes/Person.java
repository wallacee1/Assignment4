
/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    private String name;
    private String phoneNumber;

    /**
     * Constructor for objects of class Person
     */
    public Person(String name, String phoneNumeber)
    {
        // initialise instance variables
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Person(String name)
    {
        // put your code here
        this.name = name;
        this.phoneNumber = "unspecified";
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
