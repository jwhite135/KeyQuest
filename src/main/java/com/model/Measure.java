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

    /**
     * Method to print the measure and add the notes given
     * Loops through the chords and prints the notes included in each chord
     * @param chords list of chords which include the notes being played
     */
    public void printMeasure() {
        ArrayList<Integer> notes = new ArrayList<Integer>();
        String letter;
        int octave;

        for (Chord chord : chords) {
            for (Note note : chord.getNotes()) {
                letter = note.getKey().charAt(0) + "";
                octave = Integer.parseInt(note.getKey().substring(1));

                if (letter.equals("C")) {
                    notes.add(1 + octave * 7);
                } else if (letter.equals("D")) {
                    notes.add(2 + octave * 7);
                } else if (letter.equals("E")) {
                    notes.add(3 + octave * 7);
                } else if (letter.equals("F")) {
                    notes.add(4 + octave * 7);
                } else if (letter.equals("G")) {
                    notes.add(5 + octave * 7);
                } else if (letter.equals("A")) {
                    notes.add(6 + octave * 7);
                } else if (letter.equals("B")) {
                    notes.add(7 + octave * 7);
                }

                for (int i: notes) {
                    System.out.print(i + " ");
                    
                }
                System.out.print("\n");
            }
        }
    }
}
