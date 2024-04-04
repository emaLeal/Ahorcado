package com.example.ahorcado.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String word;
    private int attempts;
    private Image img;
    private boolean start;
    private int attemptHelp;
    public List<Character> help;

    public Game(String word) {
        this.word = word;
        this.attempts = 0;
        this.attemptHelp=0;
        this.help = new ArrayList<>();
        this.start = true;
    }

    public String getWord() {
        return word;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean increaseAttempts(){
        attempts++;

        return attempts < 7;
    }

    public Image getImg() {
        String PATH = "/com/example/ahorcado/images/";
        img = new Image(String.valueOf(getClass().getResource(PATH + "hanged" + attempts + ".png")));

        return img;
    }

    public boolean getStart(){
        return start;
    }

    public void endGame(){
        this.start = false;
    }

    public int getAttemptHelp() {
        return attemptHelp;
    }

    public void setAttemptHelp(int attemptHelp) {
        this.attemptHelp = attemptHelp;
    }
}
