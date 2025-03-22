package com.model;

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
}
