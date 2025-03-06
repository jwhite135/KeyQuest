package com.model;

import java.util.ArrayList;

public class UserDatabase {
    private static UserDatabase userDatabase;
    private ArrayList<User> users;

    private UserDatabase() {
        users = new ArrayList<User>();
        users = DataLoader.getUsers();
    }

    public static UserDatabase getInstance() {
        return userDatabase;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(String email, String password) {
        return users.get(0);
    }

    public boolean addUser(User user) {
        if (user == null) {
            return false;
        } 
        for (User existingUser : users) {
            if (user.getEmail().equals(existingUser.getEmail())) {
                return false;
            }
        }
        users.add(user);
        return true;
    }
}
