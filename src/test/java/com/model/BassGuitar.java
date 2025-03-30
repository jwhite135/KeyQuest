package com.model;

import java.util.ArrayList;

/**
 * The bassguitar class takes on the guitar class with the important distinction that it has 4 strings instead
 * The tuning is the same and currently hard-coded to be standard
 * @author Matthew Radin
 */
public class BassGuitar extends Guitar {
    private final String[] tuning = {"E", "A", "D", "G"};
    private ArrayList<Note> notes;

    public BassGuitar() {
        super();
    }

    @Override
    public String[] getTuning() {
        return tuning;
    }

    /**
     * The playNote method is overridden to only play notes that are of type Tablature
     * @param note The note to be played by the bass guitar
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
