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