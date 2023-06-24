package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CustomFrame extends JFrame implements Runnable{
    private int NOfRects;
    private Thread thread;
    private String frase = "";
    public CustomFrame(int NOfRects) throws HeadlessException {
        this.NOfRects = NOfRects;
        this.thread = new Thread(this);
        thread.start();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Color red = Color.red;
        g.setColor(red);



        int w = this.getWidth();
        int h = this.getHeight();

        int xc = w/2;
        int yc = h/2;

        int rectwidth = 50;
        int rectheight = 100;


        backgroundDrawImage(g);




        /*for (int i=0; i<NOfRects; i++) {
            rectwidth +=30;
            rectheight +=30;

            int x = xc - rectwidth/2;
            int y = yc - rectheight/2;

            g.drawRect(x, y, rectwidth, rectheight);
        }*/

    }




    private void backgroundDrawImage(Graphics g){
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("notebook.jpg");
        BufferedImage img= null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), null);
    }


    private void myDrawImage(Graphics g){
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("LeftTank.png");
        BufferedImage img= null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 10, 150, 150,150, null);
    }

    @Override
    public void run() {
        while(true) {

            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }

    public void setFrase(String frase) {
        this.frase = frase;
        this.repaint();
    }
}
