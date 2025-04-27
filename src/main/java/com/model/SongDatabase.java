package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * SongDatabase class that holds a list of songs and methods to search for songs by name, artist, difficulty, and genre
 * The class is a singleton class, meaning only one instance of the class can be created
 * The class is used to access the songs from the DataLoader class
 * The class includes methods to search for songs by name, artist, difficulty, and genre
 * @author Josiah White
 */
public class SongDatabase {
    private static SongDatabase songDatabase;
    private ArrayList<Song> songList;
    private Map<UUID, Song> songMap;

    /**
     * Constructor for the SongDatabase class
     * Initializes the songs ArrayList by calling the DataLoader class
     */
    private SongDatabase() {
        songList = DataLoader.getSongs();
        songMap = new HashMap<UUID, Song>();
        for(Song song : songList) {
            songMap.put(song.getUUID(), song);
        }
    }

    /**
     * Singleton method to get the instance of the SongDatabase
     * If the instance does not exist, it creates a new instance
     * @return the instance of the SongDatabase
     */
    public static SongDatabase getInstance() {
        if(songDatabase == null) {
            songDatabase = new SongDatabase();
        }
        return songDatabase;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public Map<UUID, Song> getSongMap() {
        return songMap;
    }

    public void addSong(Song song) {
        songList.add(song);
        songMap.put(song.getUUID(), song);
    }
    
    /**
     * Method to search for songs by name
     * @param name the name of the song to search for
     * @return an ArrayList of songs that have the same name
     */
    public ArrayList<Song> searchByName(String name) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songList) {
            if (song.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(song);
            }
            if (name.equalsIgnoreCase("all")) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Method to search for songs by artist
     * @param artist the artist of the song to search for
     * @return an ArrayList of songs that have the same artist
     */
    public ArrayList<Song> searchByArtist(String artist) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songList) {
            if (song.getArtist().toLowerCase().contains(artist.toLowerCase())) {
                result.add(song);
            }
            if (artist.equalsIgnoreCase("all")) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Method to search for songs by difficulty
     * @param difficulty the difficulty of the song to search for
     * @return an ArrayList of songs that have the same difficulty
     */
    public ArrayList<Song> searchByDifficulty(int difficulty) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songList) {
            if (song.getDifficulty() == difficulty) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Method to search for songs by genre
     * @param genre the genre of the song to search for
     * @return an ArrayList of songs that have the same genre
     */
    public ArrayList<Song> searchByGenre(Genre genre) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songList) {
            if (song.getGenre() == genre) {
                result.add(song);
            }
        }
        return result;
    }

    public void save() {
        DataWriter.saveSongs();
    }
}
