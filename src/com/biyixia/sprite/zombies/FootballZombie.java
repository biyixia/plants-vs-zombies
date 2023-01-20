package com.biyixia.sprite.zombies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 19:45
 */
public class FootballZombie extends Zombie {
    private  int count = 0;
    private  int max;
    private static Image[] images = new Image[239];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/Zombies/ganlan/ganlan (" + (i+1) + ").png");
        }
    }
    public FootballZombie(double y){
        super(y,1,20,200);
        max = hp;
    }
    public FootballZombie(double y, double speed, int attack, int hp) {
        super(y, speed, attack, hp);
        max = hp;
    }
    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
    }

    @Override
    void walk(GraphicsContext graphicsContext) {
        if (hp > max / 2) {
            if (count >= 48) {
                count = 0;
            }
        } else if (hp > 0 && hp <= max / 2) {
            if (count <= 96 || count >= 143) {
                count = 96;
            }
        }
        this.setX(getX()-speed);
        graphicsContext.drawImage(images[count++], this.x, this.y);
    }

    @Override
    void eat(GraphicsContext graphicsContext) {
        if (hp > max / 2) {
            if (count <= 48 || count >= 96) {
                count = 48;
            }
            if (count == 60){
                attack();
            }
        } else if (hp > 0 && hp <= max / 2) {
            if (count <= 143 || count >= 191){
                count = 143;
            }
            if (count == 170){
                attack();
            }
        }
        graphicsContext.drawImage(images[count++], this.x, this.y);
    }

    @Override
    void dead(GraphicsContext graphicsContext) {
        if (count <= 191){
            count = 191;
        }
        if (count >= 238) {
            this.live = false;
            return;
        }
        graphicsContext.drawImage(images[count++], this.x, this.y);
    }

}
