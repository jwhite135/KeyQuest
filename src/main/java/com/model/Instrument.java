package com.model;

public class Instrument {
    private String name;
    private Note minNote;
    private Note maxNote;

    public Instrument() {
        this.name = "Piano";
        this.minNote = null;
        this.maxNote = null;
    }

    public void playNote(Note note) {
        return;
    }

    public void playSong(Song song) {
        return;
    }

    public String getName() {
        return name;
    }
}
