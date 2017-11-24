/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class SpyFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private CmdInputDlg launchDlg = new CmdInputDlg();
    private SpyGuiPane displayPane = new SpyGuiPane();
    private JMenuBar menuBar = new JMenuBar();

    public SpyFrame() {
        super("JSpy");
        setIconImage(new ImageIcon(getClass().getResource("spy.png")).getImage());
        setupMenuBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        setContentPane(displayPane);

        JFrame.setDefaultLookAndFeelDecorated(true);

        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        pack();
        setVisible(true);
    }

    private void setupMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem launch = new JMenuItem("Launch");
        launch.setMnemonic(KeyEvent.VK_L);
        JMenuItem quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        JMenu preferenceMenu = new JMenu("Preference");
        preferenceMenu.setMnemonic(KeyEvent.VK_P);
        JCheckBoxMenuItem alwaysOnTop = new JCheckBoxMenuItem("Always on top");
        alwaysOnTop.setMnemonic(KeyEvent.VK_A);
        alwaysOnTop.setSelected(true);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        JMenuItem shortcuts = new JMenuItem("Shortcuts");
        shortcuts.setMnemonic(KeyEvent.VK_S);

        fileMenu.add(launch);
        fileMenu.add(quit);
        preferenceMenu.add(alwaysOnTop);
        helpMenu.add(about);
        helpMenu.add(shortcuts);

        ActionListener menuAct = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Action " + arg0);
                if (arg0.getActionCommand().equals("Quit")) {
                    System.exit(0);
                } else if (arg0.getActionCommand().equals("About")) {
                    new AboutDialog(SpyFrame.this);
                } else if (arg0.getActionCommand().equals("Launch")) {
                    launchDlg.pack();
                    launchDlg.setLocationRelativeTo(SpyFrame.this);
                    launchDlg.setAlwaysOnTop(true);
                    launchDlg.setVisible(true);
                } else if (arg0.getActionCommand().equals("Shortcuts")) {
                    new ShortcutsDialog(SpyFrame.this);
                } else if (arg0.getActionCommand().equals("Always on top")) {
                    if(alwaysOnTop.isSelected()) {
                        alwaysOnTop.setSelected(true);
                        setAlwaysOnTop(true);
                    } else {
                        alwaysOnTop.setSelected(false);
                        setAlwaysOnTop(false);
                    }
                }
            }
        };

        quit.addActionListener(menuAct);
        about.addActionListener(menuAct);
        launch.addActionListener(menuAct);
        shortcuts.addActionListener(menuAct);
        alwaysOnTop.addActionListener(menuAct);

        menuBar.add(fileMenu);
        menuBar.add(preferenceMenu);
        menuBar.add(helpMenu);
    }

}
