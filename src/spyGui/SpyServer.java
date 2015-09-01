/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyGui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import common.*;


public class SpyServer implements Runnable {
    private static String tmpDir = System.getProperty("java.io.tmpdir");
    private static String pathSeparator = System.getProperty("path.separator");
    private static String fileSeparator = System.getProperty("file.separator");


	public void run() {
		try{
			int port = 0;
			//port=21005;
			ServerSocket ss = new ServerSocket(port);
			port = ss.getLocalPort();
			writePortFile(port);
			System.out.println("Server started...");
			while ( true ) {
				
					Socket soc=ss.accept();
				   Thread cliTh=new Thread( new SpyClientReader(soc));
				   cliTh.start();
		    
			}
		} catch ( IOException e ) {
		     System.out.println( "I/O error " + e ); 
		 }
	}

	private void writePortFile(int port) {
		String spyPortFile=(new StringBuilder()).append(tmpDir).append(fileSeparator).append("spyport.txt").toString();
		String host="localhost";
		FileOperator fp=new FileOperator();
		fp.writeFile(spyPortFile,Integer.toString(port));
	}
}
