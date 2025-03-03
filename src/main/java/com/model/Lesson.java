package com.model;

public class Lesson {
    private Student student;
    private Song song;
    private String title;
    private boolean completed;
    private Teacher teacher;

    public Lesson(Song song, String title) {
        this.student = student;
        this.song = song;
        this.title = title;
        this.teacher = teacher;
        this.completed = false;
    }

    public void setComplete(boolean completed) {
        this.completed = completed;
    }
}
