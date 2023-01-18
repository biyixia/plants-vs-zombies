package com.biyixia.scene;

import com.biyixia.Director;
import com.biyixia.sprite.Car;
import com.biyixia.sprite.Glass;
import com.biyixia.sprite.Shove;
import com.biyixia.sprite.Sun;
import com.biyixia.sprite.plants.*;
import com.biyixia.utils.GameUtil;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;


/**
 * @author dbc
 * @create 2023-01-16 17:32
 */
public class StartAdventure {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.WIDTH);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private AudioClip bgm8 = GameUtil.soundPlay("sounds/bgm8.wav");
    private AudioClip defeat = GameUtil.soundPlay("sounds/shibai.wav");
    private Refresh refresh = new Refresh();
    private long lastUpdate = 0;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Glass> glasses = new ArrayList<>();
    private HashMap<Glass, Plant> plant = new HashMap<>();
    private ArrayList<SunFlower> sunFlowers = new ArrayList<>();
    private ArrayList<WallNut> wallNuts = new ArrayList<>();
    private ArrayList<PeaShooter> peaShooters = new ArrayList<>();
    private ArrayList<SnowPea> snowPeas = new ArrayList<>();

    private Sun sun = null;
    private Shove shove = null;
    private static final int CARD_X = 137, CARD_Y = 17, CARD_SPACE = 52;
    private static Date startTime;
    private static Date stopTime;
    private static int interval = 0;
    private MouseClick mouseClick = new MouseClick();
    private MouseMove mouseMove = new MouseMove();
    private static boolean move = false;
    public static int money = 10000;
    private static double x = 0;
    private static double y = 0;

    private static boolean game = false;
    private static boolean gameDefeat = false;

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
        //铲子初始化
        shove = new Shove(505, 0, 60, 65);
        startTime = new Date();
        //草地各自初始化
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                glasses.add(new Glass(46 + 82 * j, 80 + 100 * i, 82, 100));
            }
        }
    }

    public void clear(Stage stage) {

    }

    private void paint() {
        if (!game) {     //游戏失败
            bgm8.stop();
            if (!gameDefeat && !defeat.isPlaying()) {
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
        graphicsContext.fillText(String.valueOf(SunFlower.PRICE), StartAdventure.CARD_X, 75);
        graphicsContext.fillText(String.valueOf(SnowPea.PRICE), StartAdventure.CARD_X + StartAdventure.CARD_SPACE, 75);
        graphicsContext.fillText(String.valueOf(PeaShooter.PRICE), StartAdventure.CARD_X + 2 * StartAdventure.CARD_SPACE, 75);
        graphicsContext.fillText(String.valueOf(WallNut.PRICE), StartAdventure.CARD_X + 3 * StartAdventure.CARD_SPACE, 75);
        //画卡片
        if (money >= SunFlower.PRICE) {
            cardPaint(SunFlower.cards, graphicsContext, 0, true);
        } else {
            cardPaint(SunFlower.cards, graphicsContext, 0, false);
        }
        if (money >= SnowPea.PRICE) {
            cardPaint(SnowPea.cards, graphicsContext, 1, true);
        } else {
            cardPaint(SnowPea.cards, graphicsContext, 1, false);
        }
        if (money >= PeaShooter.PRICE) {
            cardPaint(PeaShooter.cards, graphicsContext, 2, true);
        } else {
            cardPaint(PeaShooter.cards, graphicsContext, 2, false);
        }
        if (money >= WallNut.PRICE) {
            cardPaint(WallNut.cards, graphicsContext, 3, true);
        } else {
            cardPaint(WallNut.cards, graphicsContext, 3, false);
        }
        //每7秒随机出现一个太阳
        stopTime = new Date();
        if ((int) (((stopTime.getTime() - startTime.getTime()) * 0.001)) == interval) {
            System.out.println(interval++);
//            interval++;
            if (!Sun.live && interval % 4 == 0) {
                Sun.live = true;
                sun = new Sun((int) (Math.random() * 680) + 70, (int) (Math.random() * 500) + 50, 50.0, 50);
            }
        }
        //画太阳
        if (Sun.live) {
            sun.paint(graphicsContext);
        }

        //画四种植物
        Collection<Plant> values = plant.values();
        for (Plant value : values) {
            value.paint(graphicsContext);
        }


        //画点击卡片后的附着效果
        if (SunFlower.move) {
            graphicsContext.drawImage(SunFlower.cards[0], x - SunFlower.cards[0].getWidth() / 2, y - SunFlower.cards[0].getHeight() / 2);
        }
        if (SnowPea.move) {
            graphicsContext.drawImage(SnowPea.cards[0], x - SnowPea.cards[0].getWidth() / 2, y - SnowPea.cards[0].getHeight() / 2);
        }
        if (PeaShooter.move) {
            graphicsContext.drawImage(PeaShooter.cards[0], x - PeaShooter.cards[0].getWidth() / 2, y - PeaShooter.cards[0].getHeight() / 2);
        }
        if (WallNut.move) {
            graphicsContext.drawImage(WallNut.cards[0], x - WallNut.cards[0].getWidth() / 2, y - WallNut.cards[0].getHeight() / 2);
        }
        if (Shove.move) {
            graphicsContext.drawImage(Shove.image, x - Shove.image.getWidth() / 2, y - Shove.image.getHeight() / 2);
        }
    }

    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long now) {
            if (now - lastUpdate > 20_000_000) {
                paint();
                lastUpdate = now;
            }
        }
    }

    private void cardPaint(Image[] cards, GraphicsContext graphicsContext, int index, boolean visible) {
        if (visible) {
            graphicsContext.drawImage(cards[0], StartAdventure.CARD_X + index * StartAdventure.CARD_SPACE, StartAdventure.CARD_Y, cards[0].getWidth(), cards[0].getHeight());
        } else {
            graphicsContext.drawImage(cards[1], StartAdventure.CARD_X + index * StartAdventure.CARD_SPACE, StartAdventure.CARD_Y, cards[0].getWidth(), cards[0].getHeight());
        }
    }

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
            if (!game) {
                Director.getInstance().toMenu();
            }
            if (sun != null && GameUtil.ifRect(event.getX(), event.getY(), sun.getX(), sun.getY(), sun.getX() + sun.getWidth(), sun.getY() + sun.getHeight())) {
                GameUtil.soundPlay("sounds/yangguang.wav").play();
                Sun.move = true;
                sun.setSpeedx((sun.getX() - 70) / 30);
                sun.setSpeedy((sun.getY() - 11) / 30);
            }
            //点击铲子，铲子移动状态改变
            if (!move) {
                if (!Shove.move && GameUtil.ifRect(event.getX(), event.getY(), shove.getX(), shove.getY(), shove.getX() + shove.getWidth(), shove.getY() + shove.getHeight())) {
                    GameUtil.soundPlay("sounds/moveshove.wav").play();
                    Shove.move = true;
                    move = true;
                }
                if (GameUtil.ifRect(event.getX(), event.getY(), CARD_X, CARD_Y, CARD_X + 39, CARD_Y + 45)) {
                    if (money >= SunFlower.PRICE) {
                        SunFlower.move = true;
                        GameUtil.soundPlay("sounds/plant.wav").play();
                        move = true;

                    }
                } else if (GameUtil.ifRect(event.getX(), event.getY(), CARD_X + CARD_SPACE, CARD_Y, CARD_X + CARD_SPACE + 39, CARD_Y + 45)) {
                    if (money >= SnowPea.PRICE) {
                        SnowPea.move = true;
                        GameUtil.soundPlay("sounds/plant.wav").play();
                        move = true;

                    }
                } else if (GameUtil.ifRect(event.getX(), event.getY(), CARD_X + CARD_SPACE * 2, CARD_Y, CARD_X + CARD_SPACE * 2 + 39, CARD_Y + 45)) {
                    if (money >= PeaShooter.PRICE) {
                        PeaShooter.move = true;
                        GameUtil.soundPlay("sounds/plant.wav").play();
                        move = true;

                    }
                } else if (GameUtil.ifRect(event.getX(), event.getY(), CARD_X + CARD_SPACE * 3, CARD_Y, CARD_X + CARD_SPACE * 3 + 39, CARD_Y + 45)) {
                    if (money >= WallNut.PRICE) {
                        WallNut.move = true;
                        GameUtil.soundPlay("sounds/plant.wav").play();
                        move = true;

                    }
                }
            }

            //放置植物或清除植物
            if (SunFlower.move || SnowPea.move || PeaShooter.move || WallNut.move || Shove.move) {
                for (Glass glass : glasses) {
                    if (!glass.live && GameUtil.ifRect(event.getX(), event.getY(), glass.getX(),
                            glass.getY(), glass.getX() + glass.getWidth(), glass.getY() + glass.getHeight())) {
                        if (SunFlower.move) {
                            GameUtil.soundPlay("sounds/plant.wav").play();
                            money -= SunFlower.PRICE;
                            plant.put(glass,new SunFlower(glass.getX(), glass.getY(), 73, 74));
                            SunFlower.move = false;
                            glass.live = true;
                        }
                        if (SnowPea.move) {
                            SnowPea.move = false;
                            money -= SnowPea.PRICE;
                            plant.put(glass,new SnowPea(glass.getX(), glass.getY(), 73, 74));
                            GameUtil.soundPlay("sounds/plant.wav").play();
                            glass.live = true;

                        }
                        if (PeaShooter.move) {
                            PeaShooter.move = false;
                            money -= PeaShooter.PRICE;
                            plant.put(glass,new PeaShooter(glass.getX(), glass.getY(), 73, 74));
                            GameUtil.soundPlay("sounds/plant.wav").play();
                            glass.live = true;

                        }
                        if (WallNut.move) {
                            WallNut.move = false;
                            money -= WallNut.PRICE;
                            plant.put(glass,new WallNut(glass.getX(), glass.getY(), 73, 74));
                            GameUtil.soundPlay("sounds/plant.wav").play();
                            glass.live = true;
                        }
                        move = false;
                    }
                    if (glass.live && GameUtil.ifRect(event.getX(), event.getY(), glass.getX(),
                            glass.getY(), glass.getX() + glass.getWidth(), glass.getY() + glass.getHeight())){
                        if (Shove.move) {
                            Shove.move = false;
                            glass.live = false;
                            move = false;
                            plant.remove(glass);
                        }
                    }
                }
            }
        }
    }
}
