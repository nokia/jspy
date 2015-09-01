/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;



import java.awt.Component;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.JTableHeader;


public class HierarchyMap {

	public String instance="";
	public String index="-1";
	public String winTitle="";
	public String props="";
	public HashMap<String,String> compHash=new HashMap<String,String>();
	public HierarchyMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setIndex(String inst){
		if(compHash.containsKey(inst)){
			String tmpIndex=(String) compHash.get(inst);
			index=""+(Integer.parseInt(tmpIndex)+1);
			compHash.put(inst,index);
		}else{
			compHash.put(inst,"0");
			index="0";
		}

	}
	
	public String getInstance(Component comp){
		if (comp instanceof JButton) {
			instance =  "JButton";		
			props=((JButton)comp).toString();
		}else if(comp instanceof JCheckBox){
			instance =  "JCheckBox"; props=((JCheckBox)comp).toString();
		}else if(comp instanceof JCheckBoxMenuItem){
			instance =  "JCheckBoxMenuItem"; props=((JCheckBoxMenuItem)comp).toString();
		}else if(comp instanceof JColorChooser){
			instance =  "JColorChooser"; props=((JColorChooser)comp).toString();
		}else if(comp instanceof JComboBox){
			instance =  "JComboBox"; props=((JComboBox)comp).toString();
		}else if(comp instanceof JDesktopPane){
			instance =  "JDesktopPane"; props=((JDesktopPane)comp).toString();
		}else if(comp instanceof JDialog){
			instance =  "JDialog"; props=((JDialog)comp).toString();
		}else if(comp instanceof JEditorPane){
			instance =  "JEditorPane"; props=((JEditorPane)comp).toString();
		}else if(comp instanceof JFileChooser){
			instance =  "JFileChooser"; props=((JFileChooser)comp).toString();
		}else if(comp instanceof JFrame){
			instance =  "JFrame"; props=((JFrame)comp).toString();
		}else if(comp instanceof JInternalFrame){
			instance =  "JInternalFrame"; props=((JInternalFrame)comp).toString();
		}else if(comp instanceof JInternalFrame.JDesktopIcon){
			instance =  "JInternalFrame.JDesktopIcon"; props=((JInternalFrame.JDesktopIcon)comp).toString();
		}else if(comp instanceof JLabel){
			instance =  "JLabel"; props=((JLabel)comp).toString();
		}else if(comp instanceof JLayeredPane){
			instance =  "JLayeredPane"; props=((JLayeredPane)comp).toString();
		}else if(comp instanceof JList){
			instance =  "JList"; props=((JList)comp).toString();
		}else if(comp instanceof JMenu){
			instance =  "JMenu"; props=((JMenu)comp).toString();
		}else if(comp instanceof JMenuBar){
			instance =  "JMenuBar"; props=((JMenuBar)comp).toString();
		}else if(comp instanceof JMenuItem){
			instance =  "JMenuItem"; props=((JMenuItem)comp).toString();
		}else if(comp instanceof JOptionPane){
			instance =  "JOptionPane"; props=((JOptionPane)comp).toString();
		}else if(comp instanceof JPanel){
			instance =  "JPanel"; props=((JPanel)comp).toString();
		}else if(comp instanceof JPopupMenu){
			instance =  "JPopupMenu"; props=((JPopupMenu)comp).toString();
		}else if(comp instanceof JProgressBar){
			instance =  "JProgressBar"; props=((JProgressBar)comp).toString();
		}else if(comp instanceof JRadioButton){
			instance =  "JRadioButton"; props=((JRadioButton)comp).toString();
		}else if(comp instanceof JRadioButtonMenuItem){
			instance =  "JRadioButtonMenuItem"; props=((JRadioButtonMenuItem)comp).toString();
		}else if(comp instanceof JRootPane){
			instance =  "JRootPane"; props=((JRootPane)comp).toString();
		}else if(comp instanceof JScrollBar){
			instance =  "JScrollBar"; props=((JScrollBar)comp).toString();
		}else if(comp instanceof JScrollPane){
			instance =  "JScrollPane"; props=((JScrollPane)comp).toString();
		}else if(comp instanceof JSeparator){
			instance =  "JSeparator"; props=((JSeparator)comp).toString();
		}else if(comp instanceof JSlider){
			instance =  "JSlider"; props=((JSlider)comp).toString();
		}else if(comp instanceof JSpinner){
			instance =  "JSpinner"; props=((JSpinner)comp).toString();
		}else if(comp instanceof JSplitPane){
			instance =  "JSplitPane"; props=((JSplitPane)comp).toString();
		}else if(comp instanceof JTabbedPane){
			instance =  "JTabbedPane"; props=((JTabbedPane)comp).toString();
		}else if(comp instanceof JTable){
			instance =  "JTable"; props=((JTable)comp).toString();
		}else if(comp instanceof JTextArea){
			instance =  "JTextArea"; props=((JTextArea)comp).toString();
		}else if(comp instanceof JTextField){
			instance =  "JTextField"; props=((JTextField)comp).toString();
		}else if(comp instanceof JTextPane){
			instance =  "JTextPane"; props=((JTextPane)comp).toString();
		}else if(comp instanceof JToggleButton){
			instance =  "JToggleButton"; props=((JToggleButton)comp).toString();
		}else if(comp instanceof JToolBar){
			instance =  "JToolBar"; props=((JToolBar)comp).toString();
		}else if(comp instanceof JToolTip){
			instance =  "JToolTip"; props=((JToolTip)comp).toString();
		}else if(comp instanceof JTree){
			instance =  "JTree"; props=((JTree)comp).toString();
		}else if(comp instanceof JViewport){
			instance =  "JViewport"; props=((JViewport)comp).toString();
		}else if(comp instanceof JWindow){
			instance =  "JWindow"; props=((JWindow)comp).toString();
		}else if(comp instanceof JTableHeader){
			instance =  "JTableHeader"; props=((JTableHeader)comp).toString();
		}else if(comp instanceof JComponent){
			instance =  "JComponent"; props=((JComponent)comp).toString();
		}else{
			instance =  null;
		}
		setIndex(instance);
		return instance;
	}

}
