package com.model;

import java.util.ArrayList;


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
