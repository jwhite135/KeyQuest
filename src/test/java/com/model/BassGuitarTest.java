package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BassGuitarTest {
    private BassGuitar bassGuitar;
    
    @Before
    public void setUp() {
        bassGuitar = new BassGuitar();
    }
    
    @Test
    public void testGetTuning() {
        String[] expectedTuning = {"E", "A", "D", "G"};
        assertArrayEquals(expectedTuning, bassGuitar.getTuning());
    }
}