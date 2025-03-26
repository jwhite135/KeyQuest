package com.model;

import java.util.ArrayList;

/**
 * keyquest class that holds the main method to run the program
 * This class is used to test the PostDatabase class and its methods
 * It currently prints out all the posts and their comments
 * @author Ian Attmore
 */
public class keyquest {
    public static void main(String[] args) {
        SongDatabase songDatabase = SongDatabase.getInstance();
        ArrayList<Song> songs = songDatabase.getSongs();
        System.out.println(songs.get(2).getName());
        songs.get(2).playSong();
        System.out.println(songs.get(3).getName());
        songs.get(3).playSong();
    }
}