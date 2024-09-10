module com.app.lilithscloud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Требуется для JDBC

    opens com.app.lilithscloud to javafx.fxml;
    exports com.app.lilithscloud;
}