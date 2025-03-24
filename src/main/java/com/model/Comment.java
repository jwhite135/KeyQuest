package com.model;

import java.time.LocalDate;
import java.util.UUID;

public class Comment {
    private String body;
    private User author;
    private LocalDate date;
    private UUID id;

    public Comment(String body, User author) {
        this.body = body;
        this.author = author;
        // Date Constructor sets to current date/time
        this.date = LocalDate.now();
    }

    public Comment(String body, User author, LocalDate date) {
        this.body = body;
        this.author = author;
        this.date = date;
    }

    public String getBody() {
        return this.body;
    }

    public User getAuthor() {
        return this.author;
    }

    public LocalDate getDate() {
        return this.date;
    }
    
}
