package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Teacher class that holds the students of the teacher
 * The teacher can make a lesson and assign it to a student
 * The teacher is a subclass of User
 * @author Ian Attmore
 */
public class Teacher extends User {
    private ArrayList<Student> students;

    /**
     * Constructor for Teacher
     * @param id the id of the teacher
     * @param username the username of the teacher
     * @param email the email of the teacher
     * @param password the password of the teacher
     * @param favoriteSongs An arraylist of the favorite songs of the teacher
     * @param friends An arraylist of the friends of the teacher
     * @param favoritePosts An arraylist of the favorite posts of the teacher
     * @param students An arraylist of the students of the teacher
     */
    public Teacher(UUID id, String username, String email, String password, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts, ArrayList<Student> students) {
        super(id, username, email, password, favoriteSongs, friends, favoritePosts);
        this.students = students;
    }

    /**
     * Makes a lesson for the teacher
     * @param song the song for the lesson
     * @param lessonTitle the title of the lesson
     * @return the lesson created
     */
    public Lesson makeLesson(Song song, String lessonTitle) {
        Lesson lesson = new Lesson(song, lessonTitle, this);
        return lesson;
    }

    /**
     * Assigns a lesson to a student
     * @param lesson the lesson to assign
     * @param student the student to assign the lesson to
     */
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
