/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


public class SpyMain {

    public static void main(String args[]) {
        Thread serverTH = new Thread(new SpyServer());
        serverTH.start();
        new SpyFrame();

    }

}
