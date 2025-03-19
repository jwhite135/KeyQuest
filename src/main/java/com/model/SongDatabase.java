package com.model;

import java.util.ArrayList;

public class SongDatabase {
    private static SongDatabase songDatabase;
    private ArrayList<Song> songs;

    private SongDatabase() {
        this.songs = new ArrayList<Song>();
    }

    public static SongDatabase getInstance() {
        if(songDatabase == null) {
            songDatabase = new SongDatabase();
        }
        return songDatabase;
    } 

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    
    public ArrayList<Song> searchByName(String name) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getName().equalsIgnoreCase(name)) {
                result.add(song);
            }
        }
        return result;
    }

    public ArrayList<Song> searchByArtist(String artist) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                result.add(song);
            }
        }
        return result;
    }

    public ArrayList<Song> searchByDifficulty(int difficulty) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getDifficulty() == difficulty) {
                result.add(song);
            }
        }
        return result;
    }

    public ArrayList<Song> searchByGenre(Genre genre) {
        ArrayList<Song> result = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getGenre() == genre) {
                result.add(song);
            }
        }
        return result;
    }
}
