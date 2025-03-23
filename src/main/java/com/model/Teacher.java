package com.model;

import java.util.ArrayList;
import java.util.UUID;
public class Teacher extends User {
    private ArrayList<Student> students;

    public Teacher(UUID id, String username, String email, String password, int dailyStreak, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts, ArrayList<Student> students) {
        super(id, username, email, password, dailyStreak, favoriteSongs, friends, favoritePosts);
        this.students = students;
    }

    public Lesson makeLesson(Song song, String lessonTitle) {
        Lesson lesson = new Lesson(song, lessonTitle, this);
        return lesson;
    }

    public void assignLesson(Lesson lesson, Student student) {
        lesson.setStudent(student);
        student.addLesson(lesson);
    }
    public String getType() {
        return "Teacher";
    }

    public String toString() {
        return "Teacher: " + getUsername() + "\n"
            + "Email: " + getEmail() + "\n"
            + "Favorite Songs: " + getFavoriteSongs() + "\n"
            + "Friends: " + getFriends() + "\n"
            + "Favorite Posts: " + getFavoritePosts() + "\n"
            + "Students: " + students + "\n\n";

    }
}
