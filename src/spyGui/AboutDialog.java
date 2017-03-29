/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


import common.Utilities;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AboutDialog(JFrame parent) {
        setName("About");
        setTitle("About JSpy");
        setForeground(Color.BLACK);

        setIconImage(new ImageIcon(getClass().getResource("spy.png")).getImage());

        Container pane = this.getContentPane();
        setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JLabel label1 = new JLabel("<html>"
                + "<center>JSpy for ROBOT Framework</center>"
                + "<center>Version 2.1.1</center>"
                + "</html>", JLabel.CENTER);
        JLabel label2 = new JLabel("Shows component details with an easy mouse move", JLabel.CENTER);
        JLabel label3 = new JLabel("<html>"
                + "<center>By Arulraj Samuel</center>"
                + "<center>Maintained by Robot Team in Nokia</center>"
                + "</html>", JLabel.CENTER);

        label1.setForeground(Color.RED);
        label2.setForeground(Color.RED);
        label3.setForeground(Color.RED);

        JLabel dumm1 = new JLabel("-------------------------", JLabel.CENTER);
        JLabel dumm2 = new JLabel("-----------------------", JLabel.CENTER);
        JLabel dumm3 = new JLabel("-------------------", JLabel.CENTER);

        pane.add(label1);
        pane.add(dumm1);
        pane.add(label2);
        pane.add(dumm2);
        pane.add(label3);
        pane.add(dumm3);

        Utilities.centerContainerComponents(pane);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);

        this.validate();
    }

}
