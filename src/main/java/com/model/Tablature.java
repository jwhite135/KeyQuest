package com.model;

import org.jfugue.player.Player;

public class Tablature extends Note {
    private int stringNumber;
    private int fret;
    private String length;
    private String key; // Includes octave
    private Instrument Instrument;
    private final static Player player = new Player();

    /**
     * Constructor for Tablature
     * Determines if the instrument is a guitar or bass guitar
     * Takes the fret and string number for the guitar and converts it to piano notation for the player
     * @param stringNumber the string number of the guitar note
     * @param fret the fret number of the guitar note
     * @param length the length of the note
     */
    public Tablature(Instrument instrument, int stringNumber, int fret, String length) {
        super(length);
        if (fret < 0 || fret > 24) {
            throw new IllegalArgumentException("Invalid fret.");
        }

        if (Instrument instanceof Guitar) {
            if (stringNumber < 1 || stringNumber > 6) {
                throw new IllegalArgumentException("Invalid string number for Guitar. Must be between 1 and 6.");
            }
        } else if (Instrument instanceof BassGuitar) {
            if (stringNumber < 1 || stringNumber > 4) {
                throw new IllegalArgumentException("Invalid string number for Bass Guitar. Must be between 1 and 4.");
            }
        } else {
            throw new IllegalArgumentException("Unsupported instrument type. Only Guitar and BassGuitar are supported.");
        }

        this.stringNumber = stringNumber;
        this.fret = fret;

        if (Instrument instanceof Guitar) {
            if (stringNumber == 1 && fret == 0) { key = "E2"; }
        else if (stringNumber == 1 && fret == 1) { key = "F2"; }
        else if (stringNumber == 1 && fret == 2) { key = "F#2"; }
        else if (stringNumber == 1 && fret == 3) { key = "G2"; }
        else if (stringNumber == 1 && fret == 4) { key = "G#2"; }
        else if (stringNumber == 1 && fret == 5) { key = "A2"; }
        else if (stringNumber == 1 && fret == 6) { key = "A#2"; }
        else if (stringNumber == 1 && fret == 7) { key = "B2"; }
        else if (stringNumber == 1 && fret == 8) { key = "C3"; }
        else if (stringNumber == 1 && fret == 9) { key = "C#3"; }
        else if (stringNumber == 1 && fret == 10) { key = "D3"; }
        else if (stringNumber == 1 && fret == 11) { key = "D#3"; }
        else if (stringNumber == 1 && fret == 12) { key = "E3"; }
        else if (stringNumber == 1 && fret == 13) { key = "F3"; }
        else if (stringNumber == 1 && fret == 14) { key = "F#3"; }
        else if (stringNumber == 1 && fret == 15) { key = "G3"; }
        else if (stringNumber == 1 && fret == 16) { key = "G#3"; }
        else if (stringNumber == 1 && fret == 17) { key = "A3"; }
        else if (stringNumber == 1 && fret == 18) { key = "A#3"; }
        else if (stringNumber == 1 && fret == 19) { key = "B3"; }
        else if (stringNumber == 1 && fret == 20) { key = "C4"; }
        else if (stringNumber == 1 && fret == 21) { key = "C#4"; }
        else if (stringNumber == 1 && fret == 22) { key = "D4"; }
        else if (stringNumber == 1 && fret == 23) { key = "D#4"; }
        else if (stringNumber == 1 && fret == 24) { key = "E4"; }
        else if (stringNumber == 2 && fret == 0) { key = "A2"; }
        else if (stringNumber == 2 && fret == 1) { key = "A#2"; }
        else if (stringNumber == 2 && fret == 2) { key = "B2"; }
        else if (stringNumber == 2 && fret == 3) { key = "C3"; }
        else if (stringNumber == 2 && fret == 4) { key = "C#3"; }
        else if (stringNumber == 2 && fret == 5) { key = "D3"; }
        else if (stringNumber == 2 && fret == 6) { key = "D#3"; }
        else if (stringNumber == 2 && fret == 7) { key = "E3"; }
        else if (stringNumber == 2 && fret == 8) { key = "F3"; }
        else if (stringNumber == 2 && fret == 9) { key = "F#3"; }
        else if (stringNumber == 2 && fret == 10) { key = "G3"; }
        else if (stringNumber == 2 && fret == 11) { key = "G#3"; }
        else if (stringNumber == 2 && fret == 12) { key = "A3"; }
        else if (stringNumber == 2 && fret == 13) { key = "A#3"; }
        else if (stringNumber == 2 && fret == 14) { key = "B3"; }
        else if (stringNumber == 2 && fret == 15) { key = "C4"; }
        else if (stringNumber == 2 && fret == 16) { key = "C#4"; }
        else if (stringNumber == 2 && fret == 17) { key = "D4"; }
        else if (stringNumber == 2 && fret == 18) { key = "D#4"; }
        else if (stringNumber == 2 && fret == 19) { key = "E4"; }
        else if (stringNumber == 2 && fret == 20) { key = "F4"; }
        else if (stringNumber == 2 && fret == 21) { key = "F#4"; }
        else if (stringNumber == 2 && fret == 22) { key = "G4"; }
        else if (stringNumber == 2 && fret == 23) { key = "G#4"; }
        else if (stringNumber == 2 && fret == 24) { key = "A4"; }
        else if (stringNumber == 3 && fret == 0) { key = "D3"; }
        else if (stringNumber == 3 && fret == 1) { key = "D#3"; }
        else if (stringNumber == 3 && fret == 2) { key = "E3"; }
        else if (stringNumber == 3 && fret == 3) { key = "F3"; }
        else if (stringNumber == 3 && fret == 4) { key = "F#3"; }
        else if (stringNumber == 3 && fret == 5) { key = "G3"; }
        else if (stringNumber == 3 && fret == 6) { key = "G#3"; }
        else if (stringNumber == 3 && fret == 7) { key = "A3"; }
        else if (stringNumber == 3 && fret == 8) { key = "A#3"; }
        else if (stringNumber == 3 && fret == 9) { key = "B3"; }
        else if (stringNumber == 3 && fret == 10) { key = "C4"; }
        else if (stringNumber == 3 && fret == 11) { key = "C#4"; }
        else if (stringNumber == 3 && fret == 12) { key = "D4"; }
        else if (stringNumber == 3 && fret == 13) { key = "D#4"; }
        else if (stringNumber == 3 && fret == 14) { key = "E4"; }
        else if (stringNumber == 3 && fret == 15) { key = "F4"; }
        else if (stringNumber == 3 && fret == 16) { key = "F#4"; }
        else if (stringNumber == 3 && fret == 17) { key = "G4"; }
        else if (stringNumber == 3 && fret == 18) { key = "G#4"; }
        else if (stringNumber == 3 && fret == 19) { key = "A4"; }
        else if (stringNumber == 3 && fret == 20) { key = "A#4"; }
        else if (stringNumber == 3 && fret == 21) { key = "B4"; }
        else if (stringNumber == 3 && fret == 22) { key = "C5"; }
        else if (stringNumber == 3 && fret == 23) { key = "C#5"; }
        else if (stringNumber == 3 && fret == 24) { key = "D5"; }
        else if (stringNumber == 4 && fret == 0) { key = "G3"; }
        else if (stringNumber == 4 && fret == 1) { key = "G#3"; }
        else if (stringNumber == 4 && fret == 2) { key = "A3"; }
        else if (stringNumber == 4 && fret == 3) { key = "A#3"; }
        else if (stringNumber == 4 && fret == 4) { key = "B3"; }
        else if (stringNumber == 4 && fret == 5) { key = "C4"; }
        else if (stringNumber == 4 && fret == 6) { key = "C#4"; }
        else if (stringNumber == 4 && fret == 7) { key = "D4"; }
        else if (stringNumber == 4 && fret == 8) { key = "D#4"; }
        else if (stringNumber == 4 && fret == 9) { key = "E4"; }
        else if (stringNumber == 4 && fret == 10) { key = "F4"; }
        else if (stringNumber == 4 && fret == 11) { key = "F#4"; }
        else if (stringNumber == 4 && fret == 12) { key = "G4"; }
        else if (stringNumber == 4 && fret == 13) { key = "G#4"; }
        else if (stringNumber == 4 && fret == 14) { key = "A4"; }
        else if (stringNumber == 4 && fret == 15) { key = "A#4"; }
        else if (stringNumber == 4 && fret == 16) { key = "B4"; }
        else if (stringNumber == 4 && fret == 17) { key = "C5"; }
        else if (stringNumber == 4 && fret == 18) { key = "C#5"; }
        else if (stringNumber == 4 && fret == 19) { key = "D5"; }
        else if (stringNumber == 4 && fret == 20) { key = "D#5"; }
        else if (stringNumber == 4 && fret == 21) { key = "E5"; }
        else if (stringNumber == 4 && fret == 22) { key = "F5"; }
        else if (stringNumber == 4 && fret == 23) { key = "F#5"; }
        else if (stringNumber == 4 && fret == 24) { key = "G5"; }
        else if (stringNumber == 5 && fret == 0) { key = "B3"; }
        else if (stringNumber == 5 && fret == 1) { key = "C4"; }
        else if (stringNumber == 5 && fret == 2) { key = "C#4"; }
        else if (stringNumber == 5 && fret == 3) { key = "D4"; }
        else if (stringNumber == 5 && fret == 4) { key = "D#4"; }
        else if (stringNumber == 5 && fret == 5) { key = "E4"; }
        else if (stringNumber == 5 && fret == 6) { key = "F4"; }
        else if (stringNumber == 5 && fret == 7) { key = "F#4"; }
        else if (stringNumber == 5 && fret == 8) { key = "G4"; }
        else if (stringNumber == 5 && fret == 9) { key = "G#4"; }
        else if (stringNumber == 5 && fret == 10) { key = "A4"; }
        else if (stringNumber == 5 && fret == 11) { key = "A#4"; }
        else if (stringNumber == 5 && fret == 12) { key = "B4"; }
        else if (stringNumber == 5 && fret == 13) { key = "C5"; }
        else if (stringNumber == 5 && fret == 14) { key = "C#5"; }
        else if (stringNumber == 5 && fret == 15) { key = "D5"; }
        else if (stringNumber == 5 && fret == 16) { key = "D#5"; }
        else if (stringNumber == 5 && fret == 17) { key = "E5"; }
        else if (stringNumber == 5 && fret == 18) { key = "F5"; }
        else if (stringNumber == 5 && fret == 19) { key = "F#5"; }
        else if (stringNumber == 5 && fret == 20) { key = "G5"; }
        else if (stringNumber == 5 && fret == 21) { key = "G#5"; }
        else if (stringNumber == 5 && fret == 22) { key = "A5"; }
        else if (stringNumber == 5 && fret == 23) { key = "A#5"; }
        else if (stringNumber == 5 && fret == 24) { key = "B5"; }
        else if (stringNumber == 6 && fret == 0) { key = "E4"; }
        else if (stringNumber == 6 && fret == 1) { key = "F4"; }
        else if (stringNumber == 6 && fret == 2) { key = "F#4"; }
        else if (stringNumber == 6 && fret == 3) { key = "G4"; }
        else if (stringNumber == 6 && fret == 4) { key = "G#4"; }
        else if (stringNumber == 6 && fret == 5) { key = "A4"; }
        else if (stringNumber == 6 && fret == 6) { key = "A#4"; }
        else if (stringNumber == 6 && fret == 7) { key = "B4"; }
        else if (stringNumber == 6 && fret == 8) { key = "C5"; }
        else if (stringNumber == 6 && fret == 9) { key = "C#5"; }
        else if (stringNumber == 6 && fret == 10) { key = "D5"; }
        else if (stringNumber == 6 && fret == 11) { key = "D#5"; }
        else if (stringNumber == 6 && fret == 12) { key = "E5"; }
        else if (stringNumber == 6 && fret == 13) { key = "F5"; }
        else if (stringNumber == 6 && fret == 14) { key = "F#5"; }
        else if (stringNumber == 6 && fret == 15) { key = "G5"; }
        else if (stringNumber == 6 && fret == 16) { key = "G#5"; }
        else if (stringNumber == 6 && fret == 17) { key = "A5"; }
        else if (stringNumber == 6 && fret == 18) { key = "A#5"; }
        else if (stringNumber == 6 && fret == 19) { key = "B5"; }
        else if (stringNumber == 6 && fret == 20) { key = "C6"; }
        else if (stringNumber == 6 && fret == 21) { key = "C#6"; }
        else if (stringNumber == 6 && fret == 22) { key = "D6"; }
        else if (stringNumber == 6 && fret == 23) { key = "D#6"; }
        else if (stringNumber == 6 && fret == 24) { key = "E6"; }
        } else if (Instrument instanceof BassGuitar) {
            if (stringNumber == 1 && fret == 0) { key = "E1"; }
        else if (stringNumber == 1 && fret == 1) { key = "F1"; }
        else if (stringNumber == 1 && fret == 2) { key = "F#1"; }
        else if (stringNumber == 1 && fret == 3) { key = "G1"; }
        else if (stringNumber == 1 && fret == 4) { key = "G#1"; }
        else if (stringNumber == 1 && fret == 5) { key = "A1"; }
        else if (stringNumber == 1 && fret == 6) { key = "A#1"; }
        else if (stringNumber == 1 && fret == 7) { key = "B1"; }
        else if (stringNumber == 1 && fret == 8) { key = "C2"; }
        else if (stringNumber == 1 && fret == 9) { key = "C#2"; }
        else if (stringNumber == 1 && fret == 10) { key = "D2"; }
        else if (stringNumber == 1 && fret == 11) { key = "D#2"; }
        else if (stringNumber == 1 && fret == 12) { key = "E2"; }
        else if (stringNumber == 1 && fret == 13) { key = "F2"; }
        else if (stringNumber == 1 && fret == 14) { key = "F#2"; }
        else if (stringNumber == 1 && fret == 15) { key = "G2"; }
        else if (stringNumber == 1 && fret == 16) { key = "G#2"; }
        else if (stringNumber == 1 && fret == 17) { key = "A2"; }
        else if (stringNumber == 1 && fret == 18) { key = "A#2"; }
        else if (stringNumber == 1 && fret == 19) { key = "B2"; }
        else if (stringNumber == 1 && fret == 20) { key = "C3"; }
        else if (stringNumber == 1 && fret == 21) { key = "C#3"; }
        else if (stringNumber == 1 && fret == 22) { key = "D3"; }
        else if (stringNumber == 1 && fret == 23) { key = "D#3"; }
        else if (stringNumber == 1 && fret == 24) { key = "E3"; }
        else if (stringNumber == 2 && fret == 0) { key = "A1"; }
        else if (stringNumber == 2 && fret == 1) { key = "A#1"; }
        else if (stringNumber == 2 && fret == 2) { key = "B1"; }
        else if (stringNumber == 2 && fret == 3) { key = "C2"; }
        else if (stringNumber == 2 && fret == 4) { key = "C#2"; }
        else if (stringNumber == 2 && fret == 5) { key = "D2"; }
        else if (stringNumber == 2 && fret == 6) { key = "D#2"; }
        else if (stringNumber == 2 && fret == 7) { key = "E2"; }
        else if (stringNumber == 2 && fret == 8) { key = "F2"; }
        else if (stringNumber == 2 && fret == 9) { key = "F#2"; }
        else if (stringNumber == 2 && fret == 10) { key = "G2"; }
        else if (stringNumber == 2 && fret == 11) { key = "G#2"; }
        else if (stringNumber == 2 && fret == 12) { key = "A2"; }
        else if (stringNumber == 2 && fret == 13) { key = "A#2"; }
        else if (stringNumber == 2 && fret == 14) { key = "B2"; }
        else if (stringNumber == 2 && fret == 15) { key = "C3"; }
        else if (stringNumber == 2 && fret == 16) { key = "C#3"; }
        else if (stringNumber == 2 && fret == 17) { key = "D3"; }
        else if (stringNumber == 2 && fret == 18) { key = "D#3"; }
        else if (stringNumber == 2 && fret == 19) { key = "E3"; }
        else if (stringNumber == 2 && fret == 20) { key = "F3"; }
        else if (stringNumber == 2 && fret == 21) { key = "F#3"; }
        else if (stringNumber == 2 && fret == 22) { key = "G3"; }
        else if (stringNumber == 2 && fret == 23) { key = "G#3"; }
        else if (stringNumber == 2 && fret == 24) { key = "A3"; }
        else if (stringNumber == 3 && fret == 0) { key = "D2"; }
        else if (stringNumber == 3 && fret == 1) { key = "D#2"; }
        else if (stringNumber == 3 && fret == 2) { key = "E2"; }
        else if (stringNumber == 3 && fret == 3) { key = "F2"; }
        else if (stringNumber == 3 && fret == 4) { key = "F#2"; }
        else if (stringNumber == 3 && fret == 5) { key = "G2"; }
        else if (stringNumber == 3 && fret == 6) { key = "G#2"; }
        else if (stringNumber == 3 && fret == 7) { key = "A2"; }
        else if (stringNumber == 3 && fret == 8) { key = "A#2"; }
        else if (stringNumber == 3 && fret == 9) { key = "B2"; }
        else if (stringNumber == 3 && fret == 10) { key = "C3"; }
        else if (stringNumber == 3 && fret == 11) { key = "C#3"; }
        else if (stringNumber == 3 && fret == 12) { key = "D3"; }
        else if (stringNumber == 3 && fret == 13) { key = "D#3"; }
        else if (stringNumber == 3 && fret == 14) { key = "E3"; }
        else if (stringNumber == 3 && fret == 15) { key = "F3"; }
        else if (stringNumber == 3 && fret == 16) { key = "F#3"; }
        else if (stringNumber == 3 && fret == 17) { key = "G3"; }
        else if (stringNumber == 3 && fret == 18) { key = "G#3"; }
        else if (stringNumber == 3 && fret == 19) { key = "A3"; }
        else if (stringNumber == 3 && fret == 20) { key = "A#3"; }
        else if (stringNumber == 3 && fret == 21) { key = "B3"; }
        else if (stringNumber == 3 && fret == 22) { key = "C4"; }
        else if (stringNumber == 3 && fret == 23) { key = "C#4"; }
        else if (stringNumber == 3 && fret == 24) { key = "D4"; }
        else if (stringNumber == 4 && fret == 0) { key = "G2"; }
        else if (stringNumber == 4 && fret == 1) { key = "G#2"; }
        else if (stringNumber == 4 && fret == 2) { key = "A2"; }
        else if (stringNumber == 4 && fret == 3) { key = "A#2"; }
        else if (stringNumber == 4 && fret == 4) { key = "B2"; }
        else if (stringNumber == 4 && fret == 5) { key = "C3"; }
        else if (stringNumber == 4 && fret == 6) { key = "C#3"; }
        else if (stringNumber == 4 && fret == 7) { key = "D3"; }
        else if (stringNumber == 4 && fret == 8) { key = "D#3"; }
        else if (stringNumber == 4 && fret == 9) { key = "E3"; }
        else if (stringNumber == 4 && fret == 10) { key = "F3"; }
        else if (stringNumber == 4 && fret == 11) { key = "F#3"; }
        else if (stringNumber == 4 && fret == 12) { key = "G3"; }
        else if (stringNumber == 4 && fret == 13) { key = "G#3"; }
        else if (stringNumber == 4 && fret == 14) { key = "A3"; }
        else if (stringNumber == 4 && fret == 15) { key = "A#3"; }
        else if (stringNumber == 4 && fret == 16) { key = "B3"; }
        else if (stringNumber == 4 && fret == 17) { key = "C4"; }
        else if (stringNumber == 4 && fret == 18) { key = "C#4"; }
        else if (stringNumber == 4 && fret == 19) { key = "D4"; }
        else if (stringNumber == 4 && fret == 20) { key = "D#4"; }
        else if (stringNumber == 4 && fret == 21) { key = "E4"; }
        else if (stringNumber == 4 && fret == 22) { key = "F4"; }
        else if (stringNumber == 4 && fret == 23) { key = "F#4"; }
        else if (stringNumber == 4 && fret == 24) { key = "G4"; }
        }
        this.length = length;
    }

    /**
     * Accessor methods for the Tablature class
     */

    public int getStringNumber() {
        return stringNumber;
    }

    public int getFret() {
        return fret;
    }

    public String getNote() {
        return key + length;
    }

    public String getLength() {
        return length;
    }

    @Override
    public void playNote() {
        player.play(getNote());
    }

    /**
     * Tablature notes do not have a piano key associated, so this method is not implemented
     * Subject to change
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKey'");
    }
}
