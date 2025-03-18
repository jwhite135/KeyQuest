package com.model;

import org.jfugue.player.Player;

public class PianoNote extends Note {
    private String key;
    private boolean sharp;
    private boolean flat;
    private static Player player = new Player();

    public PianoNote(int length, String key, boolean sharp, boolean flat) {
        super(length);
        this.key = key;
        this.sharp = sharp;
        this.flat = flat;
    }

    public void playNote() {
        player.play(key);
    }
}
