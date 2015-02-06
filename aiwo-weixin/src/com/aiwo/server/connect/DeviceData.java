package com.aiwo.server.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class DeviceData {
	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public String getData() {

		String result = "";
		;
		try {

			conn = DB.getConn();
			String sql = "select * from m9610 order by tmpId desc limit 0,1";
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				result = "\n设备mac：" + rs.getString(2) + "\n温度："
						+ subResult(rs.getString(3)) + " ℃\n湿度："
						+ subResult(rs.getString(4)) + " %\n光照："
						+ subResult(rs.getString(5)) + " lux\nPM2.5："
						+ subResult(rs.getString(6)) + " μg/m3" + "\n时间："
						+ this.getTime();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}
		return result;
	}

	private String subResult(String result) {

		if (result.contains(".")) {
			return result.substring(0, result.indexOf(".") + 2);
		} else {

			return result;
		}
	}

	private String getTime() {
		int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int min = Calendar.getInstance().get(Calendar.MINUTE);
		int sec = Calendar.getInstance().get(Calendar.SECOND);
		String minute = min < 10 ? "0" + min : String.valueOf(min);
		String second = sec < 10 ? "0" + sec : String.valueOf(sec);
		String hour = h < 10 ? "0" + h : String.valueOf(h);
		return hour + ":" + minute + ":" + second;
	}

//	public static void main(String[] args) {
//		String time = new DeviceData().subResult("123");
//		System.out.println(time);
//	}
}
