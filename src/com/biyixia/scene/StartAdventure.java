package com.biyixia.scene;

import com.biyixia.Director;
import com.biyixia.sprite.*;
import com.biyixia.sprite.bullet.Bullet;
import com.biyixia.sprite.plants.*;
import com.biyixia.sprite.zombies.*;
import com.biyixia.utils.GameUtil;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;


/**
 * @author dbc
 * @create 2023-01-16 17:32
 */
public class StartAdventure {
    private final Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private final AudioClip bgm8 = GameUtil.soundPlay("sounds/bgm8.wav");
    private final AudioClip defeat = GameUtil.soundPlay("sounds/shibai.wav");
    private final Refresh refresh = new Refresh();
    private long lastUpdate = 0;
    private final ArrayList<Car> cars = new ArrayList<>();
    public static ArrayList<Glass> glasses = new ArrayList<>();
    public static HashMap<Glass, Plant> plants = new HashMap<>();
    public static ArrayList<Zombie> zombies = new ArrayList<>();
    public static ArrayList<Sun> suns = new ArrayList<>();
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    private static SunFlower sunFlowerCard = new SunFlower();
    private static WallNut wallNutCard = new WallNut();
    private static SnowPea snowPeaCard = new SnowPea();
    private static PeaShooter peaShooterCard = new PeaShooter();
    private static ArrayList<Plant> plantCards = new ArrayList<>();
    private Shove shove = null;
    private static final int CARD_X = 137, CARD_Y = 17, CARD_SPACE = 52;
    private static Date startTime;
    private static Date stopTime;
    private static int interval = 0;
    private final MouseClick mouseClick = new MouseClick();
    private final MouseMove mouseMove = new MouseMove();
    private static boolean move = false;
    public static int money = 25;
    private static double x = 0;
    private static double y = 0;
    public static boolean game = false;
    private static boolean gameDefeat = false;
    //击杀僵尸数
    private static int num = 0;

    public void init(Stage stage) {
        game = true;
        //1.播放bgm
        bgm8.play();
        //2.加载页面
        AnchorPane anchorPane = new AnchorPane(canvas);
        stage.getScene().setRoot(anchorPane);
        stage.getScene().setOnMouseClicked(mouseClick);
        stage.getScene().setOnMouseMoved(mouseMove);
        refresh.start();
        //3.完成各对象初始化
        //小车初始化
        for (int i = 1; i < 6; i++) {
            cars.add(new Car(20 - (i - 1) * 3, 5 + i * 95, 46, 64, 8));
        }
        plantCards.add(sunFlowerCard);
        plantCards.add(snowPeaCard);
        plantCards.add(peaShooterCard);
        plantCards.add(wallNutCard);
        //铲子初始化
        shove = new Shove(505, 0, 60, 65);
        startTime = new Date();
        //草地初始化
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                glasses.add(new Glass(46 + 82 * j, 80 + 100 * i, 82, 100));
            }
        }
    }

    public void clear(Stage stage) {
        interval = 0;
        money = 25;
        num = 0;
        game = true;
        gameDefeat = false;
        //清空数组并释放内存
        glasses.clear();
        glasses.trimToSize();
        cars.clear();
        cars.trimToSize();
        zombies.clear();
        zombies.trimToSize();
        suns.clear();
        suns.trimToSize();
        bullets.clear();
        bullets.trimToSize();
        //清空植物
        for (Glass glass : glasses) {
            if (glass.live) {
                plants.remove(glass);
            }
        }
    }

    private void paint() {
        //填充画布左侧
        graphicsContext.setFill(Paint.valueOf("white"));
        graphicsContext.fillRect(0,0,10,600);
        graphicsContext.setFill(Paint.valueOf("black"));
        if (!game) {     //游戏失败
            bgm8.stop();
            if (!gameDefeat && !defeat.isPlaying()) {   //不同时播放且只播放一次
                defeat.play();
                gameDefeat = true;
            }
            graphicsContext.drawImage(new Image("images/GameFrame/back20.png"), 10, 0);
            return;
        }

        //设置背景图片
        graphicsContext.drawImage(new Image("images/GameFrame/back1.png"), 10, 0);

        //画车
        for (Car car : cars) {
            car.paint(graphicsContext);
        }

        //画铲子
        if (!Shove.move) {
            shove.paint(graphicsContext);
        }

        //初始化卡片信息
        graphicsContext.setFont(new Font("微软雅黑", 20));
        graphicsContext.fillText(String.valueOf(money), 80, 78);
        graphicsContext.setFont(new Font("微软雅黑", 10));
        for (int i = 0; i < 4; i++) {
            graphicsContext.fillText(String.valueOf(plantCards.get(i).getPrice()), CARD_X + i * CARD_SPACE, 75);
        }

        //画卡片
        int index = 0;
        for (Plant plantCard : plantCards) {
            if (money >= plantCard.getPrice()) {
                cardPaint(plantCard.getCards(), graphicsContext, index++, true);
            } else {
                cardPaint(plantCard.getCards(), graphicsContext, index++, false);
            }
        }

        //画四种植物
        Collection<Plant> values = plants.values();
        try {
            for (Plant value : values) {
                value.paint(graphicsContext);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("植物在遍历过程中删除");
        }

        //画僵尸
        Iterator<Zombie> iterator = zombies.iterator();
        while (iterator.hasNext()) {
            Zombie zombie = iterator.next();
            zombie.paint(graphicsContext);
            if (!zombie.live) {
                iterator.remove();
                num += 1;
            }
        }

        //画子弹
        for (Bullet bullet : bullets) {
            bullet.paint(graphicsContext);
        }

        //画太阳
        for (Sun sun : suns) {
            if (sun.live) {
                sun.paint(graphicsContext);
            }
        }

        //画点击卡片后的附着效果
        for (Plant plantCard : plantCards) {
            if (plantCard.getMove()) {
                graphicsContext.drawImage(plantCard.getCards()[0], x - plantCard.getCards()[0].getWidth() / 2, y - plantCard.getCards()[0].getHeight() / 2);
            }
        }
        if (Shove.move) {
            graphicsContext.drawImage(Shove.image, x - Shove.image.getWidth() / 2, y - Shove.image.getHeight() / 2);
        }

        //每隔一秒钟执行
        stopTime = new Date();
        if ((int) (((stopTime.getTime() - startTime.getTime()) * 0.001)) == interval) {
            interval++;
            System.out.println(interval);
            //每9秒随机出现一个太阳
            if (interval % 9 == 0) {
                suns.add(new Sun((int) (Math.random() * 680) + 70, (int) (Math.random() * 500) + 50, 50.0, 50));
            }
            if (interval >= 20) {
                //20秒后第一波攻势来袭
                if (num >= 0 && num <= 5) { //击杀数小于5个时，每七秒钟生成一个旗帜僵尸
                    if ((interval % 7 == 0)) {
                        addZombie(0);
                    }
                } else if (num > 5 && num <= 15) {  //击杀数在(5,25]之间时，每五秒生成一个僵尸(旗帜或铁桶）
                    if (interval % 5 == 0) {
                        addZombie((int) (Math.random() * 2));
                    }
                } else if (num > 15 && num <= 30) {
                    if (interval % 5 == 0) {    //击杀数在(15,30]之间时，每五秒生成两个僵尸(旗帜或铁桶或报纸或橄榄）
                        addZombie((int) (Math.random() * 4));
                        addZombie((int) (Math.random() * 4));
                    }
                } else if (num > 30) {      //击杀数超过30，每秒产生一个僵尸
                    addZombie((int) (Math.random() * 4));
                }
            }
        }
    }

    //生成一种僵尸
    private void addZombie(int x) {
        switch (x) {
            case 0:
                zombies.add(new FlagZombies((int) (Math.random() * 5) * 100 + 15));
                break;
            case 1:
                zombies.add(new BucketheadZombie((int) (Math.random() * 5) * 100 + 15));
                break;
            case 2:
                zombies.add(new NewspaperZombie((int) (Math.random() * 5) * 100 + 15));
                break;
            case 3:
                zombies.add(new FootballZombie((int) (Math.random() * 5) * 100 + 15));
                break;
        }
    }

    //每帧刷新画面
    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long now) {
            if (now - lastUpdate > 20_000_000) {
                paint();
                lastUpdate = now;
            }
        }
    }

    //画卡片的黑白状态
    private void cardPaint(Image[] cards, GraphicsContext graphicsContext, int index, boolean visible) {
        if (visible) {
            graphicsContext.drawImage(cards[0], StartAdventure.CARD_X + index * StartAdventure.CARD_SPACE, StartAdventure.CARD_Y, cards[0].getWidth(), cards[0].getHeight());
        } else {
            graphicsContext.drawImage(cards[1], StartAdventure.CARD_X + index * StartAdventure.CARD_SPACE, StartAdventure.CARD_Y, cards[0].getWidth(), cards[0].getHeight());
        }
    }

    //获取鼠标的位置
    private class MouseMove implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            x = event.getX();
            y = event.getY();
        }
    }

    private class MouseClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //游戏结束
            if (!game) {
                Director.getInstance().toMenu();
            }
            //检测是否点击太阳
            for (Sun sun : suns) {
                if (sun.live && GameUtil.ifRect(event.getX(), event.getY(), sun.getX(), sun.getY(), sun.getX() + sun.getWidth(), sun.getY() + sun.getHeight())) {
                    GameUtil.soundPlay("sounds/yangguang.wav").play();
                    sun.move = true;
                    sun.setSpeedx((sun.getX() - 70) / 30);
                    sun.setSpeedy((sun.getY() - 11) / 30);
                }
            }
            //点击卡片或铲子，移动卡片或铲子
            if (!move) {
                if (!Shove.move && GameUtil.ifRect(event.getX(), event.getY(), shove.getX(), shove.getY(), shove.getX() + shove.getWidth(), shove.getY() + shove.getHeight())) {
                    GameUtil.soundPlay("sounds/moveshove.wav").play();
                    Shove.move = true;
                    move = true;
                }
                for (int i = 0; i < 4; i++) {
                    if (GameUtil.ifRect(event.getX(), event.getY(), CARD_X + CARD_SPACE * i, CARD_Y, CARD_X + 39 + CARD_SPACE * i, CARD_Y + 45)) {
                        Plant card = null;
                        switch (i) {
                            case 0:
                                card = plantCards.get(0);
                                break;
                            case 1:
                                card = plantCards.get(1);
                                break;
                            case 2:
                                card = plantCards.get(2);
                                break;
                            case 3:
                                card = plantCards.get(3);
                                break;
                        }
                        if (money >= card.getPrice()) {
                            card.setMove(true);
                            GameUtil.soundPlay("sounds/plant.wav").play();
                            move = true;
                        }
                    }
                }
            } else {    //已经点击卡片或铲子
                //使用卡片或铲子
                for (Plant plantCard : plantCards) {
                    if (plantCard.getMove()) {
                        for (Glass glass : glasses) {
                            //放置植物
                            if (!glass.live && GameUtil.ifRect(event.getX(), event.getY(), glass.getX(),
                                    glass.getY(), glass.getX() + glass.getWidth(), glass.getY() + glass.getHeight())) {
                                GameUtil.soundPlay("sounds/plant.wav").play();
                                money -= plantCard.getPrice();
                                glass.live = true;
                                if (plantCard instanceof SunFlower) {
                                    plants.put(glass, new SunFlower(glass.getX(), glass.getY(), 73, 74));
                                } else if (plantCard instanceof WallNut) {
                                    plants.put(glass, new WallNut(glass.getX(), glass.getY(), 73, 74));
                                } else if (plantCard instanceof SnowPea) {
                                    plants.put(glass, new SnowPea(glass.getX(), glass.getY(), 73, 74));
                                } else if (plantCard instanceof PeaShooter) {
                                    plants.put(glass, new PeaShooter(glass.getX(), glass.getY(), 73, 74));
                                }
                            }
                        }//点击不在草地内也取消卡片的跟随
                        plantCard.setMove(false);
                        move = false;
                    }
                }
                if (Shove.move) {
                    for (Glass glass : glasses) {
                        if (glass.live && GameUtil.ifRect(event.getX(), event.getY(), glass.getX(),
                                glass.getY(), glass.getX() + glass.getWidth(), glass.getY() + glass.getHeight())) {
                            glass.live = false;
                            plants.remove(glass);
                        }
                    }//铲子点击在草地外或点击的草地没有元素取消铲子的跟随
                    Shove.move = false;
                    move = false;
                }
            }
        }
    }
}
