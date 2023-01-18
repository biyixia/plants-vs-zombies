package plantgame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
public class GameUtil {//GameUtil类：加载图片代码
    // 工具类最好将构造器私有化。
     GameUtil() {
     
    }
    
    /*
	 * 判断一个坐标是否在四个顶点组成的矩形内
	 * 逆时针从左上角给出四个顶点的坐标   x1,x2,x3,x4不可重复
	 * x2<x1   x3<x4
	 */
     static boolean ifRect(int x,int y,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) {
		if((y>=(((y2-y1)*(x-x1))/(x2-x1)+y1))&&(y<=(((y3-y2)*(x-x2))/(x3-x2)+y2))&&(y<=(((y4-y3)*(x-x3))/(x4-x3)+y3))&&(y>=(((y4-y1)*(x-x1))/(x4-x1)+y1))) {
			return true;
		}else {
			return false;
		}
	}
	 static boolean ifRect(int x,int y,int x1,int y1,int x2,int y2) {
		if(x>=x1&&x<=x2&&y>=y1&&y<=y2){
			return true;
		}else {
			return false;
		}
	}
    
    /*
     * 返回指定路径的图片对象
     */
    static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
    /*
     * 音乐播放方法
     */
     Clip bgm;
    public  void loadBGM(String path) {
    	try {
			bgm=AudioSystem.getClip();
			InputStream bg=this.getClass().getClassLoader().getResourceAsStream(path);
			AudioInputStream bj=AudioSystem.getAudioInputStream(bg);
				bgm.open(bj);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public  void playBGM() {
		bgm.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public  void stopBGM() {
    	bgm.stop();
    }
    public  void playBGM(String path,int x) {
    	if(x==1) {
    		try {
    			Clip bgm=AudioSystem.getClip();
    			InputStream bg=this.getClass().getClassLoader().getResourceAsStream(path);
    			AudioInputStream bj=AudioSystem.getAudioInputStream(bg);
    				bgm.open(bj);
    				bgm.start();
    		} catch (LineUnavailableException e) {
    			e.printStackTrace();
    		} catch (UnsupportedAudioFileException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
}