package cn.edu.hzvtc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ�������رչ����ࡣ
 */
public class DatabaseUtil {
	private static String driver = ConfigManager.getProperty("jdbc.driver");// ���ݿ������ַ���
	private static String url = ConfigManager.getProperty("jdbc.url");// ����URL�ַ���
	private static String user = ConfigManager.getProperty("jdbc.user");// ���ݿ��û���
	private static String password = ConfigManager.getProperty("jdbc.password");// �û�����
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݿ����Ӷ���
	 * @throws SQLException 
	 */
	public static Connection getConnection(){
		//��ȡ���Ӳ������쳣
		Connection conn=null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

		return conn;//�������Ӷ���
	}

	public static void closeAll(Connection conn,Statement stmt,ResultSet rs) {
		//�����������Ϊ�գ���ر�
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��Statement������Ϊ�գ���ر�
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�����ݿ����Ӷ���Ϊ�գ���ر�
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}


