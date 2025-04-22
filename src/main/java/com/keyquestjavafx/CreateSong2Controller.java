package com.keyquestjavafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.model.Chord;
import com.model.KeyQuestFACADE;
import com.model.Note;
import com.model.PianoMeasure;
import com.model.PianoNote;
import com.model.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class CreateSong2Controller implements Initializable {
    
    @FXML private TextField songTitleField;
    @FXML private ComboBox<Integer> songDifficultyCombo;
    @FXML private ComboBox<String> songGenreCombo;
    @FXML private ComboBox<String> notePitchCombo;
    @FXML private ComboBox<String> noteLengthCombo;
    @FXML private ComboBox<Integer> noteBeatCombo;
    @FXML private ListView<String> currentMeasureList;
    @FXML private ListView<String> allMeasuresList;
    @FXML private Button addNoteButton;
    @FXML private Button addMeasureButton;
    @FXML private Button saveSongButton;
    @FXML private Label measureCountLabel;
    
    private final KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
    
    private final List<PianoMeasure> measures = new ArrayList<>();
    
    private final Map<Integer, List<PianoNote>> currentBeatNotes = new HashMap<>();
    
    private int measureCount = 0;
    
    private ObservableList<String> currentMeasureItems = FXCollections.observableArrayList();
    private ObservableList<String> allMeasuresItems = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songDifficultyCombo.getItems().addAll(1, 2, 3, 4, 5);
        songDifficultyCombo.setValue(3);
        
        songGenreCombo.getItems().addAll("ROCK", "POP", "CLASSICAL", "JAZZ", "BLUES", "ELECTRONIC", "FOLK");
        songGenreCombo.setValue("ROCK");
        
        notePitchCombo.getItems().addAll(
            "A0", "A#0", "B0",
            "C1", "C#1", "D1", "D#1", "E1", "F1", "F#1", "G1", "G#1", "A1", "A#1", "B1",
            "C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
            "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3",
            "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
            "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5",
            "C6", "C#6", "D6", "D#6", "E6", "F6", "F#6", "G6", "G#6", "A6", "A#6", "B6",
            "C7", "C#7", "D7", "D#7", "E7", "F7", "F#7", "G7", "G#7", "A7", "A#7", "B7",
            "C8"
        );
        notePitchCombo.setValue("C4");
        
        noteLengthCombo.getItems().addAll("whole", "half", "quarter", "eighth", "sixteenth", "dotted-half");
        noteLengthCombo.setValue("quarter");
        
        noteBeatCombo.getItems().addAll(0, 1, 2, 3);
        noteBeatCombo.setValue(0); 
        
        currentMeasureList.setItems(currentMeasureItems);
        allMeasuresList.setItems(allMeasuresItems);
        
        initializeNewMeasure();
        
        updateMeasureCountLabel();
    }
    
    private void initializeNewMeasure() {
        currentBeatNotes.clear();
        
        for (int i = 0; i < 4; i++) {
            currentBeatNotes.put(i, new ArrayList<>());
        }
    }
    
    @FXML
    private void addNote() {
        String pitch = notePitchCombo.getValue();
        String noteType = noteLengthCombo.getValue();
        int beat = noteBeatCombo.getValue();
        
        String length = getNoteLength(noteType);
        
        boolean beatHasNotes = !currentBeatNotes.get(beat).isEmpty();
        
        if (beatHasNotes) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notes Exist at Beat " + beat);
            alert.setHeaderText(null);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(
                new ButtonType("Add Note to Chord"),
                new ButtonType("Replace Existing Notes"),
                ButtonType.CANCEL
            );
            alert.setContentText("There are already notes at beat " + beat + ". What would you like to do?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get().getText().equals("Replace Existing Notes")) {
                    currentBeatNotes.get(beat).clear();
                } else if (result.get() == ButtonType.CANCEL) {
                    return;
                }
            }
        }
        
        boolean isSharp = pitch.contains("#");
        PianoNote note = new PianoNote(length, pitch, isSharp, false);
        
        currentBeatNotes.get(beat).add(note);
        
        updateCurrentMeasureDisplay();
    }
    
    @FXML
    private void addMeasure() {
        boolean hasSomeNotes = false;
        for (List<PianoNote> beatNotes : currentBeatNotes.values()) {
            if (!beatNotes.isEmpty()) {
                hasSomeNotes = true;
                break;
            }
        }
        
        if (!hasSomeNotes) {
            showAlert("Empty Measure", "You cannot add an empty measure. Add at least one note first.");
            return;
        }
        
        PianoMeasure measure = createMeasureFromBeats();
        measures.add(measure);
        measureCount++;
        
        int totalNotes = 0;
        for (List<PianoNote> notes : currentBeatNotes.values()) {
            totalNotes += notes.size();
        }
        allMeasuresItems.add("Measure " + measureCount + " (" + totalNotes + " notes)");
        
        initializeNewMeasure();
        currentMeasureItems.clear();
        
        updateMeasureCountLabel();
    }
    
    @FXML
    private void saveSong() {
        try {
            boolean hasCurrentNotes = false;
            for (List<PianoNote> beatNotes : currentBeatNotes.values()) {
                if (!beatNotes.isEmpty()) {
                    hasCurrentNotes = true;
                    break;
                }
            }
            
            if (hasCurrentNotes) {
                boolean addCurrentMeasure = showConfirmationAlert("Add Current Measure", 
                    "You have notes in the current measure that haven't been added. Do you want to add this measure before saving?");
                
                if (addCurrentMeasure) {
                    addMeasure();
                }
            }
            
            if (measures.isEmpty()) {
                showAlert("No Measures", "Please add at least one measure to the song.");
                return;
            }
            
            String title = songTitleField.getText();
            int difficulty = songDifficultyCombo.getValue();
            String genreStr = songGenreCombo.getValue();
            
            if (title.isEmpty()) {
                showAlert("Missing Information", "Please provide a title for the song.");
                return;
            }
            
            String artist = "Current User"; 
            Song song = facade.createSong(title, artist, difficulty, genreStr, 4, 4, 120);
            
            for (int i = 0; i < measures.size(); i++) {
                PianoMeasure measure = measures.get(i);
                facade.addMeasureToSong(song, measure);
            }
            
            try {
                facade.playSong(song);
            } catch (Exception e) {
                
            }
            
            showAlert("Success", "Song saved successfully!");
            
            clearForm();
            
        } catch (Exception e) {
            showAlert("Error", "Failed to save song: " + e.getMessage());
        }
    }
    
    private String getNoteLength(String noteType) {
        switch (noteType) {
            case "whole": return "w";
            case "half": return "h";
            case "quarter": return "q";
            case "eighth": return "i";
            case "sixteenth": return "s";
            case "dotted-half": return "h.";
            default: return "q";
        }
    }
    
    private void updateCurrentMeasureDisplay() {
        currentMeasureItems.clear();
        
        for (int beat = 0; beat < 4; beat++) {
            List<PianoNote> notes = currentBeatNotes.get(beat);
            
            if (!notes.isEmpty()) {
                for (PianoNote note : notes) {
                    String noteLength = getDisplayNoteLength(note.getLength());
                    String noteString = "Beat " + beat + ": " + note.getKey() + " (" + noteLength + ")";
                    currentMeasureItems.add(noteString);
                }
            }
        }
    }
    
    private String getDisplayNoteLength(String length) {
        switch (length) {
            case "w": return "whole";
            case "h": return "half";
            case "q": return "quarter";
            case "i": return "eighth";
            case "s": return "sixteenth";
            case "h.": return "dotted-half";
            default: return length;
        }
    }
    
    private void updateMeasureCountLabel() {
        measureCountLabel.setText("Current Measure: " + (measureCount + 1));
    }

    private void clearForm() {
        songTitleField.clear();
        songDifficultyCombo.setValue(3);
        songGenreCombo.setValue("ROCK");
        notePitchCombo.setValue("C4");
        noteLengthCombo.setValue("quarter");
        noteBeatCombo.setValue(0);
        
        initializeNewMeasure();
        currentMeasureItems.clear();
        allMeasuresItems.clear();
        
        measureCount = 0;
        measures.clear();
        
        updateMeasureCountLabel();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().filter(response -> response == javafx.scene.control.ButtonType.OK).isPresent();
    }

    private PianoMeasure createMeasureFromBeats() {
        ArrayList<Chord> chords = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            List<PianoNote> beatNotes = currentBeatNotes.get(i);
            ArrayList<Note> chordNotes = new ArrayList<>();
            
            for (PianoNote note : beatNotes) {
                chordNotes.add(note);
            }
            
            if (chordNotes.isEmpty()) {
                PianoNote restNote = new PianoNote("q", "R", false, false);
                chordNotes.add(restNote);
            }
            
            chords.add(new Chord(chordNotes));
        }
        
        return new PianoMeasure(false, chords);
    }

    @FXML
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void goToPlaySong() throws IOException {
        App.setRoot("SongSearch");
    }

    @FXML
    private void goToCheckPosts() throws IOException {
        App.setRoot("PostsPage");
    }

    @FXML
    private void goToProfile() throws IOException {
        App.setRoot("ProfilePage");
    }
}
