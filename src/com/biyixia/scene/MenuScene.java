package com.biyixia.scene;

import com.biyixia.Director;
import com.biyixia.utils.GameUtil;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


/**
 * @author dbc
 * @create 2023-01-16 15:21
 */
public class MenuScene {
    private Canvas canvas = new Canvas(Director.WIDTH,Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private int count = 0;
    private Image[] images = new Image[23];
    private long lastupdate = 0;
    private Refresh refresh = new Refresh();
    private AudioClip bgm4 = GameUtil.soundPlay("sounds/bgm4.wav");
    private final MouseMove mouseMove = new MouseMove();
    private final MouseClick mouseClick = new MouseClick();
    private boolean tips = false;

    public void init(Stage stage){
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("/images/MenuFrame/menu ("+(i+1)+").png");
        }
        AnchorPane anchorPane = new AnchorPane(canvas);
        stage.getScene().setRoot(anchorPane);
        stage.getScene().setOnMouseMoved(mouseMove);
        stage.getScene().setOnMouseClicked(mouseClick);
        refresh.start();
    }
    public void clear(Stage stage){
        refresh.stop();
        Director.bgm.stop();
        stage.getScene().setOnMouseClicked(null);
        stage.getScene().setOnMouseMoved(null);
    }
    private void paint(){
        if (!Director.bgm.isPlaying()){
            Director.bgm.play();
        }
        graphicsContext.drawImage(images[count],10,0);
    }
    private class Refresh extends AnimationTimer{
        @Override
        public void handle(long now) {
            if (now - lastupdate >= 28_000_000) {
                paint();
                lastupdate = now;
            }
        }
    }
    private class MouseMove implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),46,440,143,570)) {//成就
                count = 1;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }
            else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),269,500,323,543)){//花园
                count = 2;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),408,431,461,525)){//图鉴
                count = 3;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),521,536,601,582)){//商店
                count = 4;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),599,500,667,530)){//option
                count = 5;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),672,525,718,555)){//help
                count = 6;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),734,522,785,540)){//Quit
                count = 7;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),460,120,459,200,740,250,752,150)){//开始冒险
                count = 8;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),461,208,460,273,719,320,737,258)){//mini模式
                count = 9;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),463,287,462,341,693,384,705,335)){//puzzle模式
                count = 10;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),465,350,464,394,678,450,690,404)){//survival模式
                count = 11;
                if (!tips){
                    bgm4.play();
                    tips = true;
                }
            }
            else {
                count = 0;
                tips = false;
            }
        }
    }
    private class MouseClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),46,440,143,570)) {//成就
            }
            else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),269,500,323,543)){//花园

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),408,431,461,525)){//图鉴

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),521,536,601,582)){//商店

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),599,500,667,530)){//option

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),672,525,718,555)){//help

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),734,522,785,540)){//Quit

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),460,120,459,200,740,250,752,150)){//开始冒险
                Director.getInstance().toStartAdventure();
            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),461,208,460,273,719,320,737,258)){//mini模式

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),463,287,462,341,693,384,705,335)){//puzzle模式

            }else if(GameUtil.ifRect((int) event.getX(),(int) event.getY(),465,350,464,394,678,450,690,404)){//survival模式

            }
            else {
            }
        }
    }
}
