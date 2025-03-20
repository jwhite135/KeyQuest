package com.model;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private String body;
    private User author;
    private Date date;
    private UUID id;

    public Comment(String body, User author) {
        this.body = body;
        this.author = author;
        // Date Constructor sets to current date/time
        this.date = new Date();
    }

    public String getBody() {
        return this.body;
    }

    public User getAuthor() {
        return this.author;
    }

    public Date getDate() {
        return this.date;
    }
    
}
