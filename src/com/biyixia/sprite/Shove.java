package com.biyixia.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-16 17:44
 */
public class Shove extends Sprite{
    public static boolean move = false;
    public static Image image = new Image("images/GameFrame/back5.png");
    public Shove(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image,this.x,this.y,this.width,this.height);
    }

    @Override
    public void destroy() {

    }
}
