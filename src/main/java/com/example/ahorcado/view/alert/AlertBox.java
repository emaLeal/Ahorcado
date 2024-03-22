package com.example.ahorcado.view.alert;

import javafx.scene.control.Alert;

public class AlertBox implements IAlertBox{


    @Override
    public void ShowMenssage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
