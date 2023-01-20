package com.biyixia.sprite.bullet;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.zombies.Zombie;
import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * @author dbc
 * @create 2023-01-19 12:25
 */
public class SnowBullet extends Bullet {
    private static Image image = new Image("images/Bullets/bullet2.png");
    private static int speed = 5;
    private boolean live = true;

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (live) {
            this.x += speed;
            graphicsContext.drawImage(image, this.x, this.y, image.getWidth(), image.getHeight());
            for (Zombie zombie : StartAdventure.zombies) {
                if (zombie.live) {
                    if (GameUtil.ifRect(this.x + image.getWidth(), this.y, zombie.getX() + 90, zombie.getY() + 50, zombie.getX() + zombie.getWidth(), zombie.getY() + zombie.getHeight())) {
                        if (zombie.hurted < 0){
                            zombie.hurted = 25;
                        }
                        if (!zombie.reduceSpeed){
                            zombie.reduceSpeed = true;
                        }
                        live = false;
                    }
                }
            }
        }
    }

    public SnowBullet(double x, double y) {
        super(x, y);
    }
}
