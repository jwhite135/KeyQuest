package com.model;

import java.util.ArrayList;

public class SongDatabase {
    private SongDatabase songDatabase;
    private ArrayList<Song> songs;

    private SongDatabase() {
        this.songs = new ArrayList<Song>();
    }

    public static SongDatabase getInstance() {
        return null; // Placeholder for actual implementation
    } 

    public Arraylist<Song> getSongs() {
        return songs;
    }

    public ArrayList<Song> searchByName(String name) {
        /**
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getName().equalsIgnoreCase(name)) {
                result.add(song);
            }
        }
        return result;
         */
        return null; // Placeholder for actual implementation
    }

    public ArrayList<Song> searchByArtist(String artist) {
        /**
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                result.add(song);
            }
        }
        return result;
         */
        return null; // Placeholder for actual implementation
    }

    public ArrayList<Song> searchByDifficulty(int difficulty) {
        /**
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getDifficulty().equalsIgnoreCase(difficulty)) {
                result.add(song);
            }
        }
        return result;
         */
        return null; // Placeholder for actual implementation
    }

    public ArrayList<Song> searchByGenre(Genre genre) {
        /**
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getGenre().equalsIgnoreCase(genre)) {
                result.add(song);
            }
        }
        return result;
         */
        return null; // Placeholder for actual implementation
    }
}
