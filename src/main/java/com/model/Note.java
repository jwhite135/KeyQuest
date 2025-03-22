package com.model;

abstract class Note {
    protected String length;

    public Note(String length) {
        this.length = length;
    }

    public Note() {
        // Quarter note by default
        length = "q";
    }

    public abstract void playNote();

    public String getLength() {
        return length;
    }
}
