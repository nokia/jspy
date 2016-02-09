/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class CmdInputDlg  extends JDialog{
	/**
	 * 
	 */
	public JDialog myDialog=this;
	private static final long serialVersionUID = 1L;
	JTextField textExec=new JTextField("",15);
	 private static String fileSeparator = System.getProperty("file.separator");
	private java.util.List cmdList=new ArrayList();
	public CmdInputDlg() {
		super();
		this.setName("execCmd");
		this.setTitle("Execute Command");
		Container contentPane = this.getContentPane();
		FlowLayout layout = new FlowLayout();
        contentPane.setLayout(layout);

		setSize(150, 300);
		//setLayout(new GridLayout(1, 2));
		JLabel label1=new JLabel("Cmd to launch",JLabel.LEFT);		
			
		JButton btLaunch=new JButton("Run");	
		//textExec.setSize(20,100);
		contentPane.add(label1);
		contentPane.add(textExec);
		contentPane.add(btLaunch);
		ActionListener btnAct=new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(arg0.getActionCommand().equals("Run")){
					String cmdStr=textExec.getText();
					int execLen=0;
					String executable="";
					String args="";
					
					if(!cmdStr.trim().equals("")){
						

						/*String[] cmdArr=cmdStr.split(" ");
						String execCmd=cmdArr[0];
						String cmdArgs="";
						for(int i=1;i<cmdArr.length;i++){
							cmdArgs += " "+cmdArr[i];
						}
						System.out.println("Executing command "+execCmd);
						textExec.setText("");
						*/


						System.out.println("Executing command :"+cmdStr);
						List<String> cmdList = new ArrayList<String>();
						cmdList.addAll(Arrays.asList(cmdStr.trim().split("\\s+")));
						ProcessBuilder pb = new ProcessBuilder(cmdList.toArray(new String[cmdList.size()]));
						Map<String, String> env = pb.environment();
						//String currentdir = Paths.get("").toAbsolutePath().toString();
						//System.out.println("Current dir is "+currentdir);
						String jarPath = spyAgent.AgentPreMain.class.getProtectionDomain()
								.getCodeSource()
								.getLocation()
								.getPath()
								.toString();
						env.put("JAVA_TOOL_OPTIONS", "-javaagent:\""+jarPath+"\"");
						//pb.directory(new File("C:\\Program Files\\COMclient\\bin\\"));
						pb.redirectErrorStream(true);
						try {
							Process p = pb.start();
							Thread readProcTh=new Thread(new ProcessReader(p));
							readProcTh.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						myDialog.setVisible(false);

					}
					
				}
			}

		};
		btLaunch.addActionListener(btnAct);
	}

}