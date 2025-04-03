package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class SongDatabaseTest {

    private SongDatabase songDatabase;

    @Before
    public void setUp() {
        songDatabase = SongDatabase.getInstance();
        songDatabase.getSongs().clear();
    }

    @Test
    public void testAddSong() {
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        songDatabase.addSong(song);
        assertTrue("Song should be added to the database", songDatabase.getSongs().contains(song));
    }

    @Test
    public void testSearchByName() {
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        songDatabase.addSong(song);

        ArrayList<Song> results = songDatabase.searchByName("Test Song");
        assertEquals("Search should return one song", 1, results.size());
        assertEquals("Search should return the correct song", song, results.get(0));
    }

    @Test
    public void testSearchByArtist() {
        Song song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
        songDatabase.addSong(song);

        ArrayList<Song> results = songDatabase.searchByArtist("Test Artist");
        assertEquals("Search should return one song", 1, results.size());
        assertEquals("Search should return the correct song", song, results.get(0));
    }
}
