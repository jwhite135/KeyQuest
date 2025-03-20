package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Post {
    private Song song;
    private int favorites;
    private ArrayList<Comment> comments;
    private User author;
    private Date date;
    private boolean isPrivate;
    private String title;
    private String body;
    private UUID id;

    // Construtor for creation, note that post is added to database through Facade
    public Post(Song song, User author, boolean isPrivate, String title, String body) {
        this.id = UUID.randomUUID();
        this.song = song;
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.date = new Date();
        this.isPrivate = isPrivate;
        this.favorites = 0;
    }

    // Construtor for data loading
    public Post(UUID id, Song song, ArrayList<Comment> comments, User author, Date date, boolean isPrivate, String title, String body) {
        this.id = id;
        this.song = song;
        this.comments = comments;
        this.author = author;
        this.date = date;
        this.isPrivate = isPrivate;
        this.body = body;
    }

    // Accessor methods for data writing
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

    public Date getDate() {
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
