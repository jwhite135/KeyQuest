package com.keyquestjavafx;

import javafx.scene.Node;

public class DraggableMaker {
    private double mouseAnchorX;
    private double mouseAnchorY;

    private final int BEATS_PER_MEASURE;
    private final int PIXELS_PER_BEAT;
    private final int LINE_SPACING;
    private final int STAFF_TOP;
    private final int STAFF_SLOTS;

    DraggableMaker(int beatsPerMeasure, int pixelsPerBeat, int lineSpacing, int staffTop, int staffSlots) {
        this.BEATS_PER_MEASURE = beatsPerMeasure;
        this.PIXELS_PER_BEAT = pixelsPerBeat;
        this.LINE_SPACING = lineSpacing;
        this.STAFF_TOP = staffTop;
        this.STAFF_SLOTS = staffSlots;
    }

    public void MakeDraggable(Node node) {
        node.setOnMousePressed(event -> {
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();
        });

        node.setOnMouseDragged(event -> {
            node.setLayoutX(event.getSceneX() - mouseAnchorX);
            node.setLayoutY(event.getSceneY() - mouseAnchorY);
        });

        node.setOnMouseReleased(evt -> {
            double rawX = node.getLayoutX();
            double rawY = node.getLayoutY();
    
            // horizontal snap
            int beatIndex = (int) Math.round(rawX / PIXELS_PER_BEAT);
            double snappedX = beatIndex * PIXELS_PER_BEAT;
    
            // vertical snap
            double slotHeight = LINE_SPACING / 2;
            int slotIndex = (int) Math.round((rawY - STAFF_TOP) / slotHeight);
            slotIndex = Math.max(0, Math.min(STAFF_SLOTS - 1, slotIndex));
            double snappedY = STAFF_TOP + slotIndex * slotHeight;
    
            node.setLayoutX(snappedX);
            node.setLayoutY(snappedY);
        });
    }
}
