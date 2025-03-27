package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * User class that holds the username, password, email, id, favorite songs, friends, and favorite posts of a user
 * The user can post, make a comment, favorite a post, add a friend, remove a friend, and check if the user is a match
 * @author Owen Coulam
 * @author Ian Attmore
 */
public class User {
    private String username;
    private String password;
    private String email;
    private UUID id;
    private ArrayList<Song> favoriteSongs;
    private ArrayList<User> friends;
    private ArrayList<Post> favoritePosts;

    /**
     * Default constructor for User
     * Initializes the id, username, password, email, favorite songs, friends, and favorite posts
     * @param id the id of the user
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param favoriteSongs An arraylist of the favorite songs of the user
     * @param friends An arraylist of the friends of the user
     * @param favoritePosts An arraylist of the favorite posts of the user
     */
    public User(UUID id, String username, String email, String password, ArrayList<Song> favoriteSongs, ArrayList<User> friends, ArrayList<Post> favoritePosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = favoriteSongs;
        this.friends = friends;
        this.favoritePosts = favoritePosts;
    }

    /**
     * Constructor for User in the case that the user is not already in the database and needs to be created
     * Initializes the id, username, password, email, favorite songs, friends, and favorite posts
     */
    private User(String username, String password, String email) {
        this.id = UUIDgenerator();
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteSongs = new ArrayList<Song>();
        this.friends = new ArrayList<User>();
        this.favoritePosts = new ArrayList<Post>();
    }

    /**
     * Constructor used for when the user is being read from the database
     * Specifically for when references to other objects are needed to be UUIDs instead of the actual object
     * Initializes the id, username, password, email, favorite songs, friends, and favorite posts
     * The favorite songs, friends, and favorite posts are initialized as empty arraylists
     */
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

    /**
     * Static method to get an instance of a user
     * Checks if the user already exists in the database
     * If the user does not exist, creates a new user
     * @return the user if the user does not already exist, null if the user already exists
     */
    public static User getInstance(String username, String password, String email) {
        System.out.println("Creating user");
        for (User user : UserDatabase.getInstance().getUsers()) {
            if (user.isMatch(username, password)) {
                return null;
            } else if (user.getUsername().equals(username)) {
                return null;
            }
        }
        return new User(username, password, email);
    }

    /**
     * Accessor methods for the instance variables
     */

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
        return this.username.equals(username) && this.password.equals(password);
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

    public UUID UUIDgenerator() {
        return UUID.randomUUID();
    }
}