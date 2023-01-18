package com.biyixia.sprite;

import com.biyixia.scene.StartAdventure;
import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author dbc
 * @create 2023-01-16 17:45
 */
public class Sun extends Sprite {
    public static boolean live = false;
    public static final Image[] images = new Image[11];
    private static int count = 0;
    public static boolean move = false;
    double speedx, speedy;//阳光被收集后的移动速度
    double maxY;

    static {
        for (int i = 0; i < 11; i++) {
            images[i] = new Image("images/Sun/太阳" + i + ".png");
        }
    }

    public Sun(double x, double y, int speedx, int speedy) {
        super(x, 0);
        this.speedx = speedx;
        this.speedy = speedy;
        this.maxY = y;
    }

    public Sun(double x, double y, double width, double height) {
        super(x, 0, width, height);
        this.maxY = y;
        this.speedx = (this.x - 70) / 30;
        this.speedy = (y - 11) / 30;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (count >= images.length) {
            count = 0;
        }
        graphicsContext.drawImage(images[count++], this.x, this.y, this.width, this.height);
//        graphicsContext.drawImage(images[count++], 70, 11, this.width, this.height);
        if (move) {//往太阳数字移动    this.x >= 70 && this.y >= 11
            this.x -= this.speedx;
            this.y -= this.speedy;//阳光被收取，向着左上角移动
            if (GameUtil.ifRect(this.x, this.y, 65, 6, 80, 21)) {
                StartAdventure.money += 25;
                live = false;
                move = false;
            }
        } else {//阳光未被收取
            //未降落到最低处
            if (this.y < this.maxY) {
                this.y += 11;//阳光掉落y值增长
            }
        }
    }

    public double getSpeedx() {
        return speedx;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    @Override
    public void destroy() {

    }
}
