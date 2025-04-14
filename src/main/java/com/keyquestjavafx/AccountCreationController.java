package com.keyquestjavafx;

import java.io.IOException;

import javafx.fxml.FXML;

public class AccountCreationController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}