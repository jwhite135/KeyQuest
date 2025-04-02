package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class DataWriterTest {
    
    @Test
    public void testWritingSongs() {
        SongDatabase songDatabase = SongDatabase.getInstance();
        ArrayList<Song> ogSongs = songDatabase.getSongs();
        songDatabase.save();
        DataLoader.getSongs();
        ArrayList<Song> newSongs = songDatabase.getSongs();
        assertTrue(ogSongs.containsAll(newSongs) && newSongs.containsAll(ogSongs));
    }

    @Test
    public void testWritingUsers() {
        UserDatabase userDatabase = UserDatabase.getInstance();
        ArrayList<User> ogUsers = userDatabase.getUsers();
        userDatabase.save();
        DataLoader.getUsers();
        ArrayList<User> newUsers = userDatabase.getUsers();
        assertTrue(ogUsers.containsAll(newUsers) && newUsers.containsAll(ogUsers));
    }

    @Test
    public void testWritingPosts() {
        PostDatabase postDatabase = PostDatabase.getInstance();
        ArrayList<Post> ogPosts = postDatabase.getPosts();
        postDatabase.save();
        DataLoader.getPosts();
        ArrayList<Post> newPosts = postDatabase.getPosts();
        assertTrue(ogPosts.containsAll(newPosts) && newPosts.containsAll(ogPosts));
    }

    @Test
    public void testWritingPostComments() {
        PostDatabase postDatabase = PostDatabase.getInstance();
        ArrayList<Comment> ogComments = postDatabase.getPosts().get(0).getComments();
        postDatabase.save();
        DataLoader.getPosts();
        ArrayList<Comment> newComments = postDatabase.getPosts().get(0).getComments();
        assertTrue(ogComments.containsAll(newComments) && newComments.containsAll(ogComments));
    }

    // Test for student lessons in future but not quite implemented yet
    // Same with user friends, favorites, etc.
}