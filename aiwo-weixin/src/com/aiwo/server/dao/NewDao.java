package com.aiwo.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aiwo.server.connect.Config;
import com.aiwo.server.connect.DB;
import com.aiwo.server.pojo.New;

public class NewDao {

	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public void insertNew(Map<String, String> params) {

		try {
			conn = DB.getConn();
			String sql = getSql(params, Config.INSERT);
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
	 * sql语句
	 * 
	 * @param params
	 * 
	 *            参数
	 * @return
	 * 
	 *         sql语句
	 */
	private String getSql(Map<String, String> params, String name) {

		StringBuffer sb = new StringBuffer();

		if (name.equals(Config.INSERT)) {
			sb.append("insert into news(newTime,");
			String values = "(SYSDATE(),";

			for (Map.Entry<String, String> param : params.entrySet()) {

				sb.append(param.getKey() + ",");
				values += "'" + param.getValue() + "',";
			}
			values = values.substring(0, values.length() - 1) + ")";
			String filed = sb.toString();
			filed = filed.substring(0, filed.length() - 1) + ") ";

			return filed + " values " + values;
		}
		if (name.equals(Config.UPDATE)) {
			sb.append("update news set newTime = SYSDATE(),");

			for (Map.Entry<String, String> param : params.entrySet()) {

				sb.append(param.getKey() + " = '" + param.getValue() + "',");
			}

			String update = sb.toString();
			update = update.substring(0, update.length() - 1);

			return update;
		}

		return null;
	}

	/**
	 * 
	 * 
	 * 修改
	 * 
	 * @param params
	 * 
	 * 
	 * @param proId
	 */
	public void update(Map<String, String> params, String newId) {

		try {
			conn = DB.getConn();
			String sql = getSql(params, Config.UPDATE);
			sql = sql + " where newId = " + newId;
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

	public int getCount() {
		try {
			conn = DB.getConn();
			String sql = "select count(*) from news";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}
		return 0;
	}

	public List<New> queryAll(int pageCount) {
		List<New> listNew = new ArrayList<New>();

		try {
			conn = DB.getConn();
			String sql = "select * from news order by newTime desc  limit "
					+ pageCount + ",10 ";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				New news = new New(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
				listNew.add(news);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

		return listNew;
	}

	public New queryNew(String newId) {

		try {
			conn = DB.getConn();
			String sql = "select * from news  where newId = ?";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, newId);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				return new New(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(conn, pStmt, rs) ;
		}
		return null;
	}

	public void deleteNew(String newId) {
		try {
			conn = DB.getConn();
			String sql = "delete from news where newId = ?";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, newId) ;
			pStmt.execute() ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(conn, pStmt, rs) ;
		}
		
	}
	
//	public static void main(String[] args) {
//		String ss = "afnieanf.jsp"  ;
//		System.out.println(ss.substring(ss.lastIndexOf(".")));
//	}

}
