package com.model;

import java.util.ArrayList;

public class keyquest {
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        SongDatabase songDatabase = SongDatabase.getInstance();
        songDatabase.setSongs(DataLoader.getSongs());
        ArrayList<Song> songs = songDatabase.getSongs();
        for (Song song : songs) {
            System.out.println("ID: " + song.getUUID());
            System.out.println("Genre: " + song.getGenre());
            System.out.println("Title: " + song.getName());
            System.out.println("Artist: " + song.getArtist());
            System.out.println("Difficulty: " + song.getDifficulty());
            System.out.println("---------------------------");
            song.playSong();
        }
    }
}

/*
 * ,
  {
    "id": "073657f2-0de9-4fa7-a699-2744002efe8d",
    "genre": "rock",
    "title": "Rock Song",
    "artist": "Rock Band",
    "difficulty": 2
  }
 */