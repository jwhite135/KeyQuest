package com.model;

public class PianoNote {
    private char key;
    private int octave;
    private boolean sharp;
    private boolean flat;

    public PianoNote(char key, int octave, boolean sharp, boolean flat) {
        this.key = key;
        this.octave = octave;
        this.sharp = sharp;
        this.flat = flat;
    }

    public void play() {
        
    }
}
