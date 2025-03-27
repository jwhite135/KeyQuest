package com.model;

import java.util.ArrayList;

/**
 * KeyQuestFACADE class that holds the main methods for the program
 * This class is used to interact with the User, Post, and Song databases
 * It is the main class that the GUI/User will interact with to get data, set data, and use the application
 * @author Ian Attmore, Owen Coulam
 */
public class KeyQuestFACADE {
    private User user;
    private PostDatabase posts;
    private SongDatabase songs;
    private UserDatabase users;
    private Instrument instrument;

    /**
     * Constructor for KeyQuestFACADE
     * Initializes the Post, Song, and User databases
     * Initializes the instrument to be a Piano by default
     */
    public KeyQuestFACADE() {
        posts = PostDatabase.getInstance();
        songs = SongDatabase.getInstance();
        users = UserDatabase.getInstance();
        instrument = new Piano();
    }

    private void setFacadeUser(User user) {
        this.user = user;
    }

    /**
     * Determines if the user is logged in
     * @params email and password of the user
     * @return true if the user is logged in, false if the user is not logged in or the user does not exist
     */
    public boolean login(String email, String password) {
        user = users.getUser(email, password);
        if (user == null) {
            return false;
        }
        return true;
    }

    /**
     * Method to create a new user and add them to the UserDatabase
     * @params username, password, and email of the user
     * @return false if the user already exists, true if the user is created and added to the UserDatabase
     */
    public boolean makeUser(String username, String password, String email) {
        User newUser = User.getInstance(username, password, email);
        if(newUser == null) {
            return false;
        }
        user = newUser;
        return UserDatabase.addUser(newUser);
    }

    /**
     * Method to create a new post and add it to the PostDatabase
     * @params Song post is attached to, if the post is private, the title of the post, and the body of the post
     * @return true if the post is created and added to the PostDatabase
     */
    public boolean makePost(Song song, boolean isPrivate, String title, String body) {
        Post newPost = new Post(song, this.user, isPrivate, title, body);
        return posts.addPost(newPost);
    }

    /**
     * Method to create a new comment and add it to the PostDatabase
     * @params post the comment is attached to and the body of the comment
     * @return true if the comment is created and added to the PostDatabase
     */
    public void makeComment(Post post, String comment) {
        post.addComment(comment, user);
    }

    /**
     * Method to favorite a post
     * @param post to be favorited
     */
    public void favoritePost(Post post) {
        post.addFavorite(this.user);
    }

    /*
     * Post Searching by User, Song, Name, Artist, Most Recent, and Most Liked
     */

    public ArrayList<Post> searchByUser(String user) {
        return posts.searchByUser(user);
    }

    public ArrayList<Post> searchBySong(String song) {
        return posts.searchBySong(song);
    }

    public ArrayList<Post> searchPostsByName(String title) {
        return posts.searchByName(title);
    }

    public ArrayList<Post> searchPostsByArtist(String artist) {
        return posts.searchByArtist(artist);
    }

    public ArrayList<Post> sortPostsByMostRecent() {
        return posts.sortByMostRecent();
    }

    public ArrayList<Post> sortPostsByMostLiked() {
        return posts.sortByMostLiked();
    }

    /*
     * Song Searching by Name, Artist, and Difficulty
     */

    public ArrayList<Song> searchSongsByName(String name) {
        return songs.searchByName(name);
    }

    public ArrayList<Song> searchSongsByArtist(String artist) {
        return songs.searchByArtist(artist);
    }

    public ArrayList<Song> searchSongsByDifficulty(int difficulty) {
        return songs.searchByDifficulty(difficulty);
    }

    /*
     * Other Helper Functions
     */

    public void playNote(Note note) {
        instrument.playNote(note);
    }

    public String playSong(Song song) {
        return song.playSong();
    }

    public String convertToTextFile(Song song) {
        return song.toString();
    }

    public Song createSong(String name, String artist, int difficulty, String genre, int timeSignatureNumerator, int timeSignatureDenominator, int tempo) {
        Song newSong = new Song(name, artist, difficulty, genre, timeSignatureNumerator, timeSignatureDenominator, tempo);
        songs.addSong(newSong);
        return newSong;
    }

    public void addMeasureToSong(Song song, Measure measure) {
        song.addMeasure(measure);
    }

    /**
     * Method to log the user out of the application
     * Saves the User, Post, and Song databases
     */
    public void logout() {
        user = null;
        DataWriter.saveUsers();
        DataWriter.savePosts();
        DataWriter.saveSongs();
    }
}
