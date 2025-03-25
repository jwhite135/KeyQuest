package com.model;

import java.util.ArrayList;

/**
 * Measure class that holds a list of chords
 * @author Ryan Leadbitter
 */
abstract class Measure {
    protected ArrayList<Chord> chords;

    /**
     * Constructor for Measure
     * @param chords list of chords
     */
    public Measure(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    /**
     * Method to play the measure
     * Loops through the chords and plays them
     */
    public void playMeasure() {
        for (int i = 0; i < chords.size(); i++) {
            chords.get(i).playChord();
        }
    }

    /**
     * Method to get the chords list
     * @return ArrayList<Chord>
     */
    public ArrayList<Chord> getChords() {
        return chords;
    }

    public void printMeasure() {
        for (Chord chord : chords) {
            for (Note note : chord.getNotes()) {
                if (note.getKey().equals("C")) {
                    System.out.print("C");
                } else if (note.getKey().equals("D")) {
                    System.out.print("D");
                } else if (note.getKey().equals("E")) {
                    System.out.print("E");
                } else if (note.getKey().equals("F")) {
                    System.out.print("F");
                } else if (note.getKey().equals("G")) {
                    System.out.print("G");
                } else if (note.getKey().equals("A")) {
                    System.out.print("A");
                } else if (note.getKey().equals("B")) {
                    System.out.print("B");
                }
            }
        }
    }
}
