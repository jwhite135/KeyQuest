package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class Song {
    private UUID id;
    private Genre genre;
    private String name;
    private String artist;
    private ArrayList<SheetMusic> sheetMusic;
    private int difficulty;

    public Song() {
        this.sheetMusic = new ArrayList<SheetMusic>();
    }
    
    public Song(UUID id, Genre genre, String title, String artist, ArrayList<SheetMusic> sheetMusic, int difficulty) {
        this.id = id;
        this.genre = genre;
        this.name = title;
        this.artist = artist;
        this.sheetMusic = sheetMusic;
        this.difficulty = difficulty;
    }

    public Song(Genre genre, String title, String artist, ArrayList<SheetMusic> sheetMusic, int difficulty) {
        this.genre = genre;
        this.name = title;
        this.artist = artist;
        this.sheetMusic = sheetMusic;
        this.difficulty = difficulty;
        this.id = UUID.randomUUID();
    }
        
    public void playSong() {
        return;
    }

    public void playAlong(Instrument instrument) {
        return;
    }

    // Accessors for data writing
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
}
