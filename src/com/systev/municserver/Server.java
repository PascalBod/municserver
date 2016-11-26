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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.sun.net.httpserver.HttpServer;

/**
 * Main class.
 * 
 */
public class Server {
	
	// Log configuration.
	private final static String PACKAGE_NAME = "com.systev.municserver";
	private final static String LOG_FILE_NAME = "municserver.log";
	
	// Server configuration.
	private final static String VERSION = "V0.3";
	public final static int PORT = 55040;
	private final static int MAX_QUEUED_REQUESTS = 10;
	
	private Logger logger;
	private HttpServer httpServer;

	/**
	 * 
	 */
	public void startServer() {
		
		// Initialize logging stuff.
		logger = Logger.getLogger(PACKAGE_NAME);
		Handler fh = null;
		try {
			fh = new FileHandler(LOG_FILE_NAME);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			fh = null;
			System.out.println(e.getMessage());
		} catch (IOException e) {
			fh = null;
			System.out.println(e.getMessage());
		}
		if (fh != null) {
			logger.addHandler(fh);
		}
		Handler ch = new ConsoleHandler();
		// ConsoleHandler default level is INFO.
		ch.setLevel(Level.ALL);
		logger.addHandler(ch);
		// Prevent logger from forwarding log messages to its parent.
		logger.setUseParentHandlers(false);
		//
		logger.setLevel(Level.ALL);
		
		logger.fine(VERSION);
		
		// Create HTTP server.
		InetSocketAddress socketAddress = new InetSocketAddress(PORT);
		try {
			httpServer = HttpServer.create(socketAddress, MAX_QUEUED_REQUESTS);
		} catch (IOException e) {
			logger.severe(e.getMessage());
			return;
		}
		logger.fine("Server created");
		
		// Create and associate context for POST requests.
		PostHandler postHandler = new PostHandler(logger);
		httpServer.createContext("/", postHandler);
		// Default executor: thread created by start().
		httpServer.setExecutor(null);
		httpServer.start();
		logger.fine("Server started");

	}
	
	/**
	 * Only used by tests.
	 */
	public void stopServer() {
		
		logger.fine("Shutting down server");
		httpServer.stop(0);
		
	}

	/**
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		
		Server server = new Server();
		server.startServer();

	}

}
