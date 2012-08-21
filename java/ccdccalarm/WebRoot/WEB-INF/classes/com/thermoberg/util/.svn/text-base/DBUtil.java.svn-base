package com.thermoberg.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




/**
 *  数据访问接口
 * @author Administrator
 *
 */
public class DBUtil {

	/**
	 * 取得连接
	 * @return
	 */
	public static synchronized Connection getConn(){	
		return ConnnectionFactory.getConnection() ; 
	}
	
	/**
	 * 关闭连接
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
	 * 关闭连接操作对象
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
	 * 关闭结果集
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
