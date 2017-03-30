/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class CmdInputDlg extends JDialog {

    private static final long serialVersionUID = 1L;

    private CommandComboBox commandCombo = new CommandComboBox();
    private JButton btLaunch = new JButton("Run");
    private JLabel label1 = new JLabel("Cmd to launch", JLabel.LEFT);

    public CmdInputDlg() {
        setName("execCmd");
        setTitle("Execute Command");
        setIconImage(new ImageIcon(getClass().getResource("spy.png")).getImage());

        Container contentPane = this.getContentPane();
        FlowLayout layout = new FlowLayout();
        contentPane.setLayout(layout);

        setSize(200, 300);

        setupComboBox();
        contentPane.add(label1);
        contentPane.add(commandCombo);
        contentPane.add(btLaunch);

        setupListeners();

        pack();
        setResizable(false);
    }

    private void setupListeners() {
        ActionListener btnAct = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (arg0.getActionCommand().equals("Run")) {
                    executeCmd();
                }
            }
        };

        KeyListener kl = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    executeCmd();
                }
            }
        };

        btLaunch.addActionListener(btnAct);
        btLaunch.addKeyListener(kl);
        commandCombo.getEditor().getEditorComponent().addKeyListener(kl);
    }

    private void setupComboBox() {
        commandCombo.setEditable(true);
        commandCombo.setPreferredSize(new Dimension(200, 20));
    }

    public void executeCmd() {
        String cmdStr = ((JTextComponent) (commandCombo.getEditor().getEditorComponent())).getText();

        if (cmdStr != null && !cmdStr.trim().equals("")) {

            System.out.println("Executing command :" + cmdStr);
            List<String> arguments = new ArrayList<String>();
            arguments.addAll(Arrays.asList(cmdStr.trim().split("\\s+")));
            ProcessBuilder pb = new ProcessBuilder(arguments.toArray(new String[arguments.size()]));
            Map<String, String> env = pb.environment();

            URI jSpyJarUri = null;
            try {
                jSpyJarUri = spyAgent.AgentPreMain.class.getProtectionDomain()
                        .getCodeSource().getLocation().toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            String jSpyJarPath = new File(jSpyJarUri).getPath();

            env.put("JAVA_TOOL_OPTIONS", "-javaagent:\"" + jSpyJarPath + "\"" + "=" + Integer.toString(SpyServer.serverPort));

            pb.redirectErrorStream(true);
            try {
                Process p = pb.start();
                Thread readProcTh = new Thread(new ProcessReader(p));
                readProcTh.start();
                commandCombo.addCommand(cmdStr);
                setVisible(false);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Could not execute command", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}