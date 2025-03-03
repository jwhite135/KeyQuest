package com.model;

import java.util.ArrayList;

abstract class SheetMusic {
    private int tempo;
    private int timeSignatureNumerator;
    private int timeSignatureDenominator;
    private ArrayList<Measure> measures;

    public SheetMusic() {
        
    }

    public SheetMusic(int tempo, int timeSignatureNumerator, int timeSignatureDenominator, ArrayList<Measure> measures) {
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
        this.measures = measures;
    }
}
