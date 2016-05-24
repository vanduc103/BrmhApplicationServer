package com.bdi.duclv.brmh.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class InspectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String macAddress;
	private Timestamp fromTime;
	private Timestamp toTime;
	private int sectionId;
	private int peopleContacted;
	private List<Integer> sectionList;
	private int totalResult;
	
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
	public int getPeopleContacted() {
		return peopleContacted;
	}
	public void setPeopleContacted(int peopleContacted) {
		this.peopleContacted = peopleContacted;
	}
	public List<Integer> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Integer> sectionList) {
		this.sectionList = sectionList;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

}
