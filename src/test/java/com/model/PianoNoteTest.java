package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


public class PianoNoteTest {
    PianoNote cNote;

    @Before
    public void setUp() {
        cNote = new PianoNote("w", "B3", false, true);
    }
    
    @Test
    public void testPianoNote() {
        assertNotNull(cNote);
    }

    @Test
    public void testNoteCreation() {
        assertEquals("B3", cNote.getKey());
        assertEquals("w", cNote.getLength());
        assertEquals(false, cNote.isSharp());
        assertEquals(true, cNote.isFlat());
    }
}