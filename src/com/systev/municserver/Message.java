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
 * Content of a message packet.
 *
 */
public class Message extends Payload {
	
	// Should be put into a properties file.
	private final static String MESSAGE =          "===== message data";
	private final static String PARENT_ID =        "  Parent id:     ";
	private final static String CONNECTION_ID =    "  Connection id: ";
	private final static String TYPE =             "  Type:          ";
	private final static String CHANNEL =          "  Channel:       ";
	private final static String SENDER =           "  Sender:        ";
	private final static String RECIPIENT =        "  Recipient:     ";
	private final static String PAYLOAD =          "  Payload:       ";
	private final static String RECORDED_AT =      "  Recorded at:   ";
	private final static String RECEIVED_AT =      "  Received at:   ";
	
	private long parentId;
	private long connectionId;
	private String parentIdStr;
	private String connectionIdStr;
	private String type;
	private String channel;
	private String sender;
	private String recipient;
	private String payload;
	private Date recordedAt;
	private Date receivedAt;
	
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(long connectionId) {
		this.connectionId = connectionId;
	}
	public String getParentIdStr() {
		return parentIdStr;
	}
	public void setParentIdStr(String parentIdStr) {
		this.parentIdStr = parentIdStr;
	}
	public String getConnectionIdStr() {
		return connectionIdStr;
	}
	public void setConnectionIdStr(String connectionIdStr) {
		this.connectionIdStr = connectionIdStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Date getRecorded_at() {
		return recordedAt;
	}
	public void setRecordedAt(Date recordedAt) {
		this.recordedAt = recordedAt;
	}
	public Date getReceivedAt() {
		return receivedAt;
	}
	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}
	
	/**
	 * 
	 * @return
 	 *   - only idStr is displayed, not id
	 *   - only connectionIdStr is displayed, not connectionId
	 *   - only parentIdStr is displayed, not parentId
	 */
	public String display() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(MESSAGE).append(CRLF);
		sb.append(RECEIPT_TIME).append(DATE_FORMAT.format(receiptTime)).append(CRLF);
		sb.append(ID).append(idStr).append(CRLF);
		sb.append(ASSET).append(asset).append(CRLF);
		sb.append(PARENT_ID).append(parentIdStr).append(CRLF);
		sb.append(CONNECTION_ID).append(connectionIdStr).append(CRLF);
		sb.append(TYPE).append(type).append(CRLF);
		sb.append(CHANNEL).append(channel).append(CRLF);
		sb.append(SENDER).append(sender).append(CRLF);
		sb.append(RECIPIENT).append(recipient).append(CRLF);
		sb.append(PAYLOAD).append(payload).append(CRLF);
		sb.append(RECORDED_AT).append(DATE_FORMAT.format(recordedAt)).append(CRLF);
		sb.append(RECEIVED_AT).append(DATE_FORMAT.format(receivedAt)).append(CRLF);

		return sb.toString();
		
	}

}
