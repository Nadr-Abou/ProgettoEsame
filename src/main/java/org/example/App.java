package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {
    static CustomFrame f = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        clientMain();
    }


    private static void createAndShowGUI() {
        f = new CustomFrame(4);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250, 250);
        f.setVisible(true);
    }


    static void clientMain() {
        {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (IOException e) {
                System.out.println("cannot allocate bufferedreader");
            }

            while (true) {
                try {
                    String ingresso = in.readLine();
                    System.out.println(ingresso);
                    if (f != null)
                        f.setFrase(ingresso);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
