package com.dd.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SqlHelper {
	private static Connection connection;
	private static PreparedStatement ps;
	private ResultSet rs;

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	public static Connection getConnection() {
		return connection;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	static {
		try {
			Map<String, String> map = get();
			driver  = map.get("driver");
			url = map.get("url");
			username = map.get("username");
			password = map.get("password");

			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//初始化变量
	public static Map<String, String> get() {
		Properties pp = new Properties();
		InputStream is = null;
		Map<String,String> map = new HashMap<String, String>();
		try {
			is = SqlHelper.class.getClassLoader().getResourceAsStream("com/dd/asset/db.properties");
			pp.load(is);
			driver = pp.getProperty("driver");
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			password = pp.getProperty("password");
			map.put("driver", driver);
			map.put("url", url);
			map.put("username", username);
			map.put("password", password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	//增删改方法
	public void executeUpdate(String sql,String []parameters) {
		try {
			connection = DriverManager.getConnection(url, username, password);
			ps = connection.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(connection, ps, rs);
		}
	}

	//查询方法
	public ArrayList<Object[]> executeQuery(String sql, String[] parameters) {
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		try {
			connection = DriverManager.getConnection(url, username, password);
			ps = connection.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			//执行查询
			rs = ps.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int column = metaData.getColumnCount();
			while (rs.next()) {
				Object[] objects = new Object[column];
				for (int i = 1; i <= column; i++) {
					objects[i -1] = rs.getObject(i);
				}
				arrayList.add(objects);
			}

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			//关闭资源
			//SQLHelper.close(connection, ps, rs);
		}
		return arrayList;
	}

	/**
	 * 传入表名返回该表的纪录数
	 * @param sql
	 * @return rowCount
	 */
	public int getRowCount(String sql) {
		int rowCount = 0;
		connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	//关闭资源
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
