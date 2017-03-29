/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import javax.swing.*;
import java.awt.*;


public class SpyGuiPane extends JPanel {

    private static final long serialVersionUID = 1L;

    public static JTextArea textArea;
    public static JScrollPane scrollPane;
    public static JTextPane textPane = new JTextPane();

    public SpyGuiPane() {

        setLayout(new BorderLayout());

        textPane.setText("Launch example-File->Launch->javaws some.jnlp");
        textPane.setBackground(new Color(126, 192, 255));
        textPane.setEditable(false);
        textArea = new JTextArea("JSpy Initialized...", 25, 25);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 440));

        Font font = new Font("Ariel", Font.BOLD, 12);
        textArea.setFont(font);
        setPreferredSize(new Dimension(300, 440));
        Color color = Color.BLACK;

        textArea.setForeground(color);
        textArea.setBackground(new Color(170, 190, 220));
        textArea.setAutoscrolls(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //scrollPane
        this.add(textPane, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void printText(String s) {
        if (s.equals("Clear")) {
            textArea.setText("");
        } else {
            textArea.append(s);
        }
        scrollPane.validate();
    }

}
