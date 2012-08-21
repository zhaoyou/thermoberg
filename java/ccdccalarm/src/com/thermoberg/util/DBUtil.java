package com.thermoberg.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




/**
 *  ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public class DBUtil {

	/**
	 * ȡ������
	 * @return
	 */
	public static synchronized Connection getConn(){	
		return ConnnectionFactory.getConnection() ; 
	}
	
	/**
	 * �ر�����
	 * @param conn
	 * @throws SQLException
	 */
	public static synchronized void closeConn(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
			} catch (Exception e) {
				e.printStackTrace() ;
				conn = null ;
			}
		
	}
	
	/**
	 * �ر����Ӳ�������
	 * @param st
	 */
	public static synchronized void closeStatement(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			st = null;
		}
	}
	
	/**
	 * �رս����
	 * @param rs ResultSet
	 */
	public static synchronized void closeRs(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rs = null;
		}
	}
}
