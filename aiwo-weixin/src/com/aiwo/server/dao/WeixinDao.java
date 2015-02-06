package com.aiwo.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import com.aiwo.server.connect.DB;

public class WeixinDao {

	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	/**
	 * 
	 * 扫描关注公众号时记录用户信息
	 * 
	 * @param jsonObject
	 */
	public void insertOr(JSONObject jsonObject) {

		try {
			
			if(!queryOpenId(jsonObject.getString("openid"))){
				conn = DB.getConn();
				String sql = "insert into weixin(openId,niceName,sex,country,provice,city) values(?,?,?,?,?,?)";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, jsonObject.getString("openid"));
				pStmt.setString(2, jsonObject.getString("nickname"));
				pStmt.setString(3, jsonObject.getString("sex"));
				pStmt.setString(4, jsonObject.getString("country"));
				pStmt.setString(5, jsonObject.getString("province"));
				pStmt.setString(6, jsonObject.getString("city"));
				pStmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

	}

	/**
	 * 
	 * 取消关注时删除记录的用户信息
	 * 
	 * 
	 * @param jsonObject
	 */
	public void delete(String openId) {
		try {
			conn = DB.getConn();
			String sql = "delete from weixin where openId=?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, openId);
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
	 * 用户登录绑定微信
	 * 
	 * @param userId
	 * 
	 */

	public void bindAppUser(String userId, String openId) {

		try {
			conn = DB.getConn();
			String sql = "update weixin set userId=? where openId=?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			pStmt.setString(2, openId);
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
	 * 判断微信关注公众号后是否绑定app帐号
	 * 
	 * 
	 * @param openid
	 * @return
	 */
	public boolean bandWx(String openid) {

		boolean falg = false;

		try {
			
			
			conn = DB.getConn();
			String sql = "select * from weixin where openId = ? and userId is not null ";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, openid);

			rs = pStmt.executeQuery();

			if (rs.next()) {
				falg = true;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}
		return falg;
	}

	
	
	/**
	 * 
	 * 判定该微信用户是否关系过该公众号
	 * 
	 * @param openId
	 * @return
	 */
	
	private boolean queryOpenId(String openId){
		
		boolean flag = false ;
		
		try {
			
			conn = DB.getConn() ;
			String sql = "select * from weixin where openId = ?" ;
			pStmt = conn.prepareStatement(sql) ;
			pStmt.setString(1, openId) ;
			rs = pStmt.executeQuery() ;
			if(rs.next()){
				flag = true ;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag ;
	}

	
	/**
	 * 
	 * 
	 * 
	 * @param openId
	 * @return
	 */
	public int getUserid(String openId) {
		
		try {
			conn = DB.getConn() ;
			String sql = "select * from weixin where openId = ?" ;
			pStmt = conn.prepareStatement(sql) ;
			pStmt.setString(1, openId) ;
			rs = pStmt.executeQuery() ;
			if(rs.next()){
				return rs.getInt("userId");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
}
