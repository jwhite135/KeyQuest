package com.keyquestjavafx;

import java.io.File;
import java.io.IOException;

import com.model.KeyQuestFACADE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller for the Account Settings page
 * This class handles the user input for changing account settings
 * It interacts with the KeyQuestFACADE to update user information
 * @author 
 */
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

    /**
     * Method to go to the song search page via Play Song button
     */
    @FXML
    private void goToPlaySong() throws IOException {
        App.setRoot("SongSearch");
    }

    /**
     * Method to go to the create song page via Make Song button
     */
    @FXML
    private void goToMakeSong() throws IOException {
        App.setRoot("CreateSong2");
    }

    /**
     * Method to go to the check posts page via Check Posts button
     */
    @FXML
    private void goToCheckPosts() throws IOException {
        //App.setRoot("");
        System.out.println("goToCheckPosts triggered");
    }

    /**
     * Method to go back to the home page via KeyQuestLogo png
     * @param event Clicking on the KeyQuestLogo image
     * @throws IOException If the page cannot be loaded
     */
    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        App.setRoot("HomePage");
    }

    /**
     * Method to go to the notification settings page via Notification Settings button
     * @param event Clicking on the Notification Settings button
     * @throws IOException If the page cannot be loaded
     */
    @FXML
    private void goToNotifications() throws IOException {
        App.setRoot("NotificationSettings");
    }

    /**
     * Method to go to the preferences settings page via Preferences Settings button
     * @param event Clicking on the Preferences Settings button
     * @throws IOException If the page cannot be loaded
     */
    @FXML
    private void goToPreferences() throws IOException {
        App.setRoot("PreferencesSettings");
    }

    /**
     * This method is called when the controller is initialized.
     * It sets up the initial state of the UI components by populating the choice box with languages
     * and setting the default theme toggle (light or dark mode).
     */
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

    /**
     * This method is called when the user clicks the "Apply" button for changing the username.
     * It retrieves the new username from the text field and applies it in the json.
     */
    @FXML
    private void applyUsername() {
        String newUsername = changeUsernameField.getText();
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        if (facade.checkUsername(newUsername)) {
            if (facade.checkUsernameExists(newUsername)) {
                System.out.println("Username already exists");
            } else if (newUsername.equals(facade.getCurrentUsername())) {
                System.out.println("Username is the same as the current one");
            } else {
                facade.getUser().setUsername(newUsername);
                System.out.println("New username applied: " + newUsername);
            }
        } else {
            System.out.println("Username is not valid");
        }
    }

    /**
     * This method is called when the user clicks the "Apply" button for changing the password.
     * It retrieves the new password from the text field and applies it in the json.
     */
    @FXML
    private void applyPassword() {
        String newPassword = changePasswordField.getText();
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        if (facade.checkPassword(newPassword)) {
            facade.getUser().setPassword(newPassword);
            System.out.println("New password applied: " + newPassword);
        } else {
            System.out.println("Password is not valid");
        }
    }

    /**
     * This method is called when the user clicks the "Apply" button for changing the profile picture.
     * It retrieves the new profile picture from the file chooser and applies it in the json.
     */
    @FXML
    private void applyLanguage() {
        String selectedLanguage = changeLanguageChoiceBox.getValue();
        // Logic to apply the new language
        System.out.println("New language applied: " + selectedLanguage);
    }

    /**
     * This method is called when the user clicks the "Apply" button for changing the profile picture.
     * It retrieves the new profile picture from the file chooser and applies it in the json.
     */
    @FXML
    private void chooseFile() {
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png")
        );

        File selectedFile = fileChooser.showOpenDialog((Stage) changeProfilePictureButton.getScene().getWindow());

        if (selectedFile != null) {
            try {
                // Define your target file path in the photos directory
                String photosDirPath = getClass().getClassLoader()
                .getResource("com/keyquestjavafx/images")
                .getPath();
                File photosDir = new File(photosDirPath);

                // Give the file a new name (you can customize this)
                String ext = getFileExtension(selectedFile);
                String newFileName = facade.getUser().getUsername() + "_profilepic" + ext;
                File destinationFile = new File(photosDir, newFileName);

                // Copy the file to the destination
                java.nio.file.Files.copy(
                    selectedFile.toPath(),
                    destinationFile.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );

                System.out.println("Saved file to: " + destinationFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        return (lastIndex > 0) ? name.substring(lastIndex) : "";
    }
}
