package com.keyquestjavafx;

import java.io.File;
import java.io.IOException;

import com.model.KeyQuestFACADE;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Controller for the Home page
 * This class handles the user input for navigating to different pages
 * @author Matthew Radin, Ian Attmore
 */
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

    private KeyQuestFACADE facade = KeyQuestFACADE.getInstance();

    /**
     * Included below are the buttons to go to the different pages including:
     * - Play Song
     * - Make Song
     * - Check Posts
     * - Profile
     */

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
        App.setRoot("PostSearch");
    }

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

    @FXML
    private void goToProfilePage() throws IOException {
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

    /**
     * Method to initialize the HomePageController
     * This method is called when the controller is loaded
     * It sets the username label and user image
     */
    @FXML
    public void initialize() {
        // Set the username label to the current user's username
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        if (facade.getUser() != null) {
            username.setText(facade.getUser().getUsername() + "   ");
        }

        String photosDirPath = getClass().getClassLoader()
                .getResource("com/keyquestjavafx/images")
                .getPath();
        System.out.println("photosDirPath: " + photosDirPath + facade.getUser().getUsername() + "_profile.png");
        
        File file = new File(photosDirPath + facade.getUser().getUsername()+ "_profilepic.png");

        if (file.exists()) {
            userImage.setImage(new javafx.scene.image.Image(file.toURI().toString()));
        }
    }

    /**
     * Method to log out the user
     * This method is called when the user clicks the logout button
     * It calls the logout method from the facade and navigates back to the landing page
     */
    @FXML
    private void logoutUser() throws IOException {
        // Call the logout method from the facade
        facade.logout();
        
        // Navigate back to the landing page
        App.setRoot("LandingPage");
    }
}

