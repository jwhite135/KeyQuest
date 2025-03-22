package com.model;

import org.jfugue.player.Player;

public class Tablature extends Note {
    private int stringNumber;
    private int fret;
    private String length;
    private String noteData; // Includes octave
    private final static Player player = new Player();

    public Tablature(int stringNumber, int fret, String length) {
        super(length);
        if (stringNumber < 1 || fret < 0 || stringNumber > 6 || fret > 24) {
            throw new IllegalArgumentException("Invalid string number or fret.");
        }
        this.stringNumber = stringNumber;
        this.fret = fret;
        // TO DO- Add conversion implementation
    }

    public int getStringNumber() {
        return stringNumber;
    }

    public int getFret() {
        return fret;
    }

    public String getNote() {
        return noteData;
    }

    public String getLength() {
        return length;
    }

    @Override
    public void playNote() {
        player.play(getNote());
    }
}
