package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    void goToPlaySong() throws IOException {
        System.out.println("goToPlaySong triggered");
        App.setRoot("SongSearch");
    }

    @FXML
    void goToMakeSong() throws IOException {
        App.setRoot("CreateSong");
    }

    @FXML
    void goToCheckPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    @FXML
    void goToProfile() throws IOException {
        App.setRoot("ProfilePage");
    }
}
