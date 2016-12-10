/*
 *  Copyright (C) 2016 Pascal Bodin
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.systev.municserver;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * Functional test. A server is created and then sent JSON documents.
 * JSON documents are stored in files named jsonDocument<nn>.txt, as one line,
 * in project top-level folder, and used by related testSendJson<nn>() method.
 *
 */
public class ServerTest {
	
	private static Server server;
	
	private final static String SERVER_URL = "http://localhost:" + Server.PORT;
	
	/**
	 * Reads and returns contents of a JSON document.
	 * 
	 * @param filename
	 * @return
	 */
	private String getJsonDocument(String fileName) {
		
	    BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return "";
		}
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        sb.append(line);
	        br.close();
	        return sb.toString();
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	return "";
	    }

	}
	
	/**
	 * Send a JSON document to the server, creating a dedicated HTTP client.
	 * 
	 * @param jsonDocument
	 */
	private void sendJsonDocument(String jsonDocument) {
		
		// Create and connect HTTP client.
		HttpURLConnection client;
		URL url;
		try {
			url = new URL(SERVER_URL);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			return;
		}
		try {
			client = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		try {
	        client.setDoOutput(true);                                                       
	        client.setDoInput(true);                                                        
	        client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");   
	        client.setRequestMethod("POST");                                                
			client.connect();
		} catch (SocketTimeoutException e) {
			System.out.println(e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		
        OutputStreamWriter writer;
		try {
			writer = new OutputStreamWriter(client.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}   
        try {
			writer.write(jsonDocument);
	        writer.flush();                                                                 
	        writer.close();                                                                 
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}                                                           

        InputStream input;
		try {
			input = client.getInputStream();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}                                    
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));       
        StringBuilder result = new StringBuilder();                                     
        String line;                                                                    
        try {
			while ((line = reader.readLine()) != null) {                                    
			    result.append(line);                                                        
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}     
        
        client.disconnect();
		
	}
	
	/**
	 * 
	 */
	@BeforeClass
	public static void initHttpClient() {

		// Start server.
		server = new Server();
		server.startServer();
		
		// Wait a bit, to be sure the server is available.
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * 
	 */
	@AfterClass
	public static void stopServer() {
		
		// Wait a bit, to be sure requests have been handled.
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		server.stopServer();
		
	}
	
	/**
	 * Simple presence JSON document.
	 */
	@Test
	public void testSendJson01() {
		
		String jsonDocument = getJsonDocument("jsonDocument01.txt");
		assertNotEquals("can't read jsonDocument01", jsonDocument, "");
		sendJsonDocument(jsonDocument);
		
	}
	
	/**
	 * Several track objects.
	 */
	@Test
	public void testSendJson02() {

		String jsonDocument = getJsonDocument("jsonDocument02.txt");
		assertNotEquals("can't read jsonDocument02", jsonDocument, "");
		sendJsonDocument(jsonDocument);

	}

	/**
	 * Several presence and message objects.
	 */
	@Test
	public void testSendJson03() {

		String jsonDocument = getJsonDocument("jsonDocument03.txt");
		assertNotEquals("can't read jsonDocument03", jsonDocument, "");
		sendJsonDocument(jsonDocument);

	}
	
	/**
	 * One track object.
	 */
	@Test
	public void testSendJson04() {
		
		String jsonDocument = getJsonDocument("jsonDocument04.txt");
		assertNotEquals("can't read jsonDocument04", jsonDocument, "");
		sendJsonDocument(jsonDocument);
		
	}

}
