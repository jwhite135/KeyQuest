package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AccountSettingsController {

    @FXML
    private ImageView homePageButton;

    @FXML
    private TextField changeUsernameField;

    @FXML
    private Button changeProfilePictureButton;

    @FXML
    private TextField changePasswordField;

    @FXML
    private ChoiceBox<String> changeLanguageChoiceBox;

    @FXML
    private RadioButton lightModeButton;

    @FXML
    private RadioButton darkModeButton;

    @FXML
    private ToggleGroup Theme;

    @FXML
    private Label notificationSettingsButton;

    @FXML
    private Label preferencesSettingsButton;

    @FXML
    private Button playSongButton;

    @FXML
    private Button makeSongButton;

    @FXML
    private Button checkPostsButton;

    @FXML
    private Button applyUsernameButton;

    @FXML
    private Button applyPasswordButton;

    @FXML
    private Button applyProfilePictureButton;

    @FXML
    private Button applyLanguageButton;

    @FXML
    private void goToPlaySong() throws IOException {
        App.setRoot("SongSearch");
    }

    @FXML
    private void goToMakeSong() throws IOException {
        App.setRoot("CreateSong");
    }

    @FXML
    private void goToCheckPosts() throws IOException {
        //App.setRoot("");
        System.out.println("goToCheckPosts triggered");
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void goToNotifications(MouseEvent event) throws IOException {
        App.setRoot("NotificationSettings");
    }

    @FXML
    private void goToPreferences(MouseEvent event) throws IOException {
        App.setRoot("PreferencesSettings");
    }

    @FXML
    private void initialize() {
        changeLanguageChoiceBox.getItems().addAll("English", "Spanish", "French", "German");
        changeLanguageChoiceBox.setValue("English");

        Theme.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == lightModeButton) {
                System.out.println("Light Mode selected");
            } else if (newToggle == darkModeButton) {
                System.out.println("Dark Mode selected");
            }
        });
    }

    @FXML
    private void applyUsername() {
        String newUsername = changeUsernameField.getText();
        // Logic to apply the new username
        System.out.println("New username applied: " + newUsername);
    }

    @FXML
    private void applyPassword() {
        String newPassword = changePasswordField.getText();
        // Logic to apply the new username
        System.out.println("New password applied: " + newPassword);
    }

    @FXML
    private void applyProfilePicture() {
        // Logic to apply the new profile picture
        System.out.println("New profile picture applied");
    }

    @FXML
    private void applyLanguage() {
        String selectedLanguage = changeLanguageChoiceBox.getValue();
        // Logic to apply the new language
        System.out.println("New language applied: " + selectedLanguage);
    }
}
