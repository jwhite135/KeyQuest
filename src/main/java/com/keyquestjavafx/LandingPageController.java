package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the Landing page
 * This class handles the user input for navigating to different pages
 * @author Josiah White
 */
public class LandingPageController {

    @FXML
    private Button createAccountButton;
    @FXML
    private Button logInButton;

    /**
     * These methods are used to navigate to the different pages via buttons 
     */
    @FXML
    private void createAccount() throws IOException {
        App.setRoot("AccountCreation");
    }
    @FXML
    private void logIn() throws IOException {
        App.setRoot("Login");
    }
}
