package com.model;

import java.util.Date;

public class Comment {
    private String body;
    private User author;
    private Date date;

    public Comment(String body, User author) {
        this.body = body;
        this.author = author;
        this.date = new Date();
    }
}
