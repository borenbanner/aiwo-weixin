package com.aiwo.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.aiwo.pojo.Bill;
import com.aiwo.server.connect.DB;
import com.aiwo.util.ConvertPojo;

public class PayDao {


	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	/**
	 * 
	 * 
	 * 插入数据库
	 * 
	 * 
	 * 
	 * @param map
	 */
	public void insert(Map<String, String> map) {

		try {
			conn = DB.getConn();
			String sql = getSql(map);
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
	 * 根据参数的多少生成sql语句
	 * 
	 * 
	 * 
	 * @param map
	 * @return
	 */
	private String getSql(Map<String, String> map) {

		StringBuffer sb = new StringBuffer();
		
		sb.append("insert into pay_return(");
		String values = "(";
		for (Map.Entry<String, String> param : map.entrySet()) {

			sb.append(param.getKey() + ",");
			values += "'"+param.getValue() + "',";
		}
		values = values.substring(0, values.length() - 1) + ")";
		String filed = sb.toString();
		filed = filed.substring(0, filed.length() - 1) + ") ";

		return filed +" values "+ values;
		
	}


	/**
	 * 
	 * 查询所有的订单
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public List<Bill> query(int page) throws Exception {
		
		try {
			conn = DB.getConn();
			String sql = "SELECT proName,transaction_id,out_trade_no,openId,is_subscribe,total_fee,fee_type  FROM pay_return,product WHERE attach=proId limit "+page+",10" ;
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			rs  = pStmt.executeQuery() ;
			
			return ConvertPojo.getBean(rs, new Bill()) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.closeAll(conn, pStmt, rs) ;
		}
		return null;
	}


	
	/**
	 * 
	 * 
	 * 统计条数
	 * @return
	 */
	public int getCount() {
		
		try {
			conn = DB.getConn();
			String sql = "select count(*) from pay_return";
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
	
	
	
	
	
}
