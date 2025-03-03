package com.model;

import java.util.ArrayList;

abstract class Measure {
    private ArrayList<Chord> chords;

    public Measure(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    public void playChord() {
        
    }
}
