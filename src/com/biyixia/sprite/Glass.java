package com.biyixia.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-16 17:40
 */
public class Glass extends Sprite{
    public boolean live = false;
    public Glass(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Glass(double x, double y) {
        super(x, y);
        this.width = 82;
        this.height = 91;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {

    }
}
