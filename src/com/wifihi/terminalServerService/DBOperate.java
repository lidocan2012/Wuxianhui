package com.wifihi.terminalServerService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBOperate {
	private static Connection conn;
	private static Statement stmt;
	
	private static final DBOperate dbOperate = new DBOperate();
	private DBOperate(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wuxianhui", "root", "123456");
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static DBOperate getDBOperate(){
		return dbOperate;
	}
	
	public Statement getStatement(){
		return stmt;
	}	
	
	public static Connection getConn() {
		return conn;
	}

	public void closeDB(){
		try {
			if(stmt != null && !stmt.isClosed()){
				stmt.close();
			}
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
