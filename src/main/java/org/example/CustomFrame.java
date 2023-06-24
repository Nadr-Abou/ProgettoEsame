package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CustomFrame extends JFrame implements Runnable {
    private Thread thread;

    Player leftPlayer;
    Player rightPlayer;



    public CustomFrame(Player thisPlayer) throws HeadlessException {
        this.addKeyListener(thisPlayer);
        this.thread = new Thread(this);
        thread.start();
    }


    public void setLeftPlayer(Player leftPlayer) {
        this.leftPlayer = leftPlayer;
    }

    public void setRightPlayer(Player rightPlayer) {
        this.rightPlayer = rightPlayer;
    }

    public void paint(Graphics g) {

        super.paint(g);

        if (leftPlayer == null || rightPlayer == null) {
            blockDrawImage(g);
            return;
        }



        int w = this.getWidth();
        int h = this.getHeight();


        backgroundDrawImage(g);


        for (int i = 1; i <= leftPlayer.getHeart(); i++) {
            heartDrawImage(g, 60 * i);
        }
        for (int i = 1; i <= rightPlayer.getHeart(); i++) {
            heartDrawImage(g, (w - 50) - (60 * i));
        }

        rightTankImage(g);
        leftTankImage(g);

    }

    private void blockDrawImage(Graphics g) {

        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("initialIMG.png");
        BufferedImage img = null;

        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private void backgroundDrawImage(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("notebook.jpg");
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private void heartDrawImage(Graphics g, int x) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("Heart.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, x, 50, this.getWidth() / 24, this.getHeight() / 16, null);
    }

    private void rightTankImage(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("rightTank.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, rightPlayer.getX(), rightPlayer.getY(), 100, 100, null);
    }

    private void leftTankImage(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("LeftTank.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, leftPlayer.getX(), leftPlayer.getY(), 100, 100, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //this.repaint();
        }
    }



}
