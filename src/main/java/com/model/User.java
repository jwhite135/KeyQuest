package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String username;
    private String password;
    private String email;
    private UUID id;
    private ArrayList<Song> favoriteSongs;
    private ArrayList<User> friends;
    private ArrayList<Post> favoritePosts;
    private int dailyStreak;

    public User(UUID id, String username, String email, String password, int dailyStreak, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = favoriteSongs;
        this.friends = friends;
        this.favoritePosts = favoritePosts;
        this.dailyStreak = dailyStreak;
    }

    // For creation
    private User(String username, String password, String email) {
        this.id = UUIDgenerator();
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = new ArrayList<Song>();
        this.friends = new ArrayList<User>();
        this.favoritePosts = new ArrayList<Post>();
    }

    // Misc
    public User(UUID id, String username, String email,
            String password, String type, ArrayList<UUID> favoriteSongs,
            ArrayList<UUID> friends, ArrayList<UUID> favoritePosts) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = new ArrayList<Song>();
        this.friends = new ArrayList<User>();
        this.favoritePosts = new ArrayList<Post>();
    }

    public static User getInstance(String username, String password, String email) {
        for (User user : UserDatabase.getInstance().getUsers()) {
            if (user.isMatch(username, password)) {
                return null;
            } else if (user.getUsername().equals(username)) {
                return null;
            }
        }
        return new User(username, password, email);
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

    public UUID getUUID() {
        return this.id;
    }

    public ArrayList<Song> getFavoriteSongs() {
        return this.favoriteSongs;
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

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setFavoritePosts(ArrayList<Post> favoritePosts) {
        this.favoritePosts = favoritePosts;
    }

    public boolean post(Song song, String title, String body) {
        Post post = new Post(song, this, false, title, body);
        PostDatabase.getInstance().addPost(post);
        return true;
    }

    public void makeComment(Post post, String body) {
        post.addComment(body, this);
    }

    public void favoritePost(Post post) {
        favoritePosts.add(post);
    }

    public void removeFavoritePost(Post post) {
        favoritePosts.remove(post);
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    public boolean isMatch(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String getType() {
        return "user";
    }

    public String toString() {
        return "User: " + this.username + "\n"
            + "Email: " + this.email + "\n"
            + "Favorite Songs: " + this.favoriteSongs + "\n"
            + "Friends: " + this.friends + "\n"
            + "Favorite Posts: " + this.favoritePosts + "\n\n";

    }

    private UUID UUIDgenerator() {
        return UUID.randomUUID();
    }
}