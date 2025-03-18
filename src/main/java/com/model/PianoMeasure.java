package com.model;

import java.util.ArrayList;

public class PianoMeasure extends Measure {
    private boolean showClef;

    public PianoMeasure(boolean showClef, ArrayList<Chord> chords) {
        super(chords);
        this.showClef = showClef;
    }
    
    public void playMeasure() {
        for (int i = 0; i < chords.size(); ++i) {
            chords.get(i).playChord();
        }
    }
}
