package org.ccdcc.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * 连接代理工厂
 * @author zhaoyou
 *
 */
public class ConnectionProxyFactory {
	
	private static  Logger logger = Logger.getRootLogger();
	
	private SessionFactory sessionfactory = null ;

	
	public  ConnectionProxyFactory(){
		
	}
	
	
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
			
	public  Connection getConnection() throws Exception{
		//注入的springFactory没有使用、直接使用连接池中的连接	
		//return sessionfactory.getCurrentSession().connection();
		return ConnectionFactory.getInstance().getConnection();
	}	
	
	
	public void closeConn(Connection conn)
	{
		try {
			if(conn!=null&&conn.isClosed()==false)
			{
				DbUtils.close(conn);
			}
		} catch (SQLException e) {
			logger.error("关闭Connection失败！"+e.getMessage());
		}
	}
}
