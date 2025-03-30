package com.model;

import java.util.ArrayList;

/**
 * Guitar class is a subclass of Instrument and has a tuning array that is standard for a guitar
 * @author Matthew Radin
 */
public class Guitar extends Instrument {
    private final String[] tuning = {"E", "A", "D", "G", "B", "E"};

    /**
     * Constructor for Guitar
     * Calls the super constructor for Instrument
     */
    public Guitar() {
        super();
    }

    /**
     * Getter for tuning; will be used to get the tuning of the guitar if it is to be changed
     * @return String array of the tuning of the guitar
     */
    public String[] getTuning() {
        return tuning;
    }

    /**
     * The playNote method is overridden to only play notes that are of type Tablature
     * @param note The note to be played by the guitar
     */
    @Override
    public void playNote(Note note) {
        if (note instanceof Tablature) {
            note.playNote();
        }
    }

    @Override
    public void playSong(Song song) {
        song.playSong();
    }
}
