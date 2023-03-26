import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  mik
 * @version 0.1
 */
public class SalesItemTest
{
    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test that a comment can be added, and that the comment count is correct afterwards.
     */
    @Test
    public void testAddComment()
    {
        SalesItem salesIte1 = new SalesItem("Brain surgery for Dummies", 21998);
        assertEquals(true, salesIte1.addComment("James Duckling", "This book is great. I perform brain surgery every week now.", 4));
        assertEquals(1, salesIte1.getNumberOfComments());
    }

    /**
     * Test that a comment using an illegal rating value is rejected.
     */
    @Test
    public void testIllegalRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Complete Idiots, Vol 2", 19900);
        assertEquals(false, salesIte1.addComment("Joshua Black", "Not worth the money. The font is too small.", -5));
    }

    /**
     * Test that a sales item is correctly initialised (name and price).
     */
    @Test
    public void testInit()
    {
        SalesItem salesIte1 = new SalesItem("test name", 1000);
        assertEquals("test name", salesIte1.getName());
        assertEquals(1000, salesIte1.getPrice());
    }

    @Test
    public void addComment()
    {
        SalesItem salesIte1 = new SalesItem("Brain Surgery for Dummies.", 9899);
        assertEquals(true, salesIte1.addComment("Fred", "Great - I perform brain surgery every week now!", 4));
    }

    @Test
    public void test()
    {
        SalesItem salesIte1 = new SalesItem("Pants", 45);
        assertEquals(5, salesIte1.addComment("Eric Wallace", "These pants are comfy", 5));
        salesIte1.showInfo();
    }

    @Test
    public void testAddCommentBySameAuthor()
    {
        SalesItem salesIte1 = new SalesItem("Shoes", 5000);
        assertEquals(true, salesIte1.addComment("Eric Wallace", "These shoes are terrible", 1));
        assertEquals(false, salesIte1.addComment("Eric Wallace", "They suck balls", 2));
        salesIte1.showInfo();
    }

    @Test
    public void commentRatings()
    {
        SalesItem salesIte1 = new SalesItem("BackPack", 2500);
        assertEquals(false, salesIte1.addComment("Orlando Jones", "This backpack is cheap", 0));
        assertEquals(false, salesIte1.addComment("Macho Man Randy Savage", "Ohhh Yeahhhh", 6));
    }
    
    @Test
    public void testFindMostHelpfulComment()
    {
        SalesItem gravelBike = new SalesItem("gravelBike", 12000);
        gravelBike.addComment("Macy Gray", "Terrible", 1);
        gravelBike.addComment("Mark Wahlberg", "Awsome", 5);
        gravelBike.upvoteComment(1);
        gravelBike.upvoteComment(0);
        gravelBike.upvoteComment(0);
        Comment testComment = gravelBike.findMostHelpfulComment();
        assertEquals("Macy Gray", testComment.getAuthor());
    }

    @Test
    public void testGetPrice()
    {
        SalesItem salesIte1 = new SalesItem("Microphone", 1000);
        assertEquals(1000, salesIte1.getPrice());
    }

    @Test
    public void testRemoveComment()
    {
        SalesItem salesIte1 = new SalesItem("TV", 40000);
        assertEquals(0, salesIte1.addComment("Eric Wallace", "That brand is very reputable.", 5));
        salesIte1.showInfo();
        salesIte1.upvoteComment(0);
        salesIte1.showInfo();
        salesIte1.removeComment(0);
    }
}








