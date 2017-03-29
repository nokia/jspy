/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.*;
import java.lang.instrument.Instrumentation;

public class AgentPreMain {

    public static void premain(String agentArguments, Instrumentation inst) {

        Communicator.startCommunicator(Integer.parseInt(agentArguments));
        WinEventDispatchListener winDispatcher = new WinEventDispatchListener();

        Toolkit tk = Toolkit.getDefaultToolkit();
        tk.addAWTEventListener(winDispatcher, AWTEvent.WINDOW_EVENT_MASK);

        Communicator.writeToServer("Client connected");

    }
}
