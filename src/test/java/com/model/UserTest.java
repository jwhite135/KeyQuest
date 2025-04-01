package com.model;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class UserTest {
    
    @Test
    public void testTesting() {
        assertTrue(true);
    }
    
    public void testCreateUser1()
    {
        User user = User.getInstance("username", "password", "email");
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());
    }
}