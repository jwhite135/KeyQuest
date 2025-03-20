package com.model;

public class Tablature extends Note {
    private int stringNumber;
    private int fret;

    public Tablature(int stringNumber, int fret, String length) {
        super(length);
        if (stringNumber < 1 || fret < 0) {
            throw new IllegalArgumentException("Invalid string number or fret.");
        }
        this.stringNumber = stringNumber;
        this.fret = fret;
    }

    public int getStringNumber() {
        return stringNumber;
    }

    public int getFret() {
        return fret;
    }

    public void playNote() {
       // Code for playing the note here
    }
}
