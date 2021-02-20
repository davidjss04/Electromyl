module com.fassti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.fassti to javafx.fxml;
    exports com.fassti.model;
    exports com.fassti;
}