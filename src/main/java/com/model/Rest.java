package com.model;

/**
 * Rest class that extends Note and is used to represent a rest in a song
 * Rests do not make sound, so they do not play
 * Will be used in the Song class to represent a rest in a song
 * @author Ryan Leadbitter
 */
public class Rest extends Note{
    
    public Rest(String length) {
        super(length);
    }

    /**
     * Rest does not make sound, so it doesn't play.
     */
    @Override
    public void playNote() {
        return;
    }

    /**
     * Rests do not have a key, so this method is not used in the code.
     */
    @Override
    public String getKey() {
        //TODO Figure out what to do here or if this method is needed
        return null;
    }
}
