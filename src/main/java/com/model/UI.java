package com.model;

import java.util.ArrayList;

public class UI {

    private KeyQuestFACADE facade = new KeyQuestFACADE();
    
    public void scen1() {

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

    public void scen2() {
        System.out.println("\n ----- SCENARIO 2: ------ Playing a song\n");
        
        System.out.println("Searching for songs by Tom Petty...");
        ArrayList<Song> sortedSongs = facade.searchSongsByArtist("Tom Petty");
        for (Song song : sortedSongs) {
            System.out.println(song);
        }
        // Should display "I Won't Back Down", "Mary Jane's Last Dance", and "Free Fallin"

        System.out.println("\nPlaying song 'Free Fallin'...");
        Song freeFallin = sortedSongs.get(2);
        facade.playSong(freeFallin);

        System.out.println("\nPrinting out sheet music and notes for 'Free Fallin'...");
        facade.convertToTextFile(freeFallin);
    }

    public void run() {
        scen1();
        scen2();
        // scen3();
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
        // ui.exit();
    }
}
