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
}
