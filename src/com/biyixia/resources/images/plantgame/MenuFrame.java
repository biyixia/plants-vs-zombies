package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * 主菜单界面
 */
public class MenuFrame {
	
	 static int x;
	 static int y;
	 static int loadtime;
	 static int op;
	 static boolean live;
 	 static GameUtil util;
	 static Image img[];
	
	static {
		 x=0;
		 y=30;
		 loadtime=0;
		 op=0;
		 live=false;
		 util=new GameUtil();
		 img=new Image[23];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("MenuFrame/menu ("+(i+1)+").png");
			 }
		 }
	//鼠标移动事件处理
	public static void MouseMove(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),46,507,143,570)) {//成就
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=1;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),269,519,323,562)){//花园
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=2;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),408,461,461,555)){//图鉴
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=3;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),521,566,601,622)){//商店
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=4;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),599,537,667,555)){//option
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=5;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),672,563,718,579)){//help
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=6;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),734,552,785,570)){//Quit
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=7;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),659,502,696,520)){//help左侧的花
			
		}else if(GameUtil.ifRect(e.getX(),e.getY(),710,475,742,501)){//help右侧的花
			
		}else if(GameUtil.ifRect(e.getX(),e.getY(),760,508,791,533)){//Quit上的花
			
		}else if(GameUtil.ifRect(e.getX(),e.getY(),460,180,459,233,740,277,752,211)){//开始冒险
			if(MenuFrame.loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=8;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),461,248,460,313,719,360,737,298)){//mini模式
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=9;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),463,327,462,371,693,424,705,375)){//puzzle模式
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=10;
		}else if(GameUtil.ifRect(e.getX(),e.getY(),465,390,464,434,678,490,690,444)){//survival模式
			if(loadtime==0) {
				util.playBGM("sounds/bgm4.wav",1);loadtime++;
			}op=11;
		}else {loadtime=0;op=0;}
	}
	//鼠标点击事件处理
	public static void MouseClick(MouseEvent e) {
		if(GameUtil.ifRect(e.getX(),e.getY(),46,507,143,570)) {//成就
			live=false;AchieveFrame.live=true;loadtime=0;op=0;
			util.playBGM("sounds/bgm0.wav",1);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),269,519,323,562)){//花园
			live=false;GardenFrame.live=true;loadtime=0;op=0;
			util.playBGM("sounds/bgm0.wav",1);LoadFrame.util.stopBGM();GardenFrame.util.playBGM();
		}else if(GameUtil.ifRect(e.getX(),e.getY(),408,461,461,555)){//图鉴
			MenuFrame.live=false;TuJianFrame.live=true;loadtime=0;op=0;
			util.playBGM("sounds/bgm0.wav",1);LoadFrame.util.stopBGM();TuJianFrame.util.playBGM();
		}else if(GameUtil.ifRect(e.getX(),e.getY(),521,566,601,622)){//商店
			live=false;ShopFrame.live=true;loadtime=0;op=0;
			util.playBGM("sounds/bgm0.wav",1);
		}/*else if(ifRect(e.getX(),e.getY(),599,537,667,555)){//option
			
		}*/else if(GameUtil.ifRect(e.getX(),e.getY(),672,563,718,579)){//help
			MenuFrame.live=false;HelpFrame.live=true;loadtime=0;op=0;
			LoadFrame.util.stopBGM();util.playBGM("sounds/bgm0.0.wav",1);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),734,552,785,570)){//Quit
			System.exit(0);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),659,502,696,520)){//help左侧的花
			op=20;util.playBGM("sounds/bgm0.wav",1);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),710,475,742,501)){//help右侧的花
			op=21;util.playBGM("sounds/bgm0.wav",1);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),760,508,791,533)){//Quit上的花
			op=22;util.playBGM("sounds/bgm0.wav",1);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),460,180,459,233,740,277,752,211)){//开始冒险
			MenuFrame.live=false;GameFrame.live=true;loadtime=0;op=0;LoadFrame.util.stopBGM();
			GameFrame.util.playBGM();util.playBGM("sounds/bgm7.wav", 1);
		}else if(GameUtil.ifRect(e.getX(),e.getY(),461,248,460,313,719,360,737,298)){//mini模式
			MenuFrame.live=false;MiniFrame.live=true;loadtime=0;op=0;util.playBGM("sounds/bgm0.wav",1);
			LoadFrame.util.stopBGM();TuJianFrame.util.playBGM();
		}else if(GameUtil.ifRect(e.getX(),e.getY(),463,327,462,371,693,424,705,375)){//puzzle模式
			MenuFrame.live=false;PuzzleFrame.live=true;op=0;util.playBGM("sounds/bgm0.wav",1);
			LoadFrame.util.stopBGM();TuJianFrame.util.playBGM();
		}else if(GameUtil.ifRect(e.getX(),e.getY(),465,390,464,434,678,490,690,444)){//survival模式
			MenuFrame.live=false;SurvivalFrame.live=true;op=0;util.playBGM("sounds/bgm0.wav",1);
			LoadFrame.util.stopBGM();TuJianFrame.util.playBGM();
		}
	}

	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);
	} 
}
