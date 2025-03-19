package com.model;

import java.util.ArrayList;

public class PianoMeasure extends Measure {
    private boolean showClef;

    public PianoMeasure(boolean showClef, ArrayList<Chord> chords) {
        super(chords);
        this.showClef = showClef;
    }
    
    @Override
    public void playMeasure() {
        for (int i = 0; i < chords.size(); ++i) {
            chords.get(i).playChord();
        }
    }

    public boolean showClef() {
        return showClef;
    }

    public void setShowClef(boolean showClef) {
        this.showClef = showClef;
    }
}
