/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;


import java.awt.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.JTableHeader;


public class HierarchyMap {

    public String instance = "";
    public String index = "-1";
    public String winTitle = "";
    public String props = "";
    public HashMap<String, String> compHash = new HashMap<String, String>();

    public void setIndex(String inst) {
        if (compHash.containsKey(inst)) {
            String tmpIndex = compHash.get(inst);
            index = "" + (Integer.parseInt(tmpIndex) + 1);
            compHash.put(inst, index);
        } else {
            compHash.put(inst, "0");
            index = "0";
        }

    }

    public String getInstance(Component comp) {
        if (comp instanceof JButton) {
            instance = "JButton";
        } else if (comp instanceof JCheckBox) {
            instance = "JCheckBox";
        } else if (comp instanceof JCheckBoxMenuItem) {
            instance = "JCheckBoxMenuItem";
        } else if (comp instanceof JColorChooser) {
            instance = "JColorChooser";
        } else if (comp instanceof JComboBox) {
            instance = "JComboBox";
        } else if (comp instanceof JDesktopPane) {
            instance = "JDesktopPane";
        } else if (comp instanceof JDialog) {
            instance = "JDialog";
        } else if (comp instanceof JEditorPane) {
            instance = "JEditorPane";
        } else if (comp instanceof JFileChooser) {
            instance = "JFileChooser";
        } else if (comp instanceof JFrame) {
            instance = "JFrame";
        } else if (comp instanceof JInternalFrame) {
            instance = "JInternalFrame";
        } else if (comp instanceof JInternalFrame.JDesktopIcon) {
            instance = "JInternalFrame.JDesktopIcon";
        } else if (comp instanceof JLabel) {
            instance = "JLabel";
        } else if (comp instanceof JLayeredPane) {
            instance = "JLayeredPane";
        } else if (comp instanceof JList) {
            instance = "JList";
        } else if (comp instanceof JMenu) {
            instance = "JMenu";
        } else if (comp instanceof JMenuBar) {
            instance = "JMenuBar";
        } else if (comp instanceof JMenuItem) {
            instance = "JMenuItem";
        } else if (comp instanceof JOptionPane) {
            instance = "JOptionPane";
        } else if (comp instanceof JPanel) {
            instance = "JPanel";
        } else if (comp instanceof JPopupMenu) {
            instance = "JPopupMenu";
        } else if (comp instanceof JProgressBar) {
            instance = "JProgressBar";
        } else if (comp instanceof JRadioButton) {
            instance = "JRadioButton";
        } else if (comp instanceof JRadioButtonMenuItem) {
            instance = "JRadioButtonMenuItem";
        } else if (comp instanceof JRootPane) {
            instance = "JRootPane";
        } else if (comp instanceof JScrollBar) {
            instance = "JScrollBar";
        } else if (comp instanceof JScrollPane) {
            instance = "JScrollPane";
        } else if (comp instanceof JSeparator) {
            instance = "JSeparator";
        } else if (comp instanceof JSlider) {
            instance = "JSlider";
        } else if (comp instanceof JSpinner) {
            instance = "JSpinner";
        } else if (comp instanceof JSplitPane) {
            instance = "JSplitPane";
        } else if (comp instanceof JTabbedPane) {
            instance = "JTabbedPane";
        } else if (comp instanceof JTable) {
            instance = "JTable";
        } else if (comp instanceof JTextArea) {
            instance = "JTextArea";
        } else if (comp instanceof JTextField) {
            instance = "JTextField";
        } else if (comp instanceof JTextPane) {
            instance = "JTextPane";
        } else if (comp instanceof JToggleButton) {
            instance = "JToggleButton";
        } else if (comp instanceof JToolBar) {
            instance = "JToolBar";
        } else if (comp instanceof JToolTip) {
            instance = "JToolTip";
        } else if (comp instanceof JTree) {
            instance = "JTree";
        } else if (comp instanceof JViewport) {
            instance = "JViewport";
        } else if (comp instanceof JWindow) {
            instance = "JWindow";
        } else if (comp instanceof JTableHeader) {
            instance = "JTableHeader";
        } else if (comp instanceof JComponent) {
            instance = "JComponent";
        } else if (comp instanceof TextField) {
            instance = "TextField";
        } else {
            instance = null;
        }
        props = comp.toString();
        setIndex(instance);
        return instance;
    }

}
