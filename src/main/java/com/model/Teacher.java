package com.model;

import java.util.ArrayList;
import java.util.UUID;
public class Teacher extends User {
    private ArrayList<Student> students;

    public Teacher(UUID id, String username, String email, String password, int dailyStreak, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts, ArrayList<Student> students) {
        super(id, username, email, password, dailyStreak, favoriteSongs, friends, favoritePosts);
        this.students = students;
    }

    public void makeLesson(String song, String lessonTitle) {

    }

    public void assignLesson(Lesson lesson, Student student) {

    }
    public String getType() {
        return "Teacher";
    }
}
