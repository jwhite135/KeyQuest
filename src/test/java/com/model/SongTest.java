package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class SongTest {

    private Song song;

    @Before
    public void setUp() {
        song = new Song("Test Song", "Test Artist", 1, "Pop", 4, 4, 120);
    }

    @Test
    public void testAddMeasure() {
        Measure measure = new Measure(new ArrayList<>()) {};
        song.addMeasure(measure);
        assertTrue("Measure should be added to the song", song.getSheetMusic().get(0).getMeasures().contains(measure));
    }

    @Test
    public void testPlaySong() {
        String result = song.playSong();
        assertNotNull("Playing the song should return a non-null result", result);
    }
}
