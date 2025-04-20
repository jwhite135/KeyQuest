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
    public void testPostCreationBasic() {
        User testUser = new User("babygirl", "pass", "craycray@gmail.com");
        Post newPost = new Post("Oriental Riff", testUser, "Bohemian Rhapsody", "This song is mid");
        assertNotNull("Post should be created", newPost);
    }

    @Test
    public void testPostCreationAllEmptyStrings() {
        User testUser = new User("Kenny", "blahblah", "beepboop@yahoo.com");
        Post newPost = new Post("", testUser, "", "");
        boolean condition = newPost.getSong() == null && newPost.getTitle().equals("Default title") && newPost.getBody().equals("Lorem ipsum dolor sit amet.");
        assertTrue("All Post parameters should be default", condition);
    }

    @Test
    public void testPostCreationTwoEmptyStrings() {
        User testUser = new User("Ekbdak", "bopbop", "craycray@gmail.com");
        Post newPost = new Post("", testUser, "", "This song is beautiful");
        boolean condition = newPost.getSong() == null && newPost.getTitle().equals("Default title");
        assertTrue("Specific post parameters should be default", condition);
    }

    @Test
    public void testPostCreationInvalidSong() {
        User testUser = new User("Ekbdak", "bopbop", "craycray@gmail.com");
        Post newPost = new Post("Rush E - Playable Verison!", testUser, "", "This song is beautiful");
        assertNull("Song should be null", newPost.getSong());
    }

    @Test
    public void testAddComment() {
        post.addComment("This is a comment.", user);
        assertEquals("Post should have one comment", 1, post.getComments().size());
    }

    @Test
    public void testAddEmptyComment() {
        post.addComment("", user);
        assertEquals("Post should have no comments", 0, post.getComments().size());
    }

    @Test
    public void testAddFavorite() {
        post.addFavorite(user);
        assertEquals("Favorites count increase", 1, post.getFavorites());
    }

    @Test
    public void testAddFavoriteAlreadyFavorited() {
        post.addFavorite(user);
        post.addFavorite(user);
        assertEquals("Favorites count should not increase", 1, post.getFavorites());
    }

    @Test
    public void testRemoveFavorite() {
        post.addFavorite(user);
        post.removeFavorite(user);
        assertEquals("Favorites count should decrease", 0, post.getFavorites());
    }

    @Test
    public void testRemoveFavoriteNotFavorited() {
        post.removeFavorite(user);
        assertEquals("Favorites count should not decrease", 0, post.getFavorites());
    }
}