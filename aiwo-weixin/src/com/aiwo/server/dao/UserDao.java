package com.aiwo.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aiwo.server.connect.DB;
import com.aiwo.server.pojo.Device;
import com.aiwo.server.pojo.Mac;
import com.aiwo.server.pojo.User;
import com.mysql.jdbc.Statement;

public class UserDao {

	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	/**
	 * 
	 * 
	 * app用户登录
	 * 
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */

	public User login(String userName, String userPwd) {

		User user = null;
		try {
			conn = DB.getConn();
			String sql = "select * from users where userName=? and userPwd= ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userName);
			pStmt.setString(2, userPwd);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

		return user;
	}

	/**
	 * 
	 * 根据userId 返回设备的数据
	 * 
	 * @param userId
	 * @return
	 */
	public List<Device> getAllDate(final List<Mac> macList) {

		List<Device> deviceList = new ArrayList<Device>();

		try {
			conn = DB.getConn();
			String sql = getSql(macList);
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				Device device = new Device(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7),rs.getString(8));
				deviceList.add(device);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deviceList;
	}

	/**
	 * 
	 * 获取查询sql
	 * 
	 * @param macList
	 * @return
	 */
	private String getSql(List<Mac> macList) {

		String sql = null ;
		
		if (macList.size() == 1) {
			 sql = "select tmpId,mac,wd,sd,lux,pm,'"
					+ macList.get(0).getMacName() + "' as deviceName,'"+macList.get(0).getMac_id()+"' as macId from "
					+ macList.get(0).getMac() + " order by tmpId desc  limit 1";
			return sql;
		} else {

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < macList.size(); i++) {
				sb.append("(select tmpId,mac,wd,sd,lux,pm,'"
						+ macList.get(i).getMacName() + "' as deviceName,'"+macList.get(i).getMac_id()+"' as macId from "
						+ macList.get(i).getMac() + " order by tmpId desc limit 1)");
				sb.append(" union all ");
				sql = sb.toString();
				sql = sql.substring(0, sql.lastIndexOf(" union all "));
			}
			

			return sql;
		}
	}

	/**
	 * 
	 * 获取用户绑定设备的mac码
	 * 
	 * 
	 * @param userId
	 * 
	 *            用户Id
	 * @return
	 * 
	 *         用户绑定的不止一个设备返回List集合
	 * 
	 */
	public List<Mac> getMac(int userId) {

		List<Mac> macList = new ArrayList<Mac>();
		try {
			conn = DB.getConn();
			String sql = "SELECT user_mac.`user-mac`,user_mac.macId,macName,mac FROM user_mac,mac WHERE user_mac.`macId`=mac.`macId` AND user_mac.userId = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				Mac mac = new Mac(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4));
				macList.add(mac);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

		return macList;
	}

	/**
	 * 
	 * 
	 * 
	 * 手动添加设备
	 * 
	 * 
	 * @param fieldMap
	 * 
	 *            以Map形式封装的各个属性
	 * @return
	 * 
	 * 
	 * 
	 * 
	 *         返回插入数据库自动生成的主键
	 */

	public int insert(Map<String, String> fieldMap) {

		try {
			conn = DB.getConn();
			String sql = "insert into mac(mac,macName) values(?,?) ";
			pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, fieldMap.get("mac"));
			pStmt.setString(2, fieldMap.get("macName"));
			pStmt.executeUpdate();
			rs = pStmt.getGeneratedKeys();
			while (rs.next()) {
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

	/**
	 * 
	 * 
	 * 插入到相应的
	 * 
	 * 
	 * @param userId
	 * 
	 * 
	 *            用户Id
	 * @param macId
	 * 
	 *            记录mac的唯一标识
	 * 
	 */
	public void insertUserMac(String userId, int macId) {

		try {
			conn = DB.getConn();
			String sql = "insert into user_mac(macId,userId) values(?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, macId);
			pStmt.setString(2, userId);

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
	 * 微信用户更改设备名称
	 * 
	 * 
	 * @param mapUpdate
	 * 
	 * 
	 */

	public void update(Map<String, String> mapUpdate) {

		try {
			conn = DB.getConn();
			String sql = "update mac set macName = ? where macId = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mapUpdate.get("macName"));
			pStmt.setString(2, mapUpdate.get("macId"));
			pStmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

	}

	/**
	 * 微信用户删除设备
	 * 
	 * @param macId
	 */
	public void delete(String macId) {

		try {
			conn = DB.getConn();
			String sql = "delete a,b from mac a,user_mac b where a.macId = b.macId and a.macId = ?";

			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, macId);
			pStmt.execute();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// public static void main(String[] args) {
	//
	// List<String> ma = new ArrayList<String>();
	// ma.add("mac1") ;
	// ma.add("mac2") ;
	// String sql = new UserDao().getSql(ma) ;
	// System.out.println(sql);
	// }
}
