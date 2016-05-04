package com.bdi.duclv.brmh.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class BrmhControllerService {

	private Connection getDBConnection() {
		Connection dBConn = null;
		try {
			DataSource ds = DataSource.getInstance();
			dBConn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Connection Failed !!!");
		}
		return dBConn;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/countPeople")
	public List<TrackingEntity> countPeople(
			@RequestParam(required=false, name="fromTime") Long fromTime,
			@RequestParam(required=false, name="toTime") Long toTime,
			@RequestParam(required=false, name="sectionId") Integer pSectionId) {
		
		//result to return
		List<TrackingEntity> result = new ArrayList<>();
		//clean input
		if(fromTime == null) {
			fromTime = 0L;
		}
		if(toTime == null) {
			toTime = System.currentTimeMillis();
		}
		if(pSectionId == null) {
			pSectionId = 0;
		}
		
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				System.out.println("Connection to DB successful!");
				// make query to get count by time and section
				StringBuilder query = new StringBuilder(
						"SELECT count(DISTINCT mac_address), section_id FROM tracking WHERE 1=1 ");
				query.append("AND update_time >= ? AND update_time <= ? ");
				if(pSectionId > 0) {
					query.append("AND section_id = ? ");
				}
				query.append("GROUP BY section_id ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setTimestamp(1, new Timestamp(fromTime));
				stmt.setTimestamp(2, new Timestamp(toTime));
				if(pSectionId > 0) {
					stmt.setInt(3, pSectionId);
				}
				
				ResultSet resultSet = stmt.executeQuery();
				while(resultSet.next()) {
					int peopleCount = resultSet.getInt(1);
					int sectionId = resultSet.getInt(2);
					//add to result
					TrackingEntity tracking = new TrackingEntity();
					tracking.setPeopleCount(peopleCount);
					tracking.setSectionId(sectionId);
					result.add(tracking);
				}
				resultSet.close();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			}
			finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getPeopleDetail")
	public List<TrackingEntity> getPeopleDetail(
			@RequestParam(required=false, name="fromTime") Long pFromTime,
			@RequestParam(required=false, name="toTime") Long pToTime,
			@RequestParam(required=true, name="sectionId") Integer pSectionId) {
		
		//result to return
		List<TrackingEntity> result = new ArrayList<>();
		//clean input
		if(pFromTime == null) {
			pFromTime = 0L;
		}
		if(pToTime == null) {
			pToTime = System.currentTimeMillis();
		}
		if(pSectionId == null) {
			pSectionId = 0;
		}
		
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				System.out.println("Connection to DB successful!");
				// make query to get count by time and section
				StringBuilder query = new StringBuilder(
						"SELECT mac_address, update_time, section_id FROM tracking WHERE 1=1 ");
				query.append("AND update_time >= ? AND update_time <= ? ");
				if(pSectionId > 0) {
					query.append("AND section_id = ? ");
				}
				query.append("ORDER BY update_time ASC ");
				PreparedStatement stmt = dBConn.prepareStatement(query.toString());
				stmt.setTimestamp(1, new Timestamp(pFromTime));
				stmt.setTimestamp(2, new Timestamp(pToTime));
				if(pSectionId > 0) {
					stmt.setInt(3, pSectionId);
				}
				
				//store time in section for each mac address
				Map<String, LinkedList<Timestamp>> macInSection = new HashMap<>();
				//get result
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					String macAddress = rs.getString(1);
					Timestamp updateTime = rs.getTimestamp(2);
					//add to process
					LinkedList<Timestamp> timeInSection = macInSection.get(macAddress);
					if(timeInSection == null) {
						timeInSection = new LinkedList<>();
					}
					timeInSection.add(updateTime);
					macInSection.put(macAddress, timeInSection);
				}
				rs.close();
				stmt.close();
				//process to get people first time and period of time
				Set<String> keySet = macInSection.keySet();
				for(String key : keySet) {
					LinkedList<Timestamp> timeInSection = macInSection.get(key);
					//calculate period of time by adding all time interval
					long periodOfTime = 0l;
					Timestamp firstTime = timeInSection.get(0);
					for(Timestamp ts : timeInSection) {
						periodOfTime += ts.getTime() - firstTime.getTime();
						//reassign first time to current time variable
						firstTime = ts;
					}
					System.out.println("mac= " + key + ". Period of time= " + periodOfTime);
					//add to result
					TrackingEntity tracking = new TrackingEntity();
					tracking.setMacAddress(key);
					tracking.setSectionId(pSectionId);
					tracking.setFirstTime(timeInSection.get(0));
					tracking.setPeriodOfTime(periodOfTime);
					result.add(tracking);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Query failed!");
			}
			finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
