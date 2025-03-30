package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Student class that holds the lessons and teacher of a student
 * @author Ian Attmore
 */
public class Student extends User{
    private ArrayList<Lesson> lessons;
    private Teacher teacher;

    /**
     * Constructor for Student
     * @param id the id of the student
     * @param username the username of the student
     * @param email the email of the student
     * @param password the password of the student
     * @param favoriteSongs An arraylist of the favorite songs of the student
     * @param friends An arraylist of the friends of the student
     * @param favoritePosts An arraylist of the favorite posts of the student
     * @param lessons An arraylist of the lessons assigned to the student
     * @param teacher The teacher of the student
     */
    public Student(UUID id, String username, String email, String password, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts, ArrayList<Lesson> lessons, Teacher teacher) {
        super(id, username, email, password, favoriteSongs, friends, favoritePosts);
        this.lessons = lessons;
        this.teacher = teacher;
    }

    /**
     * Accessor methods for the instance variables
     */

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