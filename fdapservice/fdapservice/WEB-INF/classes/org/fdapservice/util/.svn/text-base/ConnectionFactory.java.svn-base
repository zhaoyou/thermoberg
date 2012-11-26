package org.fdapservice.util;

import java.sql.Connection;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取连接的工厂
 * @author zhaoyou
 *
 */
public class ConnectionFactory {
		
		private static ConnectionFactory connectionFactory  = null  ;
		
		private static ComboPooledDataSource ds = null ;
		
		static {
			try {
				Properties pro = new Properties();
				pro.load(ConnectionFactory.class.getResourceAsStream("../../../db.properties")) ;
				ds = new ComboPooledDataSource();
				ds.setDriverClass(pro.getProperty("driverClass"));
				ds.setJdbcUrl(pro.getProperty("url")) ;
				ds.setUser(pro.getProperty("user")) ;
				ds.setPassword(pro.getProperty("password"));
				ds.setMinPoolSize(Integer.parseInt(pro.getProperty("minPoolSize"))) ;
				ds.setMaxPoolSize(Integer.parseInt(pro.getProperty("maxPoolSize"))) ;
				ds.setInitialPoolSize(Integer.parseInt(pro.getProperty("initialPoolSize"))) ;
		} catch (Exception e) {
			System.out.println("初始化连接池失败: "+e.getMessage());
		}
		}
		
		private ConnectionFactory(){
		}
		
		public static  ConnectionFactory getInstance(){
			if(connectionFactory==null){
				connectionFactory = new ConnectionFactory();
			}
			return connectionFactory ;
		}
		
		/**
		 * 获取连接信息
		 * @return
		 */
		public Connection getConnection(){
			try {
				return ds.getConnection() ;
			} catch (Exception e) {
				e.printStackTrace() ;
				return null ;
			}
			
		}
		
		public static void main(String[] args) {
			System.out.println(ConnectionFactory.getInstance().getConnection().hashCode()) ;
		}
		
}
