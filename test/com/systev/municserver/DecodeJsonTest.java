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

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * Unit tests, performing basic tests of JSON parsing.
 *
 */
public class DecodeJsonTest {
	
	private final static String PACKAGE_NAME = "com.systev.municserver";
	private final static String LOG_FILE_NAME = "municserver_decodejsontest.log";
	private static Logger logger;
	
	private final static String jsonString02 = "toto";
	private final static String jsonString03 = "[toto]";
	private final static String jsonString04 = "[{\"toto\":\"titi\"}]";
	private final static String jsonString05 = "[{\"meta\":{\"toto\":\"titi\"}}]";
	private final static String jsonString06 = "[{\"meta\":{\"event\":\"titi\"}}]";
	private final static String jsonString07 = "[{\"meta\":{\"event\":\"presence\"}}]";
	private final static String jsonString08 = "[{\"meta\":{\"event\":\"presence\"},\"toto\":\"titi\"}]";
	private final static String jsonString09 = "[{\"meta\":{\"account\":\"municio\",\"event\":\"presence\"},\"payload\":{\"id\":911270996919452058,\"connection_id\":911270996911063452,\"id_str\":\"911270996919452058\",\"connection_id_str\":\"911270996911063452\",\"asset\":\"356156060353505\",\"time\":\"2016-11-19T15:05:36Z\",\"type\":\"connect\",\"reason\":\"idle_out\"}}]";
	
	/**
	 * 
	 */
	@BeforeClass
	public static void initLogger() {
		
		logger = Logger.getLogger(PACKAGE_NAME);
		Handler fh = null;
		try {
			fh = new FileHandler(LOG_FILE_NAME);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			fh = null;
			System.out.println("Server: " + e.getMessage());
		} catch (IOException e) {
			fh = null;
			System.out.println("Server: " + e.getMessage());
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

	}
	
	@Test
	public void testNotAnArrayGetPayloadType() {
		DecodeJson decodeJson = new DecodeJson(jsonString02, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("not an array: not detected", rs, false);
	}
	
	@Test
	public void testArrayNotObjectGetPayloadType() {
		DecodeJson decodeJson = new DecodeJson(jsonString03, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("not an array, not detected", rs, false);
	}
	
	@Test
	public void testNoMetaGetPayloadType() {
		DecodeJson decodeJson = new DecodeJson(jsonString04, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("array, not an object: array not accepted", rs, true);
		DecodeJson.PayloadType payloadType = decodeJson.getPayloadType();
		assertEquals("no meta: not detected", DecodeJson.PayloadType.ERROR, payloadType);
	}
	
	@Test
	public void testMetaNoEventGetPayloadType() {
		DecodeJson decodeJson = new DecodeJson(jsonString05, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("array, not an object: array not accepted", rs, true);
		DecodeJson.PayloadType payloadType = decodeJson.getPayloadType();
		assertEquals("meta, no event: not detected", DecodeJson.PayloadType.ERROR, payloadType);
	}

	@Test
	public void testUnknownEventGetPayloadType() {
		DecodeJson decodeJson = new DecodeJson(jsonString06, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("array, not an object: array not accepted", rs, true);
		DecodeJson.PayloadType payloadType = decodeJson.getPayloadType();
		assertEquals("unknown event: not detected", DecodeJson.PayloadType.ERROR, payloadType);
	}

	@Test
	public void testNoPayloadGetPresence() {
		DecodeJson decodeJson = new DecodeJson(jsonString07, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("array, not an object: array not accepted", rs, true);
		DecodeJson.PayloadType payloadType = decodeJson.getPayloadType();
		assertEquals("unknown event: not detected", DecodeJson.PayloadType.PRESENCE, payloadType);
		Presence presence = decodeJson.getPresence();
		assertEquals("no payload", null, presence);
	}

	@Test
	public void testBadPayloadGetPresence() {
		DecodeJson decodeJson = new DecodeJson(jsonString08, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("array, not an object: array not accepted", rs, true);
		DecodeJson.PayloadType payloadType = decodeJson.getPayloadType();
		assertEquals("unknown event: not detected", DecodeJson.PayloadType.PRESENCE, payloadType);
		Presence presence = decodeJson.getPresence();
		assertEquals("bad payload", null, presence);
	}

	@Test
	public void testFullPayloadGetPresence() {
		DecodeJson decodeJson = new DecodeJson(jsonString09, logger);
		boolean rs = decodeJson.getNextArrayObject();
		assertEquals("array, not an object: array not accepted", rs, true);
		DecodeJson.PayloadType payloadType = decodeJson.getPayloadType();
		assertEquals("unknown event: not detected", DecodeJson.PayloadType.PRESENCE, payloadType);
		Presence presence = decodeJson.getPresence();
		assertNotNull("bad presence", presence);
		assertEquals("bad presence", presence.getId(), 911270996919452058L);
		assertEquals("bad presence", presence.getConnection_id(), 911270996911063452L);
		assertEquals("bad presence", presence.getId_str(), "911270996919452058");
		assertEquals("bad presence", presence.getConnection_id_str(), "911270996911063452");
		assertEquals("bad presence", presence.getAsset(), "356156060353505");
		String dateStr = "Sat Nov 19 15:05:36 CET 2016";
		assertEquals("bad presence", presence.getTime().toString(), dateStr);
		assertEquals("bad presence", presence.getType(), "connect");
		assertEquals("bad presence", presence.getReason(), "idle_out");
	}

}
