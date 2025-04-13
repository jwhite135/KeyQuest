package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
    public void testMakePost_Failure() {
        facade.makeUser("testUser", "password123", "test@example.com");
        facade.login("testUser", "password123");
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        boolean result = facade.makePost(song, false, "", "This is a test post.");
        assertFalse("Post should not be created successfully, since one or more parameters were empty", result);
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
    public void testSearchSongsByName_Failure() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        ArrayList<Song> results = facade.searchSongsByName("Nonexistent Song");
        assertEquals("Search should return no songs", 0, results.size());
    }

    @Test
    public void testSearchSongsByArtist_Failure() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        ArrayList<Song> results = facade.searchSongsByArtist("Nonexistent Artist");
        assertEquals("Search should return no songs", 0, results.size());
    }

    @Test
    public void testFavoritePost() {
        facade.makeUser("testUser", "password123", "test@example.com");
        facade.login("testUser", "password123");
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        facade.makePost(song, false, "", "This is a test post.");
        Post post = PostDatabase.getInstance().getPosts().get(0);
        facade.favoritePost(post);
        if (post.getFavorites() == 1) {
            assertTrue("Post should be favorited successfully", true);
        } else {
            fail("Post should be favorited successfully");
        }
    }

    @Test
    public void testSearchPostsByName() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        Song song2 = facade.createSong("Test Song 2", "Artist 2", 2, "Rock", 4, 4, 120);
        facade.makePost(song1, false, "Post 1", "This is post 1.");
        facade.makePost(song2, false, "Post 2", "This is post 2.");
        ArrayList<Post> results = facade.searchPostsByName("Post 1");
        assertEquals("Search should return one post", 1, results.size());
        assertEquals("Search should return the correct post", "Post 1", results.get(0).getTitle());
        assertEquals("Search should return the correct post", "This is post 1.", results.get(0).getBody());
    }

    @Test
    public void testSearchPostsByName_Failure() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        facade.makePost(song1, false, "Post 1", "This is post 1.");
        ArrayList<Post> results = facade.searchPostsByName("Nonexistent Post");
        assertEquals("Search should return no posts", 0, results.size());
    }

    @Test
    public void testSearchPostsByArtist() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        Song song2 = facade.createSong("Test Song 2", "Artist 1", 2, "Rock", 4, 4, 120);
        facade.makePost(song1, false, "Post 1", "This is post 1.");
        facade.makePost(song2, false, "Post 2", "This is post 2.");
        ArrayList<Post> results = facade.searchPostsByArtist("Artist 1");
        assertEquals("Search should return two posts", 2, results.size());
    }

    @Test
    public void testSearchPostsByArtist_Failure() {
        Song song1 = facade.createSong("Test Song 1", "Artist 1", 1, "Pop", 4, 4, 120);
        facade.makePost(song1, false, "Post 1", "This is post 1.");
        ArrayList<Post> results = facade.searchPostsByArtist("Nonexistent Artist");
        assertEquals("Search should return no posts", 0, results.size());
    }

}