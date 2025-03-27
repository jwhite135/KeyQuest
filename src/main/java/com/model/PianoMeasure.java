package com.model;

import java.util.ArrayList;

/**
 * PianoMeasure class that extends Measure
 * @author Ryan Leadbitter
 */
public class PianoMeasure extends Measure {
    private boolean showClef;

    /**
     * Constructor for PianoMeasure
     * @param showClef boolean to show clef
     * @param chords list of chords
     * Calls Measure super constructor
     */
    public PianoMeasure(boolean showClef, ArrayList<Chord> chords) {
        super(chords);
        this.showClef = showClef;
    }
    
    /**
     * Method to play the measure
     * Loops through the chords and plays them using playChord()
     */
    @Override
    public void playMeasure() {
        for (int i = 0; i < chords.size(); ++i) {
            chords.get(i).playChord();
        }
        System.out.println("\n---------------------------------------------");
    }

    /**
     * Method to get the showClef boolean
     * @return boolean
     */
    public boolean getShowClef() {
        return showClef;
    }

    /**
     * Method to set the showClef boolean
     * @param showClef boolean value to set or unset
     */
    public void setShowClef(boolean showClef) {
        this.showClef = showClef;
    }
}
