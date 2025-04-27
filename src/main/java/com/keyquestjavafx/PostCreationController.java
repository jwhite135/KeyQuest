package com.keyquestjavafx;

import java.io.IOException;

import com.model.KeyQuestFACADE;
import com.model.Post;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PostCreationController {

    // Top‐bar
    @FXML private ImageView logoImageView;
    @FXML private Label     usernameLabel;
    @FXML private ImageView profilePicButton;

    // Form fields
    @FXML private Label     songLabel;
    @FXML private TextField titleField;
    @FXML private TextArea  bodyField;
    @FXML private Button    submitButton;
    @FXML private Label     errorMessage;

    private final KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
    private Song song;   // must be set by whoever opens this page

    @FXML
    public void initialize() {
        // show current user
        usernameLabel.setText("Welcome, " + facade.getCurrentUsername());
    }

    /**
     * Called immediately after loading the FXML.
     * Ensures we know which Song this Post is for.
     */
    public void setSong(Song song) {
        this.song = song;
        String name = (song != null ? song.getName() : "Unknown Song");
        String artist = (song != null ? song.getArtist() : "Unknown Author");
        songLabel.setText(" " + name + " by " + artist);
    }

    /** Handle the user clicking “Submit” */
    @FXML
    private void handleSubmit() {
        String title = titleField.getText().trim();
        String body  = bodyField.getText().trim();
        if (title.isEmpty() || body.isEmpty()) {
            // you could show an alert here instead
            errorMessage.setText("Title and body must not be empty.");
            return;
        }

        Post newPost = new Post(song, facade.getUser(), false, title, body);
        facade.makePost(newPost);

        // Navigate to the PostView page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostView.fxml"));
            Parent root = loader.load();

            PostViewController pvc = loader.getController();
            pvc.setFacade(facade);
            pvc.setPost(newPost);

            App.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Navigation handlers

    @FXML
    private void goToProfile() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            Parent root = loader.load();
    
            ProfilePageController controller = loader.getController();
            controller.setUser(facade.getUser());
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }
}
