package com.biyixia.sprite.plants;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.bullet.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 10:01
 */
public class PeaShooter extends Plant {
    //豌豆射手卡片
    public static final Image[] CARDS = new Image[]{
            new Image("images/GameFrame/back14.png"),
            new Image("images/GameFrame/back15.png")
    };
    public static boolean move = false;
    public static final int PRICE = 100;
    private int count = 0;
    public static final Image[] images = new Image[36];
    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/wandou/wandou (" + (i + 1) + ").png");
        }
    }

    public PeaShooter() {
    }

    public PeaShooter(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
    }

    @Override
    public void work(GraphicsContext graphicsContext) {
        if (count >= images.length) {
            count = 0;
        }
        //测试发现，发射子弹频率过快会导致上个子弹爆炸声还没放完，第二个子弹已经爆炸因此产生第二个子弹有伤害没声音的现象
        if (count == 10) {
            StartAdventure.bullets.add(new Bullet(this.getX() + 50, this.getY()));
        }
        graphicsContext.drawImage(images[count++], this.getX(), this.getY());
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public boolean getMove() {
        return move;
    }

    @Override
    public void setMove(boolean b) {
        move = b;
    }

    @Override
    public Image[] getCards() {
        return CARDS;
    }
}
