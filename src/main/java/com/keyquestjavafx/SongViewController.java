package com.keyquestjavafx;

import com.model.KeyQuestFACADE;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SongViewController {

    @FXML private Label titleLabel;
    @FXML private Label artistLabel;
    @FXML private Label difficultyLabel;
    @FXML private ComboBox<String> instrumentDropdown;

    private KeyQuestFACADE facade;
    private Song song;
    private boolean loop;

    public void setSongFacade(KeyQuestFACADE facade) {
        this.facade = facade;
    }

    public void setSong(Song song) {
        this.song = song;
        populateSongData();
    }

    @FXML
    public void initialize() {
        instrumentDropdown.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        instrumentDropdown.setValue("Piano");
    }

    private void populateSongData() {
        if (song != null) {
            titleLabel.setText(song.getName());
            artistLabel.setText("By " + song.getArtist());
            difficultyLabel.setText("Difficulty: " + song.getDifficulty());
        }
    }

    // TODO: Add instrument functionality to backend
    // TODO: Add looping functionality
    @FXML
    private void onPlay() {
        if (song == null || facade == null) return;

        System.out.println("Playing song: " + song.getName());
        facade.playSong(song);
    }

    // TODO: add mute functionality
    @FXML
    private void onMute() {
        System.out.println("Mute toggled.");
    }

    // TODO: add metronome functionality
    @FXML
    private void onToggleMetronome() {
        System.out.println("Metronome toggled.");
    }

    @FXML
    private void onToggleLoop() {
        loop = !loop;
    }
}
