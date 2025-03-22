package com.model;

import com.music.Music;

abstract class Note {
    
    /* Frontend will take in user data, convert to
        algebric notation, and pass the notation into the
        backend to play the note

        ---- Notation ----
        <Note><Octave><Duration>
        Note: A-G, # for sharp and b for flat
        Octave: 0-8
        Duration: Letters
            - w: whole note
            - h: half note
            - q: quarter note
            - i: eighth note
            - s: sixteenth note

        Examples: C4q, F#5i, Ab3w
    */

    protected String noteData;

    public Note(String noteData) {
        this.noteData = noteData;
    }

    public Note() {
        // Middle C is default
        noteData = "C4q";
    }

    public void playNote() {
        Music.playNote(noteData);
    }

    public String getNote() {
        return noteData;
    }
}
