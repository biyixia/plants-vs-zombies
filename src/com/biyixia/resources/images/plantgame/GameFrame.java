package plantgame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
/*
 * GameFrame界面
 */
import java.util.ArrayList;
import java.util.Date;
public class GameFrame  {
	static ArrayList<Glass> glass=new ArrayList<>();//存放草地格子对象
	 static ArrayList<Car> car=new ArrayList<>();//存放割草车
	 static ArrayList<Card> card=new ArrayList<>();//存放四张卡片
	 static ArrayList<Flower> flower=new ArrayList<>();//存放花
	 static ArrayList<Wandou> wandou=new ArrayList<>();//存放豌豆射手
	 static ArrayList<Hanbing> hanbing=new ArrayList<>();//存放寒冰射手
	 static ArrayList<Jianguo> jianguo=new ArrayList<>();//存放坚果
	 static ArrayList<Sun> sunings=new ArrayList<>();//存放阳光
	 static ArrayList<Bullet> bullet=new ArrayList<>();//存放子弹
	 static ArrayList<QiZhi> qizhi=new ArrayList<>();//存放旗帜僵尸
	 static ArrayList<TieTong> tietong=new ArrayList<>();//存放铁桶僵尸
	 static ArrayList<BaoZhi> baozhi=new ArrayList<>();//存放报纸僵尸
	 static ArrayList<GanLan> ganlan=new ArrayList<>();//存放橄榄球僵尸
	 static Shove shove;
	 static boolean backgame,backmenu,restart;
	 static int x;
	 static int m=0;//控制僵尸出现
	 static int y;
	 static int loadtime;
	 static int op;
	 static int grade;//等级，调整游戏难度
	 static boolean live;
	 static GameUtil util;
	 static Image img[];
	 static Image suns[];//图片数组后加s不与类arraylist弄混
	 static Image flowers[];
	 static Image wandous[];
	 static Image hanbings[];
	 static Image jianguos[];
	 static Image bullets[];//子弹
	 static Image qizhis[];//旗帜僵尸
	 static Image tietongs[];//铁桶僵尸
	 static Image ganlans[];//橄榄球僵尸
	 static Image baozhis[];//报纸僵尸
	 static int sun;
	 static int num;//计数杀掉的僵尸
	 static Date startTime;//阳光下落间隔 
	 static Date stopTime;
	 static Date start;//僵尸出现间隔
	 static Date stop;
	 static Sun suning;
	 
	 //初始化草地格子属性,默认均无植物，live为false
	static {
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				glass.add(new Glass(46+82*j,115+100*i));	
			}
		}
		grade=1;
		sun=50;
		 x=0;
		 y=30;
		 loadtime=0;
		 op=2;
		 live=false; 
		 util=new GameUtil();
		 util.loadBGM("sounds/bgm8.wav");
		 img=new Image[21];
		 suns=new Image[11];
		 flowers=new Image[36];
		 wandous=new Image[43];
		 hanbings=new Image[39];
		 jianguos=new Image[115];
		 bullets=new Image[2];
		 qizhis=new Image[134];//僵尸数组初始化
		 tietongs=new Image[239];
		 ganlans=new Image[239];
		 baozhis=new Image[235];
		 for(int i=0;i<img.length;i++) {
			 img[i]=GameUtil.getImage("GameFrame/back"+i+".png");
			 }
		 for(int i=0;i<suns.length;i++) {
			 suns[i]=GameUtil.getImage("Sun/太阳"+i+".png");
			 }
		 for(int i=0;i<flowers.length;i++) {
			 flowers[i]=GameUtil.getImage("flower/flower ("+(i+1)+").png");
			 }
		 for(int i=0;i<wandous.length;i++) {
			 wandous[i]=GameUtil.getImage("wandou/wandou ("+(i+1)+").png");
			 }
		 for(int i=0;i<hanbings.length;i++) {
			 hanbings[i]=GameUtil.getImage("hanbing/hanbing ("+(i+1)+").png");
			 }
		 for(int i=0;i<jianguos.length;i++) {
			 jianguos[i]=GameUtil.getImage("jianguo/jianguo ("+(i+1)+").png");
			 }
		 for(int i=0;i<bullets.length;i++) {
			 bullets[i]=GameUtil.getImage("Bullets/bullet"+(i+1)+".png");
			 }
		 for(int i=0;i<qizhis.length;i++) {
			 qizhis[i]=GameUtil.getImage("Zombies/qizhi/qizhi ("+(i+1)+").png");
			 }
		 for(int i=0;i<tietongs.length;i++) {
			 tietongs[i]=GameUtil.getImage("Zombies/tietong/tietong ("+(i+1)+").png");
			 }
		 for(int i=0;i<ganlans.length;i++) {
			 ganlans[i]=GameUtil.getImage("Zombies/ganlan/ganlan ("+(i+1)+").png");
			 }
		 for(int i=0;i<baozhis.length;i++) {
			 baozhis[i]=GameUtil.getImage("Zombies/baozhi/baozhi ("+(i+1)+").png");
			 }
		 
		 
		 
		 //铲子
		 shove=new Shove(487,29,60,65,0,img[5]);
		 //将割草车加入car的arrylist
		 for(int i=1;i<6;i++) {
			 car.add(new Car(1-(i-1)*3,30+i*100,46,64,8,img[4]));
			 }
		 //将四张卡片加入card的arrylist
			 card.add(new Card(127+0*52,49,38,45,25,img[12],img[13]));//向日葵
			 card.add(new Card(127+1*52,49,38,45,100,img[14],img[15]));//豌豆射手
			 card.add(new Card(127+2*52,49,38,45,150,img[16],img[17]));//寒冰射手
			 card.add(new Card(127+3*52,49,38,45,50,img[18],img[19]));//坚果	
		 }
	//绘制画面的方法
	public static void draw(Graphics g) {
		Font font =g.getFont();//留存画笔原来的字体
		g.setFont(new Font("微软雅黑",Font.BOLD+Font.ITALIC,25));
		stopTime=new Date();
		if(loadtime==0) {//进入游戏动画
			suning=new Sun((int)(Math.random()*500)+200,50,50,(int)(Math.random()*350)+200,suns);
			g.drawImage(img[0],-212,y,null);
			 startTime=new Date();//开始游戏时间
			 start=new Date();
			loadtime++;	
		}else if(loadtime==1) {//开始游戏
			if((int)((stopTime.getTime()-startTime.getTime())*0.001)%7==0) {//十五秒出现一个阳光
				suning=new Sun((int)(Math.random()*500)+200,50,50,(int)(Math.random()*350)+200,suns);
				suning.live=true;suning.move=false;
			}
			
			g.drawImage(img[1],x,y-1,null);//背景
			g.drawString(sun+"",70, 112);//太阳数字
			
			g.setFont(font);//恢复字体
			
			g.drawImage(img[op],x,y,null);//返回菜单按钮
			stop =new  Date();
				if((int)((stopTime.getTime()-startTime.getTime())*0.001)>=20) {//40秒后第一波攻势来袭	
					if(num>=0&&num<=5) {
						if((int)((stop.getTime()-start.getTime())*0.001)>=((int)(Math.random()*5)+20)) {
								qizhi.add(new QiZhi(qizhis,(int)(Math.random()*5)*100+50,0.6,10,100));	
								start=new Date();
						}
					}else if(num>5&&num<=25) {
						if((int)((stop.getTime()-start.getTime())*0.001)>=((int)(Math.random()*5)+15)) {
							int x=(int)(Math.random()*16);
							if(x<5&&x>=0) {
								qizhi.add(new QiZhi(qizhis,(int)(Math.random()*5)*100+50,1,20,100));
							}else if(x>=5&&x<11) {
								tietong.add(new TieTong(tietongs,(int)(Math.random()*5)*100+50,0.8,15,200));
							}else if(x>=11&&x<14) {
								baozhi.add(new BaoZhi(baozhis,(int)(Math.random()*5)*100+50,0.8,15,200));
							}else {
								ganlan.add(new GanLan(ganlans,(int)(Math.random()*5)*100+50,0.8,15,200));
							}	
							start=new Date();
						}
					}else if(num>25&&num<=50) {
						if((int)((stop.getTime()-start.getTime())*0.001)>=((int)(Math.random()*5)+10)) {
							for(int i=0;i<2;i++) {//一下产生两只僵尸
								int x=(int)(Math.random()*16);
								if(x<4&&x>=0) {
									qizhi.add(new QiZhi(qizhis,(int)(Math.random()*5)*100+50,1,20,100));
								}else if(x>=4&&x<11) {
									tietong.add(new TieTong(tietongs,(int)(Math.random()*5)*100+50,0.8,15,200));
								}else if(x>=11&&x<14) {
									baozhi.add(new BaoZhi(baozhis,(int)(Math.random()*5)*100+50,0.8,15,200));
								}else {
									ganlan.add(new GanLan(ganlans,(int)(Math.random()*5)*100+50,0.8,15,200));
								}
							}
							start=new Date();
						}
					}else if(num>50){
						if((int)((stop.getTime()-start.getTime())*0.001)>=10+((int)(Math.random()*5))) {
							for(int i=0;i<2;i++) {
								int x=(int)(Math.random()*16);
								if(x<3&&x>=0) {
									qizhi.add(new QiZhi(qizhis,(int)(Math.random()*5)*100+50,1,20,100));
								}else if(x>=3&&x<9) {
									tietong.add(new TieTong(tietongs,(int)(Math.random()*5)*100+50,0.8,15,200));
								}else if(x>=9&&x<14) {
									baozhi.add(new BaoZhi(baozhis,(int)(Math.random()*5)*100+50,0.8,15,200));
								}else {
									ganlan.add(new GanLan(ganlans,(int)(Math.random()*5)*100+50,0.8,15,200));
								}	
							}
							start=new Date();
						}
					}
				}
			
				for(int i=0;i<qizhi.size();i++) {//旗帜僵尸
					if(qizhi.get(i).live) {
						qizhi.get(i).drawSelf(g);
					}else {
						qizhi.remove(i);
					}
				}
				for(int i=0;i<tietong.size();i++) {//铁桶僵尸
					if(tietong.get(i).live) {
						tietong.get(i).drawSelf(g);
					}else {
						tietong.remove(i);
					}
				}
				for(int i=0;i<baozhi.size();i++) {//报纸僵尸
					if(baozhi.get(i).live) {
						baozhi.get(i).drawSelf(g);
					}else {
						baozhi.remove(i);
					}
				}
				for(int i=0;i<ganlan.size();i++) {//橄榄僵尸
					if(ganlan.get(i).live) {
						ganlan.get(i).drawSelf(g);
					}else {
						ganlan.remove(i);
					}
				}
			for(int i=0;i<flower.size();i++) {//向日葵
				if(flower.get(i).live) {
					flower.get(i).drawSelf(g);
				}else {
					flower.remove(i);
				}
			}
			for(int i=0;i<wandou.size();i++) {//豌豆射手
				if(wandou.get(i).live) {
					wandou.get(i).drawSelf(g);
				}
			}
			for(int i=0;i<hanbing.size();i++) {//寒冰射手
				if(hanbing.get(i).live) {
					hanbing.get(i).drawSelf(g);
				}
			}
			for(int i=0;i<jianguo.size();i++) {//坚果
				if(jianguo.get(i).live) {
					jianguo.get(i).drawSelf(g);
				}
			}
			for(int i=0;i<car.size();i++) {//割草机
				car.get(i).drawSelf(g);
			}
			for(int i=0;i<card.size();i++) {//四张卡片
				card.get(i).drawSelf(g);
			}
			shove.drawSelf(g);//铲子
			suning.drawSelf(g);//画出太阳
			if(!sunings.isEmpty()) {
				for(int i=0;i<sunings.size();i++) {
					sunings.get(i).drawSelf(g);
					if(!sunings.get(i).live) {
						sunings.remove(i);
					}
				}
			}
			if(!bullet.isEmpty()) {//画出子弹+子弹回收
				for(int i=0;i<bullet.size();i++) {
					bullet.get(i).drawSelf(g);
					if(!bullet.get(i).live) {
						bullet.remove(i);
					}
					
				}
			}
			
			
			
			
			//处理卡片冷却时间
			for(int i=0;i<card.size();i++) {//记录当前时间，若和开始时间相差7.5以上，则cool=false;
				card.get(i).stop_time=new Date();
				if((card.get(i).stop_time.getTime()-card.get(i).start_time.getTime())*0.001>7.5) {
					card.get(i).cool=false;
				}
			}
			//处理向日葵生产阳光cd
			if(!flower.isEmpty()) {
				for(int i=0;i<flower.size();i++) {
					flower.get(i).end=new Date();
					if((flower.get(i).end.getTime()-flower.get(i).start.getTime())*0.001>15) {//冷却时间15s
						flower.get(i).start=new Date();//一个周期产生一个阳光，start时间重置*/
						sunings.add(new Sun(flower.get(i).x,flower.get(i).y,suns));
						System.out.println("产生阳光25");
					}
				}
			}
			//处理向日葵产生的阳光过时不收取消失事件
			if(!sunings.isEmpty()) {
				for(int i=0;i<sunings.size();i++) {
					if(!sunings.get(i).live) {//生命值为false的阳光对象被删除
						sunings.remove(i);
					}else {
						sunings.get(i).end=new Date();
						if((sunings.get(i).end.getTime()-sunings.get(i).start.getTime())*0.001>10) {//超过10秒不收取阳光。阳光的生命值为false
							sunings.get(i).live=false;
						}
						
					}
				}
			}	
		}else if(loadtime==2) {//暂停菜单绘制
			util.stopBGM();
			g.drawImage(img[8],x,y,null);
			if(backmenu) {
				g.drawImage(img[9],x,y,null);
			}else if(restart) {
				g.drawImage(img[10],x,y,null);
			}else if(backgame) {
				g.drawImage(img[11],x,y,null);
			}
		}else if(loadtime==3) {
			util.playBGM("sounds/shibai.wav",1);
			loadtime++;
		}else if(loadtime==4) {
			g.drawImage(img[20],x,y,null);
		}
	}
	//鼠标移动事件处理方法
	public static void MouseMove(MouseEvent e){
		if(loadtime==1) {

			if(GameUtil.ifRect(e.getX(),e.getY(),681,38,791,75)) {
				op=3;
			}else {op=2;}
			//铲子跟随鼠标移动
			 if(shove.move) {
				 shove.x=e.getX()-33;
				 shove.y=e.getY()-44;
				 }
			 //卡片跟随鼠标移动
			 for(int i=0;i<card.size();i++) {
				 if(card.get(i).move) {
					 card.get(i).x=e.getX()-9;
					 card.get(i).y=e.getY()-24;
				 }
			 }
		}else if(loadtime==2) {
			if(GameUtil.ifRect(e.getX(),e.getY(),301,338,502,377)) {//返回主菜单
				backmenu=true;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),301,389,502,425)) {//重新开始游戏
				restart=true;
			}else if(GameUtil.ifRect(e.getX(),e.getY(),241,460,571,528)) {//返回游戏
				backgame=true;
			}else {
				backmenu=false;restart=false;backgame=false;
			}
		}
	}		
	//鼠标点击事件处理方法
	public static void MouseClick(MouseEvent e){
		if(loadtime==1) {//游戏界面的点击事件
			if(GameUtil.ifRect(e.getX(),e.getY(),0,0,50,70)) {

			}
			//点击阳光图标,一次加25
			if(GameUtil.ifRect(e.getX(),e.getY(),67,45,112,83)) {
				sun+=25;
			}
			//暂定按一次菜单，随机一个割草车启动，阳光减25
			if(GameUtil.ifRect(e.getX(),e.getY(),681,38,791,75)) {
				loadtime=2;//点击菜单时暂停绘制游戏界面界面，绘制暂停界面
			}
			//铲子事件
			if(shove.move){//先判断是否已经点击过铲子，在改变铲子的移动状态
				for(int i=0;i<flower.size();i++) {//从花的数组里找有无要删除的
					if(GameUtil.ifRect(e.getX(),e.getY(),flower.get(i).x, flower.get(i).y, flower.get(i).x +flower.get(i).wide,flower.get(i).y+flower.get(i).high)) {
						for(int j=0;j<qizhi.size();j++) {//若铲除时旗帜僵尸在吃，则状态改变
							if(qizhi.get(j).getRect().intersects(flower.get(i).getRect())) {
								qizhi.get(j).attacking=false;qizhi.get(j).count=0;qizhi.get(j).moving=true;qizhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<tietong.size();j++) {//若铲除时铁桶僵尸在吃，则状态改变
							if(tietong.get(j).getRect().intersects(flower.get(i).getRect())) {
								if(tietong.get(j).hp>100) {
									tietong.get(j).count=0;
								}else {
									tietong.get(j).count=96;
								}
								tietong.get(j).attacking=false;tietong.get(j).moving=true;tietong.get(j).pengtime=1;
							}
						}
						for(int j=0;j<baozhi.size();j++) {//若铲除时报纸僵尸在吃，则状态改变
							if(baozhi.get(j).getRect().intersects(flower.get(i).getRect())) {
								if(baozhi.get(j).hp>100) {
									baozhi.get(j).count=0;
								}else {
									baozhi.get(j).count=94;
								}
								baozhi.get(j).attacking=false;baozhi.get(j).moving=true;baozhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<ganlan.size();j++) {//若铲除时橄榄球僵尸在吃，则状态改变
							if(ganlan.get(j).getRect().intersects(flower.get(i).getRect())) {
								if(ganlan.get(j).hp>100) {
									ganlan.get(j).count=0;
								}else {
									ganlan.get(j).count=96;
								}
								ganlan.get(j).attacking=false;ganlan.get(j).moving=true;ganlan.get(j).pengtime=1;
							}
						}
						GameFrame.util.playBGM("sounds/plant.wav",1);flower.get(i).live=false;glass.get(flower.get(i).num).live =false;flower.remove(i);
					}
					
				}
				for(int i=0;i<wandou.size();i++) {//从豌豆的数组里找有无要删除的
					if(GameUtil.ifRect(e.getX(),e.getY(),wandou.get(i).x, wandou.get(i).y, wandou.get(i).x +wandou.get(i).wide,wandou.get(i).y+wandou.get(i).high)) {
						for(int j=0;j<qizhi.size();j++) {//若铲除时旗帜僵尸在吃，则状态改变
							if(qizhi.get(j).getRect().intersects(wandou.get(i).getRect())) {
								qizhi.get(j).attacking=false;qizhi.get(j).count=0;qizhi.get(j).moving=true;qizhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<tietong.size();j++) {//若铲除时铁桶僵尸在吃，则状态改变
							if(tietong.get(j).getRect().intersects(wandou.get(i).getRect())) {
								if(tietong.get(j).hp>100) {
									tietong.get(j).count=0;
								}else {
									tietong.get(j).count=96;
								}
								tietong.get(j).attacking=false;tietong.get(j).moving=true;tietong.get(j).pengtime=1;
							}
						}
						for(int j=0;j<baozhi.size();j++) {//若铲除时报纸僵尸在吃，则状态改变
							if(baozhi.get(j).getRect().intersects(wandou.get(i).getRect())) {
								if(baozhi.get(j).hp>100) {
									baozhi.get(j).count=0;
								}else {
									baozhi.get(j).count=94;
								}
								baozhi.get(j).attacking=false;baozhi.get(j).moving=true;baozhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<ganlan.size();j++) {//若铲除时橄榄球僵尸在吃，则状态改变
							if(ganlan.get(j).getRect().intersects(wandou.get(i).getRect())) {
								if(ganlan.get(j).hp>100) {
									ganlan.get(j).count=0;
								}else {
									ganlan.get(j).count=96;
								}
								ganlan.get(j).attacking=false;ganlan.get(j).moving=true;ganlan.get(j).pengtime=1;
							}
						}
						GameFrame.util.playBGM("sounds/plant.wav",1);wandou.get(i).live=false;glass.get(wandou.get(i).num).live =false;wandou.remove(i);
					}
					
				}
				for(int i=0;i<hanbing.size();i++) {//从寒冰射手的数组里找有无要删除的
					if(GameUtil.ifRect(e.getX(),e.getY(),hanbing.get(i).x, hanbing.get(i).y, hanbing.get(i).x +hanbing.get(i).wide,hanbing.get(i).y+hanbing.get(i).high)) {
						for(int j=0;j<qizhi.size();j++) {//若铲除时旗帜僵尸在吃，则状态改变
							if(qizhi.get(j).getRect().intersects(hanbing.get(i).getRect())) {
								qizhi.get(j).attacking=false;qizhi.get(j).count=0;qizhi.get(j).moving=true;qizhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<tietong.size();j++) {//若铲除时铁桶僵尸在吃，则状态改变
							if(tietong.get(j).getRect().intersects(hanbing.get(i).getRect())) {
								if(tietong.get(j).hp>100) {
									tietong.get(j).count=0;
								}else {
									tietong.get(j).count=96;
								}
								tietong.get(j).attacking=false;tietong.get(j).moving=true;tietong.get(j).pengtime=1;
							}
						}
						for(int j=0;j<baozhi.size();j++) {//若铲除时报纸僵尸在吃，则状态改变
							if(baozhi.get(j).getRect().intersects(hanbing.get(i).getRect())) {
								if(baozhi.get(j).hp>100) {
									baozhi.get(j).count=0;
								}else {
									baozhi.get(j).count=94;
								}
								baozhi.get(j).attacking=false;baozhi.get(j).moving=true;baozhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<ganlan.size();j++) {//若铲除时橄榄球僵尸在吃，则状态改变
							if(ganlan.get(j).getRect().intersects(hanbing.get(i).getRect())) {
								if(ganlan.get(j).hp>100) {
									ganlan.get(j).count=0;
								}else {
									ganlan.get(j).count=96;
								}
								ganlan.get(j).attacking=false;ganlan.get(j).moving=true;ganlan.get(j).pengtime=1;
							}
						}
						GameFrame.util.playBGM("sounds/plant.wav",1);hanbing.get(i).live=false;glass.get(hanbing.get(i).num).live =false;hanbing.remove(i);
					}
					
				}
				for(int i=0;i<jianguo.size();i++) {//从坚果的数组里找有无要删除的
					if(GameUtil.ifRect(e.getX(),e.getY(),jianguo.get(i).x, jianguo.get(i).y, jianguo.get(i).x +jianguo.get(i).wide,jianguo.get(i).y+jianguo.get(i).high)) {
						for(int j=0;j<qizhi.size();j++) {//若铲除时旗帜僵尸在吃，则状态改变
							if(qizhi.get(j).getRect().intersects(jianguo.get(i).getRect())) {
								qizhi.get(j).attacking=false;qizhi.get(j).count=0;qizhi.get(j).moving=true;qizhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<tietong.size();j++) {//若铲除时铁桶僵尸在吃，则状态改变
							if(tietong.get(j).getRect().intersects(jianguo.get(i).getRect())) {
								if(tietong.get(j).hp>100) {
									tietong.get(j).count=0;
								}else {
									tietong.get(j).count=96;
								}
								tietong.get(j).attacking=false;tietong.get(j).moving=true;tietong.get(j).pengtime=1;
							}
						}
						for(int j=0;j<baozhi.size();j++) {//若铲除时报纸僵尸在吃，则状态改变
							if(baozhi.get(j).getRect().intersects(jianguo.get(i).getRect())) {
								if(baozhi.get(j).hp>100) {
									baozhi.get(j).count=0;
								}else {
									baozhi.get(j).count=94;
								}
								baozhi.get(j).attacking=false;baozhi.get(j).moving=true;baozhi.get(j).pengtime=1;
							}
						}
						for(int j=0;j<ganlan.size();j++) {//若铲除时橄榄球僵尸在吃，则状态改变
							if(ganlan.get(j).getRect().intersects(jianguo.get(i).getRect())) {
								if(ganlan.get(j).hp>100) {
									ganlan.get(j).count=0;
								}else {
									ganlan.get(j).count=96;
								}
								ganlan.get(j).attacking=false;ganlan.get(j).moving=true;ganlan.get(j).pengtime=1;
							}
						}
						GameFrame.util.playBGM("sounds/plant.wav",1);jianguo.get(i).live=false;glass.get(jianguo.get(i).num).live =false;jianguo.remove(i);
					}
					
				}
				 shove.move=false;
				 shove.x=495;shove.y=29;
			 }
			//点击铲子，铲子移动状态改变
			if(GameUtil.ifRect(e.getX(),e.getY(),508,32,567,96)) {
				 shove.move=true;
				 GameFrame.util.playBGM("sounds/moveshove.wav",1);
			 }
			
			//点击卡片处理事件
			for(int i=0;i<card.size();i++) {//点之前先判断是否已经点起过另一个卡片,若是，则种植物
				if(card.get(i).move) {
					card.get(i).move=false;
					switch(i) {
					case 0:{
						for(int j=0;j<glass.size();j++) {
							if(glass.get(j).live==false&&GameUtil.ifRect(e.getX(),e.getY(),glass.get(j).x,glass.get(j).y,glass.get(j).x+glass.get(j).wide,glass.get(j).y+glass.get(j).high)) {
								flower.add(new Flower(glass.get(j).x,glass.get(j).y,60,70,flowers,j));
								glass.get(j).live=true;sun-=card.get(i).price; //土块生命为true，有植物种植
								GameFrame.util.playBGM("sounds/plant.wav",1);//音效
								card.get(i).cool=true;card.get(i).start_time=new Date();//冷却起始时间
								break;
							}
						}
						card.get(i).x=127+i*52;card.get(i).y=49;break;//卡片归位
					}
					case 1:{
						for(int j=0;j<glass.size();j++) {
							if(glass.get(j).live==false&&GameUtil.ifRect(e.getX(),e.getY(),glass.get(j).x,glass.get(j).y,glass.get(j).x+glass.get(j).wide,glass.get(j).y+glass.get(j).high)) {
								wandou.add(new Wandou(glass.get(j).x,glass.get(j).y,60,70,wandous,j));
								glass.get(j).live=true;sun-=card.get(i).price;//土块生命为true，有植物种植
								GameFrame.util.playBGM("sounds/plant.wav",1);//音效
								card.get(i).cool=true;card.get(i).start_time=new Date();//冷却起始时间
								break;
							}
						}
						card.get(i).x=127+i*52;card.get(i).y=49;break;//卡片归位
					}
					case 2:{
						for(int j=0;j<glass.size();j++) {
							if(glass.get(j).live==false&&GameUtil.ifRect(e.getX(),e.getY(),glass.get(j).x,glass.get(j).y,glass.get(j).x+glass.get(j).wide,glass.get(j).y+glass.get(j).high)) {
								hanbing.add(new Hanbing(glass.get(j).x,glass.get(j).y,60,70,hanbings,j));
								glass.get(j).live=true;sun-=card.get(i).price;//土块生命为true，有植物种植
								GameFrame.util.playBGM("sounds/plant.wav",1);//音效
								card.get(i).cool=true;card.get(i).start_time=new Date();//冷却起始时间
								break;
							}
						}
						card.get(i).x=127+i*52;card.get(i).y=49;break;//卡片归位
					}
					case 3:{
						for(int j=0;j<glass.size();j++) {
							if(glass.get(j).live==false&&GameUtil.ifRect(e.getX(),e.getY(),glass.get(j).x,glass.get(j).y,glass.get(j).x+glass.get(j).wide,glass.get(j).y+glass.get(j).high)) {
								jianguo.add(new Jianguo(glass.get(j).x,glass.get(j).y,60,70,jianguos,j));
								glass.get(j).live=true;sun-=card.get(i).price;//土块生命为true，有植物种植
								GameFrame.util.playBGM("sounds/plant.wav",1);//音效
								card.get(i).cool=true;card.get(i).start_time=new Date();//冷却起始时间
								break;
							}
						}
						card.get(i).x=127+i*52;card.get(i).y=49;break;//卡片归位
					}
					}
				}
			}
			//第一次点击卡片		
			if(GameUtil.ifRect(e.getX(),e.getY(),131,40,176,106)) {
				 if(sun>=card.get(0).price&&card.get(0).cool==false) {
					 card.get(0).move=true;
					 GameFrame.util.playBGM("sounds/plant.wav",1);
				 }
			 }else if(GameUtil.ifRect(e.getX(),e.getY(),184,39,227,105)){
				 if(sun>=card.get(1).price&&card.get(1).cool==false) {
					 card.get(1).move=true;
				    GameFrame.util.playBGM("sounds/plant.wav",1);
				 }
			 }else if(GameUtil.ifRect(e.getX(),e.getY(),235,39,280,107)){
				 if(sun>=card.get(2).price&&card.get(2).cool==false) {
					 card.get(2).move=true;
					 GameFrame.util.playBGM("sounds/plant.wav",1);
				 }
			 }else if(GameUtil.ifRect(e.getX(),e.getY(),287,39,334,108)){
				 if(sun>=card.get(3).price&&card.get(3).cool==false) {
					 card.get(3).move=true;
					 GameFrame.util.playBGM("sounds/plant.wav",1);
				 }
			 }
			//点击自然出现的阳光事件
			if(GameUtil.ifRect(e.getX(),e.getY(),suning.x,suning.y,suning.x+suning.wide,suning.y+suning.high)) {
				suning.move=true;
				GameFrame.util.playBGM("sounds/yangguang.wav",1);
				if((suning.y-45)<30) {
					suning.speedx=10;
					suning.speedy=5;
				}else {
				suning.speedx=(int)((suning.x-70)/30);
				suning.speedy=(int)((suning.y-45)/30);
				}	
			}
			//点击向日葵生产的阳光事件,若阳光数组不空，则遍历并判断是否收取
			        if(!sunings.isEmpty()) {
			        	for(int i=0;i<sunings.size();i++) {
			    			if(GameUtil.ifRect(e.getX(),e.getY(),sunings.get(i).x,sunings.get(i).y,sunings.get(i).x+50,sunings.get(i).y+50)) {
			    				sunings.get(i).move=true;
			    				GameFrame.util.playBGM("sounds/yangguang.wav",1);
			    				
			    			}
			    		}
			        }
		}else if(loadtime==2) {
			if(GameUtil.ifRect(e.getX(),e.getY(),301,338,502,377)) {//返回主菜单
				loadtime=1;
				GameFrame.live=false;
				MenuFrame.live =true;
				LoadFrame.util.playBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),301,389,502,425)) {//重新开始游戏,清除游戏数据
				loadtime=0;sun=100;
				flower.clear();wandou.clear();hanbing.clear();jianguo.clear();
				qizhi.clear();tietong.clear();baozhi.clear();ganlan.clear();bullet.clear();car.clear();sunings.clear();
				for(int i=0;i<card.size();i++) {
					card.get(i).cool=false;
				}
				for(int i=0;i<glass.size();i++) {
					glass.get(i).live=false;
				}
				for(int i=1;i<6;i++) {
					car.add(new Car(1-(i-1)*3,30+i*100,46,64,8,img[4]));//重置割草车
					}
				startTime=new  Date();
				start=new Date();
				util.playBGM();
			}else if(GameUtil.ifRect(e.getX(),e.getY(),241,460,571,528)) {//返回游戏
				util.playBGM();
				loadtime=1;
			}
		}else if(loadtime==4) {			
			loadtime=0;sun=50;
			flower.clear();wandou.clear();hanbing.clear();jianguo.clear();
			qizhi.clear();tietong.clear();baozhi.clear();ganlan.clear();bullet.clear();car.clear();sunings.clear();
			for(int i=0;i<card.size();i++) {
				card.get(i).cool=false;
			}
			for(int i=0;i<glass.size();i++) {
				glass.get(i).live=false;
			}
			for(int i=1;i<6;i++) {
				car.add(new Car(1-(i-1)*3,30+i*100,46,64,8,img[4]));//重置割草车
				}
			util.stopBGM();
			GameFrame.live=false;
			MenuFrame.live =true;
			LoadFrame.util.playBGM();
		}
	}
}
