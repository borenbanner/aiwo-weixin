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
import com.aiwo.server.pojo.Product;

public class ProductDao {

	private Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;

	/**
	 * 插入信息
	 * 
	 * @param params
	 */
	public void insert(Map<String, String> params) {

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
			sb.append("insert into product(proTime,");
			String values = "(SYSDATE(),";

			for (Map.Entry<String, String> param : params.entrySet()) {

				sb.append(param.getKey() + ",");
				values += "'"+param.getValue() + "',";
			}
			values = values.substring(0, values.length() - 1) + ")";
			String filed = sb.toString();
			filed = filed.substring(0, filed.length() - 1) + ") ";

			return filed +" values "+ values;
		}
		if (name.equals(Config.UPDATE)) {
			sb.append("update product set ");

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
	public void update(Map<String, String> params, String proId) {

		try {
			conn = DB.getConn();
			String sql = getSql(params, Config.UPDATE);
			sql = sql + " where proId = " + proId;
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
	 * 统计条数
	 * 
	 * @return
	 */
	public int queryCount() {
		try {
			conn = DB.getConn();
			String sql = "select count(*) from product";
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

	/**
	 * 
	 * 所有的
	 * 
	 * 
	 * @param pageCount
	 * @return
	 */
	public List<Product> queryAll(int pageCount) {

		List<Product> listProduct = new ArrayList<Product>();

		try {
			conn = DB.getConn();
			String sql = "select * from product order by proTime desc  limit "
					+ pageCount + ",10 ";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				listProduct.add(product);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}

		return listProduct;
	}

	/**
	 * 
	 * 查询单个的内容
	 * 
	 * @param proId
	 * @return
	 */
	public Product queryObject(String proId) {

		Product product = null;
		try {
			conn = DB.getConn();
			String sql = "select * from product  where proId = ?";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, proId);

			rs = pStmt.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}
		return product;
	}

	/**
	 * 
	 * 
	 * 
	 * @param proId
	 */
	public void delete(String proId) {
		try {
			conn = DB.getConn();
			String sql = "delete from product  where proId = ?";
			System.out.println(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, proId);
			pStmt.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeAll(conn, pStmt, rs);
		}
	}

}
