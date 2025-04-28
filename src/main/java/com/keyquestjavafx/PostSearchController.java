package com.keyquestjavafx;

import java.io.IOException;
import java.util.List;

import com.model.KeyQuestFACADE;
import com.model.Post;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller for the Post Search page
 * This class handles the user input for searching and sorting posts
 * It interacts with the KeyQuestFACADE to retrieve post data
 * @author Owen Coulam
 */
public class PostSearchController {

    @FXML private Label usernameLabel;
    @FXML private ImageView profilePicButton;

    @FXML private Button searchPostButton;
    @FXML private Button makePostButton;
    @FXML private Button searchSongButton;

    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private TextField   searchField;
    @FXML private Button      searchButton;

    @FXML private ComboBox<String> sortCombo;
    @FXML private Button         sortButton;

    @FXML private Label errorMessage;
    @FXML private VBox  resultsBox;

    private final KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
    private List<Post> currentResults;
    private boolean setSong = false;

    /**
     * Called immediately after loading the FXML.
     * Initializes the search and sort options and populates the results box
     */
    @FXML
    public void initialize() {

        // searchType options
        searchTypeCombo.setItems(FXCollections.observableArrayList(
            "Title", "Song", "User"
        ));
        searchTypeCombo.getSelectionModel().selectFirst();

        // sort options
        sortCombo.setItems(FXCollections.observableArrayList(
            "Most Recent", "Most Liked"
        ));
        sortCombo.getSelectionModel().selectFirst();
        currentResults = facade.getAllPosts();
        refreshResults();
    }

    /**
     * Sets the song for the search when the user clicks on a song
     * @param song The song to search for
     */
    public void setSong(String song){
        setSong = true;
        currentResults = facade.searchPostsBySong(song);
        searchTypeCombo.setValue("Song");
        searchField.setText(song);
        refreshResults();
    }

    /**
     * Method to search for posts based on the selected type and query
     * If the search fails, an error message is displayed
     */
    @FXML
    private void onSearch() {
        errorMessage.setText("");
        String type  = searchTypeCombo.getValue();
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            errorMessage.setText("Please enter a search term.");
            return;
        }

        switch(type) {
            case "Title":
                currentResults = facade.searchPostsByName(query);
                break;
            case "Song":
                currentResults = facade.searchPostsBySong(query);
                break;
            case "User":
                currentResults = facade.searchPostsByAuthor(query);
                break;
            default:
                errorMessage.setText("Invalid search type.");
                return;
        }

        if (currentResults.isEmpty()) {
            errorMessage.setText("No posts found.");
        }
        refreshResults();
    }

    /**
     * When sort button is clicked, sort the posts by the selected criteria
     * If the sort fails, an error message is displayed
     * @param event When the sort button is clicked
     */
    @FXML
    private void onSort(ActionEvent event) {
        if (currentResults == null) {
            errorMessage.setText("Nothing to sort.");
            return;
        }
        String sortBy = sortCombo.getValue();
        if ("Most Recent".equals(sortBy)) {
            currentResults = facade.sortPostsByMostRecent();
        } else {
            currentResults = facade.sortPostsByMostLiked();
        }
        refreshResults();
    }

    /**
     * Refreshes the results box with the current search results
     * Clears the previous results and adds new ones
     * Each post is displayed as a clickable card with title and metadata
     */
    private void refreshResults() {
        resultsBox.getChildren().clear();
        for (Post p : currentResults) {
            VBox card = new VBox(5);

            Label title = new Label(p.getTitle());
            title.setStyle("-fx-font-weight: bold;");

            // Build TextFlow with conditional heart color
            Text beforeHeart = new Text(
                "By " + p.getAuthor().getUsername() +
                " • Song: " + p.getSong().getName() +
                " • " + p.getDate().toString() +
                " • "
            );
            beforeHeart.setFill(Color.BLACK);

            // Heart, red if liked, black otherwise
            boolean liked = facade.hasUserLiked(p);
            Text heart = new Text("\u2764 ");  // ❤
            heart.setFill(liked ? Color.RED : Color.BLACK);

            Text likes = new Text(String.valueOf(p.getFavorites()));
            likes.setFill(Color.BLACK);

            TextFlow metaFlow = new TextFlow(beforeHeart, heart, likes);

            card.getChildren().addAll(title, metaFlow);
            card.setStyle("-fx-padding: 10; -fx-background-color: white; "
                     + "-fx-border-color: lightgray;");
            card.setOnMouseClicked(e -> openPost(p));

            resultsBox.getChildren().add(card);
        }
    }

    /**
     * Method to open the post view when a post is clicked
     * Will give an error message if the post fails to open
     * @param selectedPost The post to be opened
     */
    @FXML
    private void openPost(Post selectedPost) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostView.fxml"));
            Parent root = loader.load();
    
            PostViewController controller = loader.getController();
            controller.setFacade(KeyQuestFACADE.getInstance());
            controller.setPost(selectedPost);
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("Failed to open post.");
        }
    }

    /**
     * Below are the methods for the buttons to navigate to other pages in the app:
     * - Go to Profile
     * - Go to Make Post
     * - Go to Search Songs
     * - Go to Home
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

    @FXML private void goToMakePost(ActionEvent e) throws IOException {
        App.setRoot("MakePost");
    }

    @FXML private void goToSearchSongs(ActionEvent e) throws IOException {
        App.setRoot("SongSearch");
    }

    @FXML private void goToHome(MouseEvent e) throws IOException {
        App.setRoot("HomePage");
    }
}
