package com.keyquestjavafx;

import java.io.IOException;

import com.model.KeyQuestFACADE;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Label songErrorLabel;

    @FXML
    void goToHome(MouseEvent event) throws IOException {
        App.setRoot("HomePage");
    }
 
    @FXML
    void goToSettings() throws IOException {
        App.setRoot("AccountSettings");
    }

    @FXML
    void goToPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    @FXML
    void goToSong() throws IOException {
        String selectedSongName = favoriteSongsList.getSelectionModel().getSelectedItem();

    if (selectedSongName == null) {
        songErrorLabel.setText("Please select a song.");
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SongView.fxml"));
        Parent root = loader.load();

        SongViewController controller = loader.getController();
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        controller.setFacade(facade);

        // Find the actual Song object from the name
        User currentUser = facade.getUser();
        var selectedSong = currentUser.getFavoriteSongs().stream()
            .filter(song -> song.getName().equals(selectedSongName))
            .findFirst()
            .orElse(null);

        if (selectedSong == null) {
            songErrorLabel.setText("Song not found.");
            return;
        }

        controller.setSong(selectedSong);
        App.getScene().setRoot(root);

    } catch (IOException e) {
        e.printStackTrace();
        songErrorLabel.setText("Failed to open song view.");
    }
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
        
        favoriteSongsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click to open song
                try {
                    goToSong();
                } catch (IOException e) {
                    e.printStackTrace();
                    songErrorLabel.setText("Failed to open song view.");
                }
            }
        });
    }
}
