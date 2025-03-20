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
    /*
     * In order to make chord work for guitar as well this may need changing
     * Either change this method or how guitar notes work as a whole
     */

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
