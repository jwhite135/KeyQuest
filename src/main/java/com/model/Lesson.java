package com.model;

public class Lesson {
    private Student student;
    private Song song;
    private String title;
    private boolean completed;
    private Teacher teacher;

    public Lesson(Song song, String title, Teacher teacher) {
        this.song = song;
        this.title = title;
        this.completed = false;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setComplete(boolean completed) {
        this.completed = completed;
    }

    public void doLesson() {
        
    }
}
