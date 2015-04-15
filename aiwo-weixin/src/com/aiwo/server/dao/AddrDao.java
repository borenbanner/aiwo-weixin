package com.aiwo.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.aiwo.server.connect.DB;
import com.aiwo.server.pojo.Addr;

public class AddrDao {

	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	/**
	 * 
	 * 底层数据处理
	 * 
	 * 
	 * @param params
	 */
	public void insertAddr(Map<String, String> params) {

		try {
			conn = DB.getConn();
			String sql = getSql(params);
			pStmt = conn.prepareStatement(sql);
			pStmt.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(conn, pStmt, rs) ;
		}
	}

	/**
	 * 
	 * 根据参数创建sql语句
	 * 
	 * 
	 * @param params
	 * @return
	 */
	private String getSql(Map<String, String> params) {

		StringBuffer sb = new StringBuffer();
		String insert = ("insert into addr(");
		String values = "  values(";

		for (Map.Entry<String, String> m : params.entrySet()) {
			insert += m.getKey() + ",";
			values += "'" + m.getValue() + "'" + ",";

		}
		insert = insert.substring(0,insert.length()-1) + ")";
		values = values.substring(0,values.length()-1)+")" ;
		sb.append(insert) ;
		sb.append(values) ;
		return sb.toString();
	}

	/**
	 * 
	 * 
	 * 查询微信个人标识 查询用户地址
	 * 
	 * @param openId
	 * @return
	 */
	public Addr queryAddr(String openId) {

		try {
			conn = DB.getConn();
			String sql = "select * from addr where openid="+openId;
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery() ;
			if(rs.next()){
				return new Addr(rs.getInt("addrId"),rs.getString("openid"),rs.getString("userName"),rs.getString("tel"),rs.getString("addr"),rs.getString("postal_code"));
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

}
