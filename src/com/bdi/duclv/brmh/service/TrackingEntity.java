package com.bdi.duclv.brmh.service;

import java.io.Serializable;
import java.sql.Timestamp;

public class TrackingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String macAddress;
	private Timestamp updateTime;
	private int sectionId;
	private String sectionName;
	private int peopleCount;
	private Timestamp firstTime;
	private Timestamp lastTime;
	private long periodOfTime;
	private int totalResult;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public int getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}
	public Timestamp getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Timestamp firstTime) {
		this.firstTime = firstTime;
	}
	public long getPeriodOfTime() {
		return periodOfTime;
	}
	public void setPeriodOfTime(long periodOfTime) {
		this.periodOfTime = periodOfTime;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

}
