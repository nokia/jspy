/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessReader implements Runnable {
    BufferedReader stdInput;
    BufferedReader stdErr;

    public ProcessReader(Process p) {
        stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

    public void run() {

        String s = null;
        try {
            while ((s = stdInput.readLine()) != null || (s = stdErr.readLine()) != null) {
                System.out.println("CMD Out: " + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Exiting process read.");
            if (stdInput != null) {
                try {
                    stdInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stdErr != null) {
                try {
                    stdErr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
