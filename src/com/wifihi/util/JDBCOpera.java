package com.wifihi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCOpera {

	public static Connection CreateConn()
			throws SQLException, ClassNotFoundException {
		String username="root";
		String password="root";
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/Wuxianhui";
		System.out.println("url= " + url);
		System.out.println("the start time of connection = "+ new java.util.Date());
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("the finish time of connection = "+ new java.util.Date());
		return con;
	}

}
