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

    public static AudioClip soundPlay(String src){
        AudioClip audioClip = new AudioClip(GameUtil.class.getClassLoader().getResource("sounds/bgm.wav").toString());
        audioClip.setVolume(0.5);
        return audioClip;
    }
}
