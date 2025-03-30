package com.model;

/**
 * Lesson class that holds the student, song, title, and completion status of the lesson
 * This class will be used to track the progress of the student and the teacher
 * @author Ian Attmore
 */
public class Lesson {
    private Student student;
    private Song song;
    private String title;
    private boolean completed;
    private Teacher teacher;

    /**
     * Constructor for Lesson
     * @params song, title, and teacher of the lesson
     * Initializes the lesson as not completed, to be changed by the student later
     */
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
        return;
    }
}
