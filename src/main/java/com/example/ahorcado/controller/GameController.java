package com.example.ahorcado.controller;

import com.example.ahorcado.model.Game;
import com.example.ahorcado.model.Player;
import com.example.ahorcado.view.GameStage;
import com.example.ahorcado.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameController {
    private Player player;
    private Game game;
    // Elementos
    @FXML
    private Label Word;
    @FXML
    private ImageView imageHanged;
    @FXML
    private Label playerName;
    @FXML
    private Label score;
    @FXML
    private Label interactionLabel;
    @FXML
    private AnchorPane PaneStart;
    @FXML
    private AnchorPane GamePane;
    @FXML
    private HBox HboxErorr;

    // para volver al inicio despues de jugar
    @FXML
    public void onHandleButtonEnd(ActionEvent event) throws IOException{
        WelcomeStage.getInstance();
        GameStage.deleteInstance();
    }

    @FXML
    public void onHandleButtonHelp(ActionEvent event) throws IOException{
        darPista();
        game.setAttemptHelp(game.getAttemptHelp() + 1);
        if (game.getAttemptHelp() == 3) {
            Button eventButton = (Button) event.getSource();
            eventButton.setDisable(true);
        }
    }
    public void onHandleButtonLetter(ActionEvent event){
        interactionLabel.setWrapText(true);
        if(game.getStart()){
            Button eventButton = (Button) event.getSource();
            String letter = eventButton.getText();
            eventButton.setDisable(true);
            String prov = Word.getText();
            String word = game.getWord();

            for (int i = 0;i < word.length();i++) {

                if (letter.equals(String.valueOf(word.charAt(i)))){
                    prov = reemplazarIndice(prov, i, letter.charAt(0));
                    player.setScore(player.getScore() + 50);
                    cambiarPuntaje();
                }
            }
            Word.setText(prov);

            if(word.contains(letter)){

                eventButton.setStyle("-fx-text-fill: white; -fx-background-color:  #3c3c3c; -fx-border-color: green;-fx-border-width: 2px");
                if(Objects.equals(Word.getText(), game.getWord())){
                    setInteractionLabel("Ganaste!! Palabra:" +game.getWord()+"\uD83D\uDE00", "green");
                    game.endGame();
                }else{
                    setInteractionLabel("Bien!!" +"\uD83D\uDE00", "green");
                }
            }else{
                if(game.increaseAttempts()){

                    setInteractionLabel("Fallaste!!" +"\uD83D\uDE41", "red");

                    addLabelError();
                    imageHanged.setImage(game.getImg());
                    player.setScore(player.getScore() - 75);
                    cambiarPuntaje();
                    eventButton.setStyle("-fx-text-fill: white; -fx-background-color:  #3c3c3c; -fx-border-color: red;-fx-border-width: 2px");
                }
                if(game.getAttempts() == 6){
                    setInteractionLabel("Perdiste!! Palabra:" +game.getWord() +"\uD83D\uDE41", "red");
                    game.endGame();
                }
            }
        }
    }


    public void setPlayer(Player player, String palabra) {

        this.player = player;
        playerName.setText(player.getName());
        game = new Game(palabra.toUpperCase());

        String prov = generarCadenaGuiones(game.getWord());

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

    private void addLabelError(){
        //Estilos para el label de error
        Label label = new Label("X");
        label.setStyle("-fx-font-family: 'Wingdings'; " +
                "-fx-font-size: 20px; " +
                "-fx-text-fill: red; " +
                "-fx-background-color: #ffeeee; " +
                "-fx-border-color: red; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px;");
        HboxErorr.getChildren().add(label);
    }

    private void setInteractionLabel(String text, String color){
        interactionLabel.setStyle("-fx-text-fill:"+ color);
        interactionLabel.setText(text);
    }

    private char obtenerPista(String palabraSecreta) {
        Random rand = new Random();
        int index = rand.nextInt(palabraSecreta.length());
        return palabraSecreta.charAt(index);
    }

    private void darPista() {
        String palabraSecreta = game.getWord();
        char pista = obtenerPista(palabraSecreta);
        if(game.getStart()){
            while (Word.getText().contains(""+pista) || game.help.contains(pista) ) {
                pista = obtenerPista(palabraSecreta);
            }
            game.help.add(pista);

            setInteractionLabel("Pista: La palabra secreta contiene la letra '" + pista + "'.", "purple");
        }
    }

    public static String generarCadenaGuiones(String palabra) {
        StringBuilder cadena = new StringBuilder();

        // Itera sobre cada caracter de la palabra original
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);

            // Si el caracter es un espacio, añade un espacio en blanco a la cadena
            if (caracter == ' ') {
                cadena.append(' ');
            } else {
                // Si no es un espacio, añade un guion bajo a la cadena
                cadena.append('_');
            }
        }

        return cadena.toString();
    }
}
