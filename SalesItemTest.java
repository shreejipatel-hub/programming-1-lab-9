import static org.junit.jupiter.api.Assertions.*;
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
    public void testRejectDuplicateAuthor()
    {
        SalesItem salesIte1 = new SalesItem("Java For Idiots", 100);
        String authorName = "Shreeji";
        String comment = "Nice Product";
        int rating = 5;
        
        boolean firstResult = salesIte1.addComment(authorName, "comment", rating);
        
        boolean secondResult = salesIte1.addComment(authorName, "Another comment", 4);
        
        assertTrue(firstResult, "The first comment should have been added");
        assertFalse(secondResult, "The second comment from the same author should be rejected (return false).");
        assertEquals(1, salesIte1.getNumberOfComments(), "The total number of comments should be 1.");
        
        
    }
    
    @Test
    public void negativeTestingOnRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Idiots", 100);
        assertFalse(salesIte1.addComment("shreeji", "it's bad", 0));
        assertFalse(salesIte1.addComment("Blah", "it's good", 6));
    }
    
    @Test
    public void testTieBreaker()
    {
        SalesItem salesIte1 = new SalesItem("Cat",100);
        String authorName = "Shreeji";
        String comment = "i like cats";
        int rating = 5;
        String author2 = "Marinara";
        String comment2 = "i like cats more";
        int rating2 = 5;
        
        salesIte1.addComment(authorName, comment, rating);
        salesIte1.addComment(author2, comment2, rating2);
        
        salesIte1.upvoteComment(0);
        salesIte1.upvoteComment(0);
        
        salesIte1.upvoteComment(1);
        salesIte1.upvoteComment(1);
        
        Comment tieWinner = salesIte1.findMostHelpfulComment();
        
        assertNotNull(tieWinner, "should return a comment object");
        assertEquals(authorName, tieWinner.getAuthor(), "The winner should be the first author");
        assertEquals(2, tieWinner.getVoteCount(), "the winner should have a vote count of 2");
        
    }
    
    
    @Test
    public void testFindMostHelpfulComment()
    {
        SalesItem salesIte1 = new SalesItem("Test helpful comment", 100);
        
        String authorA = "Evil Larry";
        String authorB = "Oopy Goopy";
        String authorC = "Mr boom";
        
        salesIte1.addComment(authorA, "it sucks!!", 5);
        salesIte1.addComment(authorB, "It's alright kinda overrated", 5);
        salesIte1.addComment(authorC, "It's the best", 5);
        
        salesIte1.upvoteComment(2);
        salesIte1.upvoteComment(2);
        
        salesIte1.downvoteComment(0);
        
        Comment mostHelpful = salesIte1.findMostHelpfulComment();
        
        assertNotNull(mostHelpful, "methode returns a comment object");
        assertEquals(authorC, mostHelpful.getAuthor(), "returned comment should be from Mr boom with 2 upvotes");
        assertEquals(2, mostHelpful.getVoteCount(), "The winner's vote count should be 2");
        
    }
    
    @Test
    public void testFindMostHelpfulCommentOnEmptyList()
    {
        SalesItem emptyItem = new SalesItem("Blah", 10);
        Comment MostHelpful = emptyItem.findMostHelpfulComment();
        
        assertNull(MostHelpful, "methode should return null when comments list is empty");
        
    }
}
