package com.biyixia.sprite.zombies;

import com.biyixia.sprite.plants.Plant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-18 19:45
 */
public class NewspaperZombie extends ZOMBIE {
    private static final Image[] images = new Image[235];
    private int count = 0;
    private final int max;

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/Zombies/baozhi/baozhi (" + (i + 1) + ").png");
        }
    }

    public NewspaperZombie(double y) {
        super(y, 1, 15, 200);
        this.max = hp;
    }

    public NewspaperZombie(double y, double speed, int attack, int hp) {
        super(y, speed, attack, hp);
        this.max = hp;
    }

    @Override
    public void walk(GraphicsContext graphicsContext) {
        if (hp > max / 2) {
            if (count >= 47) {
                count = 0;
            }
        } else if (hp > 0 && hp <= max / 2) {
            if (count <= 94 || count >= 133) {
                count = 94;
            }
            if (count == 120) {
                attack();
            }
        }
        this.setX(getX() - speed);
        graphicsContext.drawImage(images[count], this.getX(), this.getY(), images[count].getWidth(), images[count++].getHeight());
    }

    @Override
    void eat(GraphicsContext graphicsContext) {
        if (hp > max / 2) {
            if (count <= 47 || count >= 94) {
                count = 47;
            }
            if (count == 60) {
                attack();
            }
        } else if (hp > 0 && hp <= max / 2) {
            if (count <= 141 || count >= 188) {
                count = 141;
            }
            if (count == 150) {
                attack();
            }
        }
        graphicsContext.drawImage(images[count], this.getX(), this.getY(), images[count].getWidth(), images[count++].getHeight());
    }

    private void attack() {
        Plant plant = getPlant(this.x + 140, this.y + 100);
        if (plant != null){
            plant.attacked = true;
        }
    }

    @Override
    void dead(GraphicsContext graphicsContext) {
        if (count <= 188) {
            count = 188;
        }
        if (count >= 234) {
            live = false;
            return;
        }
        graphicsContext.drawImage(images[count], this.getX(), this.getY(), images[count].getWidth(), images[count++].getHeight());
    }
}
