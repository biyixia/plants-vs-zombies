package com.biyixia.sprite.zombies;

import com.biyixia.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-16 17:47
 */
public class FlagZombies extends Sprite {
    private static Image[] images = new Image[134];
    static {
        for (int i = 0; i < 134; i++) {
            images[i] = new Image("images/Zombies/qizhi/qizhi (" + (i + 1) + ").png");
        }
    }
    public FlagZombies(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
//        graphicsContext.drawImage(image,this.x,this.y,this.width,this.height);

    }

    @Override
    public void destroy() {

    }
}
