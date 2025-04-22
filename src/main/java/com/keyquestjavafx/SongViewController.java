package com.keyquestjavafx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.model.Chord;
import com.model.KeyQuestFACADE;
import com.model.Measure;
import com.model.Note;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        genreLabel.setText(song.getGenre().toString().toLowerCase());
        difficultyLabel.setText(String.valueOf(song.getDifficulty()));
    }

    private void renderSheetMusic() {
        sheetMusicContainer.getChildren().clear();

        for (var sheet : song.getSheetMusic()) {
            for (Measure measure : sheet.getMeasures()) {
                Pane measurePane = new Pane();
                measurePane.setPrefHeight(150);
                measurePane.setPrefWidth(500);

                // Draw 5 staff lines
                for (int i = 0; i < 5; i++) {
                    Line line = new Line(0, 90 + i * 10, 500, 90 + i * 10);
                    line.setStroke(Color.LIGHTGRAY);
                    measurePane.getChildren().add(line);
                }

                // Draw clef symbol and time signature
                ImageView clef = loadSymbol("treble_clef.png", 60);
                if (clef != null) {
                    clef.setLayoutX(5);
                    clef.setLayoutY(85);
                    measurePane.getChildren().add(clef);
                }

                ImageView timeSig = loadSymbol("time_signature_4_4.png", 40);
                if (timeSig != null) {
                    timeSig.setLayoutX(60);
                    timeSig.setLayoutY(90);
                    measurePane.getChildren().add(timeSig);
                }

                int currentX = 110;
                for (Chord chord : measure.getChords()) {
                    for (Note note : chord.getNotes()) {
                        String pitch = note.getKey();
                        Integer y = pitchToY.get(pitch);
                        if (y != null) {
                            ImageView noteImage = getNoteImage(note);
                            noteImage.setLayoutX(currentX);
                            noteImage.setLayoutY(y - 30);
                            measurePane.getChildren().add(noteImage);

                            if (note.isSharp()) {
                                ImageView sharp = loadSymbol("sharp.png", 20);
                                if (sharp != null) {
                                    sharp.setLayoutX(currentX - 15);
                                    sharp.setLayoutY(y - 25);
                                    measurePane.getChildren().add(sharp);
                                }
                            } else if (note.isFlat()) {
                                ImageView flat = loadSymbol("flat.png", 20);
                                if (flat != null) {
                                    flat.setLayoutX(currentX - 15);
                                    flat.setLayoutY(y - 25);
                                    measurePane.getChildren().add(flat);
                                }
                            }
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
        if (null == note.getLength()) {
            System.err.println("Unsupported note length: " + note.getLength());
            return new ImageView();
        } else switch (note.getLength()) {
            case "q":
                file = "quarter_note.png";
                break;
            case "h":
                file = "half_note.png";
                break;
            case "i":
                file = "eighth_note.png";
                break;
            case "w":
                file = "whole_note.png";
                break;
            case "s":
                file = "sixteenth_note.png";
                break;
            default:
                System.err.println("Unsupported note length: " + note.getLength());
                return new ImageView();
        }

        var input = getClass().getResourceAsStream("/com/keyquestjavafx/images/" + file);
        if (input == null) {
            System.err.println("Missing image file: " + file);
            return new ImageView();
        }

        Image img = new Image(input);
        ImageView view = new ImageView(img);
        view.setFitHeight(60); // scale up
        view.setPreserveRatio(true);
        return view;
    }

    private ImageView loadSymbol(String filename, double height) {
        var input = getClass().getResourceAsStream("/com/keyquestjavafx/images/" + filename);
        if (input == null) {
            System.err.println("Missing symbol file: " + filename);
            return null;
        }
        ImageView img = new ImageView(new Image(input));
        img.setFitHeight(height);
        img.setPreserveRatio(true);
        return img;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongSearch.fxml"));
            Parent root = loader.load();
            SongSearchController controller = loader.getController();
            controller.setFacade(facade);
            backButton.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
