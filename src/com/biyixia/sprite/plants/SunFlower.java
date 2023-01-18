package com.biyixia.sprite.plants;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * @author dbc
 * @create 2023-01-17 21:35
 */
public class SunFlower extends Plant {
    public static final Image[] cards = new Image[]{
            new Image("images/GameFrame/back12.png"),
            new Image("images/GameFrame/back13.png")
    };
    private  int count = 0;
    public static final Image[] images = new Image[36];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/flower/flower (" + (i + 1) + ").png");
        }
    }

    public static boolean move = false;
    public static final int PRICE = 25;


    public SunFlower(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (count >= images.length){
            count = 0;
        }
        graphicsContext.drawImage(images[count++],this.getX(),this.getY());
    }

    @Override
    public void destroy() {

    }
}
