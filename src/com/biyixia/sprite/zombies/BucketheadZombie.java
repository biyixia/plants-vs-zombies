package com.biyixia.sprite.zombies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 19:44
 */
public class BucketheadZombie extends ZOMBIE {
    private static Image[] images = new Image[134];
    private double speedy;

    static {
        for (int i = 0; i < 134; i++) {
            images[i] = new Image("images/Zombies/tietong/tietong (" + i + ").png");
        }
    }

    public BucketheadZombie(double y, double speed, int attack, int hp) {
        super(y, speed, attack, hp);
    }
    @Override
    public void paint(GraphicsContext graphicsContext) {
//        graphicsContext.drawImage(image,this.x,this.y,this.width,this.height);

    }

    @Override
    public void destroy() {

    }
}

