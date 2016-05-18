package com.bdi.duclv.brmh.service;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImportData {

//	public static void main(String[] args) {
//		ImportData i = new ImportData();
//		i.importData();
//	}
	
	private void importData() {
		Connection conn = null;
		try {
			conn = DataSource.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		if(conn != null) {
			try {
				//create sql
				StringBuilder sql = new StringBuilder();
				sql.append("insert into tracking2(mac_address,time,section_id) values(?, ?, ?)");
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				conn.setAutoCommit(false);
				
				String filePath = "/home/duclv/Downloads/output2.txt";
				File file = new File(filePath);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				String pattern = "yyyy-MM-dd HH:mm:ss z";
				SimpleDateFormat dfm = new SimpleDateFormat(pattern, Locale.KOREA);
				int batchSize = 100;
				int i = 0;
				while((line = reader.readLine()) != null) {
					//process each line
					//split by space
					String[] data = line.split("\\s+", 4);
					String macAddress = data[0];
					String date = data[1];
					String time = data[2];
					String sectionId = data[3];
					//set data
					stmt.setString(1, macAddress);
					Date dateTime = dfm.parse(date + " " + time + " GMT+09:00");
					stmt.setTimestamp(2, new java.sql.Timestamp(dateTime.getTime()));
					stmt.setInt(3, Integer.parseInt(sectionId));
					stmt.addBatch();
					i++;
					if(i % batchSize == 0) {
						stmt.executeBatch();
						conn.commit();
						System.out.println("Inserted " + i + " records !");
					}
				}
				stmt.executeBatch();
				conn.commit();
				stmt.close();
				reader.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
