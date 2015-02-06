package com.aiwo.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.aiwo.server.connect.DB;
import com.aiwo.server.pojo.Us;

public class UsDao {

	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	/**
	 * 
	 * 
	 * 
	 * @param params
	 */
	public void updateUs(Map<String, String> params) {
		try {
			conn = DB.getConn();
			String sql = getSql(params);
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

	}

	/**
	 * 
	 * 
	 * @param params
	 * @return
	 */
	private String getSql(Map<String, String> params) {

		StringBuffer sb = new StringBuffer();
		sb.append("update aboutus set ");

		for (Map.Entry<String, String> param : params.entrySet()) {

			sb.append(param.getKey() + " = '" + param.getValue() + "',");
		}

		String update = sb.toString();
		update = update.substring(0, update.length() - 1);

		return update;
	}

	public Us queryUs() {

		try {
			conn = DB.getConn();
			String sql = "select * from aboutus limit 1";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				return new Us(rs.getInt("usId"), rs.getString("usContent"), rs.getString("usTime"),
						rs.getString("usTitle"), rs.getString("usImage"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

		return null;
	}

}
