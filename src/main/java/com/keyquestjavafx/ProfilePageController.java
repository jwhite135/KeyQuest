package com.keyquestjavafx;

import java.io.IOException;

import com.model.KeyQuestFACADE;
import com.model.Post;
import com.model.Song;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controller for the Profile page
 * This class handles the user input for viewing and interacting with the profile page
 * It interacts with the KeyQuestFACADE to retrieve user data
 * @author Matthew Radin
 */
public class ProfilePageController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label bioLabel;

    @FXML
    private VBox favoriteSongsContainer;

    @FXML
    private VBox favoritePostsContainer;

    /**
     * Method to go back to the home page via back button png
     */
    @FXML
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    /**
     * Method to go to the settings page via settings button png
     */
    @FXML
    private void goToSettings() throws IOException {
        App.setRoot("AccountSettings");
    }

    /**
     * Instantiates the KeyQuestFACADE
     * This is used to interact with the model layer
     */
    private KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
    private User currentUser;

    /**
     * Method to set the current user
     * This method is called when the user logs in or creates an account
     * Sets up the profile page containers for favorite songs and posts
     * @param user The user object representing the current user
     */
    public void setUser(User user) {
        this.currentUser = user;
        if (currentUser != null) {
            usernameLabel.setText(currentUser.getUsername());

            if (currentUser.getFavoriteSongs().isEmpty()) {
                favoriteSongsContainer.getChildren().add(new Label("No favorite songs yet!"));
            }

            if (currentUser.getFavoritePosts().isEmpty()) {
                favoritePostsContainer.getChildren().add(new Label("No favorite posts yet!"));
            }
            
            // Populate favorite songs
            favoriteSongsContainer.getChildren().clear();
            currentUser.getFavoriteSongs().forEach(song -> {
                Button songButton = createSongButton(song);
                favoriteSongsContainer.getChildren().add(songButton);
            });

            // Populate favorite posts
            favoritePostsContainer.getChildren().clear();
            currentUser.getFavoritePosts().forEach(post -> {
                Button postButton = createPostButton(post);
                favoritePostsContainer.getChildren().add(postButton);
            });
        }
    }

    /**
     * Method creates the button for the song
     * Is modeled after the search result button for consistency in the UI
     * @param song The song object representing the favorite song of the user
     */
    private Button createSongButton(Song song) {
        Label title = new Label(song.getName());
        title.getStyleClass().add("song-title");

        Label meta = new Label("By " + song.getArtist() + " • Difficulty: " + song.getDifficulty());
        meta.getStyleClass().add("song-meta");

        VBox songContent = new VBox(title, meta);
        songContent.setSpacing(2);

        Button songButton = new Button();
        songButton.setGraphic(songContent);
        songButton.setMaxWidth(Double.MAX_VALUE);
        songButton.getStyleClass().add("search-result-button");

        songButton.setOnAction(e -> openSongView(song));
        return songButton;
    }

    /**
     * Method creates the button for the post
     * Is modeled after the search result button for consistency in the UI
     * @param post The post object representing the favorite post of the user
     */
    private Button createPostButton(Post post) {
        Label title = new Label(post.getTitle());
        title.getStyleClass().add("post-title");

        Label meta = new Label("By " + post.getAuthor().getUsername() + " • " + post.getDate());
        meta.getStyleClass().add("post-meta");

        VBox postContent = new VBox(title, meta);
        postContent.setSpacing(2);

        Button postButton = new Button();
        postButton.setGraphic(postContent);
        postButton.setMaxWidth(Double.MAX_VALUE);
        postButton.getStyleClass().add("search-result-button");

        postButton.setOnAction(e -> openPostView(post));
        return postButton;
    }

    /**
     * Method to open the song view
     * This method is called when the user clicks on a favorite song
     * It opens the song view page with the selected song
     * @param selectedSong The song object representing the selected song
     */
    private void openSongView(Song selectedSong) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongView.fxml"));
            Parent root = loader.load();

            SongViewController controller = loader.getController();
            controller.setFacade(KeyQuestFACADE.getInstance());
            controller.setSong(selectedSong);

            App.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to open the post view
     * This method is called when the user clicks on a favorite post
     * It opens the post view page with the selected post
     * @param selectedPost The post object representing the selected post
     */
    private void openPostView(Post selectedPost) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostView.fxml"));
            Parent root = loader.load();

            PostViewController controller = loader.getController();
            controller.setPost(selectedPost);

            App.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
