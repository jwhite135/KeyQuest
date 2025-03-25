package com.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public void printUsersJSON() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../../../json/users.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        scen1();
        // scen2();
        // scen3();
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
        // ui.exit();
    }
}
