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
        App.setRoot("CreateSong2");
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
