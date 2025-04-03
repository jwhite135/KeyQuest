package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


public class PianoMeasureTest {
    private PianoMeasure measure;
    
    @Before
    public void setUp() {
        ArrayList<Chord> chords = new ArrayList<>();
        ArrayList<Note> cMajor = new ArrayList<>();
        cMajor.add(new PianoNote("q", "C3", false, false));
        cMajor.add(new PianoNote("q", "E3", false, false));
        cMajor.add(new PianoNote("q", "G3", false, false));
        chords.add(new Chord(cMajor));
        ArrayList<Note> gMajor = new ArrayList<>();
        gMajor.add(new PianoNote("q", "G3", false, false));
        gMajor.add(new PianoNote("q", "B3", false, false));
        gMajor.add(new PianoNote("q", "D4", false, false));
        chords.add(new Chord(gMajor));
        ArrayList<Note> aMinor = new ArrayList<>();
        aMinor.add(new PianoNote("q", "A3", false, false));
        aMinor.add(new PianoNote("q", "C4", false, false));
        aMinor.add(new PianoNote("q", "F4", false, false));
        chords.add(new Chord(aMinor));
        ArrayList<Note> fMajor = new ArrayList<>();
        fMajor.add(new PianoNote("q", "F4", false, false));
        fMajor.add(new PianoNote("q", "A4", false, false));
        fMajor.add(new PianoNote("q", "C5", false, false));
        chords.add(new Chord(fMajor));
        measure = new PianoMeasure(false, chords);
    }

    @Test
    public void testPianoMeasureConstructor() {
        assertNotNull(measure);
    }

    @Test
    public void testMeasureCreation() {
        for (Chord chord : measure.getChords()) {
            assertNotNull(chord);
            for (Note note : chord.getNotes()) {
                assertNotNull(note);
            }
        }
    }

    @Test
    public void testPlayingPianoMeasure() {
        String output = measure.playMeasure();
        assertNotNull(output);
    }
}