package com.model;

import java.util.ArrayList;

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

    public void displayBassGuitar() {

    }

    public void playNote(Note note) {

    }

    public void playSong(Song song) {
        song.playSong();
    }
}
