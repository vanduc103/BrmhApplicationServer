package com.bdi.duclv.brmh.service;

import java.sql.Timestamp;

public class ExportEntity {
	private String inspectMac;
	private String contactedMac;
	private int sectionId;
	private int level;
	private Timestamp fromTime;
	private Timestamp toTime;
	
	public String getInspectMac() {
		return inspectMac;
	}
	public void setInspectMac(String inspectMac) {
		this.inspectMac = inspectMac;
	}
	public String getContactedMac() {
		return contactedMac;
	}
	public void setContactedMac(String contactedMac) {
		this.contactedMac = contactedMac;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Timestamp getFromTime() {
		return fromTime;
	}
	public void setFromTime(Timestamp fromTime) {
		this.fromTime = fromTime;
	}
	public Timestamp getToTime() {
		return toTime;
	}
	public void setToTime(Timestamp toTime) {
		this.toTime = toTime;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	
}
