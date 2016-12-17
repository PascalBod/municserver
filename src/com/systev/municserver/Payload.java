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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payload {
	
	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	public final static SimpleDateFormat DATE_FORMAT_MS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	protected final static String CRLF = "\r\n";
	// Should be put into a properties file.
	protected final static String RECEIPT_TIME =  "  Receipt time:  ";
	protected final static String ID =            "  Id:            ";
	protected final static String CONNECTION_ID = "  Connection id: ";
	protected final static String ASSET =         "  Asset:         ";

	protected Date receiptTime;
	protected long id;
	protected String idStr;
	protected long connectionId;
	protected String connectionIdStr;
	protected String asset;

	public Date getReceiptTime() {
		return receiptTime;
	}
	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String id_str) {
		this.idStr = id_str;
	}
	public long getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(long connectionId) {
		this.connectionId = connectionId;
	}
	public String getConnectionIdStr() {
		return connectionIdStr;
	}
	public void setConnectionIdStr(String connectionIdStr) {
		this.connectionIdStr = connectionIdStr;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}

}
