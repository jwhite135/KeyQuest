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

    public boolean login(String email, String password) {
        user = users.getUser(email, password);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean makeUser(String username, String password, String email) {
        user = new User(username, password, email);
        return UserDatabase.addUser(user);
    }

    public boolean makePost(Post post) {
        return PostDatabase.addPost(post);
    }

    public void makeComment(Post post, String comment) {
        post.addComment(comment, user);
    }

    public void favoritePost(Post post) {
        post.addFavorite();; 
    }

    public ArrayList<Post> searchByUser(User user) {
        ArrayList<Post> userPosts = new ArrayList<Post>();
        for (Post post : posts.getPosts()) {
            if (post.getAuthor().equals(user)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    public ArrayList<Post> searchBySong(Song song) {
        ArrayList<Post> songPosts = new ArrayList<Post>();
        for (Post post : posts.getPosts()) {
            if (post.getSong().equals(song)) {
                songPosts.add(post);
            }
        }
        return songPosts;
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

    public ArrayList<Post> searchByName(String title) {
        return posts.searchByName(title);
    }

    public ArrayList<Post> searchByArtist(String artist) {
        return posts.searchByArtist(artist);
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
