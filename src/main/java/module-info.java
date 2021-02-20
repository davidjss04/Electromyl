module com.fassti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.fassti.view to javafx.fxml;
    exports com.fassti;
    exports com.fassti.model;
}