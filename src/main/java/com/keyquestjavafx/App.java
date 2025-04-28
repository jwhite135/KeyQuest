package com.keyquestjavafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for KeyQuest JavaFX application
 * This class initializes the JavaFX application and sets up the initial scene
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Initializes the JavaFX application
     * This method is called when the application is launched
     * @param stage The primary stage for this application
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LandingPage"), 640, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the scene to the specified FXML file
     * @param fxml The name of the FXML file to load
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}
