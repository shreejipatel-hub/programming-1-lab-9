

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CommentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommentTest
{
    /**
     * Default constructor for test class CommentTest
     */
    public CommentTest()
    {
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
    public void testMinimumAndMaximumRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Idiots", 100);
        assertTrue(salesIte1.addComment("shreeji", "it's bad", 1));
        assertTrue(salesIte1.addComment("Blah", "it's good", 5));
    }
    
    @Test
    public void testCommentCreation()
    {
        String expectedAuthor = "Shreeji";
        int expectedRating = 4;
        String commentText = "Noice!!";
        
        Comment comment = new Comment(expectedAuthor, commentText, expectedRating);
        
        assertEquals(expectedAuthor, comment.getAuthor(), "Author should be stored");
        assertEquals(expectedRating, comment.getRating(), "Rating shoulld be stored");
        assertEquals(0, comment.getVoteCount(), "Initial vote count should be 0");
        
    }
    
    @Test
    public void testUpVoteComment()
    {
        Comment comment = new Comment("UpVote Tester", "Test Upvote", 3);
        int initialVoteCount = comment.getVoteCount();
        
        comment.upvote();
        comment.upvote();
        
        assertEquals(initialVoteCount + 2, comment.getVoteCount(),"Vote count should increase by 2");
    }
    
    @Test
    public void testDownVoteComment()
    {
        Comment comment = new Comment("downvote tester", "test downvote", 5);
        int initialVoteCount = comment.getVoteCount();
        
        comment.downvote();
        comment.downvote();
        comment.downvote();
        
        assertEquals(initialVoteCount - 3, comment.getVoteCount(), "Vote count should decrease by 3.");
    }
}