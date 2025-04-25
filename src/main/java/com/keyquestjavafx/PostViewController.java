package com.keyquestjavafx;

import java.io.IOException;
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
    private Post post;

    @FXML
    public void initialize() {
        // — Populate dynamic data —
        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome, " + postFacade.getCurrentUsername() + "!");
        }

        loadPostData();
        loadComments();

        // Optionally ensure scroll is at bottom for comments
        commentsScroll.vvalueProperty().bind(commentsContainer.heightProperty());
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private void loadPostData() {
        postTitleLabel.setText(post.getTitle());
        authorLabel.setText(post.getAuthor().getUsername() + "   -   " + post.getDate().toString());
        songLabel.setText(post.getSong().getName());
        contentText.setText(post.getBody());
    }

    private void loadComments() {
        commentsContainer.getChildren().clear();

        List<Comment> comments = post.getComments();
        for (Comment c : comments) {
            addCommentToView(
                c.getAuthor().getUsername(),
                c.getDate().toString(),
                c.getBody()
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
        String commentTextInput = commentInput.getText().trim();
        if (commentTextInput.isEmpty()) {
            return; // nothing to add
        }

        // public Post(String song, User author, String title, String body)
        // public Comment(String body, User author)

        postFacade.makeComment(this.post, commentTextInput);

        // Immediately reflect in UI
        addCommentToView(
            postFacade.getCurrentUsername(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")),
            commentTextInput
        );

        // Clear input
        commentInput.clear();
    }

    @FXML
    private void handleHomeAction() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void handleCheckPostsAction() throws IOException {
        App.setRoot("PostSearch");
    }
}

