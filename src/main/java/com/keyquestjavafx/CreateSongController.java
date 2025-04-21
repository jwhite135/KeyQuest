package com.keyquestjavafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CreateSongController implements Initializable {
    
    @FXML
    private ImageView DottedHalfNote;

    @FXML
    private ImageView EighthNote;

    @FXML
    private ImageView HalfNote;

    @FXML
    private ImageView QuarterNote;

    @FXML
    private ImageView SixteenthNote;

    @FXML
    private ImageView WholeNote;

    @FXML
    private SplitPane rootCreateSong;

    @FXML
    private VBox noteVBox;

    @FXML
    private AnchorPane sheetPane;


    // constants (can later make adjustable)
    private final int BEATS_PER_MEASURE = 4;
    private final int PIXELS_PER_BEAT = 60;
    private final int LINE_SPACING = 20;
    private final int STAFF_TOP = 50;
    private final int STAFF_SLOTS = 9;

    private final DraggableMaker dragMaker = new DraggableMaker(BEATS_PER_MEASURE, PIXELS_PER_BEAT, LINE_SPACING, STAFF_TOP, STAFF_SLOTS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drawStaffLines();
        initializeDropHandlers();
    }

@FXML
private void startNoteDrag(MouseEvent event) {
    ImageView source = (ImageView) event.getSource();
    Dragboard db = source.startDragAndDrop(TransferMode.COPY);

    ClipboardContent content = new ClipboardContent();
    content.putImage(source.getImage());
    db.setContent(content);

    Platform.runLater(() -> {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage snapshot = source.snapshot(params, null);
        db.setDragView(snapshot);
    });

    event.consume();
}
    @FXML
    private void initializeDropHandlers() {
        sheetPane.setOnDragOver(event -> {
            if (event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        sheetPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (!db.hasImage()) return;
        
            // raw drop coords
            double rawX = event.getX();
            double rawY = event.getY();
        
            // ① Horizontal snap: beat index (0…BEATS_PER_MEASURE–1)
            int beatIndex = (int) Math.round(rawX / PIXELS_PER_BEAT);
            double snappedX = beatIndex * PIXELS_PER_BEAT;
        
            // ② Vertical snap: staff “slot” (0 = top line, 1 = space below, …, 8 = bottom line)
            double slotHeight = LINE_SPACING / 2;
            int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
            slotIndex = Math.max(0, Math.min(STAFF_SLOTS - 1, slotIndex));
            double snappedY = STAFF_TOP + slotIndex * slotHeight;
        
            // create & size the note
            ImageView dropped = new ImageView(db.getImage());
            dropped.setFitWidth(QuarterNote.getFitWidth());
            dropped.setFitHeight(QuarterNote.getFitHeight());
            dropped.setPreserveRatio(true);
        
            dropped.setLayoutX(snappedX);
            dropped.setLayoutY(snappedY);
        
            dragMaker.MakeDraggable(dropped);
            sheetPane.getChildren().add(dropped);
            event.setDropCompleted(true);
            event.consume();
        });
    }
    private void drawStaffLines() {
        for (int i = 0; i < 5; i++) {
            Line line = new Line(0, STAFF_TOP + i * LINE_SPACING,
                                sheetPane.getPrefWidth(), STAFF_TOP + i * LINE_SPACING);
            line.setStroke(Color.BLACK);
            sheetPane.getChildren().add(line);
        }
    }
}
