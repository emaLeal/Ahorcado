package com.example.ahorcado.controller;

import com.example.ahorcado.model.Player;
import com.example.ahorcado.view.GameStage;
import com.example.ahorcado.view.WelcomeStage;
import com.example.ahorcado.view.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private TextField NickNameText;
    @FXML
    public void onHandleButtonPLay(ActionEvent event) throws IOException {
        String name = NickNameText.getText();


        Player player = new Player(name,1);

        System.out.println(player.getName());

        // new AlertBox().ShowMenssage("Alerta", "Registro de player","Jugador creado");

        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }
}
