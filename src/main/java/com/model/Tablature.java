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
     * This is done by creating an inline array containing the notes for the string
     * From there, the fret number is used to index the array for the note
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
            switch (stringNumber) {
                case 1:
                    // Guitar String 1 (Low E)
                    key = new String[]{"E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
                                       "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3",
                                       "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4"}[fret];
                    break;
                case 2:
                    // Guitar String 2 (A)
                    key = new String[]{"A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3",
                                       "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4",
                                       "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4"}[fret];
                    break;
                case 3:
                    // Guitar String 3 (D)
                    key = new String[]{"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3",
                                       "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4",
                                       "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5"}[fret];
                    break;
                case 4:
                    // Guitar String 4 (G):
                    key = new String[]{"G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4",
                                       "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4",
                                       "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5"}[fret];
                    break;
                case 5:
                    // Guitar String 5 (B)
                    key = new String[]{"B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4",
                                       "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5",
                                       "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5"}[fret];
                    break;
                case 6:
                    // Guitar String 6 (High E)
                    key = new String[]{"E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
                                       "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5",
                                       "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6"}[fret];
                    break;
            }
        } else if (Instrument instanceof BassGuitar) {
            switch (stringNumber) {
                case 1:
                    // Bass String 1 (Low E)
                    key = new String[]{"E1", "F1", "F#1", "G1", "G#1", "A1", "A#1", "B1",
                                       "C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2",
                                       "G#2", "A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3"}[fret];
                    break;
                case 2:
                    // Bass String 2 (A)
                    key = new String[]{"A1", "A#1", "B1", "C2", "C#2", "D2", "D#2", "E2",
                                       "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3",
                                       "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3"}[fret];
                    break;
                case 3:
                    // Bass String 3 (D)
                    key = new String[]{"D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2",
                                       "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3",
                                       "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4"}[fret];
                    break;
                case 4:
                    // Bass String 4 (G)
                    key = new String[]{"G2", "G#2", "A2", "A#2", "B2", "C3", "C#3", "D3",
                                       "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3",
                                       "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4"}[fret];
                    break;
            }
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
