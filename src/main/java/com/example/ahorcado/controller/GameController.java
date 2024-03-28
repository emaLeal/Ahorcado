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
    private Label Word;
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
        String prov = Word.getText();

        for (int i = 0;i < word.length();i++) {
            if (letter.equals(String.valueOf(word.charAt(i)))){
                prov = reemplazarIndice(prov, i, letter.charAt(0));
                player.setScore(player.getScore() + 50);
                cambiarPuntaje();
            }
        }
        Word.setText(prov);
        if(word.contains(letter)){
            eventButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-border-color: white");

        }else{
            player.setScore(player.getScore() - 75);
            cambiarPuntaje();
            eventButton.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-border-color: white");
        }

    }
    public void setPlayer(Player player, String palabra) {
        this.player = player;
        playerName.setText(player.getName());
        cambiarPuntaje();
        this.word = palabra.toUpperCase();
        String prov = "";
        for (int i = 0;i<word.length();i++) {
            prov += "_";
        }
        Word.setText(prov);
    }

    public static String reemplazarIndice(String str, int indice, char caracter) {
        if (indice < 0 || indice >= str.length()) {
            // Índice fuera de rango, devuelve el string original
            return str;
        }

        char[] chars = str.toCharArray(); // Convierte el string en un arreglo de caracteres
        chars[indice] = caracter; // Reemplaza el carácter en el índice especificado
        return new String(chars); // Convierte el arreglo de caracteres de nuevo en un string y devuelve
    }

    private void cambiarPuntaje() {
        score.setText(String.valueOf(player.getScore()));
    }
}
