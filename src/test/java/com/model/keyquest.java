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
        songs.get(0).playSong();
    }
}