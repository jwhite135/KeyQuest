package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LandingPageController {

    @FXML
    private Button createAccountButton;
    @FXML
    private Button logInButton;

    @FXML
    private void createAccount() throws IOException {
        App.setRoot("AccountCreation");
    }
    @FXML
    private void logIn() throws IOException {
        App.setRoot("Login");
    }
}
