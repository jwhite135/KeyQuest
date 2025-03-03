package com.model;

abstract class Measure {
    private ArrayList<Chord> chords;

    public Measure(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    public void playChord() {
        
    }
}
