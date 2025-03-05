package com.model;

public abstract class DataConstants {
    protected static final String USER_FILE_NAME = "./json/Users.json";
    protected static final String USER_ID = "id";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_TYPE = "type";
    protected static final String USER_DAILY_STREAK = "dailyStreak";
    protected static final String USER_FAVORITE_SONGS = "favoriteSongs";
    protected static final String USER_FRIENDS = "friends";
    protected static final String USER_FAVORITE_POSTS = "favoritePosts";
    protected static final String STUDENT_LESSONS = "lessons";
    protected static final String STUDENT_TEACHER = "teacher";
    protected static final String TEACHER_STUDENTS = "students";

    protected static final String SONG_FILE_NAME = "./json/Songs.json";
    protected static final String SONG_ID = "id";
    protected static final String SONG_GENRE = "genre";
    protected static final String SONG_TITLE = "title";
    protected static final String SONG_ARTIST = "artist";
    protected static final String SONG_DIFFICULTY = "difficulty";
    protected static final String SONG_SHEET_MUSIC = "SheetMusic";
    protected static final String SHEET_MUSIC_TYPE = "type";
    protected static final String SHEET_MUSIC_TEMPO = "tempo";
    protected static final String SHEET_MUSIC_TIME_SIG_NUM = "timeSignatureNumerator";
    protected static final String SHEET_MUSIC_TIME_SIG_DEN = "timeSignatureDenominator";
    protected static final String SHEET_MUSIC_MEASURES = "measures";
    protected static final String MEASURE_TYPE = "type";
    protected static final String MEASURE_CHORDS = "chords";
    protected static final String CHORD_NOTES = "notes";
    protected static final String NOTE_TYPE = "type";
    protected static final String NOTE_PITCH = "pitch";
    protected static final String NOTE_LENGTH = "length";

    protected static final String POST_FILE_NAME = "./json/Posts.json";
    protected static final String POST_ID = "postID";
    protected static final String POST_SONG_ID = "songID";
    protected static final String POST_NUM_FAVORITES = "numFavorites";
    protected static final String POST_COMMENT_IDS = "commentIDs";
    protected static final String POST_AUTHOR_ID = "authorID";
    protected static final String POST_DATE = "date";
    protected static final String POST_PRIVATE = "private";
    protected static final String POST_TITLE = "title";
}
