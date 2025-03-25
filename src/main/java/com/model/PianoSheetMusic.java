package com.model;

public class PianoSheetMusic extends SheetMusic {
    private char key;
    private boolean major;

    public PianoSheetMusic(char key, boolean major) {
        this.key = key;
        this.major = major;
    }

    public void printSheetMusic() {
        System.out.println("Key: " + key);
        System.out.println("Major: " + major);
        System.out.println("Tempo: " + getTempo());
        System.out.println("Time Signature: " + getTimeSigNum() + "/" + getTimeSigDen());
        System.out.println("Measures: ");
        for (Measure measure : getMeasures()) {
            measure.printMeasure();
        }
    }
}
