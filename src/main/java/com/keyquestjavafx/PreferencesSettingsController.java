package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class PreferencesSettingsController {
    @FXML
    private ChoiceBox defaultInstrumentChoiceBox;

    @FXML
    private Label applyInstrumentButton;

    @FXML
    private Label accountSettingsButton;

    @FXML
    private Label notificationsSettingsButton;

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
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void goToAccount() throws IOException {
        App.setRoot("AccountSettings");
    }

    @FXML
    private void goToNotification() throws IOException {
        App.setRoot("NotificationSettings");
    }

    @FXML
    private void applyDefaultInstrument() {
        // Get the selected instrument from the ChoiceBox
        String selectedInstrument = (String) defaultInstrumentChoiceBox.getValue();
        System.out.println("Selected instrument: " + selectedInstrument);
    }

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
