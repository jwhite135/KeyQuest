package com.model;

public class PianoSheetMusic extends SheetMusic {
    private char key;
    private boolean major;

    public PianoSheetMusic(char key, boolean major) {
        this.key = key;
        this.major = major;
    }
}
