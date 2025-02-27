module com.keyquestjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;


    opens com.keyquestjavafx to javafx.fxml;
    exports com.keyquestjavafx;

    opens com.model to javafx.fxml;
    exports com.model;

    opens com.music to javafx.fxml;
    exports com.music;
}
