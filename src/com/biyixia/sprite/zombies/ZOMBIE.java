package com.biyixia.sprite.zombies;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.Glass;
import com.biyixia.sprite.Sprite;
import com.biyixia.sprite.plants.Plant;
import com.biyixia.utils.GameUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;

import java.util.Date;

/**
 * @author dbc
 * @create 2023-01-18 19:42
 */
public class ZOMBIE extends Sprite {
    //攻击力
    int attack;
    public int hurted = -1;
    //速度
    double speed;
    double maxSpeed;
    //血量
    int hp;
    //生命
    public boolean live = true;
    //受到攻击
    public boolean attacked = false;
    //减速
    public boolean reduceSpeed = false;
    public Date startTime,stopTime;
    //吃的状态
    boolean eat = false;
    //收到攻击的音乐
    public  final AudioClip bgmAttack = GameUtil.soundPlay("sounds/attack-qizhi.wav");


    public ZOMBIE(double y, double speed, int attack, int hp) {
        super();
        super.setX(800);
        super.setY(y);
        this.speed = speed;
        this.attack = attack;
        this.hp = hp;
        this.width = 166;
        this.height = 144;
        this.maxSpeed = speed;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (hp > 0) {
            if (eat) {
                eat(graphicsContext);
            } else {
                walk(graphicsContext);
            }
            attacked();
            eatBrain();
            eat = getPlant(this.x + 140, this.y + 100) != null;
        } else {
            dead(graphicsContext);
        }
    }

    void walk(GraphicsContext graphicsContext) {
    }
    void eat(GraphicsContext graphicsContext) {
    }
    void dead(GraphicsContext graphicsContext){}
    void attacked(){
        attackedBGM(bgmAttack);
    }
    void attackedBGM(AudioClip audioClip){
        if (this.attacked && hurted >0) {
            if (!audioClip.isPlaying()) {
                audioClip.setRate(2);
                audioClip.play();
            }
            this.attacked = false;
            hp -= hurted;
            hurted = -1;
        }
        stopTime = new Date();
        if (this.reduceSpeed){
            startTime = new Date();
            this.reduceSpeed = false;
        }
        if (startTime != null){
            double interval1 = (stopTime.getTime() - startTime.getTime())*0.001;
            if ((interval1 > 0 && interval1 <=3)){
                speed = maxSpeed /2;
            }else {
                speed = maxSpeed;
            }
        }
    }
    Plant getPlant(double x, double y) {
        for (Glass glass : StartAdventure.glasses) {
            if (glass.live && GameUtil.ifRect(x, y, glass.getX(), glass.getY(),
                    glass.getX() + glass.getWidth(), glass.getY() + glass.getHeight())) {
                Plant plant = StartAdventure.plants.get(glass);
                return plant;
            }
        }
        return null;
    }
    public void eatBrain() {
        if (this.getX() + this.width < 0) {
            StartAdventure.game = false;
        }
    }
    //销毁僵尸对象
    @Override
    public void destroy() {
        StartAdventure.zombies.remove(this);
    }
}
