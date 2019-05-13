package com.wzw.util;

import com.wzw.factory.DriverFactory;

import java.sql.*;

public class ConnectionUtil {

	public static String dbUrl = "jdbc:mysql://localhost:3306/springboot?characterEncoding=utf-8&serverTimezone=UTC";
	public static String theUser = "work";
	public static String thePw = "123456";
	Connection conn = null;
	Statement stmt;
	ResultSet rs = null;

	//获取表中的字段名称、字段类型、字段描述、字段类型长度(oracle数据库)
	private static String METADATA_ORACLE="select A.column_name, A.data_type,  B.comments, a.data_length, from " +
			" user_tab_columns A,user_col_comments B where A.table_name = B.table_name and A.column_name = B.column_name" +
			" and A.table_name = ? order by a.column_id asc";

	//获取表中的字段名称、字段类型、字段描述、字段类型长度(mysql数据库)
	private static String METADATA_MYSQL = "select column_name, data_type, character_maximum_length as data_length, " +
			" column_comment as comments from information_schema.columns where table_schema = ? and table_name = ? ;";

	//获取表名的描述信息(oracle数据库)
	private static String COMMENTS_ORACLE="select table_name, comments from user_tab_comments where table_name = ?";

	//获取表名的描述信息(mysql数据库)
	private static String COMMENTS_MYSQL = "select column_comment as comments from information_schema.columns where table_name = ?";

	public ConnectionUtil() {
		try {
			Class.forName(DriverFactory.getDriver(dbUrl));
			conn = DriverManager.getConnection(dbUrl, theUser, thePw);
		} catch (Exception e) {
			e.printStackTrace();
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

	public Connection getConn() {

		return conn;
	}

	 /**
	  * @Description: 通过不同数据库，返回不同的sql语句
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/5/13 20:02
	  */
	public static String getMetaDataSql(){
		if (dbUrl.contains("mysql")) {
			return METADATA_MYSQL;
		}
		if (dbUrl.contains("oracle")) {
			return METADATA_ORACLE;
		}
		return null;
	}

	 /**
	  * @Description: 通过不同数据库，返回不同的sql语句
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/5/13 20:01
	  */
	public static String getTableCommentsSql(){
		if (dbUrl.contains("mysql")) {
			return COMMENTS_MYSQL;
		}
		if (dbUrl.contains("oracle")) {
			return COMMENTS_ORACLE;
		}
		return null;
	}

	//测试数据库连接
	public static void main(String[] args) {
		ResultSet rs;
		ConnectionUtil conn = new ConnectionUtil();
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

}