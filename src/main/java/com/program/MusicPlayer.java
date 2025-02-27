package com.program;

import com.music.Music;

public class MusicPlayer {
    public void playSong() {
        try {
            Thread.sleep(300);
            Music.playNote("D5q");
            Thread.sleep(5);
            Music.playNote("D5q");
            Thread.sleep(5);
            Music.playNote("D5q");
            Thread.sleep(5);
            Music.playNote("D5q");
            Thread.sleep(5);
            Music.playNote("C5q");
            Thread.sleep(5);
            Music.playNote("C5q");
            Thread.sleep(5);
            Music.playNote("A4q");
            Thread.sleep(5);
            Music.playNote("A4q");
            Thread.sleep(5);
            Music.playNote("C5w");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        MusicPlayer music = new MusicPlayer();
        music.playSong();
    }
}