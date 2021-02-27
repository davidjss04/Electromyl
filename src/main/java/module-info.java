module com.fassti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires annotations;
    requires org.mariadb.jdbc;

    opens com.fassti to javafx.fxml;
    exports com.fassti.model;
    exports com.fassti;
}