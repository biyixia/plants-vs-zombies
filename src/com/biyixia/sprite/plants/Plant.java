package com.biyixia.sprite.plants;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-17 21:52
 */
public abstract class Plant extends Sprite {


    public Plant(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void destroy() {

    }
}
