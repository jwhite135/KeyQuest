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
    private int numComments;
    private UUID songUUID;
    private UUID authorUUID;

    public Post(String song, User author, String title, String body) {
        this.id = UUID.randomUUID();
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.date = LocalDate.now();
        this.isPrivate = false;
        this.favorites = 0;
        this.numComments = 0;

        if (title.equals("")) {
            this.title = "Default title";
        } else {
            this.title = title;
        }

        if(body.equals("")) {
            this.body = "Lorem ipsum dolor sit amet.";
        } else {
            this.body = body;
        }

        this.body = body;
        this.song = null;
        for (Song s : SongDatabase.getInstance().getSongList()) {
            if (s.getName().equals(song)) {
                this.song = s;
            }
        }
    }

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
        this.numComments = 0;
        this.title = title;
        this.body = body;
    }

    /**
     * Constructor for reading from database and loading into memory
     */
    public Post(UUID id, UUID songUUID, ArrayList<Comment> comments, UUID authorUUID, LocalDate date, boolean isPrivate, String title, String body, int favorites) {
        this.id = id;
        this.songUUID = songUUID;
        this.comments = comments;
        this.authorUUID = authorUUID;
        this.date = date;
        this.isPrivate = isPrivate;
        this.body = body;
        this.favorites = favorites;
        this.title = title;
        this.numComments = 0;
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
        if(body.equals("")) {
            return;
        }
        comments.add(new Comment(body, author));
        numComments++;
    }

    public void addFavorite(User currentUser) {
        if(currentUser.getFavoritePosts().contains(this)) {
            return;
        }
        currentUser.favoritePost(this);
        favorites++;
    }

    public void removeFavorite(User currentUser) {
        if(!currentUser.getFavoritePosts().contains(this)) {
            return;
        }
        currentUser.removeFavoritePost(this);
        favorites--;
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

    public void setSong(Song song) {
        this.song = song;
    }

    public UUID getSongUUID() {
        return songUUID;
    }

    public UUID getAuthorUUID() {
        return authorUUID;
    }
}
