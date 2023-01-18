package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * ��԰����
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
	//���ƽ���
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);   // �̵����ı���
	}
	//����ƶ��¼�����
	public static void MouseMove(MouseEvent e) {
		if(loadtime==0) {                                      //�̵��һҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//���˵�
				op=11;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				op=9;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				op=10;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),420,252,480,324)) {//��Ƭ��
				op=1;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),505,252,554,324)) {//������
				op=2;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),571,252,628,324)) {//����
				op=3;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),653,252,702,324)) {//������
				op=4;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),381,343,432,428)) {//��ǹ�㶹
				op=5;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),454,343,503,428)) {//˫�����տ�
				op=6;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),529,343,577,428)) {//������繽
				op=7;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),602,343,651,428)) {//˯��
				op=8;
			}else {op=0;}
		}else if(loadtime==1) {                                      //�̵�ڶ�ҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//���˵�
				op=21;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				op=19;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				op=20;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),430,239,480,324)) {//�ֵش�
				op=13;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),504,239,554,324)) {//����ʯ
				op=14;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),579,239,627,324)) {//��������
				op=15;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),652,239,701,324)) {//���׼�ũ��	
				op=16;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),381,343,430,428)) {//��������
				op=17;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),456,357,505,428)) {//���������
				op=18;
			}else {op=12;}
		}else if(loadtime==2) {                                      //�̵����ҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//���˵�
				op=31;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				op=29;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				op=30;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),429,261,628,323)) {//����
				op=23;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),644,242,702,324)) {//��ˮ��
				op=24;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),381,351,431,430)) {//����
				op=25;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),454,348,504,428)) {//ɱ���
				op=26;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),529,346,577,427)) {//������
				op=27;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),600,348,655,428)) {//����
				op=28;
			}else {op=22;}
		}else if(loadtime==3) {                                      //�̵����ҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {//���˵�
				op=41;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				op=39;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				op=40;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),421,239,487,325)) {//Ģ��԰
				op=33;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),496,240,561,323)) {//ˮ���
				op=34;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),573,260,631,324)) {//���Ƴ�
				op=35;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),650,254,701,323)) {//��ţ
				op=36;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),371,342,437,428)) {//�ǻ���
				op=37;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),457,353,504,427)) {//�ǻ�������
				op=38;
			}else {op=32;}
		}
	}
	//������¼�����
	public static void MouseClick(MouseEvent e) {
		if(GameUtil.ifRect(e.getX(),e.getY(),374,551,499,619)) {   //�������˵���ť
			live=false;MenuFrame.live=true;op=0;loadtime=0;
			util.playBGM("sounds/bgm0.wav",1);
		}
		
		if(loadtime==0) {//�̵��һҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=32;loadtime=3;              //�л�������ҳ
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=12;loadtime=1;              //�л����ڶ�ҳ
			}
		}else if(loadtime==1) {//�̵�ڶ�ҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=0;loadtime=0;              //�л�����һҳ
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=22;loadtime=2;           //�л�������ҳ
			}
		}else if(loadtime==2) {//�̵����ҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=12;loadtime=1;              //�л����ڶ�ҳ
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=32;loadtime=3;             //�л�������ҳ
			}
		}else if(loadtime==3) {//�̵����ҳ
			if(GameUtil.ifRect(e.getX(),e.getY(),267,440,348,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=22;loadtime=2;              //�л�������ҳ
			}else if(GameUtil.ifRect(e.getX(),e.getY(),625,440,738,500)) {//��һҳ
				util.playBGM("sounds/bgm5.wav",1);
				op=0;loadtime=0;               //�л�����һҳ
			}
		}
	}
}
