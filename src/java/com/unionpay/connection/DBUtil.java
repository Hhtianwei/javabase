package com.unionpay.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/*
 * 数据库工具类：
 * 1、负责连接数据库， 返回连接
 * 2、负责释放资源
 */
public class DBUtil {
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void getConn() {
		Properties pro = new Properties();
		Connection conn = null;
		
		try {
			pro.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			conn = DriverManager.getConnection(pro.getProperty("URL"), pro.getProperty("name"), pro.getProperty("pwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeAll(Connection conn,Statement statement){
		closeAll(conn,statement,null);
	}
	
	public static void closeAll(Connection conn,Statement statement,ResultSet result){
		if(result!=null){
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}












