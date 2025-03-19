package com.model;

import java.util.ArrayList;

abstract class SheetMusic {
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

    public void playMeasure() {
        for (int i = 0; i < measures.size(); ++i) {
            measures.get(i).playMeasure();
        }
    }

}
