package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class PostDatabaseTest {

    private PostDatabase postDatabase;
    private User user;
    private Song song;

    @Before
    public void setUp() {
        postDatabase = PostDatabase.getInstance();
        postDatabase.getPosts().clear(); // Clear the database before each test
        user = new User("testUser", "password", "test@example.com");
        song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
    }

    @Test
    public void testAddPost() {
        Post post = new Post(song, user, false, "Test Post", "This is a test post.");
        postDatabase.addPost(post);
        assertTrue("Post should be added to the database", postDatabase.getPosts().contains(post));
    }

    @Test
    public void testSearchByUser() {
        Post post = new Post(song, user, false, "Test Post", "This is a test post.");
        postDatabase.addPost(post);

        ArrayList<Post> results = postDatabase.searchByUser("testUser");
        assertEquals("Search should return one post", 1, results.size());
        assertEquals("Search should return the correct post", post, results.get(0));
    }

    @Test
    public void testSortByMostRecent() {
        Post post1 = new Post(song, user, false, "Post 1", "Body 1");
        Post post2 = new Post(song, user, false, "Post 2", "Body 2");
        postDatabase.addPost(post1);
        postDatabase.addPost(post2);

        ArrayList<Post> sortedPosts = postDatabase.sortByMostRecent();
        assertEquals("Most recent post should be first", post2, sortedPosts.get(0));
    }
}
