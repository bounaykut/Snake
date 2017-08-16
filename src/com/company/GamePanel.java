package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

enum Direction {RIGHT, LEFT, UP, DOWN};

public class GamePanel extends JPanel {

    private Snake snake;
    private Bait bait;


    static boolean inGame = true;
    static boolean pause = false;

    static int WIDTH = 800;
    static int LENGTH = 800;
    static int ROWS_COUNT = 40;
    static int COLS_COUNT = 40;
    static int UNIT_SIZE_X = WIDTH / COLS_COUNT;
    static int UNIT_SIZE_Y = LENGTH / ROWS_COUNT;

    static int FPS = 60;
    static int SPEED_FACTOR = 5;


    public GamePanel() {

        //create the snake
        snake = new Snake();

        //initialize and randomize bait position
        randomBait();

        //attach the keyboard listener to the frame
        addKeyListener(snake.dirListener);
        setFocusable(true);


        /*TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(tasknew,300,200);*/

        //play();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (pause) {

            g.setColor(Color.RED);
            g.drawString("PAUSE", getWidth() / 2, getHeight() / 2);
        }

        g.setColor(Color.BLACK);
        //draws the space
        for (int i = 0; i <= COLS_COUNT; i++) {
            g.drawLine(UNIT_SIZE_X * i, 0, UNIT_SIZE_X * i, LENGTH);
        }

        for (int i = 0; i <= ROWS_COUNT; i++) {
            g.drawLine(0, UNIT_SIZE_Y * i, WIDTH, UNIT_SIZE_Y * i);
        }

        //draws the snake's head
        g.setColor(Color.RED);
        g.fillRect(snake.nodes.get(0).x, snake.nodes.get(0).y, UNIT_SIZE_X, UNIT_SIZE_Y);

        //draws the rest of the snake
        g.setColor(Color.BLACK);
        for (int i = 1; i < snake.nodes.size(); i++) {
            g.fillRect(snake.nodes.get(i).x, snake.nodes.get(i).y, UNIT_SIZE_X, UNIT_SIZE_Y);
        }


        //draws the bait
        g.setColor(Color.YELLOW);
        g.fillRect(bait.point.x, bait.point.y, UNIT_SIZE_X, UNIT_SIZE_Y);


        if (System.getProperty("os.name").equalsIgnoreCase("LINUX")) Toolkit.getDefaultToolkit().sync();


    }

    public void play() {

        int count = 0;
        long start = System.nanoTime(), diff = 0, wait = 1000 / FPS; //default 15 updates per 1000 msec(1 sec)
        while (inGame) {

            if(pause){
                repaint();
            }else{

                if (count < FPS / SPEED_FACTOR) {
                    count++;
                } else {
                    //feed, move and collision checks
                    feed();
                    snake.move();
                    if (snake.collision()) inGame = false;
                    count = 0;
                }

                repaint();

                diff = System.nanoTime() - start;
                diff /= 1000000;
                //System.out.println(wait-diff);
                if (diff < wait) {
                    try {
                        Thread.sleep(wait - diff);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                start = System.nanoTime();

            }

        }

    }

    public void feed() {

        //check bait collision
        Rectangle rec1 = new Rectangle(snake.nodes.get(0).x, snake.nodes.get(0).y, UNIT_SIZE_X, UNIT_SIZE_Y);
        Rectangle rec2 = new Rectangle(bait.point.x, bait.point.y, UNIT_SIZE_X, UNIT_SIZE_Y);
        if (rec1.contains(rec2)) {
            snake.grow();
            randomBait();
        }
    }


    public void randomBait() {

        Random random = new Random();

        int x = random.nextInt(COLS_COUNT);
        int y = random.nextInt(ROWS_COUNT);

        bait = new Bait(new Point(x * UNIT_SIZE_X, y * UNIT_SIZE_Y));

    }


    public boolean getInGame() {
        return inGame;
    }

    public Snake getSnake() {
        return snake;
    }
}
