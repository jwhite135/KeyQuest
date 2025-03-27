package com.model;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * PianoMeasure class that extends Measure
 * @author Ryan Leadbitter
 */
public class PianoMeasure extends Measure {
    private boolean showClef;
    private PrintStream fileStream;

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
    public String playMeasure() {
        String output = "";
        for (int i = 0; i < chords.size(); ++i) {
            output += chords.get(i).playChord();
        }
        return output +"\n---------------------------------------------\n";
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
