package com.biyixia.sprite.zombies;

import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * @author dbc
 * @create 2023-01-18 19:44
 */
public class BucketheadZombie extends ZOMBIE {
    private static Image[] images = new Image[239];
    private int max;
    private int count = 0;
    public static final AudioClip bucketAttacked = GameUtil.soundPlay("sounds/attack-tietong.wav");

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("images/Zombies/tietong/tietong (" + (i + 1) + ").png");
        }
    }

    public BucketheadZombie(Double y) {
        super(y, 1, 15, 200);
        this.max = hp;
    }

    public BucketheadZombie(double y, double speed, int attack, int hp) {
        super(y, speed, attack, hp);
        this.max = hp;
    }

    @Override
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
        } else if (hp > 0 && hp <= max / 2) {
            if (count <= 143 || count >= 191){
                count = 143;
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

    @Override
    void attacked() {
        if (hp > max / 2) {
            super.attackedBGM(bucketAttacked);
        } else if (hp > 0 && hp <= max / 2) {
            super.attackedBGM(bgmAttack);
        }
    }
}

