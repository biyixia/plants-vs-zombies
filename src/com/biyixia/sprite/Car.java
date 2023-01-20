package com.biyixia.sprite;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.zombies.Zombie;
import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;


/**
 * @author dbc
 * @create 2023-01-16 17:42
 */
public class Car extends Sprite {
    private static final AudioClip bgmCar = GameUtil.soundPlay("sounds/car.wav");
    Image image = new Image("images/GameFrame/back4.png");
    private boolean car = false;        //割草机工作声音仅播放一次

    public boolean live = true;
    int speed;

    public Car(double x, double y, double width, double height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (live) {
            graphicsContext.drawImage(image, this.x, this.y, this.width, this.height);
            for (Zombie zombie : getZombies(this.x, this.y)) {
                if (zombie != null){
                    live = false;
                    return;
                }
            }
        } else {
            if (!car && !bgmCar.isPlaying()) {
                bgmCar.play();
                car = true;
            }
            attack();
            graphicsContext.drawImage(image, this.x, this.y, this.width, this.height);
            this.x += 15;
        }
    }

    //返回割草机前的僵尸对象
    private ArrayList<Zombie> getZombies(double x, double y) {
        ArrayList<Zombie> zombies = new ArrayList<>();
        for (Zombie zombie : StartAdventure.zombies) {
            if (GameUtil.ifRect(x, y, zombie.getX() + 90, zombie.getY() + 50, zombie.getX() + zombie.getWidth(), zombie.getY() + zombie.getHeight())) {
                zombies.add(zombie);
            }
        }
        return zombies;
    }

    private void attack() {
        ArrayList<Zombie> zombies = getZombies(this.x, this.y);
        for (Zombie zombie : zombies) {
            if (zombie != null && zombie.hurted < 0){
                zombie.hurted = 9999;
            }
        }
    }
}
