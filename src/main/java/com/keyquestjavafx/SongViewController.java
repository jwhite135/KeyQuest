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
    @FXML private Button searchSongButton;
    @FXML private Button makeSongButton;
    @FXML private Button checkPostsButton;
    @FXML private Label usernameLabel;
    @FXML private ImageView profilePicButton;

    private Song song;
    private KeyQuestFACADE facade;

    private boolean loopEnabled = false;
    private boolean isMuted = false;

    private static final Map<String, Integer> pitchToY = new HashMap<>();
    static {
        pitchToY.put("C4", 130);
        pitchToY.put("D4", 125);
        pitchToY.put("E4", 120);
        pitchToY.put("F4", 115);
        pitchToY.put("G4", 110);
        pitchToY.put("A4", 105);
        pitchToY.put("B4", 100);
        pitchToY.put("C5", 95);
        pitchToY.put("D5", 90);
        pitchToY.put("E5", 85);
        pitchToY.put("F5", 80);
        pitchToY.put("G5", 75);
        pitchToY.put("R", 100); // Rest
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
        facade = KeyQuestFACADE.getInstance();
        if (usernameLabel != null) {
            usernameLabel.setText("Welcome, " + facade.getCurrentUser().getUsername());
        }
    }

    private void updateMetadata() {
        titleLabel.setText("Name: " + song.getName());
        artistLabel.setText("Artist: " + song.getArtist());
        genreLabel.setText("Genre: " + song.getGenre().toString().toLowerCase());
        difficultyLabel.setText("Difficulty: " + String.valueOf(song.getDifficulty()));
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
                ImageView timeSig;
                if (song.getSheetMusic().get(0).getTimeSigNum() == 4 && song.getSheetMusic().get(0).getTimeSigDen() == 4) {
                    timeSig = loadSymbol("time_signature_4_4.png", 58);
                    timeSig.setLayoutY(82);
                } else if (song.getSheetMusic().get(0).getTimeSigNum() == 3 && song.getSheetMusic().get(0).getTimeSigDen() == 4) {
                    timeSig = loadSymbol("time_signature_3_4.png", 40);
                    timeSig.setLayoutY(90);
                } else if (song.getSheetMusic().get(0).getTimeSigNum() == 2 && song.getSheetMusic().get(0).getTimeSigDen() == 4) {
                    timeSig = loadSymbol("time_signature_2_4.png", 58);
                    timeSig.setLayoutY(82);
                } else if (song.getSheetMusic().get(0).getTimeSigNum() == 6 && song.getSheetMusic().get(0).getTimeSigDen() == 8) {
                    timeSig = loadSymbol("time_signature_6_8.png", 58);
                    timeSig.setLayoutY(82);
                } else {
                    timeSig = loadSymbol("time_signature_4_4.png", 58); // Default to 4/4 if not recognized
                }
                if (timeSig != null) {
                    timeSig.setLayoutX(50);
                    measurePane.getChildren().add(timeSig);
                }

                int currentX = 110;
                for (Chord chord : measure.getChords()) {
                    for (Note note : chord.getNotes()) {
                        String pitch = note.getKey();
                        Integer y = pitchToY.get(pitch);
                        if (y != null) {
                            
                            int[] yOffset = new int[1];
                            ImageView noteImage = getNoteImage(note, yOffset);

                            noteImage.setLayoutX(currentX);
                            noteImage.setLayoutY(y - yOffset[0]); // Align the notehead center to pitch Y
                            measurePane.getChildren().add(noteImage);

                            if (note.isSharp()) {
                                ImageView sharp = loadSymbol("sharp.png", 20); // same for flat
                                if (sharp != null) {
                                    sharp.setLayoutX(currentX - 15);
                                    if (note.getLength().equals("w")) {
                                        sharp.setLayoutY(y - (yOffset[0] + 5));
                                    } else {
                                        sharp.setLayoutY(y - (yOffset[0]-20));
                                    }
                                    measurePane.getChildren().add(sharp);
                                }

                            } else if (note.isFlat()) {
                                ImageView flat = loadSymbol("flat.png", 20); // same for flat
                                if (flat != null) {
                                    flat.setLayoutX(currentX - 12); // place left of notehead
                                    if (note.getLength().equals("w")) {
                                        flat.setLayoutY(y - (yOffset[0] + 5));
                                    } else {
                                        flat.setLayoutY(y - (yOffset[0]-20));
                                    }
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

    private ImageView getNoteImage(Note note, int[] yOffsetOut) {
        String file;
        int offset;
        int imageHeight;
    
        boolean isRest = note.getKey().equals("R");
    
        switch (note.getLength()) {
            case "q":
                file = isRest ? "quarter_rest.png" : "quarter_note.png";
                imageHeight = isRest ? 30 : 40;
                offset = isRest ? 0 : 25;
                break;
            case "i":
                file = isRest ? "eighth_rest.png" : "eighth_note.png";
                imageHeight = isRest ? 30 : 40;
                offset = isRest ? 0 : 25;
                break;
            case "w":
                file = isRest ? "whole_rest.png" : "whole_note.png";
                imageHeight = isRest ? 12 : 10;      // match notehead sizing
                offset = isRest ? 0 : -5;
                break;
            case "h":
                file = isRest ? "half_rest.png" : "half_note.png";
                imageHeight = isRest ? 12 : 40;
                offset = isRest ? 0 : 25;
                break;
            default:
                System.err.println("Unsupported note length: " + note.getLength());
                yOffsetOut[0] = 0;
                return new ImageView();
        }
    
        var input = getClass().getResourceAsStream("/com/keyquestjavafx/images/" + file);
        if (input == null) {
            System.err.println("Missing image file: " + file);
            yOffsetOut[0] = 0;
            return new ImageView();
        }
    
        ImageView view = new ImageView(new Image(input));
        view.setPreserveRatio(true);
        if (isRest) {
            view.setFitHeight(imageHeight * 0.75);  // Only rests are scaled down
        } else {
            view.setFitHeight(imageHeight);
        }
        yOffsetOut[0] = offset;
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

    @FXML private void goToPlaySong() throws IOException {
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

    @FXML private void goToMakeSong() throws IOException {
        App.setRoot("CreateSong2");
    }

    @FXML private void goToCheckPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    @FXML private void goToProfile() throws IOException {
        App.setRoot("ProfilePage");
    }
}
