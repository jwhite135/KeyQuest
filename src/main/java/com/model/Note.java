package com.model;

abstract class Note {
    protected String length;

    public Note() {
        length = "";
    }

    public Note(String length) {
        this.length = length;
    }

    public void playNote() {

    }

    public String getNote() {
        return "";
    }
}
