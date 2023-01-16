package com.biyixia.utils;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.Objects;

/**
 * @author dbc
 * @create 2023-01-16 14:01
 */
public class GameUtil {
    public static boolean ifRect(int x,int y,int x1,int y1,int x2,int y2) {
        if(x>=x1&&x<=x2&&y>=y1&&y<=y2){
            return true;
        }else {
            return false;
        }
    }
    public static boolean ifRect(int x,int y,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) {
        if((y>=(((y2-y1)*(x-x1))/(x2-x1)+y1))&&(y<=(((y3-y2)*(x-x2))/(x3-x2)+y2))&&(y<=(((y4-y3)*(x-x3))/(x4-x3)+y3))&&(y>=(((y4-y1)*(x-x1))/(x4-x1)+y1))) {
            return true;
        }else {
            return false;
        }
    }

    public static AudioClip soundPlay(String src){
        AudioClip audioClip = new AudioClip(GameUtil.class.getClassLoader().getResource(src).toString());
        audioClip.setVolume(0.5);
        return audioClip;
    }
}
