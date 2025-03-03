package com.model;

import java.util.ArrayList;

public class PianoNote extends Note {
    private char key;
    private int octave;
    private boolean sharp;
    private boolean flat;

    public PianoNote(int length, char key, int octave, boolean sharp, boolean flat) {
        super(length);
        this.key = key;
        this.octave = octave;
        this.sharp = sharp;
        this.flat = flat;
    }

    public void play() {

    }
}
