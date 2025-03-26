package com.model;

/**
 * PianoSheetMusic class that extends SheetMusic and holds the key and major/minor of the piece
 * @author Ryan Leadbitter
 */
public class PianoSheetMusic extends SheetMusic {
    private char key;
    private boolean major;

    /**
     * Constructor for PianoSheetMusic
     * @param key contains which piano key would be pressed such as A#5, Bb3, C4, etc.
     * @param major contains whether the piece is in a major or minor key
     */
    public PianoSheetMusic(char key, boolean major) {
        this.key = key;
        this.major = major;
    }

    /**
     * Prints the sheet music information to the console, including:
     * Key, Major, Tempo, Time Signature, and Measures
     */
    public void printSheetMusic() {
        System.out.println("Key: " + key);
        System.out.println("Major: " + major);
        System.out.println("Tempo: " + getTempo());
        System.out.println("Time Signature: " + getTimeSigNum() + "/" + getTimeSigDen());
        System.out.println("Measures: ");
        for (Measure measure : getMeasures()) {
            measure.playMeasure();
        }
    }
}
