package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PostTest {

    private Post post;
    private User user;

    @Before
    public void setUp() {
        user = new User("testUser", "password", "test@example.com");
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        post = new Post(song, user, false, "Test Post", "This is a test post.");
    }

    @Test
    public void testAddComment() {
        post.addComment("This is a comment.", user);
        assertEquals("Post should have one comment", 1, post.getComments().size());
    }

    @Test
    public void testAddFavorite() {
        post.addFavorite(user);
        assertEquals("Favorites count should increase", 1, post.getFavorites());
    }
}
