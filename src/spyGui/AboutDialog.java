/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


import java.awt.*;

import javax.swing.*;

public class AboutDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutDialog() {
		super();
		this.setName("About");
		this.setTitle("About JSpy");
		this.setForeground(Color.BLACK);
		setSize(150, 300);
		Container pane = this.getContentPane();
		setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		JLabel label1=new JLabel("<html>"
				+ "<center>JSpy for ROBOT Framework</center>"
				+ "<center>Version 2.1.1</center>"
				+ "</html>", JLabel.CENTER);
		JLabel label2=new JLabel("Shows Component details in a easy Mouse move",JLabel.CENTER);		
		JLabel label3=new JLabel("<html>"
				+ "<center>By Arulraj Samuel</center>"
				+ "<center>Maintained by Robot Team in Nokia</center>"
				+ "</html>", JLabel.CENTER);
		label1.setForeground(Color.RED);
		label2.setForeground(Color.RED);
		label3.setForeground(Color.RED);
		JLabel dumm1=new JLabel("-------------------------",JLabel.CENTER);
		JLabel dumm2=new JLabel("-----------------------",JLabel.CENTER);
		JLabel dumm3=new JLabel("-------------------",JLabel.CENTER);
		pane.add(label1);
		pane.add(dumm1);
		pane.add(label2);
		pane.add(dumm2);
		pane.add(label3);
		pane.add(dumm3);

		for (Component component: pane.getComponents()) {
			JComponent jComponent = (JComponent) component;
			jComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.validate();
	}

}
