package com.bdi.duclv.brmh.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.TimerTask;

public class MakeTestData extends TimerTask {
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
	
	public void run() {
		Connection dBConn = getDBConnection();
		if (dBConn != null) {
			try {
				//random sectionId
				int sectionId = (int) new Random().nextInt(1000) % 8;
				if(sectionId == 0) {
					sectionId = 1;
				}
				//random mac address
				String[] macAddress = 
					{"00:04:96:3b:42:f1", "00:04:96:3a:3f:cc", "00:04:96:3b:42:f1",
							"00:04:96:3b:42:aa", "00:04:96:3b:42:bb", "00:04:96:3b:42:c2",
							"00:04:96:3b:42:d1", "00:04:96:3b:42:ff", "00:04:96:3b:42:a3",
							"00:04:96:3b:55:a4", "00:04:96:3b:33:dd", "00:04:96:3b:22:d2"};
				int macRandomId = new Random().nextInt(12);
				StringBuilder sql = new StringBuilder();
				sql.append("insert into tracking(mac_address,update_time,section_id) values(?, ?, ?)");
				PreparedStatement stmt = dBConn.prepareStatement(sql.toString());
				stmt.setString(1, macAddress[macRandomId]);
				stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				stmt.setInt(3, sectionId);
				stmt.execute();
				stmt.close();
				System.out.println("Inserted to section: " + sectionId);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					dBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
