package org.example;

import java.awt.event.*;
public class Player implements KeyListener{
    private int heart;
    private int x;
    private int y;

    public Player(int heart, int x, int y){
        this.heart = heart;
        this.x = x;
        this.y = y;

    }


    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()){
            case 38: //tasto freccia su
                y+=5;
                break;
            case 40: //tasto freccia giù
                y-=5;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
