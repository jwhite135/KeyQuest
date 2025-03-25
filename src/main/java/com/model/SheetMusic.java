package com.model;

import java.util.ArrayList;

public class SheetMusic {
    private int tempo;
    private int timeSignatureNumerator;
    private int timeSignatureDenominator;
    private ArrayList<Measure> measures;

    public SheetMusic() {
        tempo = 120;
        timeSignatureNumerator = 4;
        timeSignatureDenominator = 4;
    }

    public SheetMusic(int tempo, int timeSignatureNumerator, int timeSignatureDenominator, ArrayList<Measure> measures) {
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
        this.measures = measures;
    }

    public void playMeasures() {
        for (int i = 0; i < measures.size(); ++i) {
            measures.get(i).playMeasure();
        }
    }

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
