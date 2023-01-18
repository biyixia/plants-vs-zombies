package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * ͼ������
 */
public class TuJianFrame  {
	
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
		 util.loadBGM("sounds/bgm6.wav");
		 img=new Image[20];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("TuJianFrame/tujian ("+(i+1)+").png");
			 }
		 }
	//���ƻ��淽��
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);
	}
	//����ƶ��¼�������
	public static void MouseMove(MouseEvent e){
		if(loadtime==0) {                                      //��һ�������ƶ����
			if(GameUtil.ifRect(e.getX(),e.getY(),701,605,782,622)) {//�ر�
				op=3;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),516,332,667,368)) {//��ʬͼ��
				op=2;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),132,330,285,366)) {//ֲ��ͼ��
				op=1;
			}else {op=0;}
		}else if(loadtime==1) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//�ر�
				op=19;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				op=18;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),35,124,83,192)) {//�㶹����
				op=13;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),86,124,134,192)) {//���տ�
				op=14;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),191,124,239,192)) {//���
				op=15;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),294,124,343,192)) {//�����㶹
				op=16;
			}else{op=12;}
		}else if(loadtime==2) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//�ر�
				op=11;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				op=10;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,122,103,190)) {//��ͨ��ʬ
				op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,122,272,190)) {//·�Ͻ�ʬ
				op=5;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),372,122,442,190)) {//��ͨ��ʬ
				op=6;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,202,103,270)) {//��ֽ��ʬ
				op=7;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,202,272,270)) {//�����ʬ
			    op=8;
			}else{op=9;}
		}
	}

	
	//������¼�������
	public static void MouseClick(MouseEvent e){
		if(loadtime==0) {
			if(GameUtil.ifRect(e.getX(),e.getY(),701,605,782,622)) {//�ر�
				TuJianFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
				 util.stopBGM();LoadFrame.util.playBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),516,332,667,368)) {//��ʬͼ��
				loadtime=2;util.playBGM("sounds/bgm0.wav",1);op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),132,330,285,366)) {//ֲ��ͼ��
				loadtime=1;util.playBGM("sounds/bgm0.wav",1);op=12;
			}
		}else if(loadtime==1) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//�ر�
				TuJianFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
				 LoadFrame.util.playBGM();util.stopBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				loadtime=0;util.playBGM("sounds/bgm0.wav",1);op=0;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),35,124,83,192)) {//�㶹����
				util.playBGM("sounds/bgm0.wav",1);op=13;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),86,124,134,192)) {//���տ�
				util.playBGM("sounds/bgm0.wav",1);op=14;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),191,124,239,192)) {//���
				util.playBGM("sounds/bgm0.wav",1);op=15;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),294,124,343,192)) {//�����㶹
				util.playBGM("sounds/bgm0.wav",1);op=16;
			}else{util.playBGM("sounds/bgm0.wav",1);op=17;}
		}else if(loadtime==2) {
			if(GameUtil.ifRect(e.getX(),e.getY(),687,602,771,620)) {//�ر�
				TuJianFrame.live=false;MenuFrame.live=true;op=0;loadtime=0;
				 LoadFrame.util.playBGM();util.stopBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),42,602,198,620)) {//back
				loadtime=0;util.playBGM("sounds/bgm0.wav",1);op=0;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,122,103,190)) {//��ͨ��ʬ
				util.playBGM("sounds/bgm0.wav",1);op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,122,272,190)) {//·�Ͻ�ʬ
				util.playBGM("sounds/bgm0.wav",1);op=5;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),372,122,442,190)) {//��ͨ��ʬ
				util.playBGM("sounds/bgm0.wav",1);op=6;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),31,202,103,270)) {//��ֽ��ʬ
				util.playBGM("sounds/bgm0.wav",1);op=7;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),203,202,272,270)) {//�����ʬ
				util.playBGM("sounds/bgm0.wav",1);op=8;
			}else{util.playBGM("sounds/bgm0.wav",1);op=9;}
		}
	}
	
}
