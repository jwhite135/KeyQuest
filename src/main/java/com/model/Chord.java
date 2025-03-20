package com.model;

import java.util.ArrayList;
import org.jfugue.player.Player;

public class Chord {
    private ArrayList<Note> notes;
    private static Player player = new Player();
    
    public Chord(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void playChord() {
        String playThis = "";
        for (int i = 0; i < notes.size(); ++i) {
            playThis += ((PianoNote)notes.get(i)).getKey();
            if (i < notes.size() - 1) {
                playThis += "+";
            }
        }
        playThis += ((PianoNote)notes.get(0)).getLength();
        player.play(playThis);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
