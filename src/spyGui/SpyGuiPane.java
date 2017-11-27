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
    public static JTextPane topTextPane = new JTextPane();
    public static JTextPane bottomTextPane = new JTextPane();
    public static JTable table;
    private static DefaultTableModel tableModel;

    public SpyGuiPane() {

        setLayout(new BorderLayout());
        Color backgroundColor = new Color(100, 149, 237);

        topTextPane.setText("Launch example-File->Launch->javaws some.jnlp");
        topTextPane.setBackground(backgroundColor);
        topTextPane.setEditable(false);

        bottomTextPane.setText("JSpy Initialized...");
        bottomTextPane.setBackground(backgroundColor);
        bottomTextPane.setEditable(false);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Name");
        tableModel.addColumn("Value");

        Color evenRowsColor = new Color(176, 196, 222);
        Color oddRowsColor = new Color(230, 230, 250);
        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? evenRowsColor : oddRowsColor);
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
        topTextPane.setFont(textFont);
        bottomTextPane.setFont(textFont);

        setPreferredSize(new Dimension(300, 440));
        Color color = Color.BLACK;

        table.setForeground(color);
        table.setAutoscrolls(true);

        //scrollPane
        this.add(topTextPane, BorderLayout.NORTH);
        this.add(bottomTextPane, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    private static boolean isProperty(String s){
        if (s.contains("=") || s.contains(":") || s.contains("-")){
            return true;
        }
        if(s.matches("\\d+(X\\d+)?(x\\d+)?")) {
            return true;
        }
        return false;
    }

    public static void printText(String s) {
        if (s.equals("Clear")) {
            bottomTextPane.setText("");
            tableModel.setRowCount(0);
        } else if (s.contains("Client Disconnected.")) {
            tableModel.setRowCount(0);
            bottomTextPane.setText(s);
        }else if(s.equals("new line")){
            tableModel.addRow(new Object[]{null,null});
        } else if (isProperty(s)) {
            String name_value[] = s.split("[=:-]");
            if(name_value.length < 2){
                String emptyFirst[] = {"", name_value[0]};
                tableModel.addRow(emptyFirst);
            } else {
                tableModel.addRow(name_value);
            }
        } else {
            String splitText[] = s.split("\n");
            bottomTextPane.setText(splitText[0]);
        }


        scrollPane.validate();
        table.setModel(tableModel);
    }

}
