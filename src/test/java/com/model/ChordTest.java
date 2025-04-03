package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class ChordTest {
    Chord chord1;
    Chord chord2;

    @Before
    public void setUp() {
        // Initialize the Chord object with a single note for testing
        Note note = new PianoNote("w", "G4", false, false); // Example note
        chord1 = new Chord(note);
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new PianoNote("q", "G4", false, false));
        notes.add(new PianoNote("q", "B4", false, true));
        notes.add(new PianoNote("q", "D5", false, false));
        chord2 = new Chord(notes);
    }

    @Test
    public void testConstructors() {
        assertNotNull("Chord object should not be null", chord1);
        assertNotNull("Chord object should not be null", chord2);
    }

    @Test
    public void testPlayChord() {
        String output1 = chord1.playChord();
        String output2 = chord2.playChord();
        assertNotNull("Playing the chord should return a non-null result", output1);
        assertNotNull("Playing the chord should return a non-null result", output2);
    }
}