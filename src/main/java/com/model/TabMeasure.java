package com.model;

import java.util.ArrayList;
import javafx.scene.control.Tab;

public class TabMeasure extends Measure {
    private int numberOfLines;

    public TabMeasure(Instrument instrument, ArrayList<Chord> chords) {
        super(chords);
        this.numberOfLines = instrument.getName().equalsIgnoreCase("Bassguitar") ? 4 : 6;
    }

    @Override
    public void playMeasure() {
        for (Chord chord : chords) {
            chord.playChord();
        }
    }
}
