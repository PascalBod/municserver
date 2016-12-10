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

import java.util.Date;

/**
 * 
 * Content of a presence packet.
 *
 */
public class Presence extends Payload {
	
	// Should be put into a properties file.
	private final static String PRESENCE =      "===== presence data";
	private final static String TIME =          "  Time:          ";
	private final static String TYPE =          "  Type:          ";
	private final static String REASON =        "  Reason:        ";
	
	private Date time;
	private String type;
	private String reason;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * 
	 * @return
	 *   - only id_str is displayed, not id
	 *   - only connection_id_str is displayed, not connection_id
	 */
	public String display() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(PRESENCE).append(CRLF);
		sb.append(RECEIPT_TIME).append(DATE_FORMAT.format(receiptTime)).append(CRLF);
		sb.append(ID).append(id_str).append(CRLF);
		sb.append(CONNECTION_ID).append(connection_id_str).append(CRLF);
		sb.append(ASSET).append(asset).append(CRLF);
		sb.append(TIME).append(DATE_FORMAT.format(time)).append(CRLF);
		sb.append(TYPE).append(type).append(CRLF);
		sb.append(REASON).append(reason).append(CRLF);
		
		return sb.toString();
		
	}

}
