module com.keyquestjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.keyquestjavafx to javafx.fxml;
    exports com.keyquestjavafx;
}
