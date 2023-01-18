package com.biyixia.sprite.plants;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 10:01
 */
public class PeaShooter extends Plant {
    public static boolean move = false;
    public static final int PRICE = 100;
    public static final Image[] cards = new Image[]{
            new Image("images/GameFrame/back14.png"),
            new Image("images/GameFrame/back15.png")
    };
    private  int count = 0;
    public static final Image[] images = new Image[36];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/wandou/wandou (" + (i + 1) + ").png");
        }
    }

    public PeaShooter(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (count >= images.length) {
            count = 0;
        }
        graphicsContext.drawImage(images[count++], this.getX(), this.getY());
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
