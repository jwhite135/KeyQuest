package com.model;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class UserDatabaseTest {
    private UserDatabase userDatabase;
    private User testUser;

    @Before
    public void setUp() {
        userDatabase = UserDatabase.getInstance();
        testUser = new User("username", "password", "email@email.com");
        userDatabase.getUsers().clear(); // Clear users before each test
    }

    @Test
    public void testGetInstance() {
        assertNotNull(userDatabase);
    }

    @Test
    public void testAddUser_Success() {
        assertTrue(UserDatabase.addUser(testUser));
        assertEquals(1, userDatabase.getUsers().size());
    }

    @Test
    public void testAddUser_Duplicate() {
        UserDatabase.addUser(testUser);
        assertFalse(UserDatabase.addUser(testUser));
        assertEquals(1, userDatabase.getUsers().size());
    }

    @Test
    public void testGetUser_ValidCredentials() {
        UserDatabase.addUser(testUser);
        User retrievedUser = userDatabase.getUser("username", "password");
        assertNotNull(retrievedUser);
        assertEquals("username", retrievedUser.getUsername());
    }

    @Test
    public void testGetUser_InvalidCredentials() {
        UserDatabase.addUser(testUser);
        User retrievedUser = userDatabase.getUser("username", "NOTPassword");
        assertNull(retrievedUser);
    }

    @Test
    public void testGetUser_NonExistent() {
        User retrievedUser = userDatabase.getUser("NOTuser", "password");
        assertNull(retrievedUser);
    }
}
