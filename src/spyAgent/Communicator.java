/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import common.FileOperator;

public class Communicator {

    private static String tmpDir = System.getProperty("java.io.tmpdir");
    private static String pathSeparator = System.getProperty("path.separator");
    private static String fileSeparator = System.getProperty("file.separator");
	public static OutputStreamWriter outStream;
	public Communicator() {
		super();
		String machine="localhost";
		String launcherFile=(new StringBuilder()).append(tmpDir).append(fileSeparator).append("spyport.txt").toString();
		FileOperator InfoStore=new FileOperator();		
		int port=Integer.parseInt(InfoStore.readFile(launcherFile));	
		Socket MyClient;
	    try {
			InetAddress addr = InetAddress.getByName(machine);
			SocketAddress sockaddr = new InetSocketAddress(addr, port);
			final Socket sock = new Socket();
			sock.connect(sockaddr);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));			
			//System.out.println("Server connectd ");
			String responseLine;
			responseLine = in.readLine();
			//System.out.println("From Server: " + responseLine);
			outStream = new OutputStreamWriter(sock.getOutputStream());	   
	    } catch (SocketTimeoutException e) {
			System.out.println("Connection timed out");
			return;
	    }
	    catch (Exception e) {
	        System.out.println(e.getMessage());
			
	    } 
	}

	public void connServer(){

		
	
	}
	
	public static void writeToServer(String s){
		try {
			outStream.write(s+"\n");
			outStream.flush();
		} catch (IOException e) {

		}
		
	}
	
}
