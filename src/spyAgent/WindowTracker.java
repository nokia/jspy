/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class WindowTracker implements WindowFocusListener {

    public Window activeWindow;

    public void windowGainedFocus(WindowEvent arg0) {
        activeWindow = (Window) arg0.getSource();
    }

    public void windowLostFocus(WindowEvent arg0) {

    }

}
