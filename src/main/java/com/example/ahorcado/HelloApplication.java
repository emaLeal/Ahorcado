package com.example.ahorcado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFx");
        primaryStage.show();
        VBox root = new VBox();
        Text msg = new Text("Bienvenido a JavaFx");
        root.getChildren().add(msg);
        Scene scene = new Scene(root, 300, 50);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}