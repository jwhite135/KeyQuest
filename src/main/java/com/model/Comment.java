package com.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Comment class that holds the body of the comment, the author, and the date it was posted
 * @author Owen Coulam
 */
public class Comment {
    private String body;
    private User author;
    private LocalDate date;
    private UUID id;

    /**
     * Constructor for Comment
     * @param body The body of the comment
     * @param author The author of the comment
     * @param date The Date Constructor sets to current date/time from the system clock
     */
    public Comment(String body, User author) {
        this.body = body;
        this.author = author;
        this.date = LocalDate.now();
    }

    /**
     * Second constructor for Comment that allows for a date to be passed in
     * To be used for testing for now, but could be changed later such that the first constructor
     * calls the second constructor with LocalDate.now() as the date:
        public Comment(String body, User author) {
            this(body, author, LocalDate.now());
        }
     */
    public Comment(String body, User author, LocalDate date) {
        this.body = body;
        this.author = author;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDate getDate() {
        return date;
    }
    
}
