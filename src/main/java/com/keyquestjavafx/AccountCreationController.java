package com.keyquestjavafx;

import java.io.IOException;

import com.model.KeyQuestFACADE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controller for the Account Creation page
 * This class handles the user input for creating a new account
 * It interacts with the KeyQuestFACADE to create a new user
 * @author 
 */
public class AccountCreationController {

        @FXML
    private Button accountCreationButton;

    @FXML
    private TextField accountCreationEmailField;

    @FXML
    private PasswordField accountCreationPasswordField;

    @FXML
    private TextField accountCreationUsernameField;

    @FXML
    private ImageView backAccountCreationArrow;

    @FXML
    private Label errorMessage;

    /**
     * Method to go back to the landing page via back button png
     * @param event Clicking on the back button image
     * @throws IOException If the page cannot be loaded
     */
    @FXML
    void goBack(MouseEvent event) throws IOException {
        App.setRoot("LandingPage");
    }

    /**
     * Method to create a new account, leads to the home page if successful
     * If the account creation fails, an error message is displayed
     * @param event Clicking on the create account button
     * @throws IOException If the page cannot be loaded
     */
    @FXML
    void createAccount(MouseEvent event) throws IOException {
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        if(!facade.makeUser(accountCreationUsernameField.getText(), accountCreationPasswordField.getText(), accountCreationEmailField.getText())) {
            errorMessage.setText("Invalid inputs or account already exists. Please try again.");
        }
        else {
            App.setRoot("HomePage");
        }
    }

}