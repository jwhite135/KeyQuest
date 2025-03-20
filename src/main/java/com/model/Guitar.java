package com.model;

import java.util.ArrayList;

public class Guitar extends Instrument {
    private final String[] tuning = {"E", "A", "D", "G", "B", "E"};

    public Guitar() {
        super();
    }

    public String[] getTuning() {
        return tuning;
    }

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
