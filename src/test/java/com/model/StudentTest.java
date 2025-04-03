package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StudentTest {
    private Teacher teacher;
    private Student student;
    private User friend;
    private Song song;
    private Lesson lesson;

    @Before
    public void setUp() {

        UUID teacherId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();
        UUID songId = UUID.randomUUID();
        ArrayList<Song> favoriteSongs = new ArrayList<>();
        ArrayList<User> friends = new ArrayList<>();
        ArrayList<Post> favoritePosts = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        teacher = new Teacher(teacherId, "username", "email@email.com", "password", favoriteSongs, friends, favoritePosts, students);
        student = new Student(studentId, "username", "email@email.com", "password", new ArrayList<Song>(), new ArrayList<User>(), new ArrayList<Post>(), new ArrayList<Lesson>(), teacher);
        
        song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);

        friend = new User("friend", "pwd", "friend@email.com");
    }
    /*
    @Test
    public void testAddLesson() {
        Lesson lesson = new Lesson(song, "Test Lesson", teacher);
        student.addLesson(lesson);
        assertNotNull(lesson);
    }
    */

    @Test
    public void testUserCreation() {
        assertNotNull(student);
        assertEquals("username", student.getUsername());
        assertEquals("email@email.com", student.getEmail());
        assertEquals("password", student.getPassword());
    }

    @Test
    public void testAddFriend() {
        student.addFriend(friend);
        assertTrue(student.getFriends().contains(friend));
    }

    @Test
    public void testRemoveFriend() {
        student.addFriend(friend);
        student.removeFriend(friend);
        assertFalse(student.getFriends().contains(friend));
    }

    @Test
    public void testFavoritePost() {
        Song song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);
        Post post = new Post(song, student, false, "Test Post", "This is a test.");
        student.favoritePost(post);
        assertTrue(student.getFavoritePosts().contains(post));
    }

    @Test
    public void testRemoveFavoritePost() {
        Song song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);
        Post post = new Post(song, student, false, "Test Post", "This is a test.");
        student.favoritePost(post);
        student.removeFavoritePost(post);
        assertFalse(student.getFavoritePosts().contains(post));
    }

    @Test
    public void testIsMatch() {
        assertTrue(student.isMatch("username", "password"));
        assertFalse(student.isMatch("username", "NOTpassword"));
        assertFalse(student.isMatch("NOTusername", "password"));
    }
}