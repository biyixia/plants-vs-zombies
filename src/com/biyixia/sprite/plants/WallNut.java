package com.biyixia.sprite.plants;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 10:03
 */
public class WallNut extends Plant{
    public static final Image[] cards = new Image[]{
            new Image("images/GameFrame/back18.png"),
            new Image("images/GameFrame/back19.png")
    };
    private  int count = 0;
    public static final Image[] images = new Image[36];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/jianguo/jianguo ("+(i+1)+").png");
        }
    }
    public static boolean move = false;
    public static final int PRICE = 50;

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (count >= images.length){
            count = 0;
        }
        graphicsContext.drawImage(images[count++],this.getX(),this.getY());
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public WallNut(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
}
