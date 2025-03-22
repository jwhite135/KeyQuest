package com.music;

import org.jfugue.player.Player;

public class Music {

    public static void main(String[] args) {
        Player player = new Player();

        String music = 
            "V0 E3Bi Db3i Ab4q Ab4q C4i Bb4i Ab4q Ab4q Eb3q F3q Ab4q F3q Eb3h | " + 
            "V1 E3Bi D3bi C3q Eb3q Ab4i G4i Eb3q Eb3q Eb3q Db3q F3q Db3q Db3q- C3q | " + 
            "V2 G2q Ab3q Ab3q C3i Bb3i C3q C3q Ab3q Ab3q Ab3q Ab3q Ab3h | " + 
            "V3 C2q Ab2q C2q Eb2q Ab3q Ab2q C2q Db2q Db2i Eb2i F2i G2i Ab3h";

        player.play(music);
    }
}
