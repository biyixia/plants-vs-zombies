package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * MiniFrame����
 */
public class MiniFrame  {
	
	 static int x;
	 static int y;
	 static int loadtime;
	 static int op;
	 static boolean live;
	 static GameUtil util;
	 static Image img[];
	 //��ʼ������
	static {
		 x=0;
		 y=30;
		 loadtime=0;
		 op=0;
		 live=false; 
		 util=new GameUtil();
		 img=new Image[2];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("MiniFrame/mini"+i+".png");
			 }
		 }
	//���ƻ��淽��
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);
	}
	//����ƶ��¼�������
	public static void MouseMove(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),29,603,133,620)) {//�������˵�
		op=1;
		}else {op=0;}
	}

	
	//������¼�������
	public static void MouseClick(MouseEvent e){
		if(GameUtil.ifRect(e.getX(),e.getY(),29,603,133,620)) {//�������˵�
			live=false;MenuFrame.live=true;util.playBGM("sounds/bgm0.wav",1);op=0;
			LoadFrame.util.playBGM();TuJianFrame.util.stopBGM();
		}
	}
	
}
