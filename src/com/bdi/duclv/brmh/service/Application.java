package com.bdi.duclv.brmh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    	//test HanaDB
    	/*Connection dBConn = null;
		try {
			DataSource ds = DataSource.getInstance();
			dBConn = ds.getConnection();
			String sql = "select count(a.mobile_mac) \"NumberOfPeople\" from wifi_backup.mobile_location a";
			Statement stmt = dBConn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			StringBuilder msg = new StringBuilder();
	          for (int i = 1; i < md.getColumnCount() + 1; i++) {
	            if (i > 1) {
	              msg.append("\t");
	            }
	            msg.append(md.getColumnLabel(i));
	          }
	          rs.close();
	          stmt.close();
	          dBConn.close();
	          System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Connection Failed !!!");
		}*/
		
        //make test data
//        Timer timer = new Timer();
//        long period = 60 * 1000; //1 minute
        //timer.schedule(new MakeTestData(), 0, period);
    }
}
