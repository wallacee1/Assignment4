
/**
 * Write a description of class EventPost here.
 *
 * @author Eric Wallace
 * @version 1
 */
public class EventPost extends Post
{
    // instance variables - replace the example below with your own
    private String eventType;

    /**
     * Constructor for objects of class Post
     * @param author The username of the author of the post.
     */
    public EventPost(String author, String eventType)
    {
        // initialise instance variables
        super(author);
        this.eventType = eventType;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @return    the eventType
     */
    public String getEventType()
    {
        // put your code here
        return eventType;
    }
}
