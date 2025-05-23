package com.keyquestjavafx;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.model.Comment;
import com.model.KeyQuestFACADE;
import com.model.Post;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Controller for the Post View page
 * This class handles the user input for viewing and interacting with a post
 * It interacts with the KeyQuestFACADE to retrieve post data
 * @author Owen Coulam
 */
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
    @FXML private ImageView likeIcon;
    @FXML private Label likeCountLabel;

    // --- Comments panel controls ---
    @FXML private VBox      commentsContainer;
    @FXML private ScrollPane commentsScroll;
    @FXML private TextArea  commentInput;
    @FXML private Button    commentButton;

    // Facade to encapsulate data retrieval & persistence
    private KeyQuestFACADE postFacade = KeyQuestFACADE.getInstance();
    private Post post;
    private boolean liked = false;

    @FXML
    public void initialize() {
        // Optionally ensure scroll is at bottom for comments
        commentsScroll.vvalueProperty().bind(commentsContainer.heightProperty());
    }

    public void setPost(Post post) {
        this.post = post;
        loadPostData();
        loadComments();
    }

    public void setFacade(KeyQuestFACADE facade) {
        this.postFacade = facade;
    }

    /**
     * This method is used to load the post data into the view
     * It is called when the post is set, and it populates the UI elements with the post's details
     */
    private void loadPostData() {
        postTitleLabel.setText(post.getTitle());
        authorLabel.setText(post.getAuthor().getUsername() + "  -- Created at " + post.getDate().toString());
        songLabel.setText("Song: " + post.getSong().getName() + " by " + post.getSong().getArtist());
        contentText.setText(post.getBody());
        likeCountLabel.setText(String.valueOf(post.getFavorites()));
        if ( postFacade.hasUserLiked(post) ) {
            liked = true;
            likeIcon.setImage(new Image(getClass().getResourceAsStream("images/heart_red.png")));        
        }
    }

    /**
     * This method is used to load the comments into the view
     * It is called when the post is set, and it populates the UI elements with the post's comments
     */
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

    /**
     * This method is used to add a comment to the view
     * It creates a new VBox with the comment details and adds it to the comments container
     * @param user The username of the user who made the comment
     * @param timestamp The timestamp of when the comment was made
     * @param text The text of the comment
     */
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

    /**
     * This method is used to handle the comment action
     * It is called when the user clicks the comment button
     * It retrieves the comment text from the input field and adds it to the post
     */
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
    private void handleLikeAction() {
        if (liked) { return; }
        liked = true;
        postFacade.favoritePost(post);
        likeIcon.setImage(new Image(getClass().getResourceAsStream("images/heart_red.png")));
        likeCountLabel.setText(String.valueOf(post.getFavorites()));
    }

    // --- Navigation controls ---

    // Handles cases for both the home button and the logo image
    @FXML
    private void goToHome() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void goToPostsSearch() throws IOException {
        App.setRoot("PostSearch");
    }

    @FXML private void goToProfile() throws IOException {
        App.setRoot("ProfilePage");
    }

    @FXML private void goToPostSong() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SongView.fxml"));
            Parent root = loader.load();
    
            SongViewController controller = loader.getController();
            controller.setFacade(KeyQuestFACADE.getInstance());
            controller.setSong(post.getSong());
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void goToUser() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            Parent root = loader.load();
    
            ProfilePageController controller = loader.getController();
            controller.setUser(post.getAuthor());
    
            App.getScene().setRoot(root);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

