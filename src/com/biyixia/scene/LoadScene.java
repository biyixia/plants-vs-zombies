package com.biyixia.scene;

import com.biyixia.Director;
import com.biyixia.utils.GameUtil;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
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
 * @create 2023-01-16 12:26
 */
public class LoadScene {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private Refresh refresh = new Refresh();
    private static Image[] images = new Image[104];
    private int count = 0;
    private long lastUpdate = 0 ;
    private MouseMove mouseMove = new MouseMove();
    private boolean load = false;
    private MouseClick mouseClick = new MouseClick();

    public void init(Stage stage) {
        for (int i = 1; i < 105; i++) {
            images[i-1] = new Image("images/LoadFrame/load (" + i + ").png");
        }
        AnchorPane anchorPane = new AnchorPane(canvas);
        stage.getScene().setRoot(anchorPane);
        stage.getScene().setOnMouseMoved(mouseMove);
        stage.getScene().setOnMouseClicked(mouseClick);
        refresh.start();
    }

    private void paint() {
        if(count >= images.length-1) {
            if (!Director.bgm.isPlaying())
                Director.bgm.play();
            load = true;
            return;
        }
        if (count == 18){
            Director.bgm.play();
        }
        graphicsContext.drawImage(images[count++], 10, 0);
    }


    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long now) {
            //递给handle()方法的参数是一个时间戳，以纳秒为单位。所以，如果要限制更新，以便它们不会发生比一次多，设置时间戳只差在28毫秒以上
            if (now - lastUpdate >= 50_000_000) {
                paint();
                lastUpdate = now ;
            }
        }
    }

    //鼠标移动事件处理方法
    private class MouseMove implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if (load) {
                if (GameUtil.ifRect((int) event.getX(),(int) event.getY(),326,540,484,569)){
                    graphicsContext.drawImage(images[103],10,0);
                }else {
                    graphicsContext.drawImage(images[102],10,0);
                }
            }
        }
    }


    //鼠标点击事件处理方法
    private class MouseClick implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if (load) {
                if (GameUtil.ifRect((int) event.getX(),(int) event.getY(),326,540,484,569)){
                    AudioClip bgm0 = GameUtil.soundPlay("sounds/bgm0.wav");
                    bgm0.setVolume(2);
                    bgm0.play();
                    Director.getInstance().toMenu();
                }
            }
        }
    }

    public void clear(Stage stage){
        refresh.stop();
        stage.getScene().removeEventHandler(MouseEvent.MOUSE_CLICKED,mouseClick);
        stage.getScene().removeEventHandler(MouseEvent.MOUSE_MOVED,mouseMove);
    }
}
