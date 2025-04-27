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
    public String playChord(String instrument) {
        String playThis = "";
        String output = "";
        
        // Check if there are any notes to play
        if (notes.isEmpty()) {
            // Return a rest if no notes
            player.play("Rq");
            return "Rest [q]";
        }
        
        for (int i = 0; i < notes.size(); ++i) {
            PianoNote note = (PianoNote)notes.get(i);
            String key = note.getKey();
            
            // Special handling for rest notes
            if (key.equals("R")) {
                player.play("R" + note.getLength());
                return "Rest [" + note.getLength() + "]";
            }
            
            String currentNote = key;
            
            // Make sure key has at least one character before accessing characters
            if (key.length() > 0) {
                playThis += key.charAt(0);
                
                // Check if note is flat or sharp
                if (note.isFlat()) {
                    playThis += "b";
                    currentNote += "b";
                } else if (note.isSharp()) {
                    playThis += "#";
                    currentNote += "#";
                }
                
                // Only try to access second character if key is long enough
                if (key.length() > 1) {
                    playThis += key.charAt(1);
                }
                
                if (i < notes.size() - 1) {
                    playThis += "+";
                }
                
                output += currentNote + "[" + note.getLength() + "] ";
            }
        }
        
        // Only add length from the first note if there are notes
        if (!notes.isEmpty() && notes.get(0) instanceof PianoNote) {
            playThis += ((PianoNote)notes.get(0)).getLength();
            player.play("["+instrument+"] "+playThis);
        }
        
        return output;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
