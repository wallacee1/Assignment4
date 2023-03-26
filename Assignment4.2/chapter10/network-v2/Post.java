import java.util.ArrayList;

/**
 * This class stores information about a news feed post in a 
 * social network. Posts can be stored and displayed. This class
 * serves as a superclass for more specific post types.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.2
 */
public class Post 
{
    private String username;  // username of the post's author
    private long timestamp;
    private int likes;
    private ArrayList comments;
    /**
     * Constructor for objects of class Post.
     * 
     * @param author    The username of the author of this post.
     */
    public Post(String author)
    {
        username = author;
        timestamp = System.currentTimeMillis();
        likes = 0;
        comments = new ArrayList<>();
    }

    /**
     * Return the time of creation of this post.
     * 
     * @return The post's creation time, as a system time value.
     */
    public long getTimeStamp()
    {
        return timestamp;
    }
    
    /**
     * Return the username.
     * 
     * @return The post username.
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Record one more 'Like' indication from a user.
     */
    public void like()
    {
        likes++;
    }
    
    /**
     * Remove likes.
     */
    public void unlike()
    {
        if(likes > 0) {
            likes--;
        }
    }
    
    /**
     * Add a comment
     */
    public void addComment(String text)
    {
        comments.add(text);
    }
    
    public void display()
    {
        System.out.println(username);
    }
    
    /**
     * Create a string describing a time point in the past relative to current
     * time, such as "1 minute ago" or "10 seconds ago".
     * 
     * @param time The time value to convert
     * @return A relative time string for the given time
     */
    private String timeString(long time)
    {
        long current = System.currentTimeMillis();
        long pastMillis = current - time;
        long seconds = pastMillis/1000;
        long minutes = seconds/60;
        if(minutes > 0) {
            return minutes + " minutes ago";
        }
        else {
            return seconds + " seconds ago";
        }
    }
}
