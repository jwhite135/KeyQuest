package com.keyquestjavafx;

import java.util.HashMap;
import java.util.Map;

import com.model.Chord;
import com.model.KeyQuestFACADE;
import com.model.Measure;
import com.model.Note;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SongViewController {

    @FXML private ComboBox<String> instrumentDropdown;
    @FXML private VBox sheetMusicContainer;
    @FXML private Label titleLabel;
    @FXML private Label artistLabel;
    @FXML private Label genreLabel;
    @FXML private Label difficultyLabel;
    @FXML private Button playButton;
    @FXML private Button muteButton;
    @FXML private Button loopButton;
    @FXML private Button backButton;

    private Song song;
    private KeyQuestFACADE facade;

    private boolean loopEnabled = false;
    private boolean isMuted = false;

    private static final Map<String, Integer> pitchToY = new HashMap<>();
    static {
        pitchToY.put("C4", 80);
        pitchToY.put("D4", 75);
        pitchToY.put("E4", 70);
        pitchToY.put("F4", 65);
        pitchToY.put("G4", 60);
        pitchToY.put("A4", 55);
        pitchToY.put("B4", 50);
        pitchToY.put("C5", 45);
        pitchToY.put("D5", 40);
        pitchToY.put("E5", 35);
    }

    public void setSong(Song song) {
        this.song = song;
        updateMetadata();
        renderSheetMusic();
    }

    public void setFacade(KeyQuestFACADE facade) {
        this.facade = facade;
    }

    @FXML
    public void initialize() {
        instrumentDropdown.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        instrumentDropdown.setValue("Piano");
    }

    private void updateMetadata() {
        titleLabel.setText(song.getName());
        artistLabel.setText(song.getArtist());
        genreLabel.setText(song.getGenre().toString());
        difficultyLabel.setText(String.valueOf(song.getDifficulty()));
    }

    private void renderSheetMusic() {
        sheetMusicContainer.getChildren().clear();

        for (var sheet : song.getSheetMusic()) {
            for (Measure measure : sheet.getMeasures()) {
                Pane measurePane = new Pane();
                measurePane.setPrefHeight(100);
                measurePane.setPrefWidth(400);

                // Draw 5 staff lines
                for (int i = 0; i < 5; i++) {
                    Line line = new Line(0, 50 + i * 10, 400, 50 + i * 10);
                    line.setStroke(Color.LIGHTGRAY);
                    measurePane.getChildren().add(line);
                }

                int currentX = 20;
                for (Chord chord : measure.getChords()) {
                    for (Note note : chord.getNotes()) {
                        String pitch = note.getKey();
                        Integer y = pitchToY.get(pitch);
                        if (y != null) {
                            ImageView noteImage = getNoteImage(note);
                            noteImage.setLayoutX(currentX);
                            noteImage.setLayoutY(y);
                            measurePane.getChildren().add(noteImage);
                        }
                    }
                    currentX += 40;
                }

                sheetMusicContainer.getChildren().add(measurePane);
            }
        }
    }

    private ImageView getNoteImage(Note note) {
        String file;
        if ("q".equals(note.getLength())) {
            file = "quarter_note.png";
        } else if ("h".equals(note.getLength())) {
            file = "half_note.png";
        } else if ("e".equals(note.getLength())) {
            file = "eighth_note.png";
        } else {
            System.err.println("⚠️ Unsupported note length: " + note.getLength());
            return new ImageView();
        }

        var input = getClass().getResourceAsStream("/com/keyquestjavafx/images/" + file);
        if (input == null) {
            System.err.println("⚠️ Missing image file: " + file);
            return new ImageView();
        }

        Image img = new Image(input);
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        return view;
    }

    @FXML
    private void onPlay() {
        if (facade != null && song != null) {
            new Thread(() -> {
                do {
                    facade.playSong(song);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        break;
                    }
                } while (loopEnabled);
            }).start();
        }
    }

    @FXML
    private void onMute() {
        isMuted = !isMuted;
        System.out.println("Muted: " + isMuted);
    }

    @FXML
    private void onToggleLoop() {
        loopEnabled = !loopEnabled;
        System.out.println("Loop: " + loopEnabled);
    }

    @FXML
    private void onBack() {
        try {
            App.setRoot("SongSearch");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}