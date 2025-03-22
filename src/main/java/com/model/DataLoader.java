package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            FileReader reader = new FileReader(SONG_FILE_NAME);
            JSONArray songsJSON = (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < songsJSON.size(); i++) {
                JSONObject songJSON = (JSONObject)songsJSON.get(i);
                UUID id = UUID.fromString((String)songJSON.get(SONG_ID));
                Genre genre = Genre.valueOf(((String) songJSON.get(SONG_GENRE)).toUpperCase());
                String title = (String)songJSON.get(SONG_TITLE);
                String artist = (String)songJSON.get(SONG_ARTIST);
                int difficulty = ((Long)songJSON.get(SONG_DIFFICULTY)).intValue();
                ArrayList<SheetMusic> sheetMusics = new ArrayList<SheetMusic>();
                JSONArray sheets = (JSONArray)songJSON.get(SONG_SHEET_MUSIC);
                for(int j = 0; j < sheets.size(); j++) {
                    int sheetType = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TYPE)).intValue();
                    //if (sheetType == 1) {
                        int tempo = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TEMPO)).intValue();
                        int timeSignatureNumerator = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TIME_SIG_NUM)).intValue();
                        int timeSignatureDenominator = ((Long)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_TIME_SIG_DEN)).intValue();
                    //}
                    ArrayList<Measure> measures = new ArrayList<Measure>();
                    JSONArray measureList = (JSONArray)((JSONObject)sheets.get(j)).get(SHEET_MUSIC_MEASURES);
                    for(int k = 0; k < measureList.size(); k++) {
                        int measureType = ((Long)((JSONObject)measureList.get(j)).get(MEASURE_TYPE)).intValue();
                        ArrayList<Chord> chords = new ArrayList<Chord>();
                        JSONArray chordList = (JSONArray)((JSONObject)measureList.get(k)).get(MEASURE_CHORDS);
                        for(int l = 0; l < chordList.size(); l++) {
                            ArrayList<Note> notes = new ArrayList<Note>();
                            JSONArray noteList = (JSONArray)((JSONObject)chordList.get(l)).get(CHORD_NOTES);
                            for(int m = 0; m < noteList.size(); m++) {
                                int noteType = ((Long)((JSONObject)noteList.get(m)).get(NOTE_TYPE)).intValue();
                                String pitch = (String)((JSONObject)noteList.get(m)).get(NOTE_PITCH);
                                String length = (String)((JSONObject)noteList.get(m)).get(NOTE_LENGTH);
                                if(noteType == 1) {
                                    notes.add(new PianoNote(length, pitch, false, false));
                                }
                            }
                            chords.add(new Chord(notes));
                        }
                        if(measureType == 1) {
                            measures.add(new PianoMeasure(true, chords));
                        }
                    }
                    sheetMusics.add(new SheetMusic(tempo, timeSignatureNumerator, timeSignatureDenominator, measures));
                }
                songs.add(new Song(id, genre, title, artist, sheetMusics, difficulty));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }

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
                        users.add(new User(id, username, email, password, null, null, null));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
/*
    public static ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            FileReader reader = new FileReader(POST_FILE_NAME);
            JSONArray postsJSON = (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < postsJSON.size(); i++) {
                JSONObject postJSON = (JSONObject)postsJSON.get(i);
                UUID id = UUID.fromString((String)postJSON.get(POST_ID));
                UUID songID = UUID.fromString((String)postJSON.get(POST_SONG));
                Song song = null;
                for(Song s : getSongs()) {
                    if(s.getUUID().equals(songID)) {
                        song = s;
                    }
                }
                int favorites = ((Long)postJSON.get(POST_FAVORITES)).intValue();
                ArrayList<Comment> comments = new ArrayList<Comment>();
                JSONArray commentList = (JSONArray)postJSON.get(POST_COMMENTS);
                for(int j = 0; j < commentList.size(); j++) {
                    JSONObject commentJSON = (JSONObject)commentList.get(j);
                    UUID commentID = UUID.fromString((String)commentJSON.get(COMMENT_ID));
                    String text = (String)commentJSON.get(COMMENT_TEXT);
                    UUID authorID = UUID.fromString((String)commentJSON.get(COMMENT_AUTHOR));
                    User author = null;
                    for(User user : getUsers()) {
                        if(user.getUUID().equals(authorID)) {
                            author = user;
                        }
                    }
                    comments.add(new Comment(commentID, text, author));
                }
                UUID authorID = UUID.fromString((String)postJSON.get(POST_AUTHOR));
                User author = null;
                for(User user : getUsers()) {
                    if(user.getUUID().equals(authorID)) {
                        author = user;
                    }
                }
                Date date = new Date((String)postJSON.get(POST_DATE));
                boolean isPrivate = (boolean)postJSON.get(POST_PRIVATE);
                String title = (String)postJSON.get(POST_TITLE);
                posts.add(new Post(id, song, comments, author, date, isPrivate, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
*/
}
