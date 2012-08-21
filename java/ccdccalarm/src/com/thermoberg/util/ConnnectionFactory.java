package com.thermoberg.util;

import java.sql.Connection;
import java.util.Properties;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ӹ���
 * @author zhaoyou
 *
 */
public class ConnnectionFactory {
	
	private ConnnectionFactory(){
	}
	
	private static ComboPooledDataSource ds = null ;
	
	
	static {
		
		try {
			
		Properties properties = new Properties() ;
		
		properties.load(DBUtil.class.getResourceAsStream("db.properties"));
			
		ds = new ComboPooledDataSource() ;
		
		ds.setDriverClass(properties.getProperty("driver")) ;
		
		ds.setJdbcUrl(properties.getProperty("url")) ;
		
		ds.setUser(properties.getProperty("user")) ;
		
		ds.setPassword(properties.getProperty("password"));
		
		ds.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxsize"))) ;
		
		ds.setMinPoolSize(Integer.parseInt(properties.getProperty("minsize"))) ;

		
		} catch (Exception e) {
			System.out.println("��ʼ�����ӳ�ʧ��..."+e.getMessage());
		}
	}
	
	public static synchronized  Connection getConnection() {
		try {
			 
			return  ds.getConnection() ;
		} catch (Exception e) {
			System.out.println("��ȡ����ʧ��:"+e.getMessage());
		}
		return null ;
	}

}
