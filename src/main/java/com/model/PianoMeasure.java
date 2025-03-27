package com.model;

import java.io.FileNotFoundException;
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
        try {
            this.fileStream = new PrintStream("../../../../song_output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
<<<<<<< HEAD
        return output +"\n---------------------------------------------\n";
=======
        System.out.println("\n---------------------------------------------");
>>>>>>> 0da54707bc6df5bb9a0fd58a05b24e7323f7ab56
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
