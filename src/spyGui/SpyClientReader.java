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
        client = sock;
    }

    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            out.write("connected\n");
            SpyGuiPane.topTextPane.setText("Select Window and press \"CTRL+ALT+R\" to Re-Index");
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
                                SpyGuiPane.printText("Name- " + name);
                                SpyGuiPane.printText("Class Name- " + className);
                            } else if (i == 5) {
                                SpyGuiPane.printText("X: " + splitText[5]);
                            } else if (i == 6) {
                                SpyGuiPane.printText("Y: " + splitText[6]);
                            } else if (i == 7) {
                                SpyGuiPane.printText("Size: " + splitText[7]);
                            } else {
                                String temp = splitText[i].replace("[", "\n");
                                //temp = temp.replace("]", "");
                                // skip empty properties
                                // empty properties end with "="
                                String s[] = temp.split("\n");
                                for(int j=0; j<s.length; j++) {
                                    String tempLastCharacter = s[j].substring(s[j].length() - 1, s[j].length());
                                    if ((!tempLastCharacter.equals("="))){
                                        if(tempLastCharacter.equals("]")){
                                            String lastTempCharacter =  s[j].substring(s[j].length() - 2, s[j].length() -1);
                                            if((!lastTempCharacter.equals("="))) {
                                                SpyGuiPane.printText(s[j].substring(0, s[j].length() - 1));
                                                SpyGuiPane.printText("new line");
                                            }
                                        }else {
                                            SpyGuiPane.printText(s[j]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR3: " + e.getMessage());
            SpyGuiPane.printText("Client Disconnected.");
        }

    }

}


