package com.model;

import java.util.ArrayList;

public class UserDatabase {
    private static UserDatabase userDatabase;
    private ArrayList<User> users;

    private UserDatabase() {
        users = new ArrayList<User>();
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

}
