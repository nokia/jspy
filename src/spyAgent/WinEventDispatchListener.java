/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WinEventDispatchListener implements AWTEventListener {
    WindowTracker winTrack = new WindowTracker();
    private static ArrayList<KeyboardFocusManager> activeFocusManagers = new ArrayList<>();

    public void eventDispatched(AWTEvent evt) {

        if (evt.getID() == WindowEvent.WINDOW_OPENED) {
            Component compon = (Component) evt.getSource();
            Thread enuTh = new Thread(new CompEnum(compon));
            enuTh.start();
            Window win = (Window) compon;
            winTrack.activeWindow = win;
            win.addWindowFocusListener(winTrack);
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            if (!activeFocusManagers.contains(manager)) {
                manager.addKeyEventDispatcher(new KeyboardListener(winTrack));
                activeFocusManagers.add(manager);
            }
        }
    }

}
