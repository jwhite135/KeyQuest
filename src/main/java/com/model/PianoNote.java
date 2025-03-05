package com.model;

public class PianoNote extends Note {
    private String key;
    private boolean sharp;
    private boolean flat;

    public PianoNote(int length, String key, boolean sharp, boolean flat) {
        super(length);
        this.key = key;
        this.sharp = sharp;
        this.flat = flat;
    }

    public void play() {

    }
}
