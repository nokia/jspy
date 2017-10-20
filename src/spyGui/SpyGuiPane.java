/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class SpyGuiPane extends JPanel {

    private static final long serialVersionUID = 1L;

    public static JTextArea textArea;
    public static JScrollPane scrollPane;
    public static JTextPane textPane = new JTextPane();
    public static JTable table;
    private static DefaultTableModel tableModel;

    public SpyGuiPane() {

        setLayout(new BorderLayout());

        textPane.setText("Launch example-File->Launch->javaws some.jnlp");
        textPane.setBackground(new Color(100,149,237));
        textPane.setEditable(false);
        textArea = new JTextArea("JSpy Initialized...", 25, 25);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Name");
        tableModel.addColumn("Value");
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? new Color(176, 196, 222) : new Color(230, 230, 250));
                return c;
            }
        };

        scrollPane = new JScrollPane(textArea);
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 440));

        Font font = new Font("Ariel", Font.BOLD, 12);
        textArea.setFont(font);
        table.setFont(font);
        setPreferredSize(new Dimension(300, 440));
        Color color = Color.BLACK;

        textArea.setForeground(color);
        textArea.setBackground(new Color(170, 190, 220));
        textArea.setAutoscrolls(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        table.setForeground(color);
        table.setAutoscrolls(true);

        //scrollPane
        this.add(textPane, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    public static void printText(String s) {
        table.setVisible(false);
        if (s.equals("Clear")) {
            tableModel.setRowCount(0);
        } else if(s.equals("Client Connected")) {
            table.setVisible(false);
            textArea.append("s");
        } else if(s.equals("Finishing Indexing Components")) {
            table.setVisible(true);
        } else if(s.equals("Client Disconnected")) {
            table.setVisible(false);
            textArea.append("s");
        } else {
            table.setVisible(true);
            String splitText[] = s.split("[=:-]");
            tableModel.addRow(splitText);
        }
        scrollPane.validate();
        table.setModel(tableModel);
    }

}
