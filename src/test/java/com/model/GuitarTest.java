package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GuitarTest {
    private Guitar guitar;
    
    @Before
    public void setUp() {
        guitar = new Guitar();
    }
    
    @Test
    public void testGetTuning() {
        String[] expectedTuning = {"E", "A", "D", "G", "B", "E"};
        assertArrayEquals(expectedTuning, guitar.getTuning());
    }
}