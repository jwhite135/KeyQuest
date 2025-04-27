package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

/**
 * Controller for the Preferences Settings page
 * This class handles the user input for changing preference settings
 * It interacts with the KeyQuestFACADE to update user information
 * @author Ian Attmore
 */
public class PreferencesSettingsController {
    @FXML
    private ChoiceBox defaultInstrumentChoiceBox;

    @FXML
    private Label applyInstrumentButton;

    @FXML
    private Button accountSettingsButton;

    @FXML
    private Button notificationsSettingsButton;

    @FXML
    private ImageView homePageButton;

    @FXML
    private Button playSongButton;

    @FXML
    private Button makeSongButton;

    @FXML
    private Button checkPostsButton;

    @FXML
    private ToggleGroup PostStatus;

    @FXML
    private RadioButton publicButton;

    @FXML
    private RadioButton privateButton;

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
     * Method to go to the account settings page via account settings button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToAccount() throws IOException {
        App.setRoot("AccountSettings");
    }

    /**
     * Method to go to the notification settings page via notifications settings button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void goToNotification() throws IOException {
        App.setRoot("NotificationSettings");
    }

    /**
     * Method to go to the profile page via profile button
     * @throws IOException if the page cannot be loaded
     */
    @FXML
    private void applyDefaultInstrument() {
        // Get the selected instrument from the ChoiceBox
        String selectedInstrument = (String) defaultInstrumentChoiceBox.getValue();
        System.out.println("Selected instrument: " + selectedInstrument);
    }

    /**
     * Method to initialize the Preferences Settings page
     */
    @FXML
    private void initialize() {
        defaultInstrumentChoiceBox.getItems().addAll("Piano", "Guitar", "Bass Guitar");
        defaultInstrumentChoiceBox.setValue("Piano");

        PostStatus.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == publicButton) {
                System.out.println("Public selected");
            } else if (newToggle == privateButton) {
                System.out.println("Private selected");
            }
        });
    }
}
