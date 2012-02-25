package org.ccdcc.dao.jdbc.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.ccdcc.dao.CarHisDao;
import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;
import org.ccdcc.util.ConnectionProxyFactory;

/**
 * 车载历史数据访问实现类
 * @author Administrator
 *
 */
public class CarHisDaoImpl implements CarHisDao {
	
	private static Logger logger = Logger.getRootLogger() ;
	
	private ConnectionProxyFactory connectionfaotory = null;
	
	public ConnectionProxyFactory getConnectionfaotory() {
		return connectionfaotory;
	}

	public void setConnectionfaotory(ConnectionProxyFactory connectionfaotory) {
		this.connectionfaotory = connectionfaotory;
	}

	@SuppressWarnings("unchecked")
	public List<CarHisView> getCarhis(String tableName,Integer sid,String time) {
		Connection conn=null;
		try {
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = " select top 50 id,ai1,ai2,ai3" +
					",ai4,alarmStatus,latitude,latitude_dir" +
					",longitude,longitude_dir,parentId,updateTime" +
					" from  "+tableName+" where parentId =? and updateTime >? and HistDataStorageType = -1" +
					" and histAlarmStorageType = -1 and gpsStorageType = -1 and unloadStorageType = -1" ;
			return 	queryRun.query(conn, sql, new BeanListHandler<CarHisView>(CarHisView.class),new Object[]{sid,time});
		}
		catch (Exception e) {
			logger.error("获取车载历史信息失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<CarHisView> getCarhis2(String tableName,Integer sid,
			String time) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = " select top 50 id,ai1,ai2,ai3" +
			",ai4,alarmStatus,latitude,latitude_dir" +
			",longitude,longitude_dir,parentId,updateTime" +
			" from  "+tableName+" where parentId =? and updateTime >? and HistDataStorageType = -1 " +
			" and histAlarmStorageType = -1 and gpsStorageType = -1 and unloadStorageType = -1 order by updateTime" ;
			return 	queryRun.query(conn, sql, new BeanListHandler<CarHisView>(CarHisView.class),new Object[]{sid,time});
		}
		catch (Exception e) {
			logger.error("获取车载历史信息失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<CarHisView_new> getCarhis_sy(String tableName, Integer sid,
			String time) {
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			//除了gps数据，其他数据全部拿出来
			String sql = " select top 50 id,ai1,ai2,ai3" +
			",ai4,alarmStatus,latitude,latitude_dir" +
			",longitude,longitude_dir,parentId,updateTime,unloadStatus,histAlarmStorageType," +
			"gpsStorageType,histDataStorageType,unloadStorageType,histAlarmData" +
			" from  "+tableName+" where parentId =? and updateTime >? " +
			" and (HistAlarmStorageType=0 or unloadStorageType=0 or histDataStorageType=0) order by updateTime" ;
			return 	queryRun.query(conn, sql, new BeanListHandler<CarHisView_new>(CarHisView_new.class),new Object[]{sid,time});
		}
		catch (Exception e) {
			logger.error("获取车载历史信息失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}
	
}
