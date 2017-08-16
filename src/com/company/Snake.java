package com.company;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    LinkedList<Point> nodes;
    DirectionListener dirListener;

    public Snake(){

        //create the snake of length 2 units
        nodes = new LinkedList<>();

        nodes.add(new Point(5*GamePanel.UNIT_SIZE_X,5*GamePanel.UNIT_SIZE_Y));
        nodes.add(new Point(6*GamePanel.UNIT_SIZE_X,5*GamePanel.UNIT_SIZE_Y));

        dirListener = new DirectionListener();
    }


    public void move(){

        //shifts snake's units
        for(int i = nodes.size()-1; i > 0 ; i--){
            Point p1 = nodes.get(i-1);
            Point p2 = nodes.get(i);
            p2.x = p1.x;
            p2.y = p1.y;
        }

        //determine the new position of the head
        if(dirListener.getDirection() == Direction.UP){
            nodes.get(0).y-=GamePanel.UNIT_SIZE_Y;
        }else if(dirListener.getDirection() == Direction.DOWN){
            nodes.get(0).y+=GamePanel.UNIT_SIZE_Y;
        }else if(dirListener.getDirection() == Direction.RIGHT){
            nodes.get(0).x+=GamePanel.UNIT_SIZE_X;
        }else if(dirListener.getDirection() == Direction.LEFT){
            nodes.get(0).x-=GamePanel.UNIT_SIZE_X;
        }




    }




    public boolean collision(){
        //check wall collision
        boolean collideWall = wallCollision();

        //check unit collision
        boolean collideSelf = selfCollision();

        //System.out.print(collideSelf);

        if(collideWall || collideSelf) return true;

        else return false;
    }


    public void grow(){

        int lastIndex = nodes.size() - 1;

        if(dirListener.getDirection() == Direction.UP){
            nodes.addLast(new Point(nodes.get(lastIndex).x, nodes.get(lastIndex).y));
        }else if(dirListener.getDirection() == Direction.DOWN){
            nodes.addLast(new Point(nodes.get(lastIndex).x, nodes.get(lastIndex).y));
        }else if(dirListener.getDirection() == Direction.RIGHT){
            nodes.addLast(new Point(nodes.get(lastIndex).x, nodes.get(lastIndex).y));
        }else{
            nodes.addLast(new Point(nodes.get(lastIndex).x, nodes.get(lastIndex).y));
        }
    }

    public boolean wallCollision(){

        for(Point p : nodes){
            if(p.x < 0 || p.x+GamePanel.UNIT_SIZE_X > GamePanel.WIDTH) return true;
            if(p.y < 0 || p.y+GamePanel.UNIT_SIZE_Y > GamePanel.LENGTH) return true;
        }

        return false;
    }

    public boolean selfCollision(){

        for(Point p1 : nodes){
            for(Point p2 : nodes){

                if(p1 != p2){
                    Rectangle rec1 = new Rectangle(p1.x,p1.y,GamePanel.UNIT_SIZE_X,GamePanel.UNIT_SIZE_Y);
                    Rectangle rec2 = new Rectangle(p2.x,p2.y,GamePanel.UNIT_SIZE_X,GamePanel.UNIT_SIZE_Y);

                    if(rec1.contains(rec2)){
                        return true;
                    }
                }


            }
        }

        return false;
    }


}
