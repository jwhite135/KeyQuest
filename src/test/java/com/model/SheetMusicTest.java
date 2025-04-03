package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SheetMusicTest {
    private SheetMusic sheetMusic;
    private Measure measure1;
    private Measure measure2;

    @Before
    public void setUp() {
        measure1 = new PianoMeasure(false, new ArrayList<Chord>());
        measure2 = new PianoMeasure(false, new ArrayList<Chord>());
        ArrayList<Measure> measures = new ArrayList<>();
        measures.add(measure1);
        measures.add(measure2);
        
        sheetMusic = new SheetMusic(100, 3, 4, measures);
    }

    @Test
    public void testDefaultConstructor() {
        SheetMusic defaultSheetMusic = new SheetMusic();
        assertEquals(120, defaultSheetMusic.getTempo());
        assertEquals(4, defaultSheetMusic.getTimeSigNum());
        assertEquals(4, defaultSheetMusic.getTimeSigDen());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(100, sheetMusic.getTempo());
        assertEquals(3, sheetMusic.getTimeSigNum());
        assertEquals(4, sheetMusic.getTimeSigDen());
        assertEquals(2, sheetMusic.getMeasures().size());
    }

    @Test
    public void testPlayMeasures() {
        assertNotNull(sheetMusic.playMeasures());
    }

    @Test
    public void testAddMeasure() {
        Measure measure3 = new PianoMeasure(false, new ArrayList<Chord>());
        sheetMusic.addMeasure(measure3);
        assertEquals(3, sheetMusic.getMeasures().size());
        assertEquals(measure3, sheetMusic.getMeasures().get(2));
    }
}