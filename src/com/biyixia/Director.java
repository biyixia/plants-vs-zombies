package com.biyixia;

import com.biyixia.scene.LoadScene;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author dbc
 * @create 2023-01-16 11:51
 */
public class Director {
    public static final double WIDTH = 800,HEIGHT = 600;
    private Director(){}
    private static Director instance = new Director();
    private Stage stage;
    LoadScene loadScene = new LoadScene();

    public static Director getInstance(){
        return instance;
    }

    public void init(Stage stage){
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("植物大战僵尸");
        stage.getIcons().add(new Image("images/LoadFrame/ico.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        this.stage = stage;
        toLoad();
        stage.show();
    }

    public void toLoad(){
        loadScene.init(stage);
    }

    public void toMenu(){
//        menoScene.init(stage);
        loadScene.clear(stage);
    }

    public void gameStart(){

    }

    public void gemeOver(){

    }
}
