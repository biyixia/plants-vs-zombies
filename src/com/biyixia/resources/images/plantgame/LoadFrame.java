package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * 加载界面
 */
public class LoadFrame  {
	
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
		 util.loadBGM("sounds/bgm.wav");
		 img=new Image[104];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("LoadFrame/load ("+(i+1)+").png");
			 }
		 }
	//绘制画面方法
	public static void draw(Graphics g) {
		if(loadtime==0) {
			for(int i=0;i<img.length-2;i++) {
				g.drawImage(img[i],x,y,null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			loadtime++;
		}else if(loadtime==1) {
			if(op==0) {
				g.drawImage(img[img.length-2],x,y,null);
			}else if(op==1) {
				g.drawImage(img[img.length-1],x,y,null);
			}
		}
	}
	//鼠标移动事件处理方法
	public static void MouseMove(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),326,576,484,605)) {
			op=1;
		}else {op=0;}
	}

	
	//鼠标点击事件处理方法
	public static void MouseClick(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),326,576,484,605)) {
			live=false;op=0;util.playBGM("sounds/bgm0.wav",1);
			MenuFrame.live=true;
		}
	}
	
}
