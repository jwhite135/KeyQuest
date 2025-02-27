module com.keyquestjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.keyquestjavafx to javafx.fxml;
    exports com.keyquestjavafx;
    
    opens com.model to javafx.fxml;
    exports com.model;
}
