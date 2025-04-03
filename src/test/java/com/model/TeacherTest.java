package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TeacherTest {
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

        student = new Student(studentId, "student", "student@email.com", "password", new ArrayList<Song>(), new ArrayList<User>(), new ArrayList<Post>(), new ArrayList<Lesson>(), teacher);
        
        song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);

        friend = new User("friend", "pwd", "friend@email.com");
    }

    @Test
    public void testMakeLesson() {
        lesson = teacher.makeLesson(song, "Lesson Title");
        assertNotNull(lesson);
    }

    @Test
    public void testUserCreation() {
        assertNotNull(teacher);
        assertEquals("username", teacher.getUsername());
        assertEquals("email@email.com", teacher.getEmail());
        assertEquals("password", teacher.getPassword());
    }

    @Test
    public void testAddFriend() {
        teacher.addFriend(friend);
        assertTrue(teacher.getFriends().contains(friend));
    }

    @Test
    public void testRemoveFriend() {
        teacher.addFriend(friend);
        teacher.removeFriend(friend);
        assertFalse(teacher.getFriends().contains(friend));
    }

    @Test
    public void testFavoritePost() {
        Song song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);
        Post post = new Post(song, teacher, false, "Test Post", "This is a test.");
        teacher.favoritePost(post);
        assertTrue(teacher.getFavoritePosts().contains(post));
    }

    @Test
    public void testRemoveFavoritePost() {
        Song song = new Song("Test Song", "Test Artist", 1, "ROCK", 4, 4, 120);
        Post post = new Post(song, teacher, false, "Test Post", "This is a test.");
        teacher.favoritePost(post);
        teacher.removeFavoritePost(post);
        assertFalse(teacher.getFavoritePosts().contains(post));
    }

    @Test
    public void testIsMatch() {
        assertTrue(teacher.isMatch("username", "password"));
        assertFalse(teacher.isMatch("username", "NOTpassword"));
        assertFalse(teacher.isMatch("NOTusername", "password"));
    }
}