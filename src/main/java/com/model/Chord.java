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
     * Constructor for single note cord
     * @param note single note
     */
    public Chord(Note note) {
        notes = new ArrayList<Note>();
        notes.add(note);
    }

    /**
     * Plays the chord using jFugue player
     * Loops through notes and plays them
     * Adds each note to a string that is used an as argument by the player
     */
    public void playChord() {
        String playThis = "";
        for (int i = 0; i < notes.size(); ++i) {
            String currentNote = ((PianoNote)notes.get(i)).getKey();
            playThis += ((PianoNote)notes.get(i)).getKey().charAt(0);
            if(((PianoNote)notes.get(i)).isFlat()) {
                currentNote += "b";
                playThis += currentNote;
            }
            if(((PianoNote)notes.get(i)).isSharp()) {
                currentNote += "#";
                playThis += currentNote;
            }
            playThis += ((PianoNote)notes.get(i)).getKey().charAt(1);
            if (i < notes.size() - 1) {
                playThis += "+";
            }
            System.out.print(currentNote + "[" + ((PianoNote)notes.get(i)).getLength() + "] ");
        }
        playThis += ((PianoNote)notes.get(0)).getLength();
        player.play(playThis);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
