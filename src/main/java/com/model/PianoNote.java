package com.model;

import org.jfugue.player.Player;

public class PianoNote extends Note {
    private String key;
    private boolean sharp;
    private boolean flat;
    private final static Player player = new Player();

    /*  ---- Notation ----
        [Instrument]<Note><Octave><Duration>
        Instrument: Piano by default
        Note: A-G, # for sharp and b for flat
        Octave: 0-8
        Duration: Letters
            - w: whole note
            - h: half note
            - q: quarter note
            - i: eighth note
            - s: sixteenth note

        Examples of combined notation: C4q, F#5i, Ab3w
    */

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
