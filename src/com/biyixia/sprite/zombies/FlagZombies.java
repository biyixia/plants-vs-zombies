package com.biyixia.sprite.zombies;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.Glass;
import com.biyixia.sprite.Sprite;
import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-16 17:47
 */
public class FlagZombies extends ZOMBIE {
    private static Image[] images = new Image[134];
    private int count = 0;
    private boolean eat = false;
    public boolean live = true;
    static {
        for (int i = 0; i < 134; i++) {
            images[i] = new Image("images/Zombies/qizhi/qizhi (" + (i + 1) + ").png");
        }
    }

    public FlagZombies(double y, double speed, int attack, int hp) {
        super(y, speed, attack, hp);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (hp > 0) {
            if (!eat){
                walk(graphicsContext);

            }else {
                eat(graphicsContext);
            }
            for (Glass glass : StartAdventure.glasses) {
                if (glass.live && GameUtil.ifRect(this.getX()+140,this.getY()+100,glass.getX(),glass.getY(),
                        glass.getX()+glass.getWidth(),glass.getY()+glass.getHeight())){
                    eat = true;
                    return;
                }else {
                    eat = false;
                }
            }
        }else {
            dead(graphicsContext);
        }

    }
    private void walk(GraphicsContext graphicsContext){
        if (count >= 48) {
            count = 0;
        }
        this.setX(getX()-speed);
        graphicsContext.drawImage(images[count], this.getX(), this.getY(),images[count].getWidth(),images[count++].getHeight());
    }

    private void eat(GraphicsContext graphicsContext){
        if (count<49 || count >= 88){
            count = 49;
        }
        graphicsContext.drawImage(images[count], this.getX(), this.getY(),images[count].getWidth(),images[count++].getHeight());
    }
    private void dead(GraphicsContext graphicsContext){
        if (count<88){
            count = 88;
        }
        if (count >= 134){
            live = false;
            return;
        }
        graphicsContext.drawImage(images[count], this.getX(), this.getY(),images[count].getWidth(),images[count++].getHeight());
    }
    @Override
    public void destroy() {

    }
}
