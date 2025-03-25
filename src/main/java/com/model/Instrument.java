package com.model;

/**
 * Instrument class that represents a musical instrument.
 * @author Ryan Leadbitter
 */
public class Instrument {
    private String name;
    private Note minNote;
    private Note maxNote;

    /**
     * Default onstructor for Instrument class.
     * Sets the name of the instrument to "Piano".
     * Sets the minNote and maxNote to null.
     */
    public Instrument() {
        this.name = "Piano";
        this.minNote = null;
        this.maxNote = null;
    }

    /**
     * 
     * @param note
     */
    public void playNote(Note note) {
        return;
    }

    public void playSong(Song song) {
        return;
    }

    public String getName() {
        return name;
    }
}
