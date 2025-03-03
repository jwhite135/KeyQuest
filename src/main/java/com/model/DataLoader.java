package com.model;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            FileReader reader = new FileReader(SONG_FILE_NAME);
            JSONArray songsJSON = (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < songsJSON.size(); i++) {
                JSONObject songJSON = (JSONObject)songsJSON.get(i);
                UUID id = UUID.fromString((String)songJSON.get(SONG_ID));
                Genre genre = (Genre)songJSON.get(SONG_GENRE);
                String title = (String)songJSON.get(SONG_TITLE);
                String artist = (String)songJSON.get(SONG_ARTIST);
                int difficulty = (int)songJSON.get(SONG_DIFFICULTY);
                songs.add(new Song(id, genre, title, artist, null, difficulty));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
				UUID id = UUID.fromString((String)userJSON.get(USER_ID));
				String firstName = (String)userJSON.get(USER_FIRST_NAME);
				String lastName = (String)userJSON.get(USER_LAST_NAME);
				String email = (String)userJSON.get(USER_EMAIL);
                String password = (String)userJSON.get(USER_PASSWORD);
                String type = (String)userJSON.get(USER_TYPE);
                ArrayList<UUID> favoriteSongs = new ArrayList<UUID>();
                JSONArray songs = (JSONArray)userJSON.get(USER_FAVORITE_SONGS);
                if(songs != null) {
                    for(int j = 0; j < songs.size(); j++) {
                        favoriteSongs.add(UUID.fromString((String)songs.get(j)));
                    }
                }
                ArrayList<UUID> friends = new ArrayList<UUID>();
                JSONArray friendList = (JSONArray)userJSON.get(USER_FRIENDS);
                if(friendList != null) {
                    for(int j = 0; j < friendList.size(); j++) {
                        friends.add(UUID.fromString((String)friendList.get(j)));
                    }
                }
                ArrayList<UUID> favoritePosts = new ArrayList<UUID>();
                JSONArray posts = (JSONArray)userJSON.get(USER_FAVORITE_POSTS);
                if(posts != null) {
                    for(int j = 0; j < posts.size(); j++) {
                        favoritePosts.add(UUID.fromString((String)posts.get(j)));
                    }
                }
                users.add(new User(id, firstName, lastName, email, password, type, favoriteSongs, friends, favoritePosts));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<Post> getPosts() {
        return new ArrayList<Post>();
    }

    public ArrayList<Comment> getComments() {
        return new ArrayList<Comment>();
    }

    public ArrayList<Lesson> getLessons() {
        return new ArrayList<Lesson>();
    }
}
