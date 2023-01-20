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
public class Zombie extends Sprite {
    //攻击力
    int attack;
    //受到的伤害
    public int hurted = -1;
    //速度
    double speed;
    double maxSpeed;
    //血量
    int hp;
    //生命
    public boolean live = true;
    //减速
    public boolean reduceSpeed = false;
    //记录减速持续时间
    public Date startTime,stopTime;
    //吃的状态
    boolean eat = false;
    //收到攻击的音乐
    public  final AudioClip bgmAttack = GameUtil.soundPlay("sounds/attack-qizhi.wav");


    public Zombie(double y, double speed, int attack, int hp) {
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
    //吃身前的植物
    void attack(){
        Plant plant = getPlant(this.x + 140, this.y + 100);
        if (plant != null) {
            if (hurted <0) {
                plant.hurted = attack;
            }
        }
    }
    void attacked(){
        attackedBGM(bgmAttack);
    }
    //僵尸受到攻击
    void attackedBGM(AudioClip audioClip){
        //受到伤害
        if (hurted >0) {
            if (!audioClip.isPlaying()) {
                audioClip.setRate(2);
                audioClip.play();
            }
            hp -= hurted;
            hurted = -1;
        }
        //受到减速效果
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
    //获取僵尸身前的植物对象，若没有植物返回null
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
    //僵尸进入房间并吃掉了脑子！
    public void eatBrain() {
        if (this.getX() + this.width < 0) {
            StartAdventure.game = false;
        }
    }
}
