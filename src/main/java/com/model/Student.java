package com.model;

import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Lesson> lessons;
    private Teacher teacher;

    public Student() {
        super("", "", "");
        this.lessons = new ArrayList<Lesson>();
    }

    public void doLesson(Lesson lesson) {
        return;
    }

}