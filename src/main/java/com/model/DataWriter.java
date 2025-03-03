package com.model;

public class DataWriter extends DataConstants{
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
        songDetails.put(SONG_ID, song.getId());
        songDetails.put(SONG_TITLE, song.getName());
        songDetails.put(SONG_ARTIST, song.getArtist());
        songDetails.put(SONG_GENRE, song.getGenre());
        songDetails.put(SONG_DIFFICULTY, song.getDifficulty());
        //songDetails.put(SONG_SHEET_MUSIC, song.getSheetMusic());
        
        return songDetails;
    }

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
        userDetails.put(USER_ID, user.getId());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_TYPE, user.getType());
        //userDetails.put(USER_FAVORITE_SONGS, user.getFavoriteSongs());
        //userDetails.put(USER_FRIENDS, user.getFriends());
        //userDetails.put(USER_FAVORITE_POSTS, user.getFavoritePosts());
        
        return userDetails;
    }
    public void savePosts() {
        return;
    }

    public void saveComments() {
        return;
    }

    public void saveLessons() {
        return;
    }
}
