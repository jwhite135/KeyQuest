package com.model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants{

    public void saveUsers() {
        UserDatabase users = UserDatabase.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for(int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try(FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUUID());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_TYPE, user.getType());
        userDetails.put(USER_DAILY_STREAK, user.getDailyStreak());
        //userDetails.put(USER_FAVORITE_SONGS, user.getFavoriteSongs());
        //userDetails.put(USER_FRIENDS, user.getFriends());
        //userDetails.put(USER_FAVORITE_POSTS, user.getFavoritePosts());
        
        return userDetails;
    }

    /*
    public void saveSongs() {
        SongDatabase songs = SongDatabase.getInstance();
        ArrayList<Song> songList = songs.getSongs();
        JSONArray jsonSongs = new JSONArray();

        for(int i = 0; i < songList.size(); i++) {
            jsonSongs.add(getSongJSON(songList.get(i)));
        }

        try(FileWriter file = new FileWriter(SONG_FILE_NAME)) {
            file.write(jsonSongs.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getSongJSON(Song song) {
        JSONObject songDetails = new JSONObject();
        songDetails.put(SONG_ID, song.getUUID());
        songDetails.put(SONG_TITLE, song.getName());
        songDetails.put(SONG_ARTIST, song.getArtist());
        songDetails.put(SONG_GENRE, song.getGenre());
        songDetails.put(SONG_DIFFICULTY, song.getDifficulty());
        //songDetails.put(SONG_SHEET_MUSIC, song.getSheetMusic());
        
        return songDetails;
    }
    */

    /* 
    public void savePosts() {
        PostDatabase posts = PostDatabase.getInstance();
        ArrayList<Post> postList = posts.getPosts();
        JSONArray jsonPosts = new JSONArray();

        for(int i = 0; i < postList.size(); i++) {
            jsonPosts.add(getPostJSON(postList.get(i)));
        }

        try(FileWriter file = new FileWriter(POST_FILE_NAME)) {
            file.write(jsonPosts.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getPostJSON(Post post) {
        JSONObject postDetails = new JSONObject();
        postDetails.put(POST_ID, post.getId());
        postDetails.put(POST_SONG_ID, post.getSongID());
        postDetails.put(POST_NUM_FAVORITES, post.getFavorites());
        postDetails.put(POST_COMMENT_IDS, post.getComments());
        postDetails.put(POST_AUTHOR_ID, post.getA());
        postDetails.put(POST_DATE, post.getType());
        postDetails.put(POST_PRIVATE, post.getType());
        postDetails.put(POST_TITLE, post.getType());
        
        return postDetails;
    }
    
    public void saveComments() {
        return;
    }

    public JSONObject getCommentJSON(Comment comment) {
        return null;
    }

    public void saveLessons() {
        return;
    }

    public JSONObject getLessonJSON(Lesson lesson) {
        return null;
    }
    */
}
