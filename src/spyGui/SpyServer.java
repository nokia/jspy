/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SpyServer implements Runnable {

    public static int serverPort = 0;

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(serverPort);
            serverPort = ss.getLocalPort();
            System.out.println("Server started...");

            while (true) {
                Socket soc = ss.accept();
                Thread cliTh = new Thread(new SpyClientReader(soc));
                cliTh.start();
            }
        } catch (IOException e) {
            System.out.println("I/O error " + e);
        }
    }
}
