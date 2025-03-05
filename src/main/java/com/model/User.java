package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String UUID;
    private ArrayList<Song> favoriteSongs;
    private int dailyStreak;
    private ArrayList<User> friends;
    private ArrayList<Post> favoritePosts;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = new ArrayList<Song>();
        this.dailyStreak = 0;
        this.friends = new ArrayList<User>();
        this.favoritePosts = new ArrayList<Post>();
    }

    public User(UUID id, String firstName, String lastName, String email,
            String password, String type, ArrayList<UUID> favoriteSongs,
            ArrayList<UUID> friends, ArrayList<UUID> favoritePosts) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = new ArrayList<Song>();
        this.dailyStreak = 0;
        this.friends = new ArrayList<User>();
        this.favoritePosts = new ArrayList<Post>();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUUID() {
        return this.UUID;
    }

    public ArrayList<Song> getFavoriteSongs() {
        return this.favoriteSongs;
    }

    public int getDailyStreak() {
        return this.dailyStreak;
    }

    public ArrayList<User> getFriends() {
        return this.friends;
    }

    public ArrayList<Post> getFavoritePosts() {
        return this.favoritePosts;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFavoriteSongs(ArrayList<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    public void setDailyStreak(int dailyStreak) {
        this.dailyStreak = dailyStreak;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setFavoritePosts(ArrayList<Post> favoritePosts) {
        this.favoritePosts = favoritePosts;
    }

    public boolean post(Song song, String body) {
        
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