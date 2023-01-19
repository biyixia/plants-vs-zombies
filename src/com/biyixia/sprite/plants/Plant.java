package com.biyixia.sprite.plants;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.Glass;
import com.biyixia.sprite.Sprite;
import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ConcurrentModificationException;

/**
 * @author dbc
 * @create 2023-01-17 21:52
 */
public abstract class Plant extends Sprite {
    public int hp = 100;
    public boolean attacked = false;

    public Plant(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void destroy() {
        for (Glass glass : StartAdventure.glasses) {
            if (glass.live && this.x == glass.getX() && this.y == glass.getY()) {
                glass.live = false;
                StartAdventure.plants.remove(glass);
                break;
            }
        }
    }
}
