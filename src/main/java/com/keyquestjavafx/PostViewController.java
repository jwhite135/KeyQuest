package com.keyquestjavafx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.model.Comment;
import com.model.KeyQuestFACADE;
import com.model.Post;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PostViewController {

    // --- Top bar controls ---
    @FXML private ImageView logoImageView;
    @FXML private Button homeButton;
    @FXML private Button checkPostsButton;
    @FXML private Label welcomeLabel;
    @FXML private ImageView profileImageView;

    // --- Post details controls ---
    @FXML private Label postTitleLabel;
    @FXML private Label authorLabel;
    @FXML private Label songLabel;
    @FXML private Text  contentText;

    // --- Comments panel controls ---
    @FXML private VBox      commentsContainer;
    @FXML private ScrollPane commentsScroll;
    @FXML private TextArea  commentInput;
    @FXML private Button    commentButton;

    // Facade to encapsulate data retrieval & persistence
    private KeyQuestFACADE postFacade = KeyQuestFACADE.getInstance();

    @FXML
    public void initialize() {
        // — Load static assets (logo / profile placeholder) —
        logoImageView.setImage(new Image(
            getClass().getResourceAsStream("@images/KeyQuestLogoTransparent.png")
        ));
        profileImageView.setImage(new Image(
            getClass().getResourceAsStream("@images/KeyQuestUserIcon.png")
        ));

        // — Populate dynamic data —
        String currentUser = postFacade.getCurrentUsername();
        welcomeLabel.setText("Welcome, " + currentUser);

        loadPostData();
        loadComments();

        // Optionally ensure scroll is at bottom for comments
        commentsScroll.vvalueProperty().bind(commentsContainer.heightProperty());
    }

    private void loadPostData() {
        // TODO: replace with real backend call
        Post post = postFacade.getCurrentPost();
        postTitleLabel.setText(post.getTitle());
        authorLabel .setText(post.getAuthor());
        songLabel   .setText(post.getSong());
        contentText .setText(post.getBody());
    }

    private void loadComments() {
        commentsContainer.getChildren().clear();

        // TODO: fetch comments list from your service
        List<Comment> comments = postFacade.getCommentsForCurrentPost();
        for (Comment c : comments) {
            addCommentToView(
                c.getUser(),
                c.getTimestamp().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")),
                c.getText()
            );
        }
    }

    private void addCommentToView(String user, String timestamp, String text) {
        VBox box = new VBox(2);
        Label userLabel = new Label(user);
        userLabel.setStyle("-fx-font-weight: bold;");

        Label timeLabel = new Label(timestamp);
        timeLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray;");

        Label textLabel = new Label(text);
        textLabel.setWrapText(true);

        box.getChildren().addAll(userLabel, timeLabel, textLabel);
        commentsContainer.getChildren().add(box);
    }

    @FXML
    private void handleCommentAction() {
        String newComment = commentInput.getText().trim();
        if (newComment.isEmpty()) {
            return; // nothing to add
        }

        // TODO: persist the new comment via your facade / service layer
        postFacade.addCommentToCurrentPost(newComment);

        // Immediately reflect in UI
        addCommentToView(
            postFacade.getCurrentUsername(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")),
            newComment
        );

        // Clear input
        commentInput.clear();
    }

    @FXML
    private void handleHomeAction() {
        // TODO: navigate to your Home scene
        postFacade.goHome();
    }

    @FXML
    private void handleCheckPostsAction() {
        // TODO: navigate to the Posts list scene
        postFacade.showAllPosts();
    }
}

