package com.example.ahorcado.view;

import com.example.ahorcado.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {

    private GameController gameController;
    public  GameStage() throws IOException{
        String url = "/com/example/ahorcado/game-view.fxml";
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        gameController = loader.getController();

        setTitle("Game");
        Scene scene = new Scene(root);
        setScene(scene);
        Node nodeRoot = scene.getRoot();
        nodeRoot.lookupAll(".boton-a").forEach(node -> {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setStyle("-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color:  #3c3c3c;-fx-border-width: 2px");
            }
        });
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/com/example/ahorcado/images/favicon.png"))));
        setResizable(false);
        show();
    }

    private static class GameStageHolder{

        private static  GameStage INSTANCE;
    }

    public static GameStage getInstance() throws IOException {
        return GameStageHolder.INSTANCE = new GameStage();
    }

    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }

    public GameController getGameController() {
        return gameController;
    }
}
