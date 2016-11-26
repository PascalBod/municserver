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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * 
 * Class handling incoming POST requests.
 * The POST body is a JSON-encoded document.
 *
 */
public class PostHandler implements HttpHandler {
	
	private final static int RESP_CODE_OK = 200;
	
	private Logger logger;
	
	/**
	 * 
	 * @param logger
	 */
	public PostHandler(Logger logger) {
		
		this.logger = logger;
		
	}

	/**
	 * 
	 */
	@Override
	public void handle(HttpExchange exchange) {
		
		// Just being curious: get header data.
		Headers headers = exchange.getRequestHeaders();
		Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
		StringBuilder sb = new StringBuilder();
		sb.append("==================== request header\r\n");
        for (Map.Entry<String, List<String>> entry : entries) {
        	sb.append(entry.toString()).append("\r\n");
        }
        
        // Get body.
        sb.append("==================== request body\r\n");
        StringBuilder sbj = new StringBuilder();
        InputStream is = exchange.getRequestBody();
        int c;
        try {
        	while (true) {
        		c = is.read();
        		if (c == -1) break;
        		sbj.append((char)c);
        	}
        	is.close();
            
            // Send OK response, with empty body.
            exchange.sendResponseHeaders(RESP_CODE_OK, -1);
            OutputStream os = exchange.getResponseBody();
            os.close();     
        } catch (IOException e) {
        	logger.fine(sb.toString());
        	logger.severe(e.getMessage());
        	return;
        }
        String jsonDocument = sbj.toString();
        sb.append(jsonDocument).append("\r\n");
        logger.fine(sb.toString());
        
        // Create JSON decoder.
        DecodeJson decodeJson = new DecodeJson(jsonDocument, logger);
        
        // Process every object of the top-level array.
        boolean objectAvail;
        DecodeJson.PayloadType payloadType;
        while (true) {
        	objectAvail = decodeJson.getNextArrayObject();
        	if (!objectAvail) {
        		// No more object, or an error occurred.
        		break;
        	}
        	payloadType = decodeJson.getPayloadType();
            switch (payloadType) {
            case PRESENCE:
            	Presence presence = decodeJson.getPresence();
            	if (presence == null) {
            		logger.severe("**** error **** can't read presence data");
            		break;
            	}
            	// At this stage, presence object is available.
            	logger.fine("presence data is OK");
            	break;
            case MESSAGE:
            	logger.fine("message data not decoded yet");
            	break;
            case TRACK:
            	logger.fine("track data not decoded yet");
            	break;
            case ERROR:
            	logger.severe("**** error **** can't decode received data");
            	break;
            default:
            	logger.severe("**** error **** returned payload type not implemented yet");
            }  // switch (payloadType)
        }  // while (true)
        
        // Done.
        decodeJson.closeJson();
        
	}

}
