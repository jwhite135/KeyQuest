package com.model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Song class that holds the genre, name, artist, sheet music, and difficulty of a song
 * It also has methods to play the song and play along with the song
 * @author Josiah White
 * @author Matthew Radin
 */
public class Song {
    private UUID id;
    private Genre genre;
    private String name;
    private String artist;
    private ArrayList<SheetMusic> sheetMusic;
    private int difficulty;

    /**
     * This constructor is used for creating a song from the database when the UUID is known
     * @param id the UUID of the song
     * @param genre the genre of the song
     * @param title the title of the song
     * @param artist the artist of the song
     * @param sheetMusic ArrayList of sheet music for the song
     * @param difficulty the difficulty of the song represented as an integer
     */
    public Song(UUID id, Genre genre, String title, String artist, ArrayList<SheetMusic> sheetMusic, int difficulty) {
        this.id = id;
        this.genre = genre;
        this.name = title;
        this.artist = artist;
        this.sheetMusic = sheetMusic;
        this.difficulty = difficulty;
    }

    public Song(String title, String artist, int difficulty, String genre, int timeSignatureNumerator, int timeSignatureDenominator, int tempo) {
        this.id = UUID.randomUUID();
        this.name = title;
        this.artist = artist;
        this.difficulty = difficulty;
        this.genre = Genre.valueOf(genre.toUpperCase());
        this.sheetMusic = new ArrayList<SheetMusic>();
        this.sheetMusic.add(new SheetMusic(tempo, timeSignatureNumerator, timeSignatureDenominator));
    }

    /**
     * This constructor is used for creating a song from the user interface when the UUID is not known and will be generated
     */
    public Song(Genre genre, String title, String artist, ArrayList<SheetMusic> sheetMusic, int difficulty) {
        this.genre = genre;
        this.name = title;
        this.artist = artist;
        this.sheetMusic = sheetMusic;
        this.difficulty = difficulty;
        this.id = UUID.randomUUID();
    }

    public void addMeasure(Measure measure) {
        sheetMusic.get(0).addMeasure(measure);
    }
        
    public String playSong() {
        return sheetMusic.get(0).playMeasures();
    }

    /**
     * This method will play the song along with the instrument that is passed in
     * Will need to be rewritten if instrument is removed
     * Currently only supports playing along with a piano
     * @param instrument the instrument to play along with
     */
    public void playAlong(Instrument instrument) {
        if(instrument instanceof Piano) {
            sheetMusic.get(0).playMeasures();
        } else {
            System.out.println("Instrument not supported");
        }
    }

    /**
     * Accessor methods for the instance variables
     */

    public ArrayList<SheetMusic> getSheetMusic() {
        return sheetMusic;
    } 

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

    public UUID getUUID() {
        return id;
    }

    public void setUUID(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " by " + artist;
    }
}
