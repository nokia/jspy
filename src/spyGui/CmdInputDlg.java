/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class CmdInputDlg extends JDialog {
    public JDialog myDialog = this;
    private static final long serialVersionUID = 1L;
    JTextField textExec = new JTextField("", 15);

    public CmdInputDlg() {
        setName("execCmd");
        setTitle("Execute Command");

        Container contentPane = this.getContentPane();
        FlowLayout layout = new FlowLayout();
        contentPane.setLayout(layout);

        setSize(150, 300);
        JLabel label1 = new JLabel("Cmd to launch", JLabel.LEFT);

        JButton btLaunch = new JButton("Run");

        contentPane.add(label1);
        contentPane.add(textExec);
        contentPane.add(btLaunch);

        ActionListener btnAct = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (arg0.getActionCommand().equals("Run")) {
                    String cmdStr = textExec.getText();

                    if (!cmdStr.trim().equals("")) {


                        System.out.println("Executing command :" + cmdStr);
                        List<String> cmdList = new ArrayList<String>();
                        cmdList.addAll(Arrays.asList(cmdStr.trim().split("\\s+")));
                        ProcessBuilder pb = new ProcessBuilder(cmdList.toArray(new String[cmdList.size()]));
                        Map<String, String> env = pb.environment();

                        URI jarUri = null;
                        try {
                            jarUri = spyAgent.AgentPreMain.class.getProtectionDomain()
                                    .getCodeSource().getLocation().toURI();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        String jarPath = new java.io.File(jarUri).getPath();
                        env.put("JAVA_TOOL_OPTIONS", "-javaagent:\"" + jarPath + "\"");

                        pb.redirectErrorStream(true);
                        try {
                            Process p = pb.start();
                            Thread readProcTh = new Thread(new ProcessReader(p));
                            readProcTh.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myDialog.setVisible(false);

                    }

                }
            }

        };
        btLaunch.addActionListener(btnAct);
    }

}