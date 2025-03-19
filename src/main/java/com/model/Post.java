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
    private UUID id;

    public Post(Song song, ArrayList<Comment> comments, User author, Date date, boolean isPrivate, String title) {
        this.song = song;
        this.comments = comments;
        this.author = author;
        this.date = date;
        this.isPrivate = isPrivate;
    }

    public Post(UUID id, Song song, ArrayList<Comment> comments, User author, Date date, boolean isPrivate, String title) {
        this.id = id;
        this.song = song;
        this.comments = comments;
        this.author = author;
        this.date = date;
        this.isPrivate = isPrivate;
    }
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

    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public String getTitle() {
        return this.title;
    }

    public UUID getUUID() {
        return this.id;
    }

    public void setSong(Song song) {
        this.song = song;
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

    
    public void addComment(String body, String author) {
        return;
    }

    public void addFavorite() {
        return;
    }
}
