package com.aiwo.server.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		Connection conn = null ;
		
		Class.forName("com.mysql.jdbc.Driver") ;
		String url =Config.getData("url");
		conn = DriverManager.getConnection(url,Config.getData("userName"),Config.getData("password"));
		
		return  conn;
	}

	
	
	public static void closeAll(Connection conn, PreparedStatement pStmt, ResultSet rs) {
		 try {
			if(rs != null){
				 rs.close();
			 }
			 if(pStmt != null){
				 pStmt.close();
			 }
			 if(conn != null){
				 conn.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
