package com.model;

import java.util.ArrayList;

public class PianoMeasure extends Measure {
    private boolean showClef;

    public PianoMeasure(boolean showClef, ArrayList<Chord> chords) {
        super(chords);
        this.showClef = showClef;
    }
    
    public void playMeasure() {

    }
}
