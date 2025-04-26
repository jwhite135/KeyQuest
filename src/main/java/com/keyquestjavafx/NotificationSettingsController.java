package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label accountSettingsButton;

    @FXML
    private Label preferencesSettingsButton;

    @FXML
    private ImageView homePageButton;

    @FXML
    private Button playSongButton;

    @FXML
    private Button makeSongButton;

    @FXML
    private Button checkPostsButton;

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
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void goToAccount() throws IOException {
        App.setRoot("AccountSettings");
    }

    @FXML
    private void goToPreferences() throws IOException {
        App.setRoot("PreferencesSettings");
    }

    @FXML
    private void newFriendCheckClicked(MouseEvent event) {
        if (newFriendCheckBox.isSelected()) {
            System.out.println("New friend check box is selected.");
        } else {
            System.out.println("New friend check box is not selected.");
        }
    }

    @FXML
    private void likeCheckClicked(MouseEvent event) {
        if (likeCheckBox.isSelected()) {
            System.out.println("Like check box is selected.");
        } else {
            System.out.println("Like check box is not selected.");
        }
    }

    @FXML
    private void friendPostCheckClicked(MouseEvent event) {
        if (friendPostCheckBox.isSelected()) {
            System.out.println("Friend post check box is selected.");
        } else {
            System.out.println("Friend post check box is not selected.");
        }
    }

    @FXML
    private void updateCheckClicked(MouseEvent event) {
        if (updateCheckBox.isSelected()) {
            System.out.println("Update check box is selected.");
        } else {
            System.out.println("Update check box is not selected.");
        }
    }
}
