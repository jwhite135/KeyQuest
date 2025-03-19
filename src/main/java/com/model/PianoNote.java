package com.model;

import org.jfugue.player.Player;

public class PianoNote extends Note {
    private String key;
    private boolean sharp;
    private boolean flat;
    private final static Player player = new Player();

    public PianoNote(String length, String key, boolean sharp, boolean flat) {
        super(length);
        this.key = key;
        this.sharp = sharp;
        this.flat = flat;
    }

    @Override
    public void playNote() {
        player.play(getNote());
    }

    @Override
    public String getNote() {
        if (sharp) {
            return key + "#" + length;
        } else if (flat) {
            return key + "b" + length;
        } else {
            return key + length.toLowerCase();
        }
    }

    public String getKey() {
        return key;
    }

    public boolean isSharp() {
        return sharp;
    }

    public boolean isFlat() {
        return flat;
    }

    public String getLength() {
        return length;
    }
}
