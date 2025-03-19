package com.model;

import java.util.ArrayList;

public class UserDatabase {
    private static UserDatabase userDatabase;
    private static ArrayList<User> users;

    private UserDatabase() {
        users = new ArrayList<User>();
        users = DataLoader.getUsers();
    }

    public static UserDatabase getInstance() {
        if (userDatabase == null) {
            userDatabase = new UserDatabase();
        }
        return userDatabase;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean addUser(User user) {
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
