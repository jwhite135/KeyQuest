package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class UserTest {
    private User user;
    private User friend;
    private UUID userId;

    @Before
    public void setUp() {
        user = new User("username", "password", "email@email.com");
        friend = new User("friend", "pwd", "friend@email.com");
    }

    @Test
    public void testUserCreation() {
        assertNotNull(user);
        assertEquals("username", user.getUsername());
        assertEquals("email@email.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testAddFriend() {
        user.addFriend(friend);
        assertTrue(user.getFriends().contains(friend));
    }

    @Test
    public void testRemoveFriend() {
        user.addFriend(friend);
        user.removeFriend(friend);
        assertFalse(user.getFriends().contains(friend));
    }

    @Test
    public void testFavoritePost() {
        Song song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);
        Post post = new Post(song, user, false, "Test Post", "This is a test.");
        user.favoritePost(post);
        assertTrue(user.getFavoritePosts().contains(post));
    }

    @Test
    public void testRemoveFavoritePost() {
        Song song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);
        Post post = new Post(song, user, false, "Test Post", "This is a test.");
        user.favoritePost(post);
        user.removeFavoritePost(post);
        assertFalse(user.getFavoritePosts().contains(post));
    }

    @Test
    public void testIsMatch() {
        assertTrue(user.isMatch("username", "password"));
        assertFalse(user.isMatch("username", "NOTpassword"));
        assertFalse(user.isMatch("NOTusername", "password"));
    }
}