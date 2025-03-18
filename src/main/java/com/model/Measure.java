package com.model;

import java.util.ArrayList;

import org.jfugue.player.Player;

abstract class Measure {
    protected ArrayList<Chord> chords;
    private static Player player = new Player();

    public Measure(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    public void playMeasure() {
        for (int i = 0; i < chords.size(); ++i) {
            chords.get(i).playChord();
        }
    }
}
