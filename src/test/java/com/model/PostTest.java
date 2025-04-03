package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PostTest {

<<<<<<< HEAD
    @Test
    public void testPostCreationBasic() {
        User testUser = new User("babygirl", "pass", "craycray@gmail.com");
        Post newPost = new Post("Oriental Riff", testUser, "Bohemian Rhapsody", "This song is mid");
        assertNotNull(newPost);
    }

    // TODO: Empty strings
    @Test
    public void testPostCreationAllEmptyStrings() {
        User testUser = new User("Kenny", "blahblah", "beepboop@yahoo.com");
        Post newPost = new Post("", testUser, "", "");
        boolean condition = newPost.getSong() == null && newPost.getTitle().equals("Default title") && newPost.getBody().equals("Lorem ipsum dolor sit amet.");
        assertTrue(condition);
    }

    @Test
    public void testPostCreationTwoEmptyStrings() {
        User testUser = new User("Ekbdak", "bopbop", "craycray@gmail.com");
        Post newPost = new Post("", testUser, "", "This song is beautiful");
        boolean condition = newPost.getSong() == null && newPost.getTitle().equals("Default title");
        assertTrue(condition);
    }

    @Test
    public void testPostCreationInvalidSong() {
        User testUser = new User("Ekbdak", "bopbop", "craycray@gmail.com");
        Post newPost = new Post("Rush E - Playable Verison!", testUser, "", "This song is beautiful");
        assertNull(newPost.getSong());
    }

    public void testAddingComment() {
        
    }




}
=======
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
>>>>>>> 5417448b5e97abb079638fba3dd78307b09b9826
