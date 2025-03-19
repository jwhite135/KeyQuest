package com.model;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    private Song song;
    private int favorites;
    private ArrayList<Comment> comments;
    private User author;
    private Date date;
    private boolean isPrivate;
    private String title;
    private String UUID;

    public Post(Song song, ArrayList<Comment> comments, User author, Date date, boolean isPrivate) {
        this.song = song;
        this.comments = comments;
        this.author = author;
        this.date = date;
        this.isPrivate = isPrivate;
        this.favorites = 0;
        PostDatabase.getInstance().addPost(this);
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

    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public String getTitle() {
        return this.title;
    }

    public String getID() {
        return this.UUID;
    }

    public void addComment(String body, User author) {
        comments.add(new Comment(body, author));
    }

    public void addFavorite(User currentUser) {
        currentUser.favoritePost(this);
        favorites++;
    }
}
