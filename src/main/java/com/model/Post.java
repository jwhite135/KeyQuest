package com.model;

import java.util.ArrayList;

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
    }
    
    public void addComment(String body, String author) {
        return;
    }

    public void addFavorite() {
        return;
    }
}
