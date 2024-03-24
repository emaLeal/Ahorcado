package com.example.ahorcado.controller;

import com.example.ahorcado.model.Player;
import com.example.ahorcado.view.GameStage;
import com.example.ahorcado.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class GameController {
    private Player player;

    // Elementos
    @FXML
    private PasswordField word;
    @FXML
    private Label playerName;
    @FXML
    private Label score;

    @FXML
    public void onHandleButtonBack(ActionEvent event) throws IOException {
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    @FXML
    public void onHandleButtonGetWord(ActionEvent event) throws IOException {
        System.out.println(word.getText());
    }

    public void setPlayer(Player player) {
        this.player = player;
        playerName.setText(player.getName());
        score.setText(String.valueOf(player.getScore()));
    }
}
