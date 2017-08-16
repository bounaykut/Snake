package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DirectionListener implements KeyListener {

    private Direction direction;


    public DirectionListener(){
        direction = Direction.RIGHT;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_W && direction != Direction.DOWN){
            direction = Direction.UP;
        }else if(e.getKeyCode() == KeyEvent.VK_S && direction != Direction.UP){
            direction = Direction.DOWN;
        }else if(e.getKeyCode() == KeyEvent.VK_A && direction != Direction.RIGHT){
            direction = Direction.LEFT;
        }else if(e.getKeyCode() == KeyEvent.VK_D && direction != Direction.LEFT){
            direction = Direction.RIGHT;
        }else if(e.getKeyCode() == KeyEvent.VK_P){
            GamePanel.pause = (GamePanel.pause == true) ? false : true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }


}
