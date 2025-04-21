package com.keyquestjavafx;

import java.io.IOException;
import java.util.ArrayList;

import com.model.KeyQuestFACADE;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SongSearchController {

    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private Label errorMessage;
    @FXML private VBox resultsBox;

    private KeyQuestFACADE facade = KeyQuestFACADE.getInstance();

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    public void initialize() {
        searchTypeCombo.getItems().addAll("Name", "Artist", "Difficulty");
        searchTypeCombo.setValue("Name");
    }

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
                Label songLabel = new Label(song.toString());
                songLabel.getStyleClass().add("search-result");
                songLabel.setOnMouseClicked(event -> openSongView(song, event));
                resultsBox.getChildren().add(songLabel);
            }
        }
    }

    private void openSongView(Song selectedSong, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongView.fxml"));
            Parent root = loader.load();

            // Pass data to SongViewController
            SongViewController controller = loader.getController();
            controller.setFacade(facade);
            controller.setSong(selectedSong);

            Stage stage = (Stage) ((Control) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("Failed to open song view.");
        }
    }

    public void setFacade(KeyQuestFACADE facade) {
        this.facade = facade;
    }
}
