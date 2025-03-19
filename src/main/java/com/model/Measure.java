package com.model;

import java.util.ArrayList;

abstract class Measure {
    protected ArrayList<Chord> chords;

    public Measure(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    public void playMeasure() {
        for (int i = 0; i < chords.size(); ++i) {
            chords.get(i).playChord();
        }
    }
}
