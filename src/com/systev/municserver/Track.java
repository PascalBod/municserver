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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * Content of a track packet.
 *
 */
public class Track extends Payload {
	
	// Should be put into a properties file.
	private final static String TRACK =          "===== track data";
	private final static String RECORDED_AT_MS = "  Recorded at:          ";
	private final static String RECEIVED_AT =    "  Received at:          ";
	private final static String INDEX =          "  Index:                ";
	private final static String LOC =            "  Location (lat / lon): ";
	private final static String FIELDS =         "  Fields:";
	private final static String FIELD_TAB =      "    ";
	
	private Date recordedAt;
	private Date recordedAtMs;
	private Date receivedAt;
	private double lat, lon;
	private long index;
	private ArrayList<TrackField> fields;
	
	/**
	 * 
	 */
	public Track() {
		
		fields = new ArrayList<TrackField>();
		
	}
	
	public Date getRecordedAt() {
		return recordedAt;
	}
	public void setRecordedAt(Date recordedAt) {
		this.recordedAt = recordedAt;
	}
	public Date getRecordedAtMs() {
		return recordedAtMs;
	}
	public void setRecordedAtMs(Date recordedAtMs) {
		this.recordedAtMs = recordedAtMs;
	}
	public Date getReceivedAt() {
		return receivedAt;
	}
	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public long getIndex() {
		return index;
	}
	public void setIndex(long index) {
		this.index = index;
	}

	/**
	 * 
	 * @param field
	 */
	public void addField(TrackField field) {
		
		fields.add(field);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<TrackField> getFields() {
		
		return fields.iterator();
		
	}
	
	/**
	 * 
	 * @return
	 *   - only id_str is displayed, not id
	 *   - only recorded_at_ms is displayed, not recorded_at
	 */
	public String display() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(TRACK).append(CRLF);
		sb.append(RECEIPT_TIME).append(DATE_FORMAT.format(receiptTime)).append(CRLF);
		sb.append(ID).append(id_str).append(CRLF);
		sb.append(CONNECTION_ID).append(connection_id_str).append(CRLF);
		sb.append(RECORDED_AT_MS).append(DATE_FORMAT_MS.format(recordedAtMs)).append(CRLF);
		sb.append(RECEIVED_AT).append(DATE_FORMAT.format(receivedAt)).append(CRLF);
		sb.append(INDEX).append(index).append(CRLF);
		sb.append(LOC).append(lat).append(" / ").append(lon).append(CRLF);
		sb.append(FIELDS).append(CRLF);
		Iterator<TrackField> iterator = getFields();
		while (iterator.hasNext()) {
			sb.append(FIELD_TAB).append(iterator.next().display()).append(CRLF);
		}
		
		return sb.toString();
		
	}

}
