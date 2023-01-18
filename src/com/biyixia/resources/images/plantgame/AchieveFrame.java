package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * 成就界面
 */
public class AchieveFrame  {
	
	 static int x;
	 static int y;
	 static int loadtime;
	 static int op;
	 static GameUtil util;
	 static boolean live;
	 static Image img[];
	
	 
	static {
		 x=0;
		 y=30;
		 loadtime=0;
		 op=0;
		 live=false; 
		 util=new GameUtil();
		 img=new Image[12];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("AchieveFrame/achieve ("+(i+1)+").png");
			 }
		 }
	//绘制方法
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);
	}
	//鼠标移动方法处理
	public static void MouseMove(MouseEvent e) {
		if(loadtime==0) {//第一次进入成就系统
			if(GameUtil.ifRect(e.getX(),e.getY(),129,79,105,138,199,147,240,79)) {//back颜色变化
				op=1;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),748,401,802,459)) {//more颜色变化
				op=2;
			}else {op=0;}
		}else if(loadtime==1) {
			 if(GameUtil.ifRect(e.getX(),e.getY(),129,79,105,138,199,147,240,79)) {//back颜色变化
				op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),748,401,802,459)) {//more颜色变化
				op=5;
			}else {op=3;}
		}else if(loadtime==2) {
			 if(GameUtil.ifRect(e.getX(),e.getY(),129,79,105,138,199,147,240,79)) {//back颜色变化
					op=7;
				}else if(GameUtil.ifRect(e.getX(),e.getY(),748,401,802,459)) {//more颜色变化
					op=8;
				}else {op=6;}
		}else if(loadtime==3) {
			 if(GameUtil.ifRect(e.getX(),e.getY(),129,79,105,138,199,147,240,79)) {//back颜色变化
					op=10;
				}else if(GameUtil.ifRect(e.getX(),e.getY(),745,224,799,288)) {//top颜色变化
					op=11;
				}else {op=9;}
		}
	}
	//鼠标点击方法处理
	public static void MouseClick(MouseEvent e) {
		if(GameUtil.ifRect(e.getX(),e.getY(),129,79,105,138,199,147,240,79)) {//back按键
			live=false;MenuFrame.live=true;util.playBGM("sounds/bgm2.wav",1);
			op=0;loadtime=0;
		}
		if(GameUtil.ifRect(e.getX(),e.getY(),748,401,802,459)) {//more按键
			if(loadtime!=3) {
				util.playBGM("sounds/bgm2.wav",1);
				if(loadtime==0) {
					loadtime++;op=3;
				}else if(loadtime==1) {
					loadtime++;op=6;
				}else if(loadtime==2) {
					loadtime++;op=9;
				}
			}
		}
		 if(GameUtil.ifRect(e.getX(),e.getY(),745,224,799,288)) {//top按键
			 if(loadtime==3) {
				 op=0;loadtime=0;util.playBGM("sounds/bgm2.wav",1);
			 }
		 }
	}
	
	
}
