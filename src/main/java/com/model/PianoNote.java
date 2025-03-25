package com.model;

import org.jfugue.player.Player;

/**
 * PianoNote class that extends Note and implements playNote method
 * @author Ryan Leadbitter
 */
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

    /**
     * Constructor for PianoNote
     * @param length length of note (w, h, q, i, s)
     * @param key Piano key/name of note (A-G)
     * @param sharp true if sharp (#)
     * @param flat true if flat (b)
     */
    public PianoNote(String length, String key, boolean sharp, boolean flat) {
        super(length);
        this.key = key;
        this.sharp = sharp;
        this.flat = flat;
    }

    /**
     * Plays the note using jFugue player
     */
    @Override
    public void playNote() {
        player.play(getNote());
    }
    
    /**
     * Returns the note in jFugue notation
     * @return String
     */
    public String getNote() {
        if (sharp) {
            return key + "#" + length;
        } else if (flat) {
            return key + "b" + length;
        } else {
            return key + length.toLowerCase();
        }
    }

    /**
     * Returns the key of the note
     * @return String
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns if the note is sharp
     * @return boolean
     */
    public boolean isSharp() {
        return sharp;
    }

    /**
     * Returns if the note is flat
     * @return boolean
     */
    public boolean isFlat() {
        return flat;
    }

    /**
     * Returns the length of the note
     * @return String
     */
    @Override
    public String getLength() {
        return length;
    }
}
