package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class DataLoaderTest {
    
    @Test
    public void testLoadingSongs() {
        SongDatabase songDatabase = SongDatabase.getInstance();
        assertNotNull(songDatabase.getSongs());
    }

    @Test
    public void testLoadingUsers() {
        UserDatabase userDatabase = UserDatabase.getInstance();
        assertNotNull(userDatabase.getUsers());
    }

    @Test
    public void testLoadingPosts() {
        PostDatabase postDatabase = PostDatabase.getInstance();
        assertNotNull(postDatabase.getPosts());
    }

    @Test
    public void testLoadingSongContents() {
        SongDatabase songDatabase = SongDatabase.getInstance();
        ArrayList<Song> songs = songDatabase.getSongs();
        assertTrue(songs.get(0).getName().equals("Oriental Riff"));
    }

    @Test
    public void testLoadingUserContents() {
        UserDatabase userDatabase = UserDatabase.getInstance();
        ArrayList<User> users = userDatabase.getUsers();
        assertTrue(users.get(0).getUsername().equals("josiahGrey"));
    }

    @Test
    public void testLoadingPostContents() {
        PostDatabase postDatabase = PostDatabase.getInstance();
        ArrayList<Post> posts = postDatabase.getPosts();
        assertTrue(posts.get(0).getTitle().equals("Bohemian Rhapsody"));
    }

    @Test
    public void testLoadingPostComments() {
        PostDatabase postDatabase = PostDatabase.getInstance();
        ArrayList<Post> posts = postDatabase.getPosts();
        assertTrue(posts.get(0).getComments().get(0).getBody().equals("HOLY SHNIKES THIS APP is ok i guess..."));
    }

    // Test for user lessons in future but not quite implemented yet

    // Could test for null/empty json but hard to do in this case - also should never happen
}