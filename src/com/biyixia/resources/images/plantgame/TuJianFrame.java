package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * 图鉴界面
 */
public class TuJianFrame  {
	
	 static int x;
	 static int y;
	 static int loadtime;
	 static int op;
	 static boolean live;
	 static GameUtil util;
	 static Image img[];
	 //初始化属性
	static {
		 x=0;
		 y=30;
		 loadtime=0;
		 op=0;
		 live=false; 
		 util=new GameUtil();
		 util.loadBGM("sounds/bgm6.wav");
		 img=new Image[20];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("TuJianFrame/tujian ("+(i+1)+").png");
			 }
		 }
	//绘制画面方法
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);
	}
	//鼠标移动事件处理方法
	public static void MouseMove(MouseEvent e){
		if(loadtime==0) {                                      //第一个界面移动鼠标
			if(GameUtil.ifRect(e.getX(),e.getY(),701,605,782,622)) {//关闭
				op=3;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),516,332,667,368)) {//僵尸图鉴
				op=2;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),132,330,285,366)) {//植物图鉴
				op=1;
			}else {op=0;}
		}else if(loadtime==1) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//关闭
				op=19;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				op=18;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),35,124,83,192)) {//豌豆射手
				op=13;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),86,124,134,192)) {//向日葵
				op=14;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),191,124,239,192)) {//坚果
				op=15;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),294,124,343,192)) {//寒冰豌豆
				op=16;
			}else{op=12;}
		}else if(loadtime==2) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//关闭
				op=11;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				op=10;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,122,103,190)) {//普通僵尸
				op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,122,272,190)) {//路障僵尸
				op=5;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),372,122,442,190)) {//铁通僵尸
				op=6;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,202,103,270)) {//报纸僵尸
				op=7;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,202,272,270)) {//橄榄球僵尸
			    op=8;
			}else{op=9;}
		}
	}

	
	//鼠标点击事件处理方法
	public static void MouseClick(MouseEvent e){
		if(loadtime==0) {
			if(GameUtil.ifRect(e.getX(),e.getY(),701,605,782,622)) {//关闭
				TuJianFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
				 util.stopBGM();LoadFrame.util.playBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),516,332,667,368)) {//僵尸图鉴
				loadtime=2;util.playBGM("sounds/bgm0.wav",1);op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),132,330,285,366)) {//植物图鉴
				loadtime=1;util.playBGM("sounds/bgm0.wav",1);op=12;
			}
		}else if(loadtime==1) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//关闭
				TuJianFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
				 LoadFrame.util.playBGM();util.stopBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				loadtime=0;util.playBGM("sounds/bgm0.wav",1);op=0;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),35,124,83,192)) {//豌豆射手
				util.playBGM("sounds/bgm0.wav",1);op=13;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),86,124,134,192)) {//向日葵
				util.playBGM("sounds/bgm0.wav",1);op=14;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),191,124,239,192)) {//坚果
				util.playBGM("sounds/bgm0.wav",1);op=15;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),294,124,343,192)) {//寒冰豌豆
				util.playBGM("sounds/bgm0.wav",1);op=16;
			}else{util.playBGM("sounds/bgm0.wav",1);op=17;}
		}else if(loadtime==2) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//关闭
				TuJianFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
				 LoadFrame.util.playBGM();util.stopBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				loadtime=0;util.playBGM("sounds/bgm0.wav",1);op=0;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,122,103,190)) {//普通僵尸
				util.playBGM("sounds/bgm0.wav",1);op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,122,272,190)) {//路障僵尸
				util.playBGM("sounds/bgm0.wav",1);op=5;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),372,122,442,190)) {//铁通僵尸
				util.playBGM("sounds/bgm0.wav",1);op=6;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,202,103,270)) {//报纸僵尸
				util.playBGM("sounds/bgm0.wav",1);op=7;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,202,272,270)) {//橄榄球僵尸
				util.playBGM("sounds/bgm0.wav",1);op=8;
			}else{util.playBGM("sounds/bgm0.wav",1);op=9;}
		}
	}
	
}
