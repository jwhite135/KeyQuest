package com.model;

import java.util.ArrayList;

/**
 * SheetMusic class that holds the tempo, time signature, and a list of measures
 * The playMeasures method will play each measure in the list of measures
 * @author Ryan Leadbitter
 */
public class SheetMusic {
    private int tempo;
    private int timeSignatureNumerator;
    private int timeSignatureDenominator;
    private ArrayList<Measure> measures;

    /**
     * Default constructor for SheetMusic
     * Sets the tempo to 120, time signature numerator to 4, and time signature denominator to 4
     */
    public SheetMusic() {
        tempo = 120;
        timeSignatureNumerator = 4;
        timeSignatureDenominator = 4;
    }

    /**
     * Constructor #2 for SheetMusic
     * Creates default measures for given sheet music parameters
     * @param tempo the tempo of the sheet music
     * @param timeSignatureNumerator the numerator of the time signature
     * @param timeSignatureDenominator the denominator of the time signature
     */
    SheetMusic(int tempo, int timeSignatureNumerator, int timeSignatureDenominator) {
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
        this.measures = new ArrayList<Measure>();
    }

    /**
     * Constructor for SheetMusic
     * @param tempo the tempo of the sheet music
     * @param timeSignatureNumerator the numerator of the time signature
     * @param timeSignatureDenominator the denominator of the time signature
     * @param measures list of measures included in the song
     */
    public SheetMusic(int tempo, int timeSignatureNumerator, int timeSignatureDenominator, ArrayList<Measure> measures) {
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
        this.measures = measures;
    }

    /**
     * Method to play each measure in the list of measures
     */
    public void playMeasures() {
        for (int i = 0; i < measures.size(); ++i) {
            measures.get(i).playMeasure();
        }
    }

    /**
     * Accessor methods for the measures, time signature denominator, time signature numerator, and tempo
     */

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public int getTimeSigDen() {
        return timeSignatureDenominator;
    }

    public int getTimeSigNum() {
        return timeSignatureNumerator;
    }

    public int getTempo() {
        return tempo;
    }

}
