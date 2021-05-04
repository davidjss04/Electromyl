package com.tifasz.modal;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class MAlert extends Alert {

    public MAlert(AlertType alertType, String title, String header) {
        super(alertType);
        setTitle(title);
        setHeaderText(header);
        initComponent();
    }

    private void initComponent(){
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        setResizable(false);
    }

    public boolean showAlert(){
        Optional<ButtonType> buttonType = showAndWait();
        if (buttonType.get().equals(ButtonType.OK)){
            return true;
        }

        return false;
    }

}
