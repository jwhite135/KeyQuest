package com.keyquestjavafx;

import java.io.IOException;

import com.model.KeyQuestFACADE;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ProfilePageController {
 
    @FXML
    private Button backButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label bioLabel;

    @FXML
    private ListView<String> favoriteSongsList;

    @FXML
    private ListView<String> favoritePostsList;

    @FXML
    private Button settingsButton;

    @FXML
    void goToHome(MouseEvent event) throws IOException {
        App.setRoot("HomePage");
    }
 
    @FXML
    void goToSettings() throws IOException {
        App.setRoot("AccountSettings");
    }

    @FXML
    public void initialize() {
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        User currentUser = facade.getUser();

        if (currentUser != null) {
            usernameLabel.setText(currentUser.getUsername());
            // Populate favorite songs
            currentUser.getFavoriteSongs().forEach(song -> favoriteSongsList.getItems().add(song.getName()));
            // Populate favorite posts
            currentUser.getFavoritePosts().forEach(post -> favoritePostsList.getItems().add(post.getBody()));
        }
    }
}
