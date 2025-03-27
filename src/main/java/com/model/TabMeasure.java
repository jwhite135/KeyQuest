package com.model;

import java.util.ArrayList;
import javafx.scene.control.Tab;

/**
 * The TabMeasure class is a subclass of Measure and has a number of lines that is dependent on the instrument
 * The playMeasure method will play each chord in the list of chords
 * @author Matthew Radin
 */
public class TabMeasure extends Measure {
    private int numberOfLines;

    public TabMeasure(Instrument instrument, ArrayList<Chord> chords) {
        super(chords);
        this.numberOfLines = instrument.getName().equalsIgnoreCase("Bassguitar") ? 4 : 6;
    }

    /**
     * Guitar notes are converted to jfugue notes and played using the correct soundpack
     * The notes are played in the order they are in the chord
    * @return 
    */
    public String playMeasure() {
        String output = "";
        for (Chord chord : chords) {
        output += chord.playChord();
        }
        return output + "\n---------------------------------------------\n";
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }
}
