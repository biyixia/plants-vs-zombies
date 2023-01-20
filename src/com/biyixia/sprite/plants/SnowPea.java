package com.biyixia.sprite.plants;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.bullet.Bullet;
import com.biyixia.sprite.bullet.SnowBullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 10:04
 */
public class SnowPea extends Plant {
    //寒冰射手卡片
    public static final Image[] cards = new Image[]{
            new Image("images/GameFrame/back16.png"),
            new Image("images/GameFrame/back17.png")
    };
    public static boolean move = false;
    public static final int PRICE = 150;
    private  int count = 0;
    public static final Image[] images = new Image[36];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/hanbing/hanbing (" + (i + 1) + ").png");
        }
    }
    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
    }

    @Override
    public void work(GraphicsContext graphicsContext) {
        if (count >= images.length) {
            count = 0;
        }
        graphicsContext.drawImage(images[count++], this.getX(), this.getY());
        //测试发现，发射子弹频率过快会导致上个子弹爆炸声还没放完，第二个子弹已经爆炸因此产生第二个子弹有伤害没声音的现象
        if (count == 4){
            StartAdventure.bullets.add(new SnowBullet(this.getX()+50,this.getY()));
        }
    }

    public SnowPea(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
}
