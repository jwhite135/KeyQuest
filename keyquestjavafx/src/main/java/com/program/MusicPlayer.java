package com.program;

import com.music.Music;
public class MusicPlayer {
    public void playSong() {
        Music.playNote("D5q");
        Music.playNote("D5q");
        Music.playNote("D5q");
        Music.playNote("D5q");
        Music.playNote("C5q");
        Music.playNote("C5q");
        Music.playNote("A4q");
        Music.playNote("A4q");
        Music.playNote("C5w");
    }

    public static void main(String[] args) {
        MusicPlayer music = new MusicPlayer();
        music.playSong();
    }
}