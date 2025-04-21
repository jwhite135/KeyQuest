package com.keyquestjavafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.model.Chord;
import com.model.KeyQuestFACADE;
import com.model.Note;
import com.model.PianoMeasure;
import com.model.PianoNote;
import com.model.Song;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CreateSongController implements Initializable {
    
    @FXML private ImageView WholeNote;
    @FXML private ImageView HalfNote;
    @FXML private ImageView QuarterNote;
    @FXML private ImageView EighthNote;
    @FXML private ImageView SixteenthNote;
    @FXML private ImageView DottedHalfNote;
    @FXML private VBox noteVBox;
    @FXML private VBox measuresContainer;
    @FXML private ComboBox<Integer> timeSigNumerator;
    @FXML private ComboBox<Integer> timeSigDenominator;
    @FXML private TextField songTitleField;
    @FXML private ComboBox<Integer> songDifficultyCombo;
    @FXML private ComboBox<String> songGenreCombo;
    @FXML private Button addMeasureButton;
    @FXML private Button saveSongButton;
    @FXML private TextField songArtistField;

    // Constants
    private final int LINE_SPACING = 20; // Distance between staff lines
    private final int STAFF_TOP = 40;    // Top position of the staff
    private final int STAFF_SLOTS = 13;  // Increased to accommodate more positions
    private final int DEFAULT_MEASURE_WIDTH = 240; // 4 beats × 60 pixels
    
    // Data structures for tracking notes
    private final Map<AnchorPane, List<NoteView>> measureNotes = new HashMap<>();
    private final KeyQuestFACADE facade = KeyQuestFACADE.getInstance();
    
    // Counter for measure numbers
    private int measureCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load note images
        loadNoteImages();
        
        // Initialize time signature ComboBoxes
        timeSigNumerator.getItems().addAll(2, 3, 4, 6, 9, 12);
        timeSigNumerator.setValue(4); // Default 4/4 time
        
        timeSigDenominator.getItems().addAll(2, 4, 8, 16);
        timeSigDenominator.setValue(4); // Default 4/4 time
        
        // Initialize difficulty and genre ComboBoxes
        songDifficultyCombo.getItems().addAll(1, 2, 3, 4, 5);
        songDifficultyCombo.setValue(3); // Default medium difficulty
        
        songGenreCombo.getItems().addAll("ROCK", "POP", "CLASSICAL", "JAZZ", "BLUES", "ELECTRONIC", "FOLK");
        songGenreCombo.setValue("ROCK"); // Default genre
        
        // Add first measure
        addMeasure();
        
        // Set up drag handlers for notes
        setupDragHandlers();
    }
    
    private void loadNoteImages() {
        // Load note images from resources
        if (WholeNote.getImage() == null) {
            try {
                WholeNote.setImage(new Image(getClass().getResourceAsStream("/com/keyquestjavafx/images/whole_note.png")));
                HalfNote.setImage(new Image(getClass().getResourceAsStream("/com/keyquestjavafx/images/half_note.png")));
                QuarterNote.setImage(new Image(getClass().getResourceAsStream("/com/keyquestjavafx/images/quarter_note.png")));
                EighthNote.setImage(new Image(getClass().getResourceAsStream("/com/keyquestjavafx/images/eighth_note.png")));
                SixteenthNote.setImage(new Image(getClass().getResourceAsStream("/com/keyquestjavafx/images/sixteenth_note.png")));
                DottedHalfNote.setImage(new Image(getClass().getResourceAsStream("/com/keyquestjavafx/images/dotted_half_note.png")));
            } catch (Exception e) {
                // If images can't be loaded, use placeholder colored rectangles
                System.err.println("Could not load note images: " + e.getMessage());
                
                // Create labels for notes instead
                noteVBox.getChildren().clear();
                
                Label wholeLabel = new Label("Whole Note");
                wholeLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 5px;");
                wholeLabel.setOnMousePressed(this::startNoteDrag);
                wholeLabel.setUserData("whole");
                
                Label halfLabel = new Label("Half Note");
                halfLabel.setStyle("-fx-background-color: lightgreen; -fx-padding: 5px;");
                halfLabel.setOnMousePressed(this::startNoteDrag);
                halfLabel.setUserData("half");
                
                Label quarterLabel = new Label("Quarter Note");
                quarterLabel.setStyle("-fx-background-color: lightyellow; -fx-padding: 5px;");
                quarterLabel.setOnMousePressed(this::startNoteDrag);
                quarterLabel.setUserData("quarter");
                
                Label eighthLabel = new Label("Eighth Note");
                eighthLabel.setStyle("-fx-background-color: lightpink; -fx-padding: 5px;");
                eighthLabel.setOnMousePressed(this::startNoteDrag);
                eighthLabel.setUserData("eighth");
                
                Label sixteenthLabel = new Label("Sixteenth Note");
                sixteenthLabel.setStyle("-fx-background-color: lavender; -fx-padding: 5px;");
                sixteenthLabel.setOnMousePressed(this::startNoteDrag);
                sixteenthLabel.setUserData("sixteenth");
                
                noteVBox.getChildren().addAll(wholeLabel, halfLabel, quarterLabel, eighthLabel, sixteenthLabel);
            }
        }
    }
    
    @FXML
    private void startNoteDrag(MouseEvent event) {
        // This method will do nothing - we're replacing it with setupDragHandlers
    }

    private void setupDragHandlers() {
        // Set up drag handlers for all note images
        setupDragForNode(WholeNote, "whole");
        setupDragForNode(HalfNote, "half");
        setupDragForNode(QuarterNote, "quarter");
        setupDragForNode(EighthNote, "eighth");
        setupDragForNode(SixteenthNote, "sixteenth");
        setupDragForNode(DottedHalfNote, "dotted-half");
    }

    private void setupDragForNode(Node node, String noteType) {
        node.setOnDragDetected(event -> {
            Dragboard db = node.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                content.putImage(imageView.getImage());
                content.putString(noteType);
                db.setContent(content);
                
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                WritableImage snapshot = imageView.snapshot(params, null);
                db.setDragView(snapshot);
            } else if (node instanceof Label) {
                Label label = (Label) node;
                content.putString(noteType);
                db.setContent(content);
                
                // Create a simple representation for drag view
                Label dragLabel = new Label(label.getText());
                dragLabel.setStyle(label.getStyle());
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                WritableImage snapshot = dragLabel.snapshot(params, null);
                db.setDragView(snapshot);
            }
            
            event.consume();
        });
    }

    @FXML
    private void addMeasure() {
        int beatsPerMeasure = timeSigNumerator.getValue();
        int pixelsPerBeat = 60;
        double measureWidth = beatsPerMeasure * pixelsPerBeat;
        
        // Create a container for the entire measure (both staves)
        VBox measureContainer = new VBox(10); // 10px spacing between staves
        measureContainer.setPrefWidth(measureWidth + 50); // Extra space for clefs
        measureContainer.setStyle("-fx-background-color: white;");
        measureContainer.setBorder(new Border(new BorderStroke(
            Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
        )));
        measureContainer.setPadding(new Insets(10));
        
        // Add measure number label
        measureCount++;
        Label measureLabel = new Label("Measure " + measureCount);
        measureLabel.setStyle("-fx-font-weight: bold;");
        
        // Create treble staff
        AnchorPane trebleStaff = createStaff(measureWidth, "treble");
        
        // Create bass staff
        AnchorPane bassStaff = createStaff(measureWidth, "bass");
        
        // Add components to the container
        measureContainer.getChildren().addAll(measureLabel, trebleStaff, bassStaff);
        
        // Add the measure container to the main container
        measuresContainer.getChildren().add(measureContainer);
        
        // Initialize the notes list for both staves
        measureNotes.put(trebleStaff, new ArrayList<>());
        measureNotes.put(bassStaff, new ArrayList<>());
    }

    // Helper method to create a staff (treble or bass)
    private AnchorPane createStaff(double measureWidth, String staffType) {
        AnchorPane staffPane = new AnchorPane();
        staffPane.setPrefHeight(120); // Slightly taller for better spacing
        staffPane.setPrefWidth(measureWidth + 50); // Add space for clef
        
        // Standard music staff constants
        double staffLineSpacing = LINE_SPACING; // 20 pixels between staff lines
        double staffTop = STAFF_TOP; // More space at top to accommodate note placement
        
        // Add the clef image
        try {
            ImageView clefImage = new ImageView();
            // Load appropriate clef image
            String imagePath = "/com/keyquestjavafx/images/" + staffType + "_clef.png";
            Image clefImg = new Image(getClass().getResourceAsStream(imagePath));
            clefImage.setImage(clefImg);
            
            // Size and position the clef appropriately
            if ("treble".equals(staffType)) {
                clefImage.setFitHeight(85);
                clefImage.setFitWidth(40);
                clefImage.setLayoutX(5);
                clefImage.setLayoutY(staffTop - 22); // Position to align G line with second staff line
            } else { // bass clef
                clefImage.setFitHeight(65);
                clefImage.setFitWidth(40);
                clefImage.setLayoutX(5);
                clefImage.setLayoutY(staffTop - 5); // Position to align F line with fourth staff line
            }
            
            clefImage.setPreserveRatio(true);
            staffPane.getChildren().add(clefImage);
        } catch (Exception e) {
            // If clef image can't be loaded, use text label instead
            Label clefLabel = new Label(staffType.substring(0, 1).toUpperCase() + staffType.substring(1));
            clefLabel.setLayoutX(5);
            clefLabel.setLayoutY(staffTop + staffLineSpacing);
            staffPane.getChildren().add(clefLabel);
        }
        
        // Draw staff lines - shifted to make room for clef
        double clefWidth = 50; // Space for clef
        for (int i = 0; i < 5; i++) {
            Line line = new Line(
                clefWidth, staffTop + i * staffLineSpacing,
                measureWidth + clefWidth, staffTop + i * staffLineSpacing
            );
            line.setStroke(Color.BLACK);
            staffPane.getChildren().add(line);
        }
        
        // Draw ledger lines above and below the staff (as faint guides)
        for (int i = -2; i <= 7; i++) {
            if (i < 0 || i > 4) { // Only draw ledger lines (not staff lines)
                Line ledgerLine = new Line(
                    clefWidth, staffTop + i * staffLineSpacing,
                    measureWidth + clefWidth, staffTop + i * staffLineSpacing
                );
                ledgerLine.setStroke(Color.LIGHTGRAY);
                ledgerLine.getStrokeDashArray().addAll(2d, 4d); // Make it dashed
                staffPane.getChildren().add(ledgerLine);
            }
        }
        
        // Add beat divider lines
        int beatsPerMeasure = timeSigNumerator.getValue();
        int pixelsPerBeat = 60;
        
        for (int i = 1; i < beatsPerMeasure; i++) {
            double beatX = clefWidth + i * pixelsPerBeat;
            Line beatLine = new Line(
                beatX, staffTop - 10,
                beatX, staffTop + 4 * staffLineSpacing + 10
            );
            beatLine.setStroke(Color.LIGHTGRAY);
            staffPane.getChildren().add(beatLine);
        }
        
        // Add time signature
        int num = timeSigNumerator.getValue();
        int den = timeSigDenominator.getValue();
        
        Label numerator = new Label(String.valueOf(num));
        numerator.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        numerator.setLayoutX(clefWidth - 15);
        numerator.setLayoutY(staffTop - 5);
        
        Label denominator = new Label(String.valueOf(den));
        denominator.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        denominator.setLayoutX(clefWidth - 15);
        denominator.setLayoutY(staffTop + staffLineSpacing);
        
        staffPane.getChildren().addAll(numerator, denominator);
        
        // Set up drop handling for this staff
        setupMeasureDropHandling(staffPane, timeSigNumerator.getValue(), 60, clefWidth);
        
        // Tag the staff with its type for pitch calculation
        staffPane.setUserData(staffType);
        
        return staffPane;
    }

    private void setupMeasureDropHandling(AnchorPane measurePane, int beatsPerMeasure, int pixelsPerBeat, double clefOffset) {
        measurePane.setOnDragOver(event -> {
            if (event.getDragboard().hasString() || event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        measurePane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            
            String noteType = null;
            ImageView dropped = null;
            
            // Determine the note type and create visual representation
            if (db.hasString()) {
                noteType = db.getString();
                
                // Create a label if we don't have an image
                if (!db.hasImage()) {
                    Label noteLabel = new Label(noteType);
                    noteLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 3px;");
                    
                    // Raw drop coordinates
                    double rawX = event.getX();
                    double rawY = event.getY();
                
                    // Horizontal snap: beat index (accounting for clef offset)
                    double adjustedX = rawX - clefOffset;
                    int beatIndex = Math.max(0, (int) Math.round(adjustedX / pixelsPerBeat));
                    beatIndex = Math.min(beatsPerMeasure - 1, beatIndex);
                    double snappedX = (beatIndex * pixelsPerBeat) + clefOffset;
                
                    // Vertical snap: staff "slot" - remove ALL restrictions
                    double slotHeight = LINE_SPACING / 2;
                    int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
                    double snappedY = STAFF_TOP + slotIndex * slotHeight;
                    
                    noteLabel.setLayoutX(snappedX);
                    noteLabel.setLayoutY(snappedY - 10); // Center vertically
                    
                    // Add to the measure
                    measurePane.getChildren().add(noteLabel);
                    
                    // Make label draggable within the measure
                    makeLabelDraggable(noteLabel, measurePane, beatsPerMeasure, pixelsPerBeat, clefOffset);
                    
                    // Calculate pitch based on staff position and staff type
                    String staffType = (String) measurePane.getUserData();
                    String pitch = getPitchFromStaffPosition(slotIndex, staffType);
                    
                    // Store note data
                    NoteView noteView = new NoteView(noteLabel, beatIndex, slotIndex, noteType, pitch);
                    measureNotes.get(measurePane).add(noteView);
                    
                    success = true;
                }
            }
            
            // Handle image-based note
            if (db.hasImage()) {
                if (noteType == null && db.hasString()) {
                    noteType = db.getString();
                } else {
                    noteType = "quarter"; // Default
                }
                
                dropped = new ImageView(db.getImage());
                dropped.setFitWidth(40);
                dropped.setFitHeight(40);
                dropped.setPreserveRatio(true);
                
                // Raw drop coordinates
                double rawX = event.getX();
                double rawY = event.getY();
            
                // Horizontal snap: beat index (accounting for clef offset)
                double adjustedX = rawX - clefOffset;
                int beatIndex = Math.max(0, (int) Math.round(adjustedX / pixelsPerBeat));
                beatIndex = Math.min(beatsPerMeasure - 1, beatIndex);
                double snappedX = (beatIndex * pixelsPerBeat) + clefOffset;
            
                // Vertical snap: staff "slot" - remove ALL restrictions
                double slotHeight = LINE_SPACING / 2;
                int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
                double snappedY = STAFF_TOP + slotIndex * slotHeight;
                
                // Calculate vertical position based on note type
                double noteHeadOffset = calculateNoteHeadOffset(noteType, dropped.getFitHeight());
                
                dropped.setLayoutX(snappedX);
                // Position based on note head, not center of image
                dropped.setLayoutY(snappedY - noteHeadOffset);
                
                // Color-code note based on octave
                String staffType = (String) measurePane.getUserData();
                String pitch = getPitchFromStaffPosition(slotIndex, staffType);
                if (pitch.charAt(pitch.length() - 1) == '5' || pitch.charAt(pitch.length() - 1) == '6') {
                    // Higher octaves - light red tint
                    dropped.setEffect(new javafx.scene.effect.ColorAdjust(0.0, 0.2, 0.0, 0.0));
                } else if (pitch.charAt(pitch.length() - 1) == '2' || pitch.charAt(pitch.length() - 1) == '1') {
                    // Lower octaves - light blue tint
                    dropped.setEffect(new javafx.scene.effect.ColorAdjust(0.8, 0.2, 0.0, 0.0));
                }
                
                // Make note draggable within the measure
                makeImageViewDraggable(dropped, measurePane, beatsPerMeasure, pixelsPerBeat, clefOffset);
                
                // Add to the measure
                measurePane.getChildren().add(dropped);
                
                // Store note data
                NoteView noteView = new NoteView(dropped, beatIndex, slotIndex, noteType, pitch);
                measureNotes.get(measurePane).add(noteView);
                
                // Add this to your note creation code in setupMeasureDropHandling
                // Check if we need ledger lines
                if (slotIndex < 0 || slotIndex > 8) {
                    // Determine which ledger lines to add
                    int ledgerStart = (slotIndex < 0) ? ((slotIndex / 2) * 2) : 8;
                    int ledgerEnd = (slotIndex < 0) ? 0 : ((slotIndex / 2) * 2);
                    
                    for (int i = ledgerStart; i <= ledgerEnd; i += 2) {
                        if (i != 0 && i != 2 && i != 4 && i != 6 && i != 8) { // Skip staff lines
                            Line ledgerLine = new Line(
                                snappedX - 10, STAFF_TOP + i * LINE_SPACING / 2,
                                snappedX + 30, STAFF_TOP + i * LINE_SPACING / 2
                            );
                            ledgerLine.setStroke(Color.BLACK);
                            measurePane.getChildren().add(ledgerLine);
                            
                            // Store reference to the ledger line with the note
                            // so we can move it if the note moves
                            // You'll need to add code to track these with the note
                        }
                    }
                }
                
                success = true;
            }
            
            event.setDropCompleted(success);
            event.consume();
        });
    }

    // Add this helper method to calculate the proper offset for each note type
    private double calculateNoteHeadOffset(String noteType, double imageHeight) {
        // The offset depends on the note type and its image
        switch (noteType) {
            case "whole":
            case "half":
                // For whole and half notes, the note head is in the center
                return imageHeight / 2;
            case "quarter":
            case "eighth":
            case "sixteenth":
                // For these notes, the note head is typically to the right and
                // slightly above the center of the image
                return imageHeight * 0.4; // Adjust this value based on your specific images
            case "dotted-half":
                // For dotted half notes
                return imageHeight / 2;
            default:
                // Default center alignment
                return imageHeight / 2;
        }
    }
    
    private void makeImageViewDraggable(ImageView note, AnchorPane measurePane, int beatsPerMeasure, int pixelsPerBeat, double clefOffset) {
        // Variables to track the click position relative to the note
        final double[] mouseAnchorX = new double[1];
        final double[] mouseAnchorY = new double[1];
        
        // Create a tooltip-like label to show note name
        Label noteNameLabel = new Label();
        noteNameLabel.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 2px;");
        noteNameLabel.setVisible(false);
        measurePane.getChildren().add(noteNameLabel);
        
        // Show note name on hover
        note.setOnMouseEntered(event -> {
            // Find the current note data
            String staffType = (String) measurePane.getUserData();
            double rawY = note.getLayoutY() + note.getFitHeight() / 2;
            double slotHeight = LINE_SPACING / 2;
            int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
            String pitch = getPitchFromStaffPosition(slotIndex, staffType);
            
            // Set and position the name label
            noteNameLabel.setText(pitch);
            noteNameLabel.setLayoutX(note.getLayoutX() + note.getFitWidth());
            noteNameLabel.setLayoutY(note.getLayoutY());
            noteNameLabel.setVisible(true);
        });
        
        note.setOnMouseExited(event -> {
            noteNameLabel.setVisible(false);
        });
        
        note.setOnMousePressed(event -> {
            // Store the cursor position relative to the note's top-left corner
            mouseAnchorX[0] = event.getX();
            mouseAnchorY[0] = event.getY();
            note.setMouseTransparent(true);
            
            // Highlight the selected note
            note.setEffect(new javafx.scene.effect.DropShadow(10, Color.GOLD));
            
            event.consume();
        });
        
        note.setOnMouseDragged(event -> {
            // Calculate new position using the anchor point offset
            double newX = event.getSceneX() - measurePane.localToScene(0, 0).getX() - mouseAnchorX[0];
            double newY = event.getSceneY() - measurePane.localToScene(0, 0).getY() - mouseAnchorY[0];
            
            // Only restrict X position to measure bounds, allow Y to go anywhere
            newX = Math.max(clefOffset, Math.min(measurePane.getWidth() - note.getFitWidth(), newX));
            
            note.setLayoutX(newX);
            note.setLayoutY(newY);
            event.consume();
        });
        
        note.setOnMouseReleased(event -> {
            note.setMouseTransparent(false);
            
            // Remove highlight
            note.setEffect(null);
            
            // Snap to grid - only horizontally
            double rawX = note.getLayoutX();
            
            // Only restrict horizontal position to beat grid (accounting for clef offset)
            double adjustedX = rawX - clefOffset;
            int beatIndex = (int) Math.round(adjustedX / pixelsPerBeat);
            beatIndex = Math.max(0, Math.min(beatsPerMeasure - 1, beatIndex));
            double snappedX = (beatIndex * pixelsPerBeat) + clefOffset;
            
            // For vertical position, use actual Y position to calculate staff slot
            double rawY = note.getLayoutY() + note.getFitHeight() / 2; // Adjust for center
            
            double slotHeight = LINE_SPACING / 2;
            int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
            double snappedY = STAFF_TOP + slotIndex * slotHeight;
            
            // Get the note type from our stored data
            String noteType = getNoteTypeForImage(note, measurePane);
            
            // Calculate the proper head offset
            double noteHeadOffset = calculateNoteHeadOffset(noteType, note.getFitHeight());
            
            note.setLayoutX(snappedX);
            // Position based on note head, not center
            note.setLayoutY(snappedY - noteHeadOffset);
            
            // Get the staff type to calculate the correct pitch
            String staffType = (String) measurePane.getUserData();
            
            // Update the note position in our tracked list
            updateNotePosition(measurePane, note, beatIndex, slotIndex, staffType);
            
            event.consume();
        });
    }

    // Helper method to find the note type for a specific note image
    private String getNoteTypeForImage(ImageView noteImage, AnchorPane measurePane) {
        List<NoteView> notes = measureNotes.get(measurePane);
        for (NoteView note : notes) {
            if (note.getImageView() == noteImage) {
                return note.getNoteType();
            }
        }
        return "quarter"; // Default
    }
    
    private void makeLabelDraggable(Label label, AnchorPane measurePane, int beatsPerMeasure, int pixelsPerBeat, double clefOffset) {
        // Similar implementation as makeImageViewDraggable but for Labels
        // Variables to track the click position relative to the label
        final double[] mouseAnchorX = new double[1];
        final double[] mouseAnchorY = new double[1];
        
        label.setOnMousePressed(event -> {
            // Store the cursor position relative to the label's top-left corner
            mouseAnchorX[0] = event.getX();
            mouseAnchorY[0] = event.getY();
            label.setMouseTransparent(true);
            event.consume();
        });
        
        label.setOnMouseDragged(event -> {
            // Calculate new position using the anchor point offset
            double newX = event.getSceneX() - measurePane.localToScene(0, 0).getX() - mouseAnchorX[0];
            double newY = event.getSceneY() - measurePane.localToScene(0, 0).getY() - mouseAnchorY[0];
            
            // Only restrict X position, not Y position
            newX = Math.max(clefOffset, Math.min(measurePane.getWidth() - label.getWidth(), newX));
            
            label.setLayoutX(newX);
            label.setLayoutY(newY);
            event.consume();
        });
        
        label.setOnMouseReleased(event -> {
            label.setMouseTransparent(false);
            
            // Snap to grid - only horizontally
            double rawX = label.getLayoutX();
            
            // Account for clef offset when calculating beat index
            double adjustedX = rawX - clefOffset;
            int beatIndex = (int) Math.round(adjustedX / pixelsPerBeat);
            beatIndex = Math.max(0, Math.min(beatsPerMeasure - 1, beatIndex));
            double snappedX = (beatIndex * pixelsPerBeat) + clefOffset;
            
            // For vertical position, calculate staff slot from current Y position
            double rawY = label.getLayoutY() + label.getHeight() / 2; // Adjust for center
            
            double slotHeight = LINE_SPACING / 2;
            int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
            double snappedY = STAFF_TOP + slotIndex * slotHeight;
            
            label.setLayoutX(snappedX);
            label.setLayoutY(snappedY - label.getHeight() / 2);
            
            // Get the staff type to calculate the correct pitch
            String staffType = (String) measurePane.getUserData();
            
            // Update the note position in our tracked list
            updateLabelPosition(measurePane, label, beatIndex, slotIndex, staffType);
            
            event.consume();
        });
    }
    
    private void updateNotePosition(AnchorPane measurePane, ImageView noteView, int beatIndex, int slotIndex, String staffType) {
        List<NoteView> notes = measureNotes.get(measurePane);
        for (NoteView nv : notes) {
            if (nv.getImageView() == noteView) {
                nv.setBeatIndex(beatIndex);
                nv.setSlotIndex(slotIndex);
                nv.setPitch(getPitchFromStaffPosition(slotIndex, staffType));
                break;
            }
        }
    }

    private void updateLabelPosition(AnchorPane measurePane, Label label, int beatIndex, int slotIndex, String staffType) {
        List<NoteView> notes = measureNotes.get(measurePane);
        for (NoteView nv : notes) {
            if (nv.getLabel() == label) {
                nv.setBeatIndex(beatIndex);
                nv.setSlotIndex(slotIndex);
                nv.setPitch(getPitchFromStaffPosition(slotIndex, staffType));
                break;
            }
        }
    }

    private String getPitchFromStaffPosition(int slotIndex, String staffType) {
        if ("treble".equals(staffType)) {
            // Expanded treble clef pitches from LOW to HIGH (bottom to top)
            String[] treblePitches = {
                "B2", "C3", "D3", "E3", "F3", "G3", "A3", "B3", "C4", "D4", // Lower range
                "E4", "F4", "G4", "A4", "B4", "C5", "D5", "E5", "F5", "G5", // Mid-high range (G4 is 2nd line)
                "A5", "B5", "C6", "D6", "E6", "F6", "G6", "A6", "B6", "C7", "D7", "E7", "F7" // Higher octaves
            };
            
            // Calculate position in array - slots increase as you go up the staff
            // G4 is at index 12 and is on the 2nd line from bottom
            int baseSlotIndex = 20; // This value will need adjustment based on your actual UI placement
            int targetIndex = baseSlotIndex - slotIndex; // As slotIndex increases (goes down visually), we access lower pitches
            
            // Ensure the index is in bounds
            if (targetIndex < 0) {
                return treblePitches[0]; // Return lowest note
            } else if (targetIndex >= treblePitches.length) {
                return treblePitches[treblePitches.length - 1]; // Return highest note
            }
            
            return treblePitches[targetIndex];
        } else {
            // Expanded bass clef pitches from LOW to HIGH (bottom to top)
            String[] bassPitches = {
                "E1", "F1", "G1", "A1", "B1", "C2", "D2", "E2", "F2", // Lower octaves
                "G2", "A2", "B2", "C3", "D3", "E3", "F3", "G3", "A3", "B3", // Mid-low range (F3 is middle line)
                "C4", "D4", "E4", "F4", "G4", "A4", "B4", "C5", "D5", "E5", "F5", "G5" // Higher octaves
            };
            
            // Calculate position in array
            // C4 is at index 20
            int baseSlotIndex = 15; // This value will need adjustment based on your staff's visual position
            int targetIndex = baseSlotIndex - slotIndex; // As slotIndex increases (goes down visually), we access lower pitches
            
            // Ensure the index is in bounds
            if (targetIndex < 0) {
                return bassPitches[0]; // Return lowest note
            } else if (targetIndex >= bassPitches.length) {
                return bassPitches[bassPitches.length - 1]; // Return highest note
            }
            
            return bassPitches[targetIndex];
        }
    }
    
    private String getNoteTypeFromImageView(ImageView source) {
        if (source == WholeNote) return "whole";
        if (source == HalfNote) return "half";
        if (source == QuarterNote) return "quarter";
        if (source == EighthNote) return "eighth";
        if (source == SixteenthNote) return "sixteenth";
        if (source == DottedHalfNote) return "dotted-half";
        return "quarter"; // Default
    }
    
    private String getNoteLength(String noteType) {
        // Convert note type to note length for backend
        switch (noteType) {
            case "whole": return "w";
            case "half": return "h";
            case "quarter": return "q";
            case "eighth": return "i";
            case "sixteenth": return "s";
            case "dotted-half": return "h.";
            default: return "q"; // Default quarter note
        }
    }
    
    @FXML
    private void saveSong() {
        try {
            // Get song metadata
            String title = songTitleField.getText();
            int difficulty = songDifficultyCombo.getValue();
            String genreStr = songGenreCombo.getValue();
            int timeSignatureNum = timeSigNumerator.getValue();
            int timeSignatureDen = timeSigDenominator.getValue();
            int tempo = 120; // Default tempo, could be added to UI
            
            // Validate title
            if (title.isEmpty()) {
                showAlert("Missing Information", "Please provide a title for the song.");
                return;
            }
            
            // Get the current user as the artist
            String artist = "Current User"; // This should come from your user management system
            
            // Create the song in the backend
            Song song = facade.createSong(title, artist, difficulty, genreStr, timeSignatureNum, timeSignatureDen, tempo);
            
            // Process each measure (each VBox contains a treble and bass staff)
            for (Node measureContainer : measuresContainer.getChildren()) {
                if (measureContainer instanceof VBox) {
                    VBox vbox = (VBox) measureContainer;
                    
                    // Find the treble and bass staves
                    AnchorPane trebleStaff = null;
                    AnchorPane bassStaff = null;
                    
                    // Skip the first child (it's the measure label)
                    for (int i = 1; i < vbox.getChildren().size(); i++) {
                        Node child = vbox.getChildren().get(i);
                        if (child instanceof AnchorPane) {
                            AnchorPane staff = (AnchorPane) child;
                            String staffType = (String) staff.getUserData();
                            
                            if ("treble".equals(staffType)) {
                                trebleStaff = staff;
                            } else if ("bass".equals(staffType)) {
                                bassStaff = staff;
                            }
                        }
                    }
                    
                    // Create and add a measure that combines treble and bass
                    PianoMeasure measure = createDualStaffMeasure(trebleStaff, bassStaff);
                    facade.addMeasureToSong(song, measure);
                }
            }
            
            // Show success message
            showAlert("Success", "Song saved successfully!");
            
            // Navigate back or clear form
            clearForm();
            
        } catch (Exception e) {
            showAlert("Error", "Failed to save song: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private PianoMeasure createDualStaffMeasure(AnchorPane trebleStaff, AnchorPane bassStaff) {
        // Group notes by beat position for treble clef
        Map<Integer, List<NoteView>> trebleNotesByBeat = new HashMap<>();
        if (trebleStaff != null && measureNotes.containsKey(trebleStaff)) {
            for (NoteView noteView : measureNotes.get(trebleStaff)) {
                int beatIndex = noteView.getBeatIndex();
                trebleNotesByBeat.computeIfAbsent(beatIndex, k -> new ArrayList<>()).add(noteView);
            }
        }
        
        // Group notes by beat position for bass clef
        Map<Integer, List<NoteView>> bassNotesByBeat = new HashMap<>();
        if (bassStaff != null && measureNotes.containsKey(bassStaff)) {
            for (NoteView noteView : measureNotes.get(bassStaff)) {
                int beatIndex = noteView.getBeatIndex();
                bassNotesByBeat.computeIfAbsent(beatIndex, k -> new ArrayList<>()).add(noteView);
            }
        }
        
        // Create chords for each beat position
        ArrayList<Chord> chords = new ArrayList<>();
        
        // Ensure we have a chord for each beat position
        for (int i = 0; i < timeSigNumerator.getValue(); i++) {
            ArrayList<Note> notes = new ArrayList<>();
            
            // Add treble notes for this beat
            if (trebleNotesByBeat.containsKey(i)) {
                for (NoteView noteView : trebleNotesByBeat.get(i)) {
                    Note note = new PianoNote(
                        getNoteLength(noteView.getNoteType()),
                        noteView.getPitch(),
                        false,  // isSharp
                        false   // isFlat
                    );
                    notes.add(note);
                }
            }
            
            // Add bass notes for this beat
            if (bassNotesByBeat.containsKey(i)) {
                for (NoteView noteView : bassNotesByBeat.get(i)) {
                    Note note = new PianoNote(
                        getNoteLength(noteView.getNoteType()),
                        noteView.getPitch(),
                        false,  // isSharp
                        false   // isFlat
                    );
                    notes.add(note);
                }
            }
            
            // Add the chord (even if empty)
            chords.add(new Chord(notes));
        }
        
        // Create and return a new Measure
        return new PianoMeasure(
            false,  // isRepeat
            chords
        );
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void clearForm() {
        songTitleField.clear();
        songArtistField.clear();
        songDifficultyCombo.setValue(3);
        songGenreCombo.setValue("ROCK");
        
        // Clear measures
        measuresContainer.getChildren().clear();
        measureNotes.clear();
        measureCount = 0;
        
        // Add a new empty measure
        addMeasure();
    }
    
    // Inner class to track note information
    private static class NoteView {
        private final Object view; // Can be ImageView or Label
        private int beatIndex;
        private int slotIndex;
        private final String noteType;
        private String pitch;
        
        public NoteView(Object view, int beatIndex, int slotIndex, String noteType, String pitch) {
            this.view = view;
            this.beatIndex = beatIndex;
            this.slotIndex = slotIndex;
            this.noteType = noteType;
            this.pitch = pitch;
        }
        
        public ImageView getImageView() {
            return (view instanceof ImageView) ? (ImageView) view : null;
        }
        
        public Label getLabel() {
            return (view instanceof Label) ? (Label) view : null;
        }
        
        public int getBeatIndex() {
            return beatIndex;
        }
        
        public void setBeatIndex(int beatIndex) {
            this.beatIndex = beatIndex;
        }
        
        public int getSlotIndex() {
            return slotIndex;
        }
        
        public void setSlotIndex(int slotIndex) {
            this.slotIndex = slotIndex;
        }
        
        public String getNoteType() {
            return noteType;
        }
        
        public String getPitch() {
            return pitch;
        }
        
        public void setPitch(String pitch) {
            this.pitch = pitch;
        }
    }
}
