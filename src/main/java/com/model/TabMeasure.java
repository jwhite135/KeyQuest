package com.model;

import java.util.ArrayList;
import javafx.scene.control.Tab;

public class TabMeasure extends Measure {
    private int numberOfLines;

    public TabMeasure(Instrument instrument, ArrayList<Chord> chords) {
        super(chords);
        this.numberOfLines = instrument.getName().equalsIgnoreCase("Bassguitar") ? 4 : 6;
    }

    /**
     * This method needs to be evaluated for change, as chord currently runs on piano notes.
     * Guitar notes can be played as piano notes for the sound if needed.
     */
    public void playMeasure() {
        for (Chord chord : chords) {
            chord.playChord();
        }
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }
}
