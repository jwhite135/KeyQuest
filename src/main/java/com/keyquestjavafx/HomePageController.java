package com.keyquestjavafx;

import java.io.IOException;

import com.model.KeyQuestFACADE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HomePageController {

    @FXML
    private Button playSongButton;

    @FXML
    private Button makeSongButton;

    @FXML
    private Button checkPostsButton;

    @FXML
    private Button profileButton;

    @FXML
    private Label username;

    @FXML
    private ImageView userImage;

    @FXML
    void goToPlaySong() throws IOException {
        System.out.println("goToPlaySong triggered");
        App.setRoot("SongSearch");
    }

    @FXML
    void goToMakeSong() throws IOException {
        App.setRoot("CreateSong2");
    }

    @FXML
    void goToCheckPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    @FXML
    void goToProfile() throws IOException {
        App.setRoot("ProfilePage");
    }

    @FXML
    void goToProfilePage() throws IOException {
        App.setRoot("ProfilePage");
    }

    @FXML
    public void initialize() {
        // Set the username label to the current user's username
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        if (facade.getUser() != null) {
            username.setText(facade.getUser().getUsername() + "   ");
        }
    }
}

