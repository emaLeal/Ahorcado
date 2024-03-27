package com.example.ahorcado.controller;

import com.example.ahorcado.model.Player;
import com.example.ahorcado.view.GameStage;
import com.example.ahorcado.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameController {
    private Player player;

    // Elementos
    @FXML
    private String word = "CELULAR";
    @FXML
    private Label playerName;
    @FXML
    private Label score;
    @FXML
    private Button buttonStart;
    @FXML
    private AnchorPane PaneStart;
    @FXML
    private AnchorPane GamePane;

    @FXML
    public void onHandleButtonBack(ActionEvent event) throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    @FXML
    public void onHandleButtonGetWord(ActionEvent event) throws IOException {
        PaneStart.setVisible(false);
        GamePane.setVisible(true);
    }

    public void onHandleButtonLetter(ActionEvent event){

        Button eventButton = (Button) event.getSource();
        String letter = eventButton.getText();
        eventButton.setDisable(true);

        if(word.contains(letter)){
            eventButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-border-color: white");

        }else{
            eventButton.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-border-color: white");

        }

    }
    public void setPlayer(Player player) {
        this.player = player;
        playerName.setText(player.getName());
        score.setText(String.valueOf(player.getScore()));
    }
}
