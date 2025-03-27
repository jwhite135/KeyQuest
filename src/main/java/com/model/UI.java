package com.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class that prints out the scenarios for the KeyQuest program
 * Holds a main method to run the UI and test the scenarios
 * @author Owen Coulam
 */
public class UI {

    private KeyQuestFACADE facade = new KeyQuestFACADE();
    Scanner scanner = new Scanner(System.in);
    
    public void scenario1() {

        System.out.println(" ----- SCENARIO 1: ------ Logging in with an existing account\n");
        // Check for existing account in JSON
        System.out.println("\nWe can see Fellicia Fredrickson" +
            "has an account, but Fred does not.\n");
        
        // Will print second line since account already exists
        System.out.println("Attempting to create account for with username 'ffredrickson'"
            + "and password 'abc123' and email fred@yahoo.com");
        if(facade.makeUser("ffredrickson", "abc123", "fred@yahoo.com") ) {
            System.out.println("Account has been created! You are now logged in.");
        } else {
            System.out.println("Account already exists. Please log in.");
        }

        // Tries again with new username "ffred", prints first line now
        System.out.println("Attempting to create account for with username 'ffred'"
            + "with all other credentials the same");
        if(facade.makeUser("ffred", "abc123", "fred@yahoo.com") ) {
            System.out.println("Account has been created! You are now logged in.");
        } else {
            System.out.println("Account already exists. Please log in.");
        }

        System.out.println("Saving data and logging out...");
        facade.logout();
        // Check for existing account in JSON
        System.out.println("Attempting to log in as 'ffred' with password 'abc123'");
        if( facade.login("ffred", "abc123") ) {
            System.out.println("You are now logged in");
        } else {
            System.out.println("Incorrect username or password");
        }
        
    }

    public void scenario2() {
        System.out.println("\n ----- SCENARIO 2: ------ Playing a song\n");
        
        System.out.println("Searching for songs by Beethoven...");
        ArrayList<Song> sortedSongs = facade.searchSongsByArtist("Beethoven");
        for (Song song : sortedSongs) {
            System.out.println(song);
        }
        // Should display "Oriental Riff"

        System.out.println("\nPlaying song 'Free Fallin'...");
        Song freeFallin = sortedSongs.get(0);
        facade.playSong(freeFallin);

        System.out.println("\nPrinting out sheet music and notes for 'Free Fallin'...");
        System.out.println(facade.convertToTextFile(freeFallin));
    }

    public void scenario3() {
        System.out.println("\n ----- SCENARIO 3: ------ Creating a song\n");
        // Check for existing account in JSON
        System.out.println("Attempting to log in as 'ffredrickson' with password 'easy123'...");
        if ( facade.login("ffredrickson", "easy123") ) {
            System.out.println("You are now logged in");
        } else {
            System.out.println("Incorrect username or password");
        }

        System.out.println("Creating a new song...");
        Song CMajorScale = initDemoSong();
        System.out.println("Song created successfully!");
        facade.playSong(CMajorScale);

        facade.logout();
        // Show users JSON file and songs JSON file

        System.out.println("Attempting to log in as 'ffred' with password 'abc123'...");
        if ( facade.login("ffred", "abc123") ) {
            System.out.println("You are now logged in");
        } else {
            System.out.println("Incorrect username or password");
        }

        System.out.println("Searching for C Major Scale song...");
        ArrayList<Song> tempSongs = facade.searchSongsByName("C Major Scale"); // Should have length 1
        System.out.println("Playing C Major Scale song...");
        facade.playSong(tempSongs.get(0));
    }

    public Song initDemoSong() {
        // Create a new song: A Horse's Journey by Unknown Artist
        String name = "A Horse's Journey";
        String artist = "Unknown Artist";
        int difficulty = 1;
        String genre = "Classical";
        int timeSignatureDenominator = 4;
        int timeSignatureNumerator = 4;
        int tempo = 120;
        Song cMajorScale = facade.createSong(name, artist, difficulty, genre, timeSignatureNumerator, timeSignatureDenominator, tempo);
        ArrayList<Note> chord1 = new ArrayList<>();
        chord1.add(new PianoNote("q", "C4", false, false));
        ArrayList<Note> chord2 = new ArrayList<>();
        chord2.add(new PianoNote("q", "D4", false, false));
        ArrayList<Note> chord3 = new ArrayList<>();
        chord3.add(new PianoNote("q", "E4", false, false));
        ArrayList<Note> chord4 = new ArrayList<>();
        chord4.add(new PianoNote("q", "F4", false, false));
        ArrayList<Chord> measure1 = new ArrayList<>();
        measure1.add(new Chord(chord1));
        measure1.add(new Chord(chord2));
        measure1.add(new Chord(chord3));
        measure1.add(new Chord(chord4));
        facade.addMeasureToSong(cMajorScale, new PianoMeasure(false, measure1));
        ArrayList<Note> chord5 = new ArrayList<>();
        chord5.add(new PianoNote("q", "G4", false, false));
        ArrayList<Note> chord6 = new ArrayList<>();
        chord6.add(new PianoNote("q", "A4", false, false));
        ArrayList<Note> chord7 = new ArrayList<>();
        chord7.add(new PianoNote("q", "B4", false, false));
        ArrayList<Note> chord8 = new ArrayList<>();
        chord8.add(new PianoNote("q", "C5", false, false));
        ArrayList<Chord> measure2 = new ArrayList<>();
        measure2.add(new Chord(chord5));
        measure2.add(new Chord(chord6));
        measure2.add(new Chord(chord7));
        measure2.add(new Chord(chord8));
        facade.addMeasureToSong(cMajorScale, new PianoMeasure(false, measure2));
        return cMajorScale;
    }

    public void run() {
        //scenario1();
        scenario2();
        // scenario3();
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
        // ui.exit();
    }
}
