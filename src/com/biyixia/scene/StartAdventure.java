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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
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
    private static boolean running;
    private static boolean menuLight;
    private final Image menuButton = new Image("images/GameFrame/back2.png");
    private final Image selectedMenu = new Image("images/GameFrame/back3.png");
    private final Image menu = new Image("images/GameFrame/back8.png");
    private final Image mainMenu = new Image("images/GameFrame/back9.png");
    private static boolean selectMainMenu;
    private final Image restart = new Image("images/GameFrame/back10.png");
    private static boolean selectGoOn;
    private final Image goOn = new Image("images/GameFrame/back11.png");
    private static boolean selectRestart = false;
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
    private static LocalDateTime startTime;
    private static LocalDateTime stopTime;
    public static int interval;
    private final MouseClick mouseClick = new MouseClick();
    private final MouseMove mouseMove = new MouseMove();
    private static boolean move;
    public static int money;
    private static double x;
    private static double y;
    public static boolean game;
    private static boolean gameDefeat;
    //???????????????
    private static int num = 0;

    public void init(Stage stage) {
        //?????????????????????
        menuLight = false;
        selectMainMenu = false;
        selectGoOn = false;
        selectRestart = false;
        running = true;
        //?????????????????????
        game = true;
        gameDefeat = false;
        interval = 0;
        money = 25;
        num = 0;
        move = false;
        //1.??????bgm
        bgm8.play();
        //2.????????????
        AnchorPane anchorPane = new AnchorPane(canvas);
        stage.getScene().setRoot(anchorPane);
        stage.getScene().setOnMouseClicked(mouseClick);
        stage.getScene().setOnMouseMoved(mouseMove);
        refresh.start();
        //3.????????????????????????
        //???????????????
        for (int i = 1; i < 6; i++) {
            cars.add(new Car(20 - (i - 1) * 3, 5 + i * 95, 46, 64, 8));
        }
        plantCards.add(sunFlowerCard);
        plantCards.add(snowPeaCard);
        plantCards.add(peaShooterCard);
        plantCards.add(wallNutCard);
        //???????????????
        shove = new Shove(505, 0, 60, 65);
        startTime = LocalDateTime.now();
        //???????????????
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                glasses.add(new Glass(46 + 82 * j, 80 + 100 * i, 82, 100));
            }
        }
    }

    public void clear(Stage stage) {
        bgm8.stop();
        //????????????????????????
        for (Glass glass : glasses) {
            if (glass.live) {
                plants.remove(glass);
            }
        }
        //???????????????????????????
        plantCards.clear();
        plantCards.trimToSize();
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
    }

    private void paint() {
        if (!game) {     //????????????
            bgm8.stop();
            if (!gameDefeat && !defeat.isPlaying()) {   //?????????????????????????????????
                defeat.play();
                gameDefeat = true;
            }
            graphicsContext.drawImage(new Image("images/GameFrame/back20.png"), 10, 0);
            return;
        }

        //??????????????????
        graphicsContext.setFill(Paint.valueOf("white"));
        graphicsContext.fillRect(0, 0, 10, 600);
        graphicsContext.setFill(Paint.valueOf("black"));

        //??????????????????
        graphicsContext.drawImage(new Image("images/GameFrame/back1.png"), 10, 0);

        //???????????????
        if (menuLight) {
            graphicsContext.drawImage(selectedMenu, 0, 0);
        } else {
            graphicsContext.drawImage(menuButton, 0, 0);
        }

        //??????
        for (Car car : cars) {
            car.paint(graphicsContext);
        }

        //?????????
        if (!Shove.move) {
            shove.paint(graphicsContext);
        }

        //?????????????????????
        graphicsContext.setFont(new Font("????????????", 20));
        graphicsContext.fillText(String.valueOf(money), 80, 78);
        graphicsContext.setFont(new Font("????????????", 10));
        for (int i = 0; i < 4; i++) {
            graphicsContext.fillText(String.valueOf(plantCards.get(i).getPrice()), CARD_X + i * CARD_SPACE, 75);
        }

        //?????????
        int index = 0;
        for (Plant plantCard : plantCards) {
            if (money >= plantCard.getPrice()) {
                cardPaint(plantCard.getCards(), graphicsContext, index++, true);
            } else {
                cardPaint(plantCard.getCards(), graphicsContext, index++, false);
            }
        }

        //???????????????
        Collection<Plant> values = plants.values();
        try {
            for (Plant value : values) {
                value.paint(graphicsContext);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("??????????????????????????????");
        }

        //?????????
        Iterator<Zombie> iterator = zombies.iterator();
        while (iterator.hasNext()) {
            Zombie zombie = iterator.next();
            zombie.paint(graphicsContext);
            if (!zombie.live) {
                iterator.remove();
                num += 1;
            }
        }

        //?????????
        for (Bullet bullet : bullets) {
            bullet.paint(graphicsContext);
        }

        //?????????
        for (Sun sun : suns) {
            if (sun.live) {
                sun.paint(graphicsContext);
            }
        }

        //?????????????????????????????????
        for (Plant plantCard : plantCards) {
            if (plantCard.getMove()) {
                graphicsContext.drawImage(plantCard.getCards()[0], x - plantCard.getCards()[0].getWidth() / 2, y - plantCard.getCards()[0].getHeight() / 2);
            }
        }
        if (Shove.move) {
            graphicsContext.drawImage(Shove.image, x - Shove.image.getWidth() / 2, y - Shove.image.getHeight() / 2);
        }

        //?????????????????????
        stopTime = LocalDateTime.now();
        if (startTime.until(stopTime, ChronoUnit.SECONDS) == interval) {
            interval++;
            System.out.println(interval);
            //???9???????????????????????????
            if (interval % 9 == 0) {
                suns.add(new Sun((int) (Math.random() * 680) + 70, (int) (Math.random() * 500) + 50, 50.0, 50));
            }
            if (interval >= 20) {
                //20???????????????????????????
                if (num >= 0 && num <= 5) { //???????????????5?????????????????????????????????????????????
                    if ((interval % 7 == 0)) {
                        addZombie(0);
                    }
                } else if (num > 5 && num <= 15) {  //????????????(5,25]???????????????????????????????????????(??????????????????
                    if (interval % 5 == 0) {
                        addZombie((int) (Math.random() * 2));
                    }
                } else if (num > 15 && num <= 30) {
                    if (interval % 5 == 0) {    //????????????(15,30]???????????????????????????????????????(????????????????????????????????????
                        addZombie((int) (Math.random() * 4));
                        addZombie((int) (Math.random() * 4));
                    }
                } else if (num > 30) {      //???????????????30???????????????????????????
                    addZombie((int) (Math.random() * 4));
                }
            }
        }
    }

    //??????????????????
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

    //??????????????????
    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long now) {
            if (running && now - lastUpdate > 20_000_000) {
                paint();
                lastUpdate = now;
            }
            if (!running) {
                //???????????????
                if (selectRestart) {
                    graphicsContext.drawImage(restart, 0, 0);
                } else if (selectMainMenu) {
                    graphicsContext.drawImage(mainMenu, 0, 0);
                } else if (selectGoOn) {
                    graphicsContext.drawImage(goOn,0,0);
                } else {
                    graphicsContext.drawImage(menu, 0, 0);
                }
            }
        }
    }

    //????????????????????????
    private void cardPaint(Image[] cards, GraphicsContext graphicsContext, int index, boolean visible) {
        if (visible) {
            graphicsContext.drawImage(cards[0], StartAdventure.CARD_X + index * StartAdventure.CARD_SPACE, StartAdventure.CARD_Y, cards[0].getWidth(), cards[0].getHeight());
        } else {
            graphicsContext.drawImage(cards[1], StartAdventure.CARD_X + index * StartAdventure.CARD_SPACE, StartAdventure.CARD_Y, cards[0].getWidth(), cards[0].getHeight());
        }
    }

    //?????????????????????
    private class MouseMove implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            x = event.getX();
            y = event.getY();
            if (GameUtil.ifRect(x, y, 670, 5, 785, 45)) {
                menuLight = true;
            } else {
                menuLight = false;
            }
            if (!running) {
                if (GameUtil.ifRect(x, y, 280, 300, 500, 350)) {
                    selectMainMenu = true;
                }else {
                    selectMainMenu = false;
                }
                if (GameUtil.ifRect(x, y, 280, 355, 500, 400)) {
                    selectRestart = true;
                } else {
                    selectRestart = false;
                }
                if (GameUtil.ifRect(x, y, 280, 430, 500, 500)) {
                    selectGoOn = true;
                } else {
                    selectGoOn = false;
                }
            }
        }
    }

    private class MouseClick implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if (running) {
                //????????????
                if (!game) {
                    Director.getInstance().toMenu();
                }
                //?????????????????????????????????????????????
                if (menuLight) {
                    running = false;
                }
                //????????????????????????
                for (Sun sun : suns) {
                    if (sun.live && GameUtil.ifRect(event.getX(), event.getY(), sun.getX(), sun.getY(), sun.getX() + sun.getWidth(), sun.getY() + sun.getHeight())) {
                        GameUtil.soundPlay("sounds/yangguang.wav").play();
                        sun.move = true;
                        sun.setSpeedx((sun.getX() - 70) / 30);
                        sun.setSpeedy((sun.getY() - 11) / 30);
                    }
                }
                //?????????????????????????????????????????????
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
                } else {    //???????????????????????????
                    if (Shove.move) {
                        for (Glass glass : glasses) {
                            if (glass.live && GameUtil.ifRect(event.getX(), event.getY(), glass.getX(),
                                    glass.getY(), glass.getX() + glass.getWidth(), glass.getY() + glass.getHeight())) {
                                glass.live = false;
                                plants.remove(glass);
                            }
                        }//???????????????????????????????????????????????????????????????????????????
                        Shove.move = false;
                        move = false;
                    }
                    //?????????????????????
                    for (Plant plantCard : plantCards) {
                        if (plantCard.getMove()) {
                            for (Glass glass : glasses) {
                                //????????????
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
                            }//?????????????????????????????????????????????
                            plantCard.setMove(false);
                            move = false;
                        }
                    }
                }
            } else {
                if (selectRestart) {
                    Director.getInstance().toStartAdventure();
                }
                if (selectMainMenu) {
                    Director.getInstance().toMenu();
                }
                if (selectGoOn){
                    startTime = LocalDateTime.now().minus(interval-1,ChronoUnit.SECONDS);
                    selectGoOn = false;
                    running = true;
                }
            }
        }
    }
}
