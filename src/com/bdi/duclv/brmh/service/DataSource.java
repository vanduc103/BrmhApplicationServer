package com.bdi.duclv.brmh.service;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {
	private static DataSource datasource;
	private ComboPooledDataSource cpds;

	private DataSource() throws IOException, SQLException, PropertyVetoException {
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver"); // loads the jdbc driver
		cpds.setJdbcUrl("jdbc:mysql://147.47.206.15:13306/brmh_test?characterEncoding=utf8");
		cpds.setUser("root");
		cpds.setPassword("duclv");

		// the settings below are optional -- c3p0 can work with defaults
		cpds.setMinPoolSize(10);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(250);
		cpds.setMaxStatements(1800);
		cpds.setCheckoutTimeout(10 * 1000); //10s

	}

	public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (datasource == null) {
			datasource = new DataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		Connection connection = this.cpds.getConnection();
		System.out.println("Num conn = " + this.cpds.getNumConnections() + ", num idle = " + this.cpds.getNumIdleConnections()
		+ ", num busy = " + this.cpds.getNumBusyConnections());
		return connection;
	}

}
