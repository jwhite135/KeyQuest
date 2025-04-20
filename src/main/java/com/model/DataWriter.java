package com.model;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * DataWriter class that writes data to JSON files
 * Writes: Users, Songs, Posts
 * For users, writes: ID, Username, Email, Password, Type, FavoriteSongs, Friends, FavoritePosts
 * For songs, writes: ID, Genre, Title, Artist, Difficulty, SheetMusic, Tempo, TimeSignature, Measures, Chords, Notes
 * For posts, writes: ID, SongID, NumFavorites, Comments, AuthorID, Date, Private, Title, Body
 * @see DataConstants
 * @author Josiah White
 */
public class DataWriter extends DataConstants {
    /* 
     * Writes user data to JSON file
     */
    public static void saveUsers() {
        UserDatabase users = UserDatabase.getInstance();
        ArrayList<User> userList = users.getUserList();
        JSONArray jsonUsers = new JSONArray();
        // Iterates through each user and adds them to the JSON array
        for(int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        // Writes the JSON array to the file
        try(FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Goes through the given user and turns it to a JSONObject
     * @param user User to be turned into a JSONObject
     * @return JSONObject of the given User
     */
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        // Retrieves attributes and puts them into the JSONObject to be put into the file.  
        userDetails.put(USER_ID, user.getUUID().toString());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_TYPE, user.getType());

        userDetails.put(USER_FAVORITE_SONGS, getUUIDList(user.getFavoriteSongs()));
        userDetails.put(USER_FRIENDS, getUUIDList(user.getFriends()));
        userDetails.put(USER_FAVORITE_POSTS, getUUIDList(user.getFavoritePosts()));
        
        return userDetails;
    }

    /*
     * Writes song data to JSON file
     */
    public static void saveSongs() {
        SongDatabase songs = SongDatabase.getInstance();
        ArrayList<Song> songList = songs.getSongList();
        JSONArray jsonSongs = new JSONArray();

        // Iterates through each song and adds them to the JSON array
        for(int i = 0; i < songList.size(); i++) {
            jsonSongs.add(getSongJSON(songList.get(i)));
        }

        // Writes the JSON array to the file
        try(FileWriter file = new FileWriter(SONG_FILE_NAME)) {
            file.write(jsonSongs.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Goes through the given song and turns it to a JSONObject
     * @param song Song to be turned into a JSONObject
     * @return JSONObject of the given Song
     */
    private static JSONObject getSongJSON(Song song) {
        JSONObject songDetails = new JSONObject();

        // Retrieves attributes and puts them into the JSONObject to be put into the file.
        songDetails.put(SONG_ID, song.getUUID().toString());
        songDetails.put(SONG_TITLE, song.getName());
        songDetails.put(SONG_ARTIST, song.getArtist());
        songDetails.put(SONG_GENRE, song.getGenre().toString().toLowerCase());
        songDetails.put(SONG_DIFFICULTY, song.getDifficulty());

        // Goes through each sheet music in the song's list and adds them into a JSONArray
        ArrayList<SheetMusic> sheetMusic = song.getSheetMusic();
        JSONArray sheetMusicArray = new JSONArray();

        for (int i = 0; i < sheetMusic.size(); i++) {
            JSONObject sheetMusicDetails = new JSONObject();
            // Goes through the sheet music attributes  
            sheetMusicDetails.put(SHEET_MUSIC_TYPE, 1);
            sheetMusicDetails.put(SHEET_MUSIC_TEMPO, sheetMusic.get(i).getTempo());
            sheetMusicDetails.put(SHEET_MUSIC_TIME_SIG_NUM, sheetMusic.get(i).getTimeSigNum());
            sheetMusicDetails.put(SHEET_MUSIC_TIME_SIG_DEN, sheetMusic.get(i).getTimeSigDen());

            // Goes through each measure in the sheet music's list and adds them into a JSONArray
            ArrayList<Measure> measures = sheetMusic.get(i).getMeasures();
            JSONArray measureArray = new JSONArray();

            for (int j = 0; j < measures.size(); j++) {
                JSONObject measureDetails = new JSONObject();
                // Goes through the measure attributes
                measureDetails.put(MEASURE_TYPE, 1);

                // Goes through each chord in the measure's list and adds them into a JSONArray
                ArrayList<Chord> chords = measures.get(j).getChords();
                JSONArray chordArray = new JSONArray();

                for (int k = 0; k < chords.size(); k++) {
                    JSONObject chordDetails = new JSONObject();

                    // Goes through each note in the chord's list and adds them into a JSONArray
                    ArrayList<Note> notes = chords.get(k).getNotes();
                    JSONArray noteArray = new JSONArray();

                    for (int l = 0; l < notes.size(); l++) {
                        JSONObject noteDetails = new JSONObject();
                        
                        // Goes through the note attributes
                        noteDetails.put(NOTE_TYPE, 1);
                        noteDetails.put(NOTE_LENGTH, notes.get(l).getLength());
                        noteDetails.put(NOTE_KEY, ((PianoNote)notes.get(l)).getKey());
                        noteDetails.put(NOTE_SHARP, ((PianoNote)notes.get(l)).isSharp());
                        noteDetails.put(NOTE_FLAT, ((PianoNote)notes.get(l)).isFlat());
                        
                        // Adds the note to the note array
                        noteArray.add(noteDetails);
                    }
                    // Adds the note array to the chord
                    chordDetails.put(CHORD_NOTES, noteArray);

                    // Adds the chord to the chord array
                    chordArray.add(chordDetails);
                }
                // Adds the chord array to the measure
                measureDetails.put(MEASURE_CHORDS, chordArray);

                // Adds the measure to the measure array
                measureArray.add(measureDetails);
            }
            // Adds the measure array to the sheet music
            sheetMusicDetails.put(SHEET_MUSIC_MEASURES, measureArray);
            // TODO: Properly implement guitar and other further instruments for parsing and saving
            /* 
            else {
                sheetMusicDetails.put(SHEET_MUSIC_TYPE, 2);
                sheetMusicDetails.put(SHEET_MUSIC_TEMPO, sheetMusic.get(i).getTempo());
                sheetMusicDetails.put(SHEET_MUSIC_TIME_SIG_NUM, sheetMusic.get(i).getTimeSigNum());
                sheetMusicDetails.put(SHEET_MUSIC_TIME_SIG_DEN, sheetMusic.get(i).getTimeSigDen());
                ArrayList<Measure> measures = sheetMusic.get(i).getMeasures();
                JSONArray measureArray = new JSONArray();
                for (int j = 0; j < measures.size(); j++) {
                    JSONObject measureDetails = new JSONObject();
                    measureDetails.put(MEASURE_TYPE, 2);
                    measureDetails.put(MEASURE_NUM_OF_LINES, ((TabMeasure)measures.get(j)).getNumberOfLines());
                    ArrayList<Chord> chords = measures.get(j).getChords();
                    JSONArray chordArray = new JSONArray();
                    for (int k = 0; k < chords.size(); k++) {
                        JSONObject chordDetails = new JSONObject();
                        ArrayList<Note> notes = chords.get(k).getNotes();
                        JSONArray noteArray = new JSONArray();
                        for (int l = 0; l < notes.size(); l++) {
                            JSONObject noteDetails = new JSONObject();
                            noteDetails.put(NOTE_TYPE, 2);
                            noteDetails.put(NOTE_STRING_NUMBER, ((Tablature)notes.get(l)).getStringNumber());
                            noteDetails.put(NOTE_FRET, ((Tablature)notes.get(l)).getFret());
                            noteDetails.put(NOTE_LENGTH, notes.get(l).getLength());
                            noteArray.add(noteDetails);
                        }
                        chordDetails.put(CHORD_NOTES, noteArray);
                        chordArray.add(chordDetails);
                    }
                    measureDetails.put(MEASURE_CHORDS, chordArray);
                    measureArray.add(measureDetails);
                }
                sheetMusicDetails.put(SHEET_MUSIC_MEASURES, measureArray);
                sheetMusicArray.add(sheetMusicDetails);
            } */
            // Adds the sheet music array to the song
            sheetMusicArray.add(sheetMusicDetails);
        }
        // Adds the song to the song array
        songDetails.put(SONG_SHEET_MUSIC, sheetMusicArray);
        
        return songDetails;
    }
    
    /* 
     * Writes post data to JSON file
     */
    public static void savePosts() {
        PostDatabase posts = PostDatabase.getInstance();
        ArrayList<Post> postList = posts.getPostList();
        JSONArray jsonPosts = new JSONArray();

        // Iterates through each post and adds them to the JSON array
        for(int i = 0; i < postList.size(); i++) {
            jsonPosts.add(getPostJSON(postList.get(i)));
        }

        // Writes the JSON array to the file
        try(FileWriter file = new FileWriter(POST_FILE_NAME)) {
            file.write(jsonPosts.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Goes through the given post and turns it to a JSONObject
     * @param post Post to be turned into a JSONObject
     * @return JSONObject of the given Post
     */
    private static JSONObject getPostJSON(Post post) {

        JSONObject postDetails = new JSONObject();
        
        // Retrieves attributes and puts them into the JSONObject to be put into the file.
        postDetails.put(POST_ID, post.getUUID());
        postDetails.put(POST_SONG_ID, post.getSong().getUUID());
        postDetails.put(POST_NUM_FAVORITES, post.getFavorites());

        // Goes through each comment in the post's list and adds them into a JSONArray
        ArrayList<Comment> comments = post.getComments();
        JSONArray commentArray = new JSONArray();

        for (int i = 0; i < comments.size(); i++) {
            JSONObject commentDetails = new JSONObject();

            // Retrieves attributes and puts them into the JSONObject to be put into the file.
            commentDetails.put(COMMENT_BODY, comments.get(i).getBody());
            commentDetails.put(COMMENT_AUTHOR_ID, comments.get(i).getAuthor().getUUID());
            commentDetails.put(COMMENT_DATE, comments.get(i).getDate());

            // Adds the comment to the comment array
            commentArray.add(commentDetails);
        }
        // Gets the remaining post attributes
        postDetails.put(POST_COMMENTS, commentArray);
        postDetails.put(POST_AUTHOR_ID, post.getAuthor().getUUID());
        postDetails.put(POST_DATE, post.getDate());
        postDetails.put(POST_PRIVATE, post.getIsPrivate());
        postDetails.put(POST_TITLE, post.getTitle());
        postDetails.put(POST_BODY, post.getBody());
        
        return postDetails;
    }
    
    /*
     * Goes through the given list of items and gets their UUIDs
     * @param items List of items to get UUIDs from
     * @return JSONArray of UUIDs
     */
    private static <T> JSONArray getUUIDList(ArrayList<T> items) {
        ArrayList<String> uuidList = new ArrayList<>();
        try {
            for (T item : items) {
                Method getUUIDMethod = item.getClass().getMethod("getUUID");
                String uuid = ((UUID) getUUIDMethod.invoke(item)).toString();
                uuidList.add(uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray UUIDArray = new JSONArray();
        for(String uuid : uuidList) {
            UUIDArray.add(uuid);
        }
        return UUIDArray;
    }
}
