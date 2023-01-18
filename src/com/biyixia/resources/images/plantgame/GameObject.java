package plantgame;
/*
 * ��Ϸ����ĸ���
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Date;

public class GameObject {
	int num;//ֲ�����������һһ��Ӧ
	int x,y;
	int wide,high;
	int speed;
	Image img;
	boolean live=true;
	//�޲οչ���
	public GameObject() {
	}


	public GameObject(int x, int y) {//
		super();
		this.x = x;
		this.y = y;
	}



	public GameObject(int x, int y, int wide, int high) {
		super();
		this.x = x;
		this.y = y;
		this.wide = wide;
		this.high = high;
	}



	public GameObject(int x, int y, int wide, int high, int speed, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.wide = wide;
		this.high = high;
		this.speed = speed;
		this.img = img;
	}

	public void drawSelf(Graphics g) {
		g.drawImage(img,x,y,null);
	}
	
	
	
	
	/*
	 * �����������ھ���,���ں�����ײ���
	 */
	public Rectangle getRect() {
		return new Rectangle(x,y,wide,high);
	}
}
//�ݵظ�����,ȷ��ֲ��λ��45��ʵ������
class Glass extends GameObject{

	public Glass(int x, int y) {		
		super(x, y);
		this.wide=82;
		this.high=91;
		this.live=false;
	}
}
//��ݻ���
class Car extends GameObject{
	boolean move=false;
	int movetime=0;//���㲥����Ч
	 public Car(int x, int y, int wide, int high, int speed, Image img) {
		 super(x,y,wide,high,speed,img);
	 }
	 //��ݻ����Լ��ķ���
	 public void drawSelf(Graphics g) {
		 if(this.live) {
			 g.drawImage(this.img,this.x,this.y,this.wide,this.high,null);//����ֵΪtrue�򻭳������򲻻�
			 
			 if(this.move) {//���Ӵ����ƶ�״̬
				 this.x+=speed;
				 if(this.movetime==0) {//��һ�δ����ƶ�״̬������Ч
					 GameFrame.util.playBGM("sounds/car.wav",1);
					 movetime++;
				 }
			 }
			 for(int i=0;i<GameFrame.qizhi.size();i++) {
				 if(this.getRect().intersects(GameFrame.qizhi.get(i).getRect())) {
					 this.move =true;
					 if(GameFrame.qizhi.get(i).moving) {
						 GameFrame.qizhi.get(i).moving=false;
					 }else if(GameFrame.qizhi.get(i).attacking) {
						 GameFrame.qizhi.get(i).attacking=false;
					 }
					 GameFrame.qizhi.get(i).count=88;
					 GameFrame.qizhi.get(i).dieing=true;
				 }
			 }
			 for(int i=0;i<GameFrame.tietong.size();i++) {
				 if(this.getRect().intersects(GameFrame.tietong.get(i).getRect())) {
					 this.move =true;
					 if(GameFrame.tietong.get(i).moving) {
						 GameFrame.tietong.get(i).moving=false;
					 }else if(GameFrame.tietong.get(i).attacking) {
						 GameFrame.tietong.get(i).attacking=false;
					 }
					 GameFrame.tietong.get(i).count=191;
					 GameFrame.tietong.get(i).dieing=true;
				 }
			 }
			 for(int i=0;i<GameFrame.baozhi.size();i++) {
				 if(this.getRect().intersects(GameFrame.baozhi.get(i).getRect())) {
					 this.move =true;
					 if(GameFrame.baozhi.get(i).moving) {
						 GameFrame.baozhi.get(i).moving=false;
					 }else if(GameFrame.baozhi.get(i).attacking) {
						 GameFrame.baozhi.get(i).attacking=false;
					 }
					 GameFrame.baozhi.get(i).count=188;
					 GameFrame.baozhi.get(i).dieing=true;
				 }
			 }
			 for(int i=0;i<GameFrame.ganlan.size();i++) {
				 if(this.getRect().intersects(GameFrame.ganlan.get(i).getRect())) {
					 this.move =true;
					 if(GameFrame.ganlan.get(i).moving) {
						 GameFrame.ganlan.get(i).moving=false;
					 }else if(GameFrame.ganlan.get(i).attacking) {
						 GameFrame.ganlan.get(i).attacking=false;
					 }
					 GameFrame.ganlan.get(i).count=192;
					 GameFrame.ganlan.get(i).dieing=true;
				 }
			 }

			 
			 
			 if(this.x>850) {//������Ļ������
				 this.live=false;
			 }
		 }
		
	} 
}
//����
class Shove extends GameObject{
	boolean move=false;
	 public Shove(int x, int y, int wide, int high, int speed, Image img) {
		 super(x,y,wide,high,speed,img);
	 }
	 
	 public void drawSelf(Graphics g) {
		 g.drawImage(this.img,this.x,this.y,null);
	 }
}

//��Ƭ
class Card extends GameObject{
	boolean move;
	boolean cool;
	int price;
	int z,w;//z,w����ɫֲ��ʹ�ã����ֿ�Ƭ�ۻ��Ʋ���
	Image img1;
	Date start_time;//�����ж���ȴʱ��
	Date stop_time;
	//���캯��
	public Card(int x, int y, int wide, int high, int price,Image img,Image img1) {
		this.x=x;this.y=y;this.wide=wide;this.high=high;this.img=img;this.img1=img1;this.price=price;
		this.z=x;this.w=y;this.move=false;this.cool=false;
		this.start_time=new Date();this.stop_time=new Date();
	}
	//��������
	@Override
	public void drawSelf(Graphics g) {
		if(this.move) {
			g.drawImage(this.img1,this.z,this.w,null);
			g.drawImage(this.img,this.x,this.y,null);
			g.drawString(this.price+"",this.z+6,this.w+58);//ֲ��ļ۸�
		}else {
			if(GameFrame.sun>=this.price&&this.cool==false) {//�����㹻,δ��ȴ���ܵ��ֲ��
				g.drawImage(this.img,this.x,this.y,null);
			}else {
				g.drawImage(this.img1,this.z,this.w,null);
			}
			g.drawString(this.price+"",this.z+6,this.w+58);//ֲ��ļ۸�
		}
		
	}
}
//����
class Sun extends GameObject {
	Image imgs[];
	int max;
	int count;//ָʾ���ŵ��ڼ���ͼƬ
	int speedx,speedy;//���ⱻ�ռ�����ƶ��ٶ�
	boolean move=false;
	Date start;
	Date end;
	
	
	public  Sun(int x,int y,Image imgs[]) {//���տ�����������
		this.x=x;this.y=y;//x,yֵ�����տ�ȷ��
		this.wide=50;this.high=50;//�����ж��Ƿ�������
		this.imgs=imgs;this.max=y;
		this.count=0;this.start=new Date();
		this.speedx=(int)((this.x-70)/30);this.speedy=(int)((this.y-45)/30);
	}
	
	public  Sun(int x,int wide,int high,int max,Image imgs[]) {//�Զ����ֵ�����
		this.x=x;this.y=-30;//��ʼ���һ��xֵ��yֵΪ��Ļ�Ϸ���ʼ�������⣬��������ֱ�����maxֵ��
		this.wide=wide;this.high=high;//�����ж��Ƿ�������
		this.imgs=imgs;this.max=max;
		this.count=0;this.speedx=(int)((this.x-70)/30);this.speedy=(int)((this.max-45)/30);
	}
	@Override
	public void drawSelf(Graphics g) {
		
		if(this.live) {
			if(this.count<this.imgs.length-1) {//�����ֲ�������̫��
				g.drawImage(imgs[this.count++],this.x,this.y,50,50,null);
			}else {
				g.drawImage(imgs[this.count],this.x,this.y,50,50,null);
				this.count=0;
			}
			
			if(this.move) {//��̫�������ƶ�
				if(this.x>=70) {
					if(this.y>=45) {//�յ����½�
						this.x-=this.speedx;this.y-=this.speedy;//���ⱻ��ȡ���������Ͻ��ƶ�
					}else {//�յ����Ͻ�
						this.x-=this.speedx;this.y+=5;
					}
				}else if(this.x<70&&this.y>=45) {//�յ����½�
					this.x+=2;this.y-=this.speedy;
				}else {
					
				}
				
				if(GameUtil.ifRect(this.x, this.y,50,20,80,55)){//̫������Ŀ�ĵ�70,45
					if(this.live) {GameFrame.sun+=25;this.live=false;}
				}
			}else {//����δ����ȡ
				//δ���䵽��ʹ�
				if(this.y<this.max) {
					this.y+=(int)( (this.max-this.y)/50);//�������yֵ����
				}
				
				
			}
		}
	}
	
}
//����
class Flower extends GameObject{
	Image imgs[];
	int count;
	int hp;
	Date start,end;//����̫��
	
	public Flower(int x, int y, int wide, int high,Image imgs [],int num) {
		super(x, y, wide, high);
		this.start=new Date();
		this.imgs=imgs;
		this.count=0;
		this.num=num;
		this.hp=100;
		
	}

	@Override
	public void drawSelf(Graphics g) {
		if(this.hp<=0) {
			this.live=false;
		}
		if(this.live) {
			if(count<imgs.length-1) {
				g.drawImage(imgs[this.count++],this.x,this.y,null);
			}else {
				g.drawImage(imgs[this.count++],this.x,this.y,null);
				this.count=0;
			}
		}else {			
			GameFrame.glass.get(GameFrame.flower.get(GameFrame.flower.indexOf(this)).num).live =false;
			GameFrame.flower.remove(GameFrame.flower.indexOf(this));

		}
	}	
}
//�㶹������
class Wandou extends GameObject{
	Image imgs[];
	boolean attack;
	int count;
	int hp;
	Date start,end;
	
	public Wandou(int x, int y, int wide, int high,Image imgs [],int num) {//������㶹����
		super(x, y, wide, high);
		start=new Date();
		this.imgs=imgs;
		this.count=0;
		this.num=num;
		this.hp=100;
	}

	@Override
	public void drawSelf(Graphics g) {
		if(this.hp<=0) {
			this.live=false;
		}
		if(this.live) {
			this.end=new Date();//���������㶹���ַ����ӵ���Ƶ��-->1.5��
			if((this.end.getTime()-this.start.getTime())*0.001>=1.5&&this.count==12) {
				for(int i=0;i<GameFrame.qizhi.size();i++) {
					if(GameFrame.qizhi.get(i).y+65==this.y) {
						this.attack=true;
					}
				}
				for(int i=0;i<GameFrame.tietong.size();i++) {
					if(GameFrame.tietong.get(i).y+65==this.y) {
						this.attack=true;
					}
				}
				for(int i=0;i<GameFrame.baozhi.size();i++) {
					if(GameFrame.baozhi.get(i).y+65==this.y) {
						this.attack=true;
					}
				}
				for(int i=0;i<GameFrame.ganlan.size();i++) {
					if(GameFrame.ganlan.get(i).y+65==this.y) {
						this.attack=true;
					}
				}
				if(this.attack) {
					GameFrame.util.playBGM("sounds/biu.wav", 1);
					GameFrame.bullet.add((new Bullet(this.x,this.y,GameFrame.bullets[0], false,10)));
					this.attack=false;
				}
				this.start=new Date();
			}//�����㶹����
			if(count<imgs.length-1) {
				g.drawImage(imgs[this.count++],this.x,this.y,null);
			}else {
				g.drawImage(imgs[this.count++],this.x,this.y,null);
				this.count=0;
			}
		}else {
			GameFrame.glass.get(GameFrame.wandou.get(GameFrame.wandou.indexOf(this)).num).live =false;
			GameFrame.wandou.remove(GameFrame.wandou.indexOf(this));
		}
	}
}
//��������
class Hanbing extends GameObject{
	Image imgs[];
	int count;
	int hp;
	boolean attack;
	Date start,end;
	
	public Hanbing(int x, int y, int wide, int high,Image imgs [],int num) {//����溮������
		super(x, y, wide, high);
		start=new Date();
		this.imgs=imgs;
		this.count=0;
		this.num=num;
		this.hp=100;
	}

	@Override
	public void drawSelf(Graphics g) {
		if(this.hp<=0) {
			this.live=false;
		}
		if(this.live) {
					this.end=new Date();//���������㶹���ַ����ӵ���Ƶ��-->1.5��
					if((this.end.getTime()-this.start.getTime())*0.001>=1.5&&this.count==7) {
						for(int i=0;i<GameFrame.qizhi.size();i++) {
							if(GameFrame.qizhi.get(i).y+65==this.y) {
								this.attack=true;
							}
						}
						for(int i=0;i<GameFrame.tietong.size();i++) {
							if(GameFrame.tietong.get(i).y+65==this.y) {
								this.attack=true;
							}
						}
						for(int i=0;i<GameFrame.baozhi.size();i++) {
							if(GameFrame.baozhi.get(i).y+65==this.y) {
								this.attack=true;
							}
						}
						for(int i=0;i<GameFrame.ganlan.size();i++) {
							if(GameFrame.ganlan.get(i).y+65==this.y) {
								this.attack=true;
							}
						}
						if(this.attack) {
							GameFrame.util.playBGM("sounds/biu.wav", 1);
							GameFrame.bullet.add((new Bullet(this.x,this.y,GameFrame.bullets[1], true,5)));
							this.attack=false;
						}
						this.start=new Date();}
					//�����㶹����
					if(count<imgs.length-1) {
						g.drawImage(imgs[this.count++],this.x,this.y,null);
						}else {
							g.drawImage(imgs[this.count++],this.x,this.y,null);
							this.count=0;
			}
		}else {
			GameFrame.glass.get(GameFrame.hanbing.get(GameFrame.hanbing.indexOf(this)).num).live =false;
			GameFrame.hanbing.remove(GameFrame.hanbing.indexOf(this));
		}
	}
}
//���
class Jianguo extends GameObject{
	Image imgs[];
	int count;
	int hp;
	
	public Jianguo(int x, int y, int wide, int high,Image imgs [],int num) {//�������
		super(x, y, wide, high); 
		this.imgs=imgs;
		this.count=0;
		this.num=num;
		this.hp=200;
	}

	@Override
	public void drawSelf(Graphics g) {
		if(this.hp<=0) {this.live=false;}
		if(this.live) {
			if(this.hp>100) {//����ֵ��
				if(this.count<39) {
					g.drawImage(imgs[this.count++],this.x,this.y,null);
				}else {
					g.drawImage(imgs[this.count++],this.x,this.y,null);
					this.count=0;
				}
			}else if(this.hp>50&&this.hp<=100) {//����ֵ��
				if(this.count<=39) {this.count=40;}
				if(this.count<76) {
					g.drawImage(imgs[this.count++],this.x,this.y,null);
				}else {
					g.drawImage(imgs[this.count++],this.x,this.y,null);
					this.count=40;
				}
			}else {//����ֵ��
				if(this.count<=76) {this.count=77;}
				if(count<114) {
					g.drawImage(imgs[this.count++],this.x,this.y,null);
				}else {
					g.drawImage(imgs[this.count++],this.x,this.y,null);
					this.count=0;
				}
			}
			if(count<imgs.length-1) {
				g.drawImage(imgs[this.count++],this.x,this.y,null);
			}else {
				g.drawImage(imgs[this.count++],this.x,this.y,null);
				this.count=0;
			}
		}else {
			GameFrame.glass.get(GameFrame.jianguo.get(GameFrame.jianguo.indexOf(this)).num).live =false;
			GameFrame.jianguo.remove(GameFrame.jianguo.indexOf(this));
		}
	}
}
//�ӵ�
class Bullet extends GameObject{
	boolean slow;
	int attack;
	public Bullet(int x, int y,Image img,boolean slow,int attack) {
		this.x=68+x;this.y=y;
		this.wide=26;this.high=26;
		this.img=img;
		this.attack=attack;
		this.slow=slow;
	}
	@Override
	public void drawSelf(Graphics g) {
		if(this.live) {
			g.drawImage(img,this.x,this.y,null);
			this.x+=5;//�ӵ����ٶ�
		}
		if(this.x>850) {this.live=false;}
	}
}
//���Ľ�ʬ
class QiZhi extends GameObject {
	boolean attacking,moving,dieing;
	int attack,hp,count,pengtime;//pengtime���������ֲ���ʱ
	Date attack_start,attack_end;//���������
	double x,speed,slow_speed;
	Image imgs[];
	
	 public QiZhi(Image imgs[],int y,double speed,int attack,int hp) {//���캯��,Ĭ�ϳ�ʼ�ƶ�Ϊtrue�����������٣�����Ϊfalse
		this.x=810;this.y=y;this.attacking=false;this.moving=true;this.dieing=false;
		this.imgs=imgs;this.speed=speed;this.attack=attack;this.hp=hp;
		this.wide=45;this.high=60;this.slow_speed=speed/2.0;
		this.pengtime=1;
	}
	
	
	@Override
	public void drawSelf(Graphics g) {
		if(this.x<-150) {
			GameFrame.loadtime=3;
			this.x+=10;
		}
		if(this.hp<=0) {
			this.attacking=false;
			this.moving=false;
			this.dieing=true;
		}
		if(this.live) {
			if(this.moving) {
				g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
				this.x-=this.speed;
				if(this.count==48) {this.count=0;}
			}else if(this.attacking) {
				g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
				if(this.count==88) {this.count=48;}
			}else if(this.dieing) {
				g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
			    if(this.count==134) {
			    	this.live=false;
			    	GameFrame.num++;
			    }
			}
			if(!GameFrame.bullet.isEmpty()&&this.hp>0) {//��������0�ӵ����վͱ�������,�����ཻ�������ֵ,�ӵ��Ƴ�
				for(int i=0;i<GameFrame.bullet.size();i++) {
					if(GameFrame.bullet.get(i).getRect().intersects(this.getRect())) {//�ӵ��ľ��κͽ�ʬ�ľ����ཻ,���ӵ����н�ʬ
						if(GameFrame.bullet.get(i).slow) {this.speed=this.slow_speed;}//��ʬ����
						this.hp-=GameFrame.bullet.get(i).attack;//Ѫ������
						GameFrame.util.playBGM("sounds/attack-qizhi.wav",1);//������Ч
						GameFrame.bullet.remove(i);//�Ƴ��ӵ�
						if(this.hp<=0) {
							count=88;
							this.attacking=false;this.moving=false;this.dieing=true;
						}
					}
				}
			}
			//���տ����ܹ���
			for(int i=0;i<GameFrame.flower.size();i++) {
				if(this.getRect().intersects(GameFrame.flower.get(i).getRect())) {//���տ��뽩ʬ��ײ
					if(GameFrame.flower.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									this.count=48;this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.flower.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.flower.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//������ܹ���
			for(int i=0;i<GameFrame.jianguo.size();i++) {
				if(this.getRect().intersects(GameFrame.jianguo.get(i).getRect())) {//����뽩ʬ��ײ
					if(GameFrame.jianguo.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									this.count=48;this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.jianguo.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.jianguo.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�㶹�������ܹ���
			for(int i=0;i<GameFrame.wandou.size();i++) {
				if(this.getRect().intersects(GameFrame.wandou.get(i).getRect())) {//�㶹�뽩ʬ��ײ
					if(GameFrame.wandou.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									this.count=48;this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.wandou.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.wandou.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�����������ܹ���
			for(int i=0;i<GameFrame.hanbing.size();i++) {
				if(this.getRect().intersects(GameFrame.hanbing.get(i).getRect())) {//���������뽩ʬ��ײ
					if(GameFrame.hanbing.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									this.count=48;this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.hanbing.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.hanbing.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
		}
		
		
	}


	@Override
	public Rectangle getRect() {
		return new Rectangle((int)(this.x+105),this.y+50,this.wide,this.high);//110��ͼƬ����뽩ʬ���������
	}
}
//��ͨ��ʬ
class TieTong extends GameObject{

	boolean attacking,moving,dieing;
	int attack,hp,count,pengtime;//pengtime���������ֲ���ʱ
	Date attack_start,attack_end;//���������
	double x,speed,slow_speed;
	Image imgs[];
	
	 public TieTong(Image imgs[],int y,double speed,int attack,int hp) {//���캯��,Ĭ�ϳ�ʼ�ƶ�Ϊtrue�����������٣�����Ϊfalse
		this.x=810;this.y=y;this.attacking=false;this.moving=true;this.dieing=false;
		this.imgs=imgs;this.speed=speed;this.attack=attack;this.hp=hp;
		this.wide=45;this.high=60;this.slow_speed=speed/2.0;
		this.pengtime=1;
	}
	
	
	@Override
	public void drawSelf(Graphics g) {
		if(this.x<-150) {
			GameFrame.loadtime=3;
			this.x+=10;
		}
		if(this.live) {
			if(this.moving) {
				if(this.hp>100) {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					this.x-=this.speed;
					if(this.count==48) {this.count=0;}
				}else {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					this.x-=2*this.speed;
					if(this.count==143) {this.count=96;}
				}
			}else if(this.attacking) {
				if(this.hp>100) {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					if(this.count==96) {this.count=48;}
				}else {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					if(this.count==191) {this.count=143;}
				}
			}else if(this.dieing) {
				this.moving=false;this.attacking=false;
				g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
			    if(this.count==239) {
			    	this.live=false;
			    	GameFrame.num++;
			    }
			}
			if(this.hp>0) {//��������0�ӵ����վͱ�������,�����ཻ�������ֵ,�ӵ��Ƴ�
				for(int i=0;i<GameFrame.bullet.size();i++) {
					if(GameFrame.bullet.get(i).getRect().intersects(this.getRect())) {//�ӵ��ľ��κͽ�ʬ�ľ����ཻ,���ӵ����н�ʬ
						if(GameFrame.bullet.get(i).slow) {this.speed=this.slow_speed;}//��ʬ����
						if(this.hp>100) {
							GameFrame.util.playBGM("sounds/attack-tietong.wav",1);//��Ѫ��������Ч
						}else {
							GameFrame.util.playBGM("sounds/attack-qizhi.wav",1);//��Ѫ��������Ч
						}
						if(this.hp-GameFrame.bullet.get(i).attack==100||this.hp-GameFrame.bullet.get(i).attack==95) {//��ʬѪ���Ƿ񽡿�
							if(this.moving) {
								this.count=96;
							}else if(this.attacking) {
								this.count=143;
							}
						}else if(this.hp-GameFrame.bullet.get(i).attack<=0) {//�жϽ�ʬѪ���Ƿ�Ҫ����ɱ
							this.count=191;
							this.attacking=false;this.moving=false;this.dieing=true;
						}
						this.hp-=GameFrame.bullet.get(i).attack;//Ѫ������
						GameFrame.bullet.remove(i);//�Ƴ��ӵ�
					}
				}
			}else {
				this.dieing=true;
			}
			//���տ����ܹ���
			for(int i=0;i<GameFrame.flower.size();i++) {
				if(this.getRect().intersects(GameFrame.flower.get(i).getRect())) {//���տ��뽩ʬ��ײ
					if(GameFrame.flower.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.flower.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.flower.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//������ܹ���
			for(int i=0;i<GameFrame.jianguo.size();i++) {
				if(this.getRect().intersects(GameFrame.jianguo.get(i).getRect())) {//����뽩ʬ��ײ
					if(GameFrame.jianguo.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.jianguo.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.jianguo.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�㶹�������ܹ���
			for(int i=0;i<GameFrame.wandou.size();i++) {
				if(this.getRect().intersects(GameFrame.wandou.get(i).getRect())) {//�㶹�뽩ʬ��ײ
					if(GameFrame.wandou.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.wandou.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.wandou.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�����������ܹ���
			for(int i=0;i<GameFrame.hanbing.size();i++) {
				if(this.getRect().intersects(GameFrame.hanbing.get(i).getRect())) {//���������뽩ʬ��ײ
					if(GameFrame.hanbing.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.hanbing.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.hanbing.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
		}
		
		
	}


	@Override
	public Rectangle getRect() {
		return new Rectangle((int)(this.x+75),this.y+50,this.wide,this.high);//110��ͼƬ����뽩ʬ���������
	}
}
//��ֽ��ʬ
class BaoZhi extends GameObject {
	boolean attacking,moving,dieing;
	int attack,hp,count,pengtime;//pengtime���������ֲ���ʱ
	Date attack_start,attack_end;//���������
	double x,speed,slow_speed;
	Image imgs[];
	
	 public BaoZhi(Image imgs[],int y,double speed,int attack,int hp) {//���캯��,Ĭ�ϳ�ʼ�ƶ�Ϊtrue�����������٣�����Ϊfalse
		this.x=810;this.y=y;this.attacking=false;this.moving=true;this.dieing=false;
		this.imgs=imgs;this.speed=speed;this.attack=attack;this.hp=hp;
		this.wide=45;this.high=70;this.slow_speed=speed/2.0;
		this.pengtime=1;
	}
	
	
	@Override
	public void drawSelf(Graphics g) {
		if(this.x<-150) {
			GameFrame.loadtime=3;
			this.x+=10;
		}
		if(this.hp<=0) {
			this.attacking=false;
			this.moving=false;
			this.dieing=true;
		}
		if(this.live) {
			if(this.moving) {
				if(this.hp>100) {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					this.x-=this.speed;
					if(this.count==47) {this.count=0;}
				}else {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					this.x-=2*this.speed;
					if(this.count==141) {this.count=94;}
				}
			}else if(this.attacking) {
				if(this.hp>100) {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					if(this.count==94) {this.count=47;}
				}else {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					if(this.count==187) {this.count=141;}
				}
			}else if(this.dieing) {//187-----234
				this.moving=false;this.attacking=false;
				g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
			    if(this.count==235) {
			    	this.live=false;
			    	GameFrame.num++;
			    }
			}
			if(this.hp>0) {//��������0�ӵ����վͱ�������,�����ཻ�������ֵ,�ӵ��Ƴ�
				for(int i=0;i<GameFrame.bullet.size();i++) {
					if(GameFrame.bullet.get(i).getRect().intersects(this.getRect())) {//�ӵ��ľ��κͽ�ʬ�ľ����ཻ,���ӵ����н�ʬ
						if(GameFrame.bullet.get(i).slow) {this.speed=this.slow_speed;}//��ʬ����
						if(this.hp>100) {
							GameFrame.util.playBGM("sounds/attack-qizhi.wav",1);//��Ѫ��������Ч
						}else {
							GameFrame.util.playBGM("sounds/attack-qizhi.wav",1);//��Ѫ��������Ч
						}
						if(this.hp-GameFrame.bullet.get(i).attack==100||this.hp-GameFrame.bullet.get(i).attack==95) {//��ʬѪ���Ƿ񽡿�
							if(this.moving) {
								this.count=96;
							}else if(this.attacking) {
								this.count=143;
							}
						}else if(this.hp-GameFrame.bullet.get(i).attack<=0) {//�жϽ�ʬѪ���Ƿ�Ҫ����ɱ
							this.count=191;
							this.attacking=false;this.moving=false;this.dieing=true;
						}
						this.hp-=GameFrame.bullet.get(i).attack;//Ѫ������
						GameFrame.bullet.remove(i);//�Ƴ��ӵ�
					}
				}
			}
			//���տ����ܹ���
			for(int i=0;i<GameFrame.flower.size();i++) {
				if(this.getRect().intersects(GameFrame.flower.get(i).getRect())) {//���տ��뽩ʬ��ײ
					if(GameFrame.flower.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.flower.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.flower.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//������ܹ���
			for(int i=0;i<GameFrame.jianguo.size();i++) {
				if(this.getRect().intersects(GameFrame.jianguo.get(i).getRect())) {//����뽩ʬ��ײ
					if(GameFrame.jianguo.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.jianguo.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.jianguo.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�㶹�������ܹ���
			for(int i=0;i<GameFrame.wandou.size();i++) {
				if(this.getRect().intersects(GameFrame.wandou.get(i).getRect())) {//�㶹�뽩ʬ��ײ
					if(GameFrame.wandou.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.wandou.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.wandou.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�����������ܹ���
			for(int i=0;i<GameFrame.hanbing.size();i++) {
				if(this.getRect().intersects(GameFrame.hanbing.get(i).getRect())) {//���������뽩ʬ��ײ
					if(GameFrame.hanbing.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=143;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.hanbing.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.hanbing.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
		}
		
		
	}


	@Override
	public Rectangle getRect() {
		return new Rectangle((int)(this.x+45),this.y+50,this.wide,this.high);//110��ͼƬ����뽩ʬ���������
	}

}
//�����ʬ
class GanLan extends GameObject{

	boolean attacking,moving,dieing;
	int attack,hp,count,pengtime;//pengtime���������ֲ���ʱ
	Date attack_start,attack_end;//���������
	double x,speed,slow_speed;
	Image imgs[];
	
	 public GanLan(Image imgs[],int y,double speed,int attack,int hp) {//���캯��,Ĭ�ϳ�ʼ�ƶ�Ϊtrue�����������٣�����Ϊfalse
		this.x=810;this.y=y;this.attacking=false;this.moving=true;this.dieing=false;
		this.imgs=imgs;this.speed=speed;this.attack=attack;this.hp=hp;
		this.wide=35;this.high=60;this.slow_speed=speed/2.0;
		this.pengtime=1;
	}
	
	
	@Override
	public void drawSelf(Graphics g) {
		if(this.x<-150) {
			GameFrame.loadtime=3;
			this.x+=10;
		}
		if(this.hp<=0) {
			this.attacking=false;
			this.moving=false;
			this.dieing=true;
		}
		if(this.live) {
			if(this.moving) {
				if(this.hp>100) {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					this.x-=this.speed;
					if(this.count==48) {this.count=0;}
				}else {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					this.x-=2*this.speed;
					if(this.count==144) {this.count=96;}
				}
			}else if(this.attacking) {
				if(this.hp>100) {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					if(this.count==96) {this.count=48;}
				}else {//��Ѫ����ʬ����
					g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
					if(this.count==192) {this.count=144;}
				}
			}else if(this.dieing) {
				this.moving=false;this.attacking=false;
				g.drawImage(imgs[this.count++],(int)this.x,this.y, null);
			    if(this.count==239) {
			    	this.live=false;
			    	GameFrame.num++;
			    }
			}
			   if(this.hp>0) {//��������0�ӵ����վͱ�������,�����ཻ�������ֵ,�ӵ��Ƴ�
				for(int i=0;i<GameFrame.bullet.size();i++) {
					if(GameFrame.bullet.get(i).getRect().intersects(this.getRect())) {//�ӵ��ľ��κͽ�ʬ�ľ����ཻ,���ӵ����н�ʬ
						if(GameFrame.bullet.get(i).slow) {this.speed=this.slow_speed;}//��ʬ����
						if(this.hp>100) {
							GameFrame.util.playBGM("sounds/attack-tietong.wav",1);//��Ѫ��������Ч
						}else {
							GameFrame.util.playBGM("sounds/attack-qizhi.wav",1);//��Ѫ��������Ч
						}
						if(this.hp-GameFrame.bullet.get(i).attack==100||this.hp-GameFrame.bullet.get(i).attack==95) {//��ʬѪ���Ƿ񽡿�
							if(this.moving) {
								this.count=96;
							}else if(this.attacking) {
								this.count=144;
							}
						}else if(this.hp-GameFrame.bullet.get(i).attack<=0) {//�жϽ�ʬѪ���Ƿ�Ҫ����ɱ
							this.count=192;
							this.attacking=false;this.moving=false;this.dieing=true;
						}
						this.hp-=GameFrame.bullet.get(i).attack;//Ѫ������
						GameFrame.bullet.remove(i);//�Ƴ��ӵ�
					}
				}
			  }
			//���տ����ܹ���
			for(int i=0;i<GameFrame.flower.size();i++) {
				if(this.getRect().intersects(GameFrame.flower.get(i).getRect())) {//���տ��뽩ʬ��ײ
					if(GameFrame.flower.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=144;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.flower.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.flower.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.flower.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//������ܹ���
			for(int i=0;i<GameFrame.jianguo.size();i++) {
				if(this.getRect().intersects(GameFrame.jianguo.get(i).getRect())) {//����뽩ʬ��ײ
					if(GameFrame.jianguo.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=144;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.jianguo.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.jianguo.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.jianguo.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�㶹�������ܹ���
			for(int i=0;i<GameFrame.wandou.size();i++) {
				if(this.getRect().intersects(GameFrame.wandou.get(i).getRect())) {//�㶹�뽩ʬ��ײ
					if(GameFrame.wandou.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=144;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.wandou.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.wandou.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.wandou.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
			//�����������ܹ���
			for(int i=0;i<GameFrame.hanbing.size();i++) {
				if(this.getRect().intersects(GameFrame.hanbing.get(i).getRect())) {//���������뽩ʬ��ײ
					if(GameFrame.hanbing.get(i).hp>0){//����ֵ����0��������
								if(this.pengtime==1) {//�սӴ�����ʼʱ���ʼ��
									if(this.hp>100) {
										this.count=48;
									}else {
										this.count=144;
									}
									this.attack_start=new Date();this.pengtime++;
									this.moving=false;this.attacking=true;
								}
								this.attack_end=new Date();
								if((this.attack_end.getTime()-this.attack_start.getTime())*0.001>=1){//��һ���Ѫ��
									if(GameFrame.hanbing.get(i).hp-this.attack<=0) {
										for(int j=0;j<GameFrame.qizhi.size();j++) {//�����ڳԵ����Ľ�ʬ�ָ�״̬
											if(GameFrame.qizhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												GameFrame.qizhi.get(j).pengtime=1;GameFrame.qizhi.get(j).count=0;
												GameFrame.qizhi.get(j).attacking=false;GameFrame.qizhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.tietong.size();j++) {//�����ڳԵ���Ͱ��ʬ�ָ�״̬
											if(GameFrame.tietong.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.tietong.get(j).hp>100) {
													GameFrame.tietong.get(j).count=0;
												}else {
													GameFrame.tietong.get(j).count=96;
												}
												GameFrame.tietong.get(j).pengtime=1;
												GameFrame.tietong.get(j).attacking=false;GameFrame.tietong.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.baozhi.size();j++) {//�����ڳԵı�ֽ��ʬ�ָ�״̬
											if(GameFrame.baozhi.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.baozhi.get(j).hp>100) {
													GameFrame.baozhi.get(j).count=0;
												}else {
													GameFrame.baozhi.get(j).count=94;
												}
												GameFrame.baozhi.get(j).pengtime=1;
												GameFrame.baozhi.get(j).attacking=false;GameFrame.baozhi.get(j).moving=true;
											}
										}
										for(int j=0;j<GameFrame.ganlan.size();j++) {//�����ڳԵ������ʬ�ָ�״̬
											if(GameFrame.ganlan.get(j).getRect().intersects(GameFrame.hanbing.get(i).getRect())) {
												if(GameFrame.ganlan.get(j).hp>100) {
													GameFrame.ganlan.get(j).count=0;
												}else {
													GameFrame.ganlan.get(j).count=96;
												}
												GameFrame.ganlan.get(j).pengtime=1;
												GameFrame.ganlan.get(j).attacking=false;GameFrame.ganlan.get(j).moving=true;
											}
										}
									}
									GameFrame.util.playBGM("sounds/eat"+(int)(Math.random()*3)+".wav",1);//����������Чѡһ������
									GameFrame.hanbing.get(i).hp-=this.attack;
									this.attack_start=new Date();//��ʼʱ������
								}
							}
				}
			}
		}
		
		
	}


	@Override
	public Rectangle getRect() {
		return new Rectangle((int)(this.x+85),this.y+60,this.wide,this.high);//110��ͼƬ����뽩ʬ���������
	}

}