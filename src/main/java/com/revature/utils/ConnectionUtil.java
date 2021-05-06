package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.secrets.Secrets;

public class ConnectionUtil {

	static Secrets secret = new Secrets();

	public static Connection getConnection() throws SQLException {
		// register our driver. makes the application aware of what particular driver
		// class we are using.
		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// "jdbc:postgresql:endpoint:5432:demos"
		String url = "jdbc:postgresql://rev-appian.chmqmoijx1w4.us-east-1.rds.amazonaws.com:5432/demos";
		String username = secret.getUsername();
		String password = secret.getPassword();

		return DriverManager.getConnection(url, username, password);

	}
}
