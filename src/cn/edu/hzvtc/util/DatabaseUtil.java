package cn.edu.hzvtc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接与关闭工具类。
 */
public class DatabaseUtil {
	private static String driver = ConfigManager.getProperty("jdbc.driver");// 数据库驱动字符串
	private static String url = ConfigManager.getProperty("jdbc.url");// 连接URL字符串
	private static String user = ConfigManager.getProperty("jdbc.user");// 数据库用户名
	private static String password = ConfigManager.getProperty("jdbc.password");// 用户密码
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接对象
	 * @throws SQLException 
	 */
	public static Connection getConnection(){
		//获取连接并捕获异常
		Connection conn=null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return conn;//返回连接对象
	}

	public static void closeAll(Connection conn,Statement stmt,ResultSet rs) {
		//若结果集对象不为空，则关闭
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//若Statement集对象不为空，则关闭
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//若数据库连接对象不为空，则关闭
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}


