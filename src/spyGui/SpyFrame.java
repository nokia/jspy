/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;


public class SpyFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    static public JScrollPane scrollPane;
    JFrame frame = new JFrame("JSpy");
    public SpyGuiPane dispalyPane = new SpyGuiPane();
    CmdInputDlg launchDlg = new CmdInputDlg();

    public SpyFrame() {
        super();
        frame.setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        JMenuItem launch = new JMenuItem("Launch");
        launch.setMnemonic(KeyEvent.VK_L);
        JMenuItem quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        JMenu menu1 = new JMenu("Help");
        menu1.setMnemonic(KeyEvent.VK_H);
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        menu.add(launch);
        menu.add(quit);
        menu1.add(about);

        ActionListener menuAct = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Action " + arg0);
                if (arg0.getActionCommand().equals("Quit")) {
                    System.exit(0);
                } else if (arg0.getActionCommand().equals("About")) {
                    AboutDialog aboutDial = new AboutDialog();
                    Component comp = (Component) arg0.getSource();
                    Container cont = comp.getParent();
                    aboutDial.setLocation(frame.getX() + 10, frame.getY() + 200);
                    aboutDial.setAlwaysOnTop(true);
                    aboutDial.pack();
                    aboutDial.setVisible(true);
                } else if (arg0.getActionCommand().equals("Launch")) {
                    launchDlg.setLocation(frame.getX() + 10, frame.getY() + 200);
                    launchDlg.setAlwaysOnTop(true);
                    launchDlg.pack();
                    launchDlg.setVisible(true);
                }


            }
        };
        quit.addActionListener(menuAct);
        about.addActionListener(menuAct);
        launch.addActionListener(menuAct);

        menuBar.add(menu);
        menuBar.add(menu1);
        scrollPane = new JScrollPane(dispalyPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.validate();
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setLocation(900, 200);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }


}
