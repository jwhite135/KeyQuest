package com.model;

import java.util.ArrayList;

import org.jfugue.player.Player;

/**
 * Chord class that holds a list of notes
 * @author Ryan Leadbitter
 */
public class Chord {
    private ArrayList<Note> notes;
    private static Player player = new Player();
    
    /**
     * Constructor for Chord
     * @param notes list of notes
     */
    public Chord(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * Plays the chord using jFugue player
     * Loops through notes and plays them
     * Adds each note to a string that is used an as argument by the player
     */
    public void playChord() {
        String playThis = "";
        for (int i = 0; i < notes.size(); ++i) {
            playThis += ((PianoNote)notes.get(i)).getKey().charAt(0);
            if(((PianoNote)notes.get(i)).isFlat()) {
                playThis += "b";
            }
            if(((PianoNote)notes.get(i)).isSharp()) {
                playThis += "#";
            }
            playThis += ((PianoNote)notes.get(i)).getKey().charAt(1);
            if (i < notes.size() - 1) {
                playThis += "+";
            }
        }
        playThis += ((PianoNote)notes.get(0)).getLength();
        System.out.println("playing " + playThis);
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
