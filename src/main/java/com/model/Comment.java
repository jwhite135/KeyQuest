package com.model;

import java.util.Date;

public class Comment {
    private String body;
    private User author;
    private Date date;

    public Comment(String body, User author) {
        this.body = body;
        this.author = author;
        // Date Constructor sets to current date/time
        this.date = new Date();
    }
    
}
