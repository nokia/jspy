/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class KeyboardListener implements KeyEventDispatcher {
    WindowTracker winTrack;
    boolean altPressed, ctrlPressed = false;

    public KeyboardListener(WindowTracker winTrack) {
        this.winTrack = winTrack;
    }

    public boolean dispatchKeyEvent(KeyEvent arg0) {
        if (arg0.getID() == KeyEvent.KEY_PRESSED) {
            if (arg0.getKeyCode() == 17) {
                altPressed = true;
            } else if (arg0.getKeyCode() == 18) {
                ctrlPressed = true;
            } else {
                if (altPressed && ctrlPressed && arg0.getKeyCode() == 82) {
                    Communicator.writeToServer("Pressed ctrl+alt+r");
                    System.out.println("Re-Indexing the Components");
                    Thread enuTh = new Thread(new CompEnum(winTrack.activeWindow));
                    enuTh.start();
                }
            }

        } else if (arg0.getID() == KeyEvent.KEY_RELEASED) {
            if (arg0.getKeyCode() == 17) {
                altPressed = false;
            } else if (arg0.getKeyCode() == 18) {
                ctrlPressed = false;
            }

        }

        return false;
    }

}
