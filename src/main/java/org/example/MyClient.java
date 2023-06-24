package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) {
        Player thisPlayer = new Player(3, 0, 0);
        Player otherPlayer = new Player(3, 0, 0);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(thisPlayer, otherPlayer);
            }
        });
    }


    private static void createAndShowGUI(Player thisPlayer, Player otherPlayer) {
        CustomFrame f = new CustomFrame(thisPlayer, otherPlayer);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1220, 686);
        f.setResizable(false);
        f.setVisible(true);
        clientMain(thisPlayer, otherPlayer, f);
    }

    static void clientMain(Player thisPlayer, Player otherPlayer, CustomFrame f) {

        String hostName = "127.0.0.1";
        int portNumber = 1234;
        Socket echoSocket = null;

        try {
            echoSocket = new Socket(hostName, portNumber);
        } catch (IOException e) {
            System.out.println("cannot reach server " + e);
        }

        PrintWriter out = null;

        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("YOU MUST CONNECT THE SERVER!!");
        }

        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (Exception e) {
            System.out.println("cannot allocate bufferedreader");
        }


        Movimento movements = new Movimento(thisPlayer, otherPlayer, f, out);
        movements.start();

        thisPlayer.setY(250);
        thisPlayer.setX(0);
        otherPlayer.setY(250);
        otherPlayer.setX(1220-100);

        f.setNotReady(false);
        f.repaint();

        try {
            String paramIniziali = in.readLine();
            /*SPACCHETTAMENTO JSON NELLE COORDINATE*/
//                thisPlayer.setY();
//                thisPlayer.setX();
//                otherPlayer.setX();
//                otherPlayer.setY();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (true) {


        }


    }


}
