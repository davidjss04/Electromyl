module com.fassti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires annotations;
    requires org.mariadb.jdbc;

    opens com.tifasz to javafx.fxml;
    exports com.tifasz.model;
    exports com.tifasz;
}