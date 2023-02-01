package com.biyixia.sprite.plants;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 10:03
 */
public class WallNut extends Plant {
    //坚果卡片
    public static final Image[] CARDS = new Image[]{
            new Image("images/GameFrame/back18.png"),
            new Image("images/GameFrame/back19.png")
    };
    public static boolean move = false;
    public static final int PRICE = 50;
    private int count = 0;
    public static final Image[] images = new Image[115];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/jianguo/jianguo (" + (i + 1) + ").png");
        }
    }


    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
    }

    @Override
    public void work(GraphicsContext graphicsContext) {
        if (hp > 200 && hp <= 300) {
            if (count >= 40) {
                count = 0;
            }
        }else if (hp > 100 && hp <= 200) {
            if (count < 40 || count >= 77) {
                count = 40;
            }
        }else if (hp <= 100){
            if (count<77 || count >= 115){
                count = 77;
            }
        }
        graphicsContext.drawImage(images[count++], this.getX(), this.getY());
    }

    public WallNut() {
    }

    public WallNut(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.hp = 300;
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
