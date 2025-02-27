package com.model;

public class Song {
    private Genre genre;    
    private String name;
    private String artist;
    private ArrayList<SheetMusic> sheetMusic;
    private int difficulty;
    private String UUID;

    public Song() {
        this.sheetMusic = new ArrayList<SheetMusic>();
    }
    
    public Song(Genre genre, String title, String artist, ArrayList<SheetMusic> sheetMusic, int difficulty) {
        this.genre = genre;
        this.name = title;
        this.artist = artist;
        this.sheetMusic = sheetMusic;
        this.difficulty = difficulty;
    }
        
    public void playSong() {
        return;
    }

    public void playAlong(Intrument instrument) {
        return;
    }

    /*
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
    */
}
