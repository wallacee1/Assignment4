
/**
 * The Student class represents a student in a student administration system.
 * It holds the student details relevant in our context.
 * 
 * @author Michael Kölling and David Barnes
 * @version 2016.02.29
 */
public class Student extends Person
{
    // the student ID
    private String id;
    // the amount of credits for study taken so far
    private int credits;

    /**
     * Create a new student with a given name and ID number.
     */
    public Student(String fullName, String studentID)
    {
        super(fullName);
        id = studentID;
        credits = 0;
    }

    /**
     * Return the full name of this student.
     */
    public Student(String fullName, String studentID, String phoneNumber)
    {
        super(fullName, phoneNumber);
        id = studentID;
        credits = 0;
    }
    
    /**
     * Return the student ID of this student.
     */
    public String getStudentID()
    {
        return id;
    }

    /**
     * Add some credit points to the student's accumulated credits.
     */
    public void addCredits(int additionalPoints)
    {
        credits += additionalPoints;
    }

    /**
     * Return the number of credit points this student has accumulated.
     */
    public int getCredits()
    {
        return credits;
    }

    /**
     * Return the login name of this student. The login name is a combination
     * of the first four characters of the student's name and the first three
     * characters of the student's ID number.
     */
    public String getLoginName()
    {
        return getName().substring(0,4) + id.substring(0,3);
    }
    
    /**
     * Print the student's name and ID number to the output terminal.
     */
    public void print()
    {
        System.out.println(getName() + ", student ID: " + id + ", credits: " + credits);
    }
}
