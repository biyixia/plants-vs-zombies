package com.biyixia.sprite.zombies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-16 17:47
 */
public class FlagZombies extends Zombie {
    private static final Image[] images = new Image[134];
    private int count = 0;

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/Zombies/qizhi/qizhi (" + (i + 1) + ").png");
        }
    }

    public FlagZombies(double y){
        super(y,0.8,10,100);
    }
    public FlagZombies(double y, double speed, int attack, int hp) {
        super(y, speed, attack, hp);
    }
    public void paint(GraphicsContext graphicsContext){
        super.paint(graphicsContext);
    }
    @Override
    void walk(GraphicsContext graphicsContext) {
        if (count >= 48) {
            count = 0;
        }
        this.setX(getX()-speed);
        graphicsContext.drawImage(images[count], this.getX(), this.getY(),images[count].getWidth(),images[count++].getHeight());
    }

    @Override
    void eat(GraphicsContext graphicsContext) {
        if (count<49 || count >= 88){
            count = 49;
        }
        if (count == 60){
            attack();
        }
        graphicsContext.drawImage(images[count], this.getX(), this.getY(),images[count].getWidth(),images[count++].getHeight());
    }

    @Override
    void dead(GraphicsContext graphicsContext) {
        if (count<88){
            count = 88;
        }
        if (count >= 134){
            live = false;
            return;
        }
        graphicsContext.drawImage(images[count], this.getX(), this.getY(),images[count].getWidth(),images[count++].getHeight());
    }
}
