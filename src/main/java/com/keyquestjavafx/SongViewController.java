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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * The SongViewController class is responsible for displaying the song details and sheet music in the UI.
 * It handles user interactions such as playing, muting, looping the song, and navigating to other views.
 * @author Ryan Leadbitter
 */
public class SongViewController {

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
    @FXML private ImageView backArrow;
    @FXML private Button createPostButton;
    @FXML private Button viewPostsButton;
    

    private Song song;
    private KeyQuestFACADE facade;
    private final String defaultInstrument = "Piano";

    private boolean loopEnabled = false;
    private boolean isMuted = false;

    /**
     * * Mapping of pitch names to Y coordinates for rendering notes on the sheet music.
     */
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
        pitchToY.put("R", 100);
    }

    /**
     * Sets the song to be displayed in the view.
     * @param song The song to be displayed.
     */
    public void setSong(Song song) {
        this.song = song;
        updateMetadata();
        renderSheetMusic();
    }

    /**
     * Sets the KeyQuestFACADE instance for the controller.
     * @param facade The KeyQuestFACADE instance to be set.
     */
    public void setFacade(KeyQuestFACADE facade) {
        this.facade = facade;
    }

    /**
     * Initializes the controller and sets the username label.
     */
    @FXML
    public void initialize() {
        facade = KeyQuestFACADE.getInstance();
        if (usernameLabel != null) {
            usernameLabel.setText("Welcome, " + facade.getCurrentUser().getUsername());
        }
    }

    /**
     * Sets labels for the song's title, artist, genre, and difficulty.
     * The difficulty is represented by a string of stars (★) and empty stars (☆).
     * This method is called to update the metadata displayed in the UI.
     */
    private void updateMetadata() {
        titleLabel.setText(song.getName());
        artistLabel.setText(song.getArtist());
        genreLabel.setText(song.getGenre().toString().toLowerCase());
        difficultyLabel.setText(getRatingStars(song.getDifficulty()));
    }
    
    /**
     * Converts the difficulty level to a string of stars (★) and empty stars (☆).
     * @param difficulty The difficulty level (1-5).
     * @return A string representing the difficulty level with stars.
     */
    private String getRatingStars(int difficulty) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            stars.append("★");
        }
        for (int i = difficulty; i < 5; i++) {
            stars.append("☆"); 
        }
        return stars.toString();
    }

    /**
     * Renders the sheet music for the song by creating a visual representation of the notes and measures.
     * This method clears the existing sheet music container and adds new elements based on the song's data.
     * It creates a pane for each measure, draws lines for the staff, and adds note images based on their pitch and length.
     * It also adds symbols for the treble clef and time signature.
     * The method uses a mapping of pitch names to Y coordinates to position the notes correctly on the staff.
     */
    private void renderSheetMusic() {
        sheetMusicContainer.getChildren().clear();

        for (var sheet : song.getSheetMusic()) {
            for (Measure measure : sheet.getMeasures()) {
                Pane measurePane = new Pane();
                measurePane.setPrefHeight(150);
                measurePane.setPrefWidth(500);

                for (int i = 0; i < 5; i++) {
                    Line line = new Line(0, 90 + i * 10, 500, 90 + i * 10);
                    line.setStroke(Color.LIGHTGRAY);
                    measurePane.getChildren().add(line);
                }

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
                    timeSig = loadSymbol("time_signature_4_4.png", 58);
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

    /**
     * Loads the image for a note or rest based on its length and pitch.
     * @param note The note object containing information about the note.
     * @param yOffsetOut An array to store the Y offset for the note image.
     * @return An ImageView representing the note or rest image.
     */
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
    
    /**
     * Loads an image symbol from the resources folder.
     * @param filename The name of the image file to load.
     * @param height The height to set for the ImageView.
     * @return An ImageView containing the loaded image.
     */
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

    /**
     * Plays song using the facade's playSong method when the play button is clicked.
     */
    @FXML
    private void onPlay() {
        if (facade != null && song != null) {
            if (isMuted) {
                System.out.println("Playing song (muted): " + song.getName() + " with " + defaultInstrument);
            } else {
                System.out.println("Playing song: " + song.getName() + " with " + defaultInstrument);
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
    }

    /**
     * Returns to search screen when the back button is clicked.
     * It loads the SongSearch.fxml file and sets the scene to it.
     */
    @FXML
    private void onBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongSearch.fxml"));
            Parent root = loader.load();
            SongSearchController controller = loader.getController();
            controller.setFacade(facade);
            
            // Use backArrow instead of backButton to get the scene
            backArrow.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns to search screen when the back button is clicked.
     * It loads the SongSearch.fxml file and sets the scene to it.
     * @throws IOException
     */
    @FXML
    private void goToPlaySong() throws IOException {
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

    /**
     * Goes to post creation screen when the create post button is clicked.
     * It loads the PostCreation.fxml file and sets the scene to it.
     * Uses current song to set the song in the PostCreationController.
     * @throws IOException
     */
    @FXML
    private void onCreatingPost() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostCreation.fxml"));
            Parent root = loader.load();
    
            PostCreationController controller = loader.getController();
            controller.setSong(this.song);
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Goes to post search screen when the post song button is clicked.
     * It loads the PostSearch.fxml file and sets the scene to it.
     * Uses current song to set the song name in the PostSearchController.
     */
    @FXML
    private void onSearchingPosts() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PostSearch.fxml"));
            Parent root = loader.load();
    
            PostSearchController controller = loader.getController();
            controller.setSong(this.song.getName());
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Goes to make song screen when the make song button is clicked.
     * @throws IOException
     */
    @FXML
    private void goToMakeSong() throws IOException {
        App.setRoot("CreateSong2");
    }

    /**
     * Goes to check posts screen when the check posts button is clicked.
     * @throws IOException
     */
    @FXML
    private void goToCheckPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    /**
     * Goes to profile page when the profile button is clicked.
     * It loads the ProfilePage.fxml file and sets the scene to it.
     * @throws IOException
     */
    @FXML
    private void goToProfile() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            Parent root = loader.load();
    
            ProfilePageController controller = loader.getController();
            controller.setUser(facade.getUser());
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Goes to home page when the home button is clicked.
     * It loads the HomePage.fxml file and sets the scene to it.
     * @throws IOException
     */
    @FXML
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }
}
