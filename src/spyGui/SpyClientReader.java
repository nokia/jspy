/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SpyClientReader implements Runnable {
    public Socket client;

    public SpyClientReader(Socket sock) {
        super();
        client = sock;
    }

    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            out.write("connected\n");
            SpyGuiPane.textPane.setText("Select Window and press \"CTRL+ALT+R\" to Re-Index");
            out.flush();
            BufferedReader in;
            InputStreamReader inStream = new InputStreamReader(client.getInputStream());
            in = new BufferedReader(inStream);
            String tempText;

            while (true) {
                while ((tempText = in.readLine()) != null) {
                    out.write("test from server\n");
                    out.flush();
                    SpyGuiPane.printText("Clear");
                    System.out.println("From Client: " + tempText);
                    String splitText[] = tempText.split(",");
                    for (int i = 0; i < splitText.length; i++) {
                        if (!(splitText[i].equals(""))) {
                            if (i == 4) {
                                int index = splitText[4].indexOf("[");
                                String className = splitText[4].substring(0, index + 1).replace("[", "");
                                String name = splitText[4].substring(index + 1, splitText[4].length());
                                SpyGuiPane.printText("Name- " + name + "\n");
                                SpyGuiPane.printText("Class Name- " + className + "\n");
                                SpyGuiPane.printText("-----------------------------------\n");
                            } else if (i == 5) {
                                SpyGuiPane.printText("X: " + splitText[5] + "\n");
                            } else if (i == 6) {
                                SpyGuiPane.printText("Y: " + splitText[6] + "\n");
                            } else if (i == 7) {
                                SpyGuiPane.printText("Size: " + splitText[7] + "\n");
                            } else {
                                String temp = splitText[i].replace("[", "\n");
                                temp = temp.replace("]", "\n");
                                SpyGuiPane.printText(temp + "\n");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR3: " + e.getMessage());
            SpyGuiPane.printText("Client Disconnected.\n");
        }

    }

}


