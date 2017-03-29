/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;

public class Communicator {

    public static OutputStreamWriter outStream;

    public static void startCommunicator(int port) {

        String machine = "localhost";

        try {
            InetAddress addr = InetAddress.getByName(machine);
            SocketAddress sockaddr = new InetSocketAddress(addr, port);
            final Socket sock = new Socket();
            sock.connect(sockaddr);
            outStream = new OutputStreamWriter(sock.getOutputStream());
        } catch (SocketTimeoutException e) {
            System.out.println("Connection timed out");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToServer(String s) {
        try {
            outStream.write(s + "\n");
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
