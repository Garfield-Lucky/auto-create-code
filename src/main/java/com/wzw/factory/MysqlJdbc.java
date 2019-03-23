package com.wzw.factory;

import java.sql.*;

public class MysqlJdbc {

	public static String dbUrl = "jdbc:mysql://localhost:3306/springboot?characterEncoding=utf-8&serverTimezone=UTC";
	public static String theUser = "work";
	public static String thePw = "123456";
	Connection conn = null;
	Statement stmt;
	ResultSet rs = null;

	public MysqlJdbc() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, theUser, thePw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean executeUpdate(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void close() {
		try {
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void closeConnection(Connection con) {
		try {

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ResultSet rs;
		MysqlJdbc conn = new MysqlJdbc();
		rs = conn.executeQuery("select * from student t");
		try {
			while (rs.next()) {
				System.out.println(rs.getString("age"));
				System.out.println(rs.getString("sex"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}