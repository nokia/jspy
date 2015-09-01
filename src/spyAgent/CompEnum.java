/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CompEnum implements Runnable{
	String title="";
	HierarchyMap hieMap=new HierarchyMap();
	Component component;
	public CompEnum(Component compon) {
		super();
		component=compon;
		if (compon instanceof JFrame) {
			JFrame srcFrame = (JFrame) compon;
			title=srcFrame.getTitle();
		}else if(compon instanceof JDialog) {
			JDialog srcDlg = (JDialog) compon;
			title=srcDlg.getTitle();
		}else if(compon instanceof JFileChooser) {
			JFileChooser srcDlg = (JFileChooser) compon;
			title=srcDlg.getDialogTitle();
		}
		
		hieMap.winTitle=title;
		//Window win=(Window) compon;
		//win.addContainerListener(new ContListner(hieMap));

	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Communicator.writeToServer("Started Indexing Components");
		ListComponent compList=new ListComponent(hieMap);
		compList.listComponentsInContext(component);
		Communicator.writeToServer("Finished Indexing Components");
		
	}

}
