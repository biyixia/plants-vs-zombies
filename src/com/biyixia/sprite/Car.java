package com.biyixia.sprite;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.zombies.ZOMBIE;
import com.biyixia.utils.GameUtil;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


/**
 * @author dbc
 * @create 2023-01-16 17:42
 */
public class Car extends Sprite{
    private  AudioClip bgmCar = GameUtil.soundPlay("sounds/car.wav");
    private  boolean car = false;
    public  boolean live = true;
    int speed;
    Image image = new Image("images/GameFrame/back4.png");

    public Car(double x, double y, double width, double height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (live) {
            graphicsContext.drawImage(image, this.x, this.y, this.width, this.height);
        }else {
            if (!car && !bgmCar.isPlaying()){
                bgmCar.play();
                car = true;
            }
            attack();
            graphicsContext.drawImage(image, this.x, this.y, this.width, this.height);
            this.x += 15;
        }
        work();
    }
    /* TODO 割草机攻击僵尸 */
    private void work(){
        for (ZOMBIE zombie : StartAdventure.zombies) {
            if (GameUtil.ifRect(this.x, this.y, zombie.getX() + 90, zombie.getY() + 50, zombie.getX() + zombie.getWidth(), zombie.getY() + zombie.getHeight())) {
                live = false;
            }
        }
    }

    private void attack(){
        for (ZOMBIE zombie : StartAdventure.zombies) {
            if (GameUtil.ifRect(this.x, this.y, zombie.getX() + 90, zombie.getY() + 50, zombie.getX() + zombie.getWidth(), zombie.getY() + zombie.getHeight())) {
                live = false;
                zombie.setHp(-1);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
