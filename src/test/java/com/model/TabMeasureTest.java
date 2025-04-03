package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class TabMeasureTest {
    private TabMeasure guitarTabMeasure;
    private Instrument guitar;
    private Instrument bass;
    private ArrayList<Chord> chords;

    @Before
    public void setUp() {
        guitar = new Instrument();
        chords = new ArrayList<>();
        chords.add(new Chord(new PianoNote("q", "A", false, false)));
        chords.add(new Chord(new PianoNote("q", "A", false, false)));

        guitarTabMeasure = new TabMeasure(guitar, chords);
    }

    @Test
    public void testNumberOfLines() {
        assertEquals(6, guitarTabMeasure.getNumberOfLines());
    }

    @Test
    public void testPlayMeasure() {
        assertNotNull(guitarTabMeasure.playMeasure());
    }
}

