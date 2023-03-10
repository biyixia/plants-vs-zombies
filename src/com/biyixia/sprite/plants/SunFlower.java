package com.biyixia.sprite.plants;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.Sun;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Date;


/**
 * @author dbc
 * @create 2023-01-17 21:35
 */
public class SunFlower extends Plant {
    //太阳花卡片
    public static final Image[] CARDS = new Image[]{
            new Image("images/GameFrame/back12.png"),
            new Image("images/GameFrame/back13.png")
    };
    public static boolean move = false;
    public static final int PRICE = 25;
    private int count = 0;
    private int startTime = StartAdventure.interval;
    private static final Image[] images = new Image[36];
    private boolean first = false;

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/flower/flower (" + (i + 1) + ").png");
        }
    }

    public SunFlower(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public SunFlower() {
        super();
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
        createSun();
    }

    private void createSun() {
        int stopTime = StartAdventure.interval;
        //太阳花首次产生太阳的时间是6秒
        if (!first && (stopTime - startTime > 6)) {
            first = true;
            StartAdventure.suns.add(new Sun(this.getX(), this.getY()));
            startTime = StartAdventure.interval;
        }
        //此后每20秒生产一个太阳
        if ((stopTime - startTime) > 20) {
            StartAdventure.suns.add(new Sun(this.getX(), this.getY()));
            startTime = StartAdventure.interval;
        }
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
