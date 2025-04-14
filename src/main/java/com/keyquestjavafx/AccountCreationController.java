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

    @FXML
    void goBack(MouseEvent event) throws IOException {
        App.setRoot("LandingPage");
    }

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