package com.model;

import java.util.ArrayList;

/**
 * Measure class that holds a list of chords
 * @author Ryan Leadbitter
 */
public abstract class Measure {
    protected ArrayList<Chord> chords;
    

    /**
     * Constructor for Measure
     * @param chords list of chords
     */
    public Measure(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    /**
     * Method to play the measure
     * Loops through the chords and plays them
     */
    public String playMeasure() {
        for (int i = 0; i < chords.size(); i++) {
            chords.get(i).playChord();
        }
        return "\n---------------------------------------------\n";
    }

    /**
     * Method to get the chords list
     * @return ArrayList<Chord>
     */
    public ArrayList<Chord> getChords() {
        return chords;
    }
}
