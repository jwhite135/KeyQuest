package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Student extends User{
    private ArrayList<Lesson> lessons;
    private Teacher teacher;

    public Student(UUID id, String username, String email, String password, int dailyStreak, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts, ArrayList<Lesson> lessons, Teacher teacher) {
        super(id, username, email, password, dailyStreak, favoriteSongs, friends, favoritePosts);
        this.lessons = lessons;
        this.teacher = teacher;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    public void doLesson(Lesson lesson) {
        lesson.doLesson();
    }

    public String getType() {
        return "Student";
    }

    public String toString() {
        return "Teacher: " + getUsername() + "\n"
            + "Email: " + getEmail() + "\n"
            + "Favorite Songs: " + getFavoriteSongs() + "\n"
            + "Friends: " + getFriends() + "\n"
            + "Favorite Posts: " + getFavoritePosts() + "\n"
            + "Teacher: " + teacher + "\n"
            + "Lessons: " + lessons + "\n\n";

    }
}