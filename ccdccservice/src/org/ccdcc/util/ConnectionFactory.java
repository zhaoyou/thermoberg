package org.ccdcc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private static ConnectionFactory factory = null ;
	
	private static ComboPooledDataSource ds = null ;
	
		static {
		try {
				Properties pro = new Properties();
				pro.load(ConnectionFactory.class.getResourceAsStream("db.properties")) ;
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
	
	
	/**
	* 获取连接池工厂实例
	* @return ConnectionFactory实例
	*/
	public static ConnectionFactory getInstance(){
	if(factory==null)
	factory = new ConnectionFactory();
	return factory ;
	}
	
	
	/**
	* 获取连接池中的Connection
	* @return Connection
	* @throws SQLException
	*/
	public final synchronized Connection getConnection() throws SQLException
	{
		try {
			return ds.getConnection() ;
		} catch (SQLException e) {
			System.out.println("获取连接Connection失败: "+e.getMessage());
			throw e ;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
			System.out.println(ConnectionFactory.getInstance().getConnection().hashCode()) ;
	}
}
