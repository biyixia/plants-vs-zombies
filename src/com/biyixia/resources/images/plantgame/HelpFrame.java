package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * ��������
 */
public class HelpFrame  {
	
	 static int x;
	 static int y;
	 static int loadtime;
	 static int op;
	 static GameUtil util;
	 static boolean live;
	 static Image img[];
	 //��ʼ������
	static {
		 x=0;
		 y=30;
		 loadtime=0;
		 op=0;
		 live=false; 
		 util=new  GameUtil();
		 img=new Image[2];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("HelpFrame/help"+i+".png");
			 }
		 }
	//���ƻ��淽��
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);
	}
	//����ƶ��¼�������
	public static void MouseMove(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),333,555,486,590)) {//���˵�
			op=1;
		}else {op=0;}
	}
	//������¼�������
	public static void MouseClick(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),333,555,486,590)) {//���˵�
			HelpFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
			util.playBGM("sounds/bgm0.wav",1);LoadFrame.util.playBGM();
		}
	}
	
}
