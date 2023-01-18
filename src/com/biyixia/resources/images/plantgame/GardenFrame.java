package plantgame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * ��԰����
 */
public class GardenFrame  {
	
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
		 util.loadBGM("sounds/bgm3.wav");
		 img=new Image[17];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("GardenFrame/garden ("+(i+1)+").png");
			 }
		 }
	//���ƽ���
	public static void draw(Graphics g) {
		g.drawImage(img[op],x,y,null);   // ��԰����ı���
		
		for(int i=9;i<=16;i++) {              // ��԰����Ĺ���
			if(i==11) {
				g.drawImage(img[i],70*(i-9),31,null);
			}else if(i==12) {
				g.drawImage(img[i],70*(i-9),32,null);
			}
			else if(i==13) {
				g.drawImage(img[i],7+70*(i-9),35,null);
			}
			else g.drawImage(img[i],70*(i-9),26,null);
		}
	}
	//����ƶ��¼� ����
	public static void MouseMove(MouseEvent e) {
		if(GameUtil.ifRect(e.getX(),e.getY(),639,42,793,80)) {//���˵���
			 if(loadtime==0) {
				 op=1;
			 }else if(loadtime==1) {
				 op=4;;
			 }else {op=7;}
	 }else if(GameUtil.ifRect(e.getX(),e.getY(),727,88,683,139,792,128,802,90)) {//ͨ���̵�
		 if(loadtime==0) {
			 op=2;
		 }else if(loadtime==1) {
			 op=5;
		 }else {op=8;}
    }else {
   	 if(loadtime==0) {
			 op=0;
		 }else if(loadtime==1) {
			 op=3;
		 }else {op=6;}
    }
	}
	//������¼�����
public static void MouseClick(MouseEvent e) {
	if(GameUtil.ifRect(e.getX(),e.getY(),639,42,793,80)) {//�������˵���
		 op=0;loadtime=0;live=false;MenuFrame.live=true;util.playBGM("sounds/bgm2.wav",1);
		 LoadFrame.util.playBGM();util.stopBGM();
	}else if(GameUtil.ifRect(e.getX(),e.getY(),727,88,683,139,792,128,802,90)) {//ͨ���̵�
		 op=0;loadtime=0;live=false;ShopFrame.live=true;util.playBGM("sounds/bgm0.wav",1);
		 LoadFrame.util.playBGM();util.stopBGM();
		}else if(GameUtil.ifRect(e.getX(),e.getY(),573,45,627,87)) {//ͨ����һ����԰����
		if(loadtime==0) {
			op=3;util.playBGM("sounds/bgm0.wav",1);loadtime++;
		}else if(loadtime==1){
			op=6;util.playBGM("sounds/bgm0.wav",1);loadtime++;
		}else if(loadtime==2){
			op=0;util.playBGM("sounds/bgm0.wav",1);loadtime=0;
	}
  }
}
	
}
