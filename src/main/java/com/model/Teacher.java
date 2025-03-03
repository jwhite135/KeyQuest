package com.model;

import java.util.ArrayList;
public class Teacher extends User {
    private ArrayList<Student> students;

    public Teacher() {
        super("", "", "");
        students = new ArrayList<Student>();
    }

    public void makeLesson(String song, String lessonTitle) {

    }

    public void assignLesson(Lesson lesson, Student student) {

    }
}
