package com.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {
    
    @Test
    public void testCommentCreation() {
        Comment comment = new Comment("This is a test comment", new User("John Doe", "blahsd", "gmail@gmail.com"));
        assertNotNull("Comment was created", comment);
    }

    @Test
    public void testCommentCreationBlankBody() {
        Comment comment = new Comment("", new User("John Doe", "blahsd", "gmail@gmail.com"));
        boolean condition = comment.getBody().equals("Lorem ipsum dolor sit amet.");
        assertTrue("Blank field, so default body used", condition);
    }
    
}