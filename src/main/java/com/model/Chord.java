package com.model;

import java.util.ArrayList;

public class Chord {
    private ArrayList<Note> notes;
    
    public Chord(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void playChord() {
        for (int i = 0; i < notes.size(); ++i) {
            notes.get(i).playNote();
        }
    }
}
