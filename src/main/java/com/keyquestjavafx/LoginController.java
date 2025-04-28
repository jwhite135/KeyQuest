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
 * Controller for the Login page
 * This class handles the user input for logging in
 * It interacts with the KeyQuestFACADE to authenticate the user
 * @author Josiah White
 */
public class LoginController {

    @FXML
    private ImageView backLoginArrow;

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField loginUsernameField;

    @FXML
    void goBack(MouseEvent event) throws IOException {
        App.setRoot("LandingPage");
    }

    /**
     * Method to log in the user, leads to the home page if successful
     * If the login fails, an error message is displayed
     * @param event Clicking on the login button
     */
    @FXML
    void login(MouseEvent event) throws IOException {
        KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
        if(!facade.login(loginUsernameField.getText(), loginPasswordField.getText())) {
            errorMessage.setText("Invalid username or password. Please try again.");
        }
        else {
            App.setRoot("HomePage");
        }
        
    }
}
