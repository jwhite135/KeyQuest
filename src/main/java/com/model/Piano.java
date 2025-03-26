package com.model;

import java.util.ArrayList;

/**
 * Piano class that holds a list of notes, along with the min and max notes that can be played
 * @author Ryan Leadbitter
 */
public class Piano extends Instrument {
    private ArrayList<PianoNote> notes;
    private PianoNote minNote;
    private PianoNote maxNote;

    public Piano() {

    }

    public void displayPiano() {

    }

    public void playNote(Note note) {

    }

    public void playSong(Song song) {
        song.playSong();
    }
}
