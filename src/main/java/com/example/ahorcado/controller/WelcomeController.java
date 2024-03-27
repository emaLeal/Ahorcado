package com.example.ahorcado.controller;

import com.example.ahorcado.model.Player;
import com.example.ahorcado.view.GameStage;
import com.example.ahorcado.view.WelcomeStage;
import com.example.ahorcado.view.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class    WelcomeController {
    // Modelos
    private Player player;

    // Componentes GUI
    @FXML
    private TextField NickNameText;
    @FXML
    private Label err;

    /*
    * Funcion para iniciar el juego con el nombre del usuario
    *
    * @param event El evento que se manipula
    *
     */
    @FXML
    public void onHandleButtonPLay(ActionEvent event) throws IOException {


        try {
            String name = NickNameText.getText();
            if (name == null || name.trim().isEmpty()) { // Validar Campo de Texto
                this.err.setText("El nombre no puede estar Vacio");
                throw new Error("Name No puede estar vacio");
            }
            this.err.setText("");
            createPlayer(name);
            String palabra = new AlertBox().ShowMenssage("Ingresa palabra", "Palabra");
            GameStage.getInstance().getGameController().setPlayer(this.player, palabra);
            WelcomeStage.deleteInstance();
        } catch (Error err) {
            System.out.println(err);
        }
    }

    /*
    * Funcion para crear el jugador
    * @param name Nombre del jugador
    */
    void createPlayer(String name) {
        this.player = new Player(name, 1, 0);
    }
}
