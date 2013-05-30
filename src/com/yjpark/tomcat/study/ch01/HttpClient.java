package com.yjpark.tomcat.study.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClient {
	
	@SuppressWarnings("static-access")
	public void send() throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("127.0.0.1", 8080);
		OutputStream os = socket.getOutputStream();
		boolean autoflush = true;
		PrintWriter out = new PrintWriter(os, autoflush);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		out.println("GET /index.jsp HTTP/1.1");
		out.println("Host: localhost:8080");
		out.println("Connection: Close");
		out.println();
		boolean loop = true;
		StringBuffer sb = new StringBuffer(8096);
		
		while (loop) {
			if (in.ready()) {
				int i= 0;
				while (i != -1) {
					i = in.read();
					sb.append((char) i);
				}
				loop = false;
			}
			Thread.currentThread().sleep(50);
		}
		
		System.out.println(sb.toString());
		socket.close();
	}
	
}