package com.model;

/**
 * GuitarSheetMusic class that extends SheetMusic and holds the tuning of the guitar
 * This class will be used in part with sheet music to feed in the tuning
 * Here for future use, not currently implemented in the project
 * @author Matthew Radin
 */
public class GuitarSheetMusic extends SheetMusic {
    private String tuning;

    public GuitarSheetMusic(String tuning) {
        this.tuning = tuning;
    }
}
