package com.model;

import java.util.ArrayList;

public class KeyQuestFACADE {
    private User user;
    private PostDatabase posts;
    private SongDatabase songs;
    private UserDatabase users;
    private Instrument instrument;

    public KeyQuestFACADE() {
        posts = PostDatabase.getInstance();
        songs = SongDatabase.getInstance();
        users = UserDatabase.getInstance();
        instrument = new Piano();
    }

    private void setFacadeUser(User user) {
        this.user = user;
    }

    public boolean login(String email, String password) {
        user = users.getUser(email, password);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean makeUser(String username, String password, String email) {
        User newUser = User.getInstance(username, password, email);
        if(newUser == null) {
            return false;
        }
        user = newUser;
        return UserDatabase.addUser(newUser);
    }

    public boolean makePost(Song song, boolean isPrivate, String title, String body) {
        Post newPost = new Post(song, this.user, isPrivate, title, body);
        return posts.addPost(newPost);
    }

    public void makeComment(Post post, String comment) {
        post.addComment(comment, user);
    }

    public void favoritePost(Post post) {
        post.addFavorite(this.user);
    }

    public ArrayList<Post> searchByUser(String user) {
        return posts.searchByUser(user);
    }

    public ArrayList<Post> searchBySong(String song) {
        return posts.searchBySong(song);
    }

    public ArrayList<Post> searchByName(String title) {
        return posts.searchByName(title);
    }

    public ArrayList<Post> searchByArtist(String artist) {
        return posts.searchByArtist(artist);
    }

    public ArrayList<Post> sortByMostRecent() {
        return posts.sortByMostRecent();
    }

    public ArrayList<Post> sortByMostLiked() {
        return posts.sortByMostLiked();
    }

    public void playNote(Note note) {
        instrument.playNote(note);
    }

    public void playSong(Song song) {
        instrument.playSong(song);
    }

    public void createSong(SheetMusic sheetMusic, String name) {
        
    }

    public void searchByDifficulty(int difficulty) {
        posts.searchByDifficulty(difficulty);
    }

    public void searchByGenre(Genre genre) {
        posts.searchByGenre(genre);
    }

    public SheetMusic getSheetMusic(Song song) {
        return songs.getSheetMusic(song);
    }

    public void makeLesson(Song song, String lessonTitle) {
        users.addLesson(song, lessonTitle);
    }

    public void assignLesson(Lesson lesson, User user) {
        user.addLesson(lesson);
    }

    public void doLesson(Lesson lesson) {
        
    }

    public void setLessonComplete(Lesson lesson) {
        lesson.setComplete(true);
    }

    public void logout() {
        user = null;
        DataWriter.saveUsers();
        DataWriter.savePosts();
        DataWriter.saveSongs();
    }
}
