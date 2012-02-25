package org.fdapservice.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库操作的
 * @author zhaoyou
 *
 */
public class DBUtil {
		/**
		 * 获取数据库连接操作
		 * @return		Connection 数据库连接
		 */
		public static Connection getCon() {
			return ConnectionFactory.getInstance().getConnection() ;
		}
		
		
		/**
		 * 关闭数据库连接
		 * @param con		需要关闭的数据库连接
		 */
		public static void closeCon(Connection con) {
			try {
				if(con!=null ){
					con.close() ;
				}
			} catch (Exception e) {
				e.printStackTrace() ;
			}		
		}
		
		/**
		 * 关闭数据操作对象
		 * @param state		需要关闭的数据库操作对象
		 */
		public static void closeStatement(Statement state){
			try {
				if(state!=null){
					state.close() ;
				}
			} catch (Exception e) {
				e.printStackTrace() ;
			}	
		}
		
		
		/**
		 * 关闭结果集对象
		 * @param rs		需要关闭的结果集对象
		 */
		public static void closeResultSet(ResultSet rs){
			try {
				if(rs!=null){
					rs.close() ;
				}
			} catch (Exception e) {
				e.printStackTrace() ;
			}
		}
}
