package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * DataLoader class that reads in the JSON files and creates objects from them
 * Loads: UUID, Genre, Song, SheetMusic, Measure, Chord, Note, User, Lesson, Post, Comment
 * For songs, loads: ID, Genre, Title, Artist, Difficulty, SheetMusic, Tempo, TimeSignature, Measures, Chords, Notes
 * For users, loads: ID, Username, Email, Password, Type, FavoriteSongs, Friends, FavoritePosts, Lessons, Teacher, Students
 * For posts, loads: ID, SongID, NumFavorites, Comments, AuthorID, Date, Private, Title, Body
 * For comments, loads: Body, Author, Date
 * @see DataConstants
 * @author Josiah White
 */
public class DataLoader extends DataConstants {
    /*
     * Reads in the songs from the JSON file and returns an ArrayList of songs for the SongDatabase
     * @return ArrayList<Song> songs
     */
    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            FileReader reader = new FileReader(SONG_FILE_NAME);
            JSONArray songsJSON = (JSONArray)new JSONParser().parse(reader);

            // Parsing each song in the JSON file
            for(int i = 0; i < songsJSON.size(); i++) {
                JSONObject songJSON = (JSONObject)songsJSON.get(i);

                // Song attributes
                UUID id = UUID.fromString((String)songJSON.get(SONG_ID));
                Genre genre = Genre.valueOf(((String) songJSON.get(SONG_GENRE)).toUpperCase());
                String title = (String)songJSON.get(SONG_TITLE);
                String artist = (String)songJSON.get(SONG_ARTIST);
                int difficulty = ((Long)songJSON.get(SONG_DIFFICULTY)).intValue();

                // Parsing SheetMusic List
                ArrayList<SheetMusic> sheetMusics = new ArrayList<SheetMusic>();
                JSONArray sheets = (JSONArray)songJSON.get(SONG_SHEET_MUSIC);
                for(int j = 0; j < sheets.size(); j++) {

                    // Parsing sheet music attributes
                    int sheetType = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TYPE)).intValue();
                    int tempo = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TEMPO)).intValue();
                    int timeSignatureNumerator = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TIME_SIG_NUM)).intValue();
                    int timeSignatureDenominator = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TIME_SIG_DEN)).intValue();

                    // Parsing Measures
                    ArrayList<Measure> measures = new ArrayList<Measure>();
                    JSONArray measureList = (JSONArray)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_MEASURES);
                    for(int k = 0; k < measureList.size(); k++) {

                        // Parsing measure attributes
                        int measureType = ((Long)((JSONObject)measureList.get(k)).get(MEASURE_TYPE)).intValue();
                        // Parsing chords
                        ArrayList<Chord> chords = new ArrayList<Chord>();
                        JSONArray chordList = (JSONArray)((JSONObject)measureList.get(k)).get(MEASURE_CHORDS);
                        for(int l = 0; l < chordList.size(); l++) {
                            // Parsing chord attributes
                            ArrayList<Note> notes = new ArrayList<Note>();
                            JSONArray noteList = (JSONArray)((JSONObject)chordList.get(l)).get(CHORD_NOTES);
                            for(int m = 0; m < noteList.size(); m++) {

                                // Parsing note attributes
                                int noteType = ((Long)((JSONObject)noteList.get(m)).get(NOTE_TYPE)).intValue();
                                String key = (String)((JSONObject)noteList.get(m)).get(NOTE_KEY);
                                String length = (String)((JSONObject)noteList.get(m)).get(NOTE_LENGTH);
                                boolean sharp = (boolean)((JSONObject)noteList.get(m)).getOrDefault(NOTE_SHARP, false);
                                boolean flat = (boolean)((JSONObject)noteList.get(m)).getOrDefault(NOTE_FLAT, false);

                                if(noteType == 1) {
                                    // Creates a piano note if that is the type of the note
                                    notes.add(new PianoNote(length, key, sharp, flat));
                                }
                            }
                            // Adds the chord to the chordlist
                            chords.add(new Chord(notes));
                        }
                        if(measureType == 1) {
                            // Creates a piano measure if that is the type of measure
                            measures.add(new PianoMeasure(true, chords));
                        }
                    }
                    // Adds the sheet music to the sheet music list
                    sheetMusics.add(new SheetMusic(tempo, timeSignatureNumerator, timeSignatureDenominator, measures));
                }
                // Creates a song to add to the song list
                songs.add(new Song(id, genre, title, artist, sheetMusics, difficulty));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
    /*
     * Reads in the users from the JSON file and returns an ArrayList of users for the UserDatabase
     * @return ArrayList<User> users
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
				UUID id = UUID.fromString((String)userJSON.get(USER_ID));
				String username = (String)userJSON.get(USER_USERNAME);
				String email = (String)userJSON.get(USER_EMAIL);
                String password = (String)userJSON.get(USER_PASSWORD);
                String type = (String)userJSON.get(USER_TYPE);
                // TODO: Implement further User attributes and figure out how to initialize from UUIDs
                /*
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
                    */
                // TODO: Fully implement student and teacher distinction - archetype here.  
                switch(type) {
                    case "Student":
                        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
                        JSONArray lessonList = (JSONArray)userJSON.get(STUDENT_LESSONS);
                        // parse Lesson
                        UUID teacherID = UUID.fromString((String)userJSON.get(STUDENT_TEACHER));
                        users.add(new Student(id, username, email, password, null, null, null, null, null));
                        break;
                    case "Teacher":
                        ArrayList<UUID> students = new ArrayList<UUID>();
                        JSONArray studentList = (JSONArray)userJSON.get(TEACHER_STUDENTS);
                        if(studentList != null) {
                            for(int j = 0; j < studentList.size(); j++) {
                                students.add(UUID.fromString((String)studentList.get(j)));
                            }
                        }
                        users.add(new Teacher(id, username, email, password, null, null, null, null));
                        break;
                    default:
                        // Adds the parsed user to the user list
                        users.add(new User(id, username, email, password, null, null, null));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Returns the list of users
        return users;
    }

    /* 
     * Reads in the posts from the JSON file and returns an ArrayList of posts for the PostDatabase
     * @return ArrayList<Post> posts
     */
    public static ArrayList<Post> getPosts() {

        ArrayList<Post> posts = new ArrayList<Post>();
        
        try {
            FileReader reader = new FileReader(POST_FILE_NAME);
            JSONArray postsJSON = (JSONArray)new JSONParser().parse(reader);
            // Parsing each post in the JSON file
            for(int i = 0; i < postsJSON.size(); i++) {

                JSONObject postJSON = (JSONObject)postsJSON.get(i);
                UUID id = UUID.fromString((String)postJSON.get(POST_ID));
                UUID songID = UUID.fromString((String)postJSON.get(POST_SONG_ID));
                int favorites = ((Long)postJSON.get(POST_NUM_FAVORITES)).intValue();

                ArrayList<Comment> comments = new ArrayList<Comment>();
                JSONArray commentList = (JSONArray)postJSON.get(POST_COMMENTS);
                // Parsing each comment on the post
                for(int j = 0; j < commentList.size(); j++) {

                    // Parsing comment attributes
                    JSONObject commentJSON = (JSONObject)commentList.get(j);
                    String text = (String)commentJSON.get(COMMENT_BODY);
                    UUID authorID = UUID.fromString((String)commentJSON.get(COMMENT_AUTHOR_ID));
                    LocalDate date = LocalDate.parse((String)commentJSON.get(COMMENT_DATE));

                    // Adding the comment to the comment list
                    comments.add(new Comment(text, null, date));
                }

                UUID authorID = UUID.fromString((String)postJSON.get(POST_AUTHOR_ID));
                LocalDate date = LocalDate.parse((String)postJSON.get(POST_DATE));
                boolean isPrivate = (boolean)postJSON.get(POST_PRIVATE);
                String title = (String)postJSON.get(POST_TITLE);
                String body = (String)postJSON.get(POST_BODY);

                // Adding the post to the post list
                posts.add(new Post(id, null, comments, null, date, isPrivate, title, body, favorites));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Returns the list of posts
        return posts;
    }
}
