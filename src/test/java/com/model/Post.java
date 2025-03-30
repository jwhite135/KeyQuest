package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Post class that holds the song, comments, author, date, privacy, title, body, and favorites of a post
 * @author Ian Attmore
 * @author Owen Coulam
 */
public class Post {
    private Song song;
    private int favorites;
    private ArrayList<Comment> comments;
    private User author;
    private LocalDate date;
    private boolean isPrivate;
    private String title;
    private String body;
    private UUID id;

    /**
     * Construtor for creation, note that post is added to database through Facade
     * @param song the post is attached to
     * @param author the user who created the post
     * @param isPrivate whether the post is private
     * @param title the title of the post
     * @param body the body of the post
     */
    public Post(Song song, User author, boolean isPrivate, String title, String body) {
        this.id = UUID.randomUUID();
        this.song = song;
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.date = LocalDate.now();
        this.isPrivate = isPrivate;
        this.favorites = 0;
    }

    /**
     * Constructor for reading from database and loading into memory
     */
    public Post(UUID id, Song song, ArrayList<Comment> comments, User author, LocalDate date, boolean isPrivate, String title, String body, int favorites) {
        this.id = id;
        this.song = song;
        this.comments = comments;
        this.author = author;
        this.date = date;
        this.isPrivate = isPrivate;
        this.body = body;
        this.favorites = favorites;
        this.title = title;
    }

    /**
     * Accessor methods for the instance variables
     */
    
    public Song getSong() {
        return this.song;
    }

    public int getFavorites() {
        return this.favorites;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public User getAuthor() {
        return this.author;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getBody() {
        return this.body;
    }

    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public String getTitle() {
        return this.title;
    }

    public UUID getUUID() {
        return this.id;
    }

    public void addComment(String body, User author) {
        comments.add(new Comment(body, author));
    }

    public void addFavorite(User currentUser) {
        currentUser.favoritePost(this);
        favorites++;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
