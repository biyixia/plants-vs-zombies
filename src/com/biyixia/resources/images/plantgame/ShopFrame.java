package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * 花园界面
 */
public class ShopFrame  {
	
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
		 img=new Image[43];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("ShopFrame/shop ("+(i+1)+").png");
			 }
		 }
	//绘制界面
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);   // 商店界面的背景
	}
	//鼠标移动事件处理
	public static void MouseMove(MouseEvent e) {
		if(loadtime==0) {                                      //商店第一页
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//主菜单
				op=11;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				op=9;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				op=10;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),420,252,480,324)) {//卡片槽
				op=1;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),505,252,554,324)) {//池塘车
				op=2;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),571,252,628,324)) {//钉耙
				op=3;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),653,252,702,324)) {//房顶车
				op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),381,343,432,428)) {//机枪豌豆
				op=5;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),454,343,503,428)) {//双胞向日葵
				op=6;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),529,343,577,428)) {//多嘴大喷菇
				op=7;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),602,343,651,428)) {//睡莲
				op=8;
			}else {op=0;}
		}else if(loadtime==1) {                                      //商店第二页
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//主菜单
				op=21;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				op=19;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				op=20;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),430,239,480,324)) {//钢地刺
				op=13;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),504,239,554,324)) {//吸金石
				op=14;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),579,239,627,324)) {//冰冻西瓜
				op=15;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),652,239,701,324)) {//玉米加农炮	
				op=16;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),381,343,430,428)) {//变身茄子
				op=17;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),456,357,505,428)) {//坚果愈合术
				op=18;
			}else {op=12;}
		}else if(loadtime==2) {                                      //商店第三页
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//主菜单
				op=31;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				op=29;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				op=30;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),429,261,628,323)) {//花盆
				op=23;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),644,242,702,324)) {//金水壶
				op=24;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),381,351,431,430)) {//化肥
				op=25;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),454,348,504,428)) {//杀虫剂
				op=26;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),529,346,577,427)) {//留声机
				op=27;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),600,348,655,428)) {//手套
				op=28;
			}else {op=22;}
		}else if(loadtime==3) {                                      //商店第四页
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//主菜单
				op=41;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				op=39;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				op=40;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),421,239,487,325)) {//蘑菇园
				op=33;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),496,240,561,323)) {//水族馆
				op=34;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),573,260,631,324)) {//手推车
				op=35;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),650,254,701,323)) {//蜗牛
				op=36;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),371,342,437,428)) {//智慧树
				op=37;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),457,353,504,427)) {//智慧树肥料
				op=38;
			}else {op=32;}
		}
	}
	//鼠标点击事件处理
	public static void MouseClick(MouseEvent e) {
		if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {   //返回主菜单按钮
			live=false;MenuFrame.live=true;op=0;loadtime=0;
			util.playBGM("sounds/bgm0.wav",1);
		}
		
		if(loadtime==0) {//商店第一页
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				util.playBGM("sounds/bgm5.wav",1);
				op=32;loadtime=3;              //切换到第四页
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				util.playBGM("sounds/bgm5.wav",1);
				op=12;loadtime=1;              //切换到第二页
			}
		}else if(loadtime==1) {//商店第二页
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				util.playBGM("sounds/bgm5.wav",1);
				op=0;loadtime=0;              //切换到第一页
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				util.playBGM("sounds/bgm5.wav",1);
				op=22;loadtime=2;           //切换到第三页
			}
		}else if(loadtime==2) {//商店第三页
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				util.playBGM("sounds/bgm5.wav",1);
				op=12;loadtime=1;              //切换到第二页
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				util.playBGM("sounds/bgm5.wav",1);
				op=32;loadtime=3;             //切换到第四页
			}
		}else if(loadtime==3) {//商店第四页
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//上一页
				util.playBGM("sounds/bgm5.wav",1);
				op=22;loadtime=2;              //切换到第三页
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//下一页
				util.playBGM("sounds/bgm5.wav",1);
				op=0;loadtime=0;               //切换到第一页
			}
		}
	}
}
