/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;


public class CompMouseListner implements MouseListener {
    public static Color color;

    String myIndex;
    int compHierarchy = -1;
    String winTitle;
    String classType;
    String compProps = "";

    static boolean flag = false;

    Border prevBorder = BorderFactory.createEmptyBorder();
    Border blackline = BorderFactory.createLineBorder(Color.black, 2);

    public CompMouseListner(String index, int compHierarchy, String title, String objType, String srcCompProps) {
        myIndex = index;
        winTitle = title;
        classType = objType;
        compProps = srcCompProps;
        this.compHierarchy = compHierarchy;
    }

    public void mouseClicked(MouseEvent arg0) {
        Component comp = arg0.getComponent();
        if (comp instanceof JTabbedPane) {
            JTabbedPane tabpane = (JTabbedPane) comp;
            int tabIndex = tabpane.getSelectedIndex();
            String tabName = tabpane.getTitleAt(tabIndex);
            String tabText = ",Tab Index - " + tabIndex + ",Tab Title- " + tabName;
            Communicator.writeToServer("Window Title - " + winTitle + ",Index - " + myIndex + ",Instance Of - " + classType + ",Comp. Hierarchy - " + compHierarchy + "," + tabpane.toString() + tabText);
        }
        if (comp instanceof JTree) {
            JTree tree = (JTree) comp;
            int nodeCount = tree.getLeadSelectionRow();
            String nodePath = tree.getSelectionPath().toString();
            String tabText = ",Node Count - " + nodeCount + ",Node Path- " + nodePath;
            Communicator.writeToServer("Window Title - " + winTitle + ",Index - " + myIndex + ",Instance Of - " + classType + ",Comp. Hierarchy - " + compHierarchy + "," + tree.toString() + tabText);
        }
    }

    public void mousePressed(MouseEvent arg0) {

    }

    public void mouseReleased(MouseEvent arg0) {

    }

    public void mouseEntered(MouseEvent arg0) {
        if (!flag) {
            try {
                JComponent comp = (JComponent) arg0.getComponent();
                Communicator.writeToServer("Window Title - " + winTitle + ",Index - " + myIndex + ",Instance Of - " + classType + ",Comp. Hierarchy - " + compHierarchy + "," + compProps);
                color = comp.getBackground();
                prevBorder = comp.getBorder();
                comp.setBorder(blackline);
                comp.setBackground(Color.PINK);
                flag = true;
            } catch (Exception e) {

            }
        }

    }

    public void mouseExited(MouseEvent arg0) {
        if (flag) {
            try {
                JComponent comp = (JComponent) arg0.getComponent();
                comp.setBorder(prevBorder);
                comp.setBackground(color);
                flag = false;
            } catch (Exception e) {

            }
        }

    }

}
