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
public class Presence {
	
	private long id;
	private String id_str;
	private long connection_id;
	private String connection_id_str;
	private String asset;
	private Date time;
	private String type;
	private String reason;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getId_str() {
		return id_str;
	}
	public void setId_str(String id_str) {
		this.id_str = id_str;
	}
	public long getConnection_id() {
		return connection_id;
	}
	public void setConnection_id(long connection_id) {
		this.connection_id = connection_id;
	}
	public String getConnection_id_str() {
		return connection_id_str;
	}
	public void setConnection_id_str(String connection_id_str) {
		this.connection_id_str = connection_id_str;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
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

}
