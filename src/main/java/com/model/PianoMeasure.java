package com.model;

import java.util.ArrayList;

/**
 * PianoMeasure class that extends Measure
 * @author Ryan Leadbitter
 */
public class PianoMeasure extends Measure {
    private boolean showClef;

    /**
     * Constructor for PianoMeasure
     * @param showClef boolean to show clef
     * @param chords list of chords
     * Calls Measure super constructor
     */
    public PianoMeasure(boolean showClef, ArrayList<Chord> chords) {
        super(chords);
        this.showClef = showClef;
    }
    
    /**
     * Method to play the measure
     * Loops through the chords and plays them using playChord()
     */
    @Override
    public void playMeasure() {
        for (int i = 0; i < chords.size(); ++i) {
            chords.get(i).playChord();
        }
    }

    /**
     * Method to get the showClef boolean
     * @return boolean
     */
    public boolean getShowClef() {
        return showClef;
    }

    /**
     * Method to set the showClef boolean
     * @param showClef boolean value to set or unset
     */
    public void setShowClef(boolean showClef) {
        this.showClef = showClef;
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
                if (note.getKey().charAt(1) == '#' || note.getKey().charAt(1) == 'b') {
                    octave = Integer.parseInt(note.getKey().substring(2));
                } else {
                    octave = Integer.parseInt(note.getKey().substring(1));
                }

                switch (letter) {
                    case "C":
                        notes.add(1 + octave * 7);
                        break;
                    case "D":
                        notes.add(2 + octave * 7);
                        break;
                    case "E":
                        notes.add(3 + octave * 7);
                        break;
                    case "F":
                        notes.add(4 + octave * 7);
                        break;
                    case "G":
                        notes.add(5 + octave * 7);
                        break;
                    case "A":
                        notes.add(6 + octave * 7);
                        break;
                    case "B":
                        notes.add(7 + octave * 7);
                        break;
                    default:
                        break;
                }

                for (int i: notes) {
                    System.out.print(i + " ");
                    
                }
                System.out.print("\n");
            }
        }
    }
}
