package com.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class TablatureTest {
    
    @Test
    public void testTablatureGuitar() {
        Tablature tablature = new Tablature(new Guitar(), 1, 5, "q");
        assertTrue(tablature.getKey().equals("G#2"));
    }

    @Test
    public void testTablatureBassGuitar() {
        Tablature tablature = new Tablature(new BassGuitar(), 2, 5, "q");
        assertTrue(tablature.getKey().equals("C#2"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testTablaturePiano() {
        Tablature tablature = new Tablature(new Piano(), 1, 5, "q");
        assertFalse(true);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testTablatureNoInstrument() {
        Tablature tablature = new Tablature(null, 1, 5, "q");
        assertFalse(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTablatureInvalidStringNumber() {
        Tablature tablature = new Tablature(new Guitar(), 7, 5, "q");
        assertFalse(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTablatureInvalidFret() {
        Tablature tablature = new Tablature(new Guitar(), 1, 25, "q");
        assertFalse(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTablatureInvalidLength() {
        Tablature tablature = new Tablature(new Guitar(), 1, 5, "invalid");
        assertFalse(true);
    }
}