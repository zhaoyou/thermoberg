package org.ccdcc.dao.jdbc.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.ccdcc.dao.BoxStartUpDao;
import org.ccdcc.entity.BoxStartUpView;
import org.ccdcc.entity.StartUpView;
import org.ccdcc.util.ConnectionProxyFactory;

public class BoxStartUpDaoImpl implements BoxStartUpDao {

	private static Logger logger = Logger.getRootLogger() ;
	
	private ConnectionProxyFactory connectionfaotory = null;
	
	public ConnectionProxyFactory getConnectionfaotory() {
		return connectionfaotory;
	}
	
	@Override
	public BoxStartUpView get(String tableName, Integer id) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select  beginAddress,beginTime,carrier," +
			"endAddress,endTime,id,receiver," +
			"recordInterval,shipper ,updateStatus,"+
			"tlimitType ,tuplimit,tdwlimit from " +tableName+
			" s where id = ?";
			return 	(BoxStartUpView)queryRun.query(conn, sql,new BeanHandler <BoxStartUpView>(BoxStartUpView.class),new Object[]{id});
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<BoxStartUpView> getBoxStartUpById(String tableName, Integer id) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select top 5 beginAddress,beginTime,carrier," +
			"endAddress,endTime,id,receiver," +
			"recordInterval,shipper,updateStatus,"+
			"tlimitType ,tuplimit,tdwlimit from " +tableName+
			" s where id >?";
			return 	queryRun.query(conn, sql, new BeanListHandler<BoxStartUpView>(BoxStartUpView.class),new Object[]{id});
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<BoxStartUpView> getBoxStartUpByTime(String tableName,
			String time) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select top 10 beginAddress,beginTime,carrier," +
					"endAddress,endTime,id,receiver," +
					"recordInterval,shipper,updateStatus,"+
					"tlimitType ,tuplimit,tdwlimit from " +tableName+
					" s where begintime > ?" ;
			return 	queryRun.query(conn, sql, new BeanListHandler<BoxStartUpView>(BoxStartUpView.class),new Object[]{time});
		}
		catch (Exception e) {
			logger.error("获取启停记录失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	@Override
	public Integer getId(String tableName, String time) {
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

}
