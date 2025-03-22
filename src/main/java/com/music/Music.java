package com.music;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Music {

    public static void main(String[] args) {
        try {
            Player player = new Player();
            Pattern soprano = new Pattern("Eb3i Db3i | Ab4q Ab4q C4i Bb4i | Ab4q Ab4q Eb3q | F3q Ab4q F3q | Eb3h");
            soprano.setVoice(0).setInstrument("Trumpet").setTempo(84);
            Pattern alto = new Pattern("Eb3i Db3i | C3q Eb3q Ab4i G4i | Eb3q Eb3q Eb3q | Db3q F3q Db3q | Db3q- C3q");
            alto.setVoice(1).setInstrument("French_Horn").setTempo(84);
            Pattern tenor = new Pattern("G2q | Ab3q Ab3q C3i | Bb3i C3q C3q | Ab3q Ab3q Ab3q | Ab3q Ab3h");
            tenor.setVoice(2).setInstrument("Trombone").setTempo(84);
            Pattern bass = new Pattern("C2q | Ab2q C2q Eb2q | Ab3q Ab2q C2q | Db2q Db2i Eb2i F2i G2i | Ab3h");
            bass.setVoice(3).setInstrument("Tuba").setTempo(84);
            player.play(soprano, alto, tenor, bass);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
