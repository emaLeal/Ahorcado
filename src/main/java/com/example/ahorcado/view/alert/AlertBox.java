package com.example.ahorcado.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertBox implements IAlertBox{

    @Override
    public String ShowMenssage(String title, String header) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setTitle("Ingresar Palabra");
        dialog.setHeaderText("Por favor ingrese la palabra:");

        // Creamos un campo de contraseña
        PasswordField passwordField = new PasswordField();

        // Creamos un botón de confirmación
        Button confirmButton = new Button("Confirmar");
        confirmButton.setOnAction(event -> {
            if (passwordField.getText().isEmpty() || containsNumber(passwordField.getText())) {
                dialog.setHeaderText("Por favor ingrese una palabra válida:");
            } else {
                dialog.setResult(ButtonType.OK); // Establecemos el resultado del diálogo como OK
                dialog.close(); // Cerramos el diálogo
            }
        });

        // Creamos un contenedor para el campo de contraseña y el botón
        VBox vbox = new VBox(passwordField, confirmButton);
        dialog.getDialogPane().setContent(vbox);

        // Aplicar estilo
        dialog.getDialogPane().setStyle("-fx-text-fill: red;-fx-background-color: red;-fx-border-color: black");
        confirmButton.setStyle("-fx-text-fill: white; -fx-background-color: black;-fx-border-color: red ");
        dialog.setGraphic(new javafx.scene.image.ImageView(new Image(getClass().getResourceAsStream("/com/example/ahorcado/images/favicon.png"))));
        dialog.setResizable(false);
        //cambiar el color del encabezado (no le sepo)
        //dialog.getDialogPane().lookup(".header - panel").setStyle("-fx-background-color: red; -fx-text-fill: white;");
        dialog.getDialogPane().getScene().getWindow().setWidth(300);
        dialog.getDialogPane().getScene().getWindow().setHeight(200);


        // Mostramos el diálogo y esperamos hasta que se complete
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String word = passwordField.getText();
            return word;
        }
        return null;
    }

    private boolean containsNumber(String text) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
