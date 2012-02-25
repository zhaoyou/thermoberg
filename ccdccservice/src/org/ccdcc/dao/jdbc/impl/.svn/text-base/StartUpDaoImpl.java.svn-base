package org.ccdcc.dao.jdbc.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.ccdcc.dao.StartUpDao;
import org.ccdcc.entity.StartUpView;
import org.ccdcc.util.ConnectionProxyFactory;

/**
 * 历史启停记录数据访问实现类
 * @author Administrator
 *
 */
public class StartUpDaoImpl implements StartUpDao {
	private static Logger logger = Logger.getRootLogger() ;
	
	private ConnectionProxyFactory connectionfaotory = null;
	
	public ConnectionProxyFactory getConnectionfaotory() {
		return connectionfaotory;
	}

	public void setConnectionfaotory(ConnectionProxyFactory connectionfaotory) {
		this.connectionfaotory = connectionfaotory;
	}

	@SuppressWarnings("unchecked")
	public List<StartUpView> getStartUpByTime(String tableName,String time) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select top 10 beginAddress,beginTime,carrier," +
					"endAddress,endTime,id,receiver," +
					"recordInterval,shipper,updateStatus,"+
					"tlimitType ,tuplimit,tdwlimit from " +tableName+
					" s where begintime > ?" ;
			return 	queryRun.query(conn, sql, new BeanListHandler<StartUpView>(StartUpView.class),new Object[]{time});
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	public Integer getId(String tableName,String time) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id from "+tableName+" where beginTime =?" ;
			int result=queryRun.query(conn, sql,new BeanHandler <Integer>(Integer.class),new Object[]{time});
			return result;
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return 0;
		
	}

	@SuppressWarnings("unchecked")
	public List<StartUpView> getStartUpById(String tableName,Integer id) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select top 5 beginAddress,beginTime,carrier," +
			"endAddress,endTime,id,receiver," +
			"recordInterval,shipper,updateStatus,"+
			"tlimitType ,tuplimit,tdwlimit from " +tableName+
			" s where id >?";
			return 	queryRun.query(conn, sql, new BeanListHandler<StartUpView>(StartUpView.class),new Object[]{id});
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	public StartUpView get(final String tableName,final Integer id) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select  beginAddress,beginTime,carrier," +
			"endAddress,endTime,id,receiver," +
			"recordInterval,shipper ,updateStatus,"+
			"tlimitType ,tuplimit,tdwlimit from " +tableName+
			" s where id = ?";
			return 	(StartUpView)queryRun.query(conn, sql,new BeanHandler <StartUpView>(StartUpView.class),new Object[]{id});
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
		
	}

}
