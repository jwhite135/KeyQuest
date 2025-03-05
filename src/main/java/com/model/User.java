package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String username;
    private String password;
    private String email;
    private UUID id;
    private ArrayList<Song> favoriteSongs;
    private int dailyStreak;
    private ArrayList<User> friends;
    private ArrayList<Post> favoritePosts;

    public User(UUID id, String username, String email, String password, int dailyStreak, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = favoriteSongs;
        this.dailyStreak = dailyStreak;
        this.friends = friends;
        this.favoritePosts = favoritePosts;
    }
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = new ArrayList<Song>();
        this.dailyStreak = 0;
        this.friends = new ArrayList<User>();
        this.favoritePosts = new ArrayList<Post>();
    }

    public boolean post(Song song) {
        return false;
    }

    public void makeComment(String body) {
        return;
    }

    public void favoritePost(Post post) {
        return;
    }

    public boolean isMatch(String username, String password) {
        return false;
    }
}