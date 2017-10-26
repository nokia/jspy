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

    public static JScrollPane scrollPane;
    public static JTextPane northTextPane = new JTextPane();
    public static JTextPane southTextPane = new JTextPane();
    public static JTable table;
    private static DefaultTableModel tableModel;

    public SpyGuiPane() {

        setLayout(new BorderLayout());

        northTextPane.setText("Launch example-File->Launch->javaws some.jnlp");
        northTextPane.setBackground(new Color(100, 149, 237));
        northTextPane.setEditable(false);

        southTextPane.setText("JSpy Initialized...");
        southTextPane.setBackground(new Color(100, 149, 237));
        southTextPane.setEditable(false);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Name");
        tableModel.addColumn("Value");
        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? new Color(176, 196, 222) : new Color(230, 230, 250));
                return c;
            }
        };

        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 440));

        Font dataFont = new Font("Ariel", Font.BOLD, 12);
        table.setFont(dataFont);

        Font textFont = new Font("Ariel", Font.ITALIC, 12);
        northTextPane.setFont(textFont);
        southTextPane.setFont(textFont);

        setPreferredSize(new Dimension(300, 440));
        Color color = Color.BLACK;

        table.setForeground(color);
        table.setAutoscrolls(true);

        //scrollPane
        this.add(northTextPane, BorderLayout.NORTH);
        this.add(southTextPane, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    public static void printText(String s) {
        if (s.equals("Clear")) {
            southTextPane.setText("");
            tableModel.setRowCount(0);
        }
        if (s.contains("=") || s.contains(":") || s.contains("-")) {
            String splitText[] = s.split("[=:-]");
            tableModel.addRow(splitText);
        } else {
            String splitText[] = s.split("\n");
            southTextPane.setText(splitText[0]);
        }
        if (s.contains("Client Disconnected."))
            tableModel.setRowCount(0);

        scrollPane.validate();
        table.setModel(tableModel);
    }

}
