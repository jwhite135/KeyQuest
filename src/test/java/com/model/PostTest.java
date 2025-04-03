package com.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class PostTest {

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