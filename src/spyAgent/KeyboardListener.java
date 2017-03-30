/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import common.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardListener implements KeyEventDispatcher {
    public static String highlightedComponentName = "";

    private WindowTracker winTrack;
    private boolean altPressed = false, ctrlPressed = false;

    public KeyboardListener(WindowTracker winTrack) {
        this.winTrack = winTrack;
    }

    public boolean dispatchKeyEvent(KeyEvent arg0) {
        if (arg0.getID() == KeyEvent.KEY_PRESSED) {
            if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
                altPressed = true;
            } else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
                ctrlPressed = true;
            } else if (altPressed && ctrlPressed && arg0.getKeyCode() == KeyEvent.VK_R) {
                Communicator.writeToServer("Pressed ctrl+alt+R");
                System.out.println("Re-Indexing the Components");
                Thread enuTh = new Thread(new CompEnum(winTrack.activeWindow));
                enuTh.start();
            } else if (ctrlPressed && altPressed && arg0.getKeyCode() == KeyEvent.VK_C) {
                Utilities.copyStringToClipboard(highlightedComponentName);
            } else if (ctrlPressed && altPressed && arg0.getKeyCode() == KeyEvent.VK_S) {
                CompMouseListner.setActive = !CompMouseListner.setActive;
            }

        } else if (arg0.getID() == KeyEvent.KEY_RELEASED) {
            if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
                altPressed = false;
            } else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
                ctrlPressed = false;
            }
        }
        return false;
    }

}