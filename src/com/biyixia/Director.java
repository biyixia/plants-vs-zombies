package com.biyixia;

import com.biyixia.scene.LoadScene;
import com.biyixia.scene.MenuScene;
import com.biyixia.utils.GameUtil;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
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
    public static AudioClip bgm = GameUtil.soundPlay("sounds/bgm.wav");
    LoadScene loadScene = new LoadScene();
    MenuScene menuScene = new MenuScene();

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
        loadScene.clear(stage);
        menuScene.init(stage);
    }

    public void toStartAdventure(){
        menuScene.clear(stage);
//        adventrue.init(stage);
    }

    public void gemeOver(){

    }
}
