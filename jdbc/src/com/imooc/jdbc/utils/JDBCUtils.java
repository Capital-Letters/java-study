package com.imooc.jdbc.utils;
/**
 * JDBC工具类
 * @author admin
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static final String driverClass;
	private static final String url;
	private static final String username;
	private static final String password;
	
	static {
		//加载属性文件并解析
		Properties props = new Properties();
		//通常用类的加载器的方式获取输入流
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driverClass = props.getProperty("driverClass") ;
		url = props.getProperty("url") ;
		username = props.getProperty("username") ;
		password = props.getProperty("password") ;
	}
	
	
	
	/**
	 * 注册驱动方法
	 * @throws ClassNotFoundException 
	 */
	public static void loadDriver() throws ClassNotFoundException {
		Class.forName(driverClass);
	}
	
	/**
	 * 获得连接的方法
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws Exception {
		loadDriver();
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	/**
	 * 资源释放
	 */
	public static void release(Statement stmt , Connection conn ) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	public static void release(ResultSet rs ,Statement stmt , Connection conn) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
}
