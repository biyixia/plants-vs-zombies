package com.biyixia.sprite.zombies;

import com.biyixia.scene.StartAdventure;
import com.biyixia.sprite.Sprite;

/**
 * @author dbc
 * @create 2023-01-18 19:42
 */
public class ZOMBIE extends Sprite {
    int attack;
    double speed;
    int hp;
    public boolean live = true;
    public boolean attacked = false;

    public ZOMBIE(double y, double speed, int attack, int hp) {
        super();
        super.setX(800);
        super.setY(y);
        this.speed = speed;
        this.attack = attack;
        this.hp = hp;
        this.width = 166;
        this.height = 144;
    }

    public void eatBrain(){
        if (this.getX()+this.width<0){
            StartAdventure.game = false;
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void destroy() {

    }
}
