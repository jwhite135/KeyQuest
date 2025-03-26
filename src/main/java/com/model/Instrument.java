package com.model;

/**
 * Instrument class that is the superclass of all instruments
 * Currently only has a name, minNote, and maxNote
 * Will be used to play notes and songs
 * On the chopping block for future iterations
 * @author Owen Coulam
 */
public class Instrument {
    private String name;
    private Note minNote;
    private Note maxNote;

    public Instrument() {
        this.name = "Piano";
        this.minNote = null;
        this.maxNote = null;
    }

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
