package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controller for the Notification Settings page
 * This class handles the user input for changing notification settings
 * It interacts with the KeyQuestFACADE to update user information
 * @author Ian Attmore
 */

public class NotificationSettingsController {


    @FXML
    private CheckBox likeCheckBox;

    @FXML
    private CheckBox friendPostCheckBox;

    @FXML
    private CheckBox updateCheckBox;

    @FXML
    private CheckBox newFriendCheckBox;

    @FXML
    private Button accountSettingsButton;

    @FXML
    private Button preferencesSettingsButton;

    @FXML
    private ImageView homePageButton;

    @FXML
    private Button playSongButton;

    @FXML
    private Button makeSongButton;

    @FXML
    private Button checkPostsButton;

    /**
     * Method to go to the play song page via play song button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToPlaySong() throws IOException {
        App.setRoot("SongSearch");
    }

    /**
     * Method to go to the make song page via make song button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToMakeSong() throws IOException {
        App.setRoot("CreateSong2");
    }

    /**
     * Method to go to the check posts page via check posts button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToCheckPosts() throws IOException {
        //App.setRoot("");
        System.out.println("goToCheckPosts triggered");
    }

    /**
     * Method to go to the home page via home page button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    /**
     * Method to go to the account settings page via Account Settings button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToAccount() throws IOException {
        App.setRoot("AccountSettings");
    }

    /**
     * Method to go to the preferences settings page via Preferences Settings button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToPreferences() throws IOException {
        App.setRoot("PreferencesSettings");
    }

    /**
     * Method that checks if the new friend check box is selected or not
     * @param event
     */
    @FXML
    private void newFriendCheckClicked(MouseEvent event) {
        if (newFriendCheckBox.isSelected()) {
            System.out.println("New friend check box is selected.");
        } else {
            System.out.println("New friend check box is not selected.");
        }
    }

    /**
     * Method that checks if the like check box is selected or not
     * @param event 
     */
    @FXML
    private void likeCheckClicked(MouseEvent event) {
        if (likeCheckBox.isSelected()) {
            System.out.println("Like check box is selected.");
        } else {
            System.out.println("Like check box is not selected.");
        }
    }

    /**
     * Method that checks if the friend post check box is selected or not
     * @param event 
     */
    @FXML
    private void friendPostCheckClicked(MouseEvent event) {
        if (friendPostCheckBox.isSelected()) {
            System.out.println("Friend post check box is selected.");
        } else {
            System.out.println("Friend post check box is not selected.");
        }
    }

    /**
     * Method that checks if the update check box is selected or not
     * @param event 
     */
    @FXML
    private void updateCheckClicked(MouseEvent event) {
        if (updateCheckBox.isSelected()) {
            System.out.println("Update check box is selected.");
        } else {
            System.out.println("Update check box is not selected.");
        }
    }
}
