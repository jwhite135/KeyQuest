package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class KeyQuestFACADETest {

    private KeyQuestFACADE facade;

    @Before
    public void setUp() {
        facade = new KeyQuestFACADE();
    }

    @Test
    public void testMakeUser() {
        boolean result = facade.makeUser("testUser", "password123", "test@example.com");
        assertTrue("User should be created successfully", result);
    }

    @Test
    public void testMakeUser_UserAlreadyExists() {
        facade.makeUser("testUser", "password123", "test@example.com");
        boolean result = facade.makeUser("testUser", "password123", "test@example.com");
        assertFalse("User creation should fail if the user already exists", result);
    }

    @Test
    public void testLogin_Success() {
        facade.makeUser("testUser", "password123", "test@example.com");
        boolean result = facade.login("testUser", "password123");
        assertTrue("Login should succeed with correct credentials", result);
    }

    @Test
    public void testLogin_Failure() {
        facade.makeUser("testUser", "password123", "test@example.com");
        boolean result = facade.login("testUser", "wrongPassword");
        assertFalse("Login should fail with incorrect credentials", result);
    }

    @Test
    public void testMakePost() {
        facade.makeUser("testUser", "password123", "test@example.com");
        facade.login("testUser", "password123");
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        boolean result = facade.makePost(song, false, "Test Post", "This is a test post.");
        assertTrue("Post should be created successfully", result);
    }

    @Test
    public void testSearchSongsByName() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        Song song2 = facade.createSong("Test Song 2", "Artist 2", 2, "Rock", 4, 4, 120);
        ArrayList<Song> results = facade.searchSongsByName("Test Song 1");
        assertEquals("Search should return one song", 1, results.size());
        assertEquals("Search should return the correct song", song1, results.get(0));
    }

    @Test
    public void testSearchSongsByArtist() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        Song song2 = facade.createSong("Test Song 2", "Artist 1", 2, "Rock", 4, 4, 120);
        ArrayList<Song> results = facade.searchSongsByArtist("Artist 1");
        assertEquals("Search should return two songs", 2, results.size());
        assertTrue("Search should return the correct songs", results.contains(song1) && results.contains(song2));
    }

    @Test
    public void testLogout() {
        facade.makeUser("testUser", "password123", "test@example.com");
        facade.login("testUser", "password123");
        facade.logout();
        assertFalse("User should be logged out", facade.login("testUser", "password123"));
    }
}
