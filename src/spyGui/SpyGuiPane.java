/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;


public class SpyGuiPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTextArea textArea;
	public static JScrollPane scrollPane;
	public static JTextPane textpane=new JTextPane();
	public SpyGuiPane() {
		
		textpane.setText("Launch example-File->Launch->javaws some.jnlp");
		textpane.setBackground(new Color(255, 70, 50));
		textpane.setEditable(false);
		textArea=new JTextArea("JSpy Initialized...",25,25);
		scrollPane= new JScrollPane(textArea);
		//JScrollPane areaScrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(300, 440));
		Font font = new Font("Ariel", Font.BOLD, 12);
		textArea.setFont(font);
		setPreferredSize(new Dimension(300,440));
		Color color=new Color(255, 200, 200);
		textArea.setBackground(color);
		textArea.setForeground(new Color(50, 50, 225));
		textArea.setAutoscrolls(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		//scrollPane 
		this.add(textpane);
		this.add(scrollPane);
	}

	public static void printText(String s){
		if(s.equals("Clear")){
			textArea.setText("");
		}else{
			textArea.append(s);			
		}
		scrollPane.validate();
	}
	
}
