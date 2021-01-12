module com.fassti {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.fassti to javafx.fxml;
    exports com.fassti;
}