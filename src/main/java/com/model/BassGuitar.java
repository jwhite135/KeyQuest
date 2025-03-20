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

    public void playNote(Note note) {
        if (note instanceof Tablature) {
            note.playNote();
        }
    }

    public void playSong(Song song) {
        song.playSong();
    }
}
