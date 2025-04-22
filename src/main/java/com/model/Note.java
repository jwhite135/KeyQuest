package com.model;

/**
 * Note class that will be used to hold the information of a note object
 * Will be used in the Chord class to play notes
 * @author Ryan Leadbitter
 */
public abstract class Note {
    protected String length;

    public Note(String length) {
        this.length = length;
    }

    /**
     * Constructor for Note
     * Quarter note by default
     */
    public Note() {
        length = "q";
    }

    public abstract void playNote();

    public String getLength() {
        return length;
    }
    
    public abstract String getKey();

    public abstract boolean isSharp();
    public abstract boolean isFlat();
    
}
