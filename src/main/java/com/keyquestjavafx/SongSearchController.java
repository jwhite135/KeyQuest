package com.keyquestjavafx;

import java.io.IOException;
import java.util.ArrayList;

import com.model.KeyQuestFACADE;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Controller class for the SongSearch page.
 * This class handles the search functionality for songs based on name, artist, or difficulty.
 * It also manages the display of search results and navigation to other pages.
 * @author Ryan Leadbitter
 */
public class SongSearchController {

    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private Label errorMessage;
    @FXML private VBox resultsBox;
    @FXML private Button searchSongButton;
    @FXML private Button makeSongButton;
    @FXML private Button checkPostsButton;
    @FXML private Label usernameLabel;
    @FXML private ImageView profilePicButton;

    private KeyQuestFACADE facade = KeyQuestFACADE.getInstance();

    @FXML
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    /**
     * Initializes the SongSearch page by populating the search type combo box and displaying all songs.
     * It also sets up the event handler for the search button.
     * This method is called automatically when the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        searchTypeCombo.getItems().addAll("Name", "Artist", "Difficulty");
        searchTypeCombo.setValue("Name");
        ArrayList<Song> results = facade.getAllSongs();
        for (Song song : results) {
            // Create label for title
            Label title = new Label(song.getName());
            title.getStyleClass().add("song-title");
        
            // Create label for artist/difficulty metadata
            Label meta = new Label("By " + song.getArtist() + " • Difficulty: " + song.getDifficulty());
            meta.getStyleClass().add("song-meta");
        
            VBox songContent = new VBox(title, meta);
            songContent.setSpacing(2);
        
            Button songButton = new Button();
            songButton.setGraphic(songContent);
            songButton.setMaxWidth(Double.MAX_VALUE);
            songButton.getStyleClass().add("search-result-button");
        
            songButton.setOnAction(e -> openSongView(song));
            
            resultsBox.getChildren().add(songButton);
        }
    }

    /**
     * Handles the search functionality when the search button is clicked.
     * It retrieves the search term and type, performs the search, and displays the results.
     * If no results are found or if there is an error, an appropriate message is displayed.
     */
    @FXML
    private void onSearch() {
        String query = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();
        resultsBox.getChildren().clear();
        errorMessage.setText("");

        if (query.isEmpty()) {
            errorMessage.setText("Please enter a search term.");
            return;
        }

        ArrayList<Song> results = new ArrayList<>();
        switch (searchType) {
            case "Name":
            results = facade.searchSongsByName(query);
            break;
            case "Artist":
            results = facade.searchSongsByArtist(query);
            break;
            case "Difficulty":
            try {
                int difficulty = Integer.parseInt(query);
                results = facade.searchSongsByDifficulty(difficulty);
            } catch (NumberFormatException e) {
                errorMessage.setText("Please enter a valid number for difficulty.");
            }
            break;
            default:
            errorMessage.setText("Invalid search type.");
            break;
        }

        if (results.isEmpty()) {
            errorMessage.setText("No songs found.");
        } else {
            for (Song song : results) {
                // Create label for title
                Label title = new Label(song.getName());
                title.getStyleClass().add("song-title");
            
                // Create label for artist/difficulty metadata
                Label meta = new Label("By " + song.getArtist() + " • Difficulty: " + song.getDifficulty());
                meta.getStyleClass().add("song-meta");
            
                VBox songContent = new VBox(title, meta);
                songContent.setSpacing(2);
            
                Button songButton = new Button();
                songButton.setGraphic(songContent);
                songButton.setMaxWidth(Double.MAX_VALUE);
                songButton.getStyleClass().add("search-result-button");
            
                songButton.setOnAction(e -> openSongView(song));
                
                resultsBox.getChildren().add(songButton);
            }
        }
    }

    /**
     * Opens the SongView page for the selected song.
     * It loads the SongView.fxml file and sets the selected song in the controller.
     * @param selectedSong The song to be displayed in the SongView.
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
            errorMessage.setText("Failed to open song view.");
        }
    }

    /**
     * Sets the KeyQuestFACADE instance for this controller.
     * This method is used to inject the facade into the controller.
     * @param facade The KeyQuestFACADE instance to be set.
     */
    public void setFacade(KeyQuestFACADE facade) {
        this.facade = facade;
    }

    /**
     * Goes to make song page when the make song button is clicked.
     * @throws IOException
     */
    @FXML private void goToMakeSong() throws IOException {
        App.setRoot("CreateSong2");
    }

    /**
     * Goes to check posts page when the check posts button is clicked.
     * @param username The username to be displayed.
     * @throws IOException
     */
    @FXML private void goToCheckPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    /**
     * Goes to profile page when the profile button is clicked.
     * @throws IOException
     */
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
}
