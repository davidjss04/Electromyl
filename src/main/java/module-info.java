module com.tifasz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.mariadb.jdbc;
    requires org.jetbrains.annotations;

    opens com.tifasz to javafx.fxml;
    exports com.tifasz;
    exports com.tifasz.controller;
}