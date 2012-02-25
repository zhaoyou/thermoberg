package org.ccdcc.dao.jdbc.impl;

import java.sql.Connection;
//import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.ccdcc.dao.CarRealDao;
//import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;
import org.ccdcc.util.ConnectionProxyFactory;

/**
 * 车载实时数据访问实现类
 * @author Administrator
 *
 */
public class CarRealDaoImpl implements CarRealDao{

	private static  Logger logger = Logger.getRootLogger();

	private ConnectionProxyFactory connectionfactory = null ;
	
	public ConnectionProxyFactory getConnectionfactory() {
		return connectionfactory;
	}

	public void setConnectionfactory(ConnectionProxyFactory connectionfactory) {
		this.connectionfactory = connectionfactory;
	}

	@SuppressWarnings("unchecked")
	public List<CarRealView> getByProject(String projectId) {	
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfactory.getConnection();
			String sql = "select * from tbcccarrealview where projectId = ?  " ;
			return 	runner.query(conn, sql, new BeanListHandler<CarRealView>(CarRealView.class), new Object[]{projectId});	
		} catch (Exception e) {
			logger.error("获取车载实时信息失败: "+e.getMessage());
		} 
		finally{
			connectionfactory.closeConn(conn);
		}
		return null;
	}
	
	
	

	@Override
	public List<CarRealData> getByHqid(String hqid) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfactory.getConnection();
			String sql = "select tp.projectname as CarNo,cv.ai1 as ai1,cv.ai2 as ai2,cv.ai3 as ai3,cv.ai4 as ai4,cv.updateTime as updateTime,cv.runStatus as runStatus,cv.connectStatus as connectionStatus from tbccrealdata_car cv " +
					"inner join tbccprjtype tp on cv.projectid=tp.projectid " +
					"inner join tbccbranchprjrelation tbpr on cv.projectid=tbpr.projectid inner join tbcchqbranchrelation thbr on tbpr.branchid=thbr.branchid where thbr.hqid="+hqid+" and tp.projecttype=2;" ;
			return runner.query(conn, sql, new BeanListHandler<CarRealData>(CarRealData.class), new Object[]{});	
		} catch (Exception e) {
			logger.error("获取总部分支车载实时信息失败: "+e.getMessage());
		} 
		finally{
			connectionfactory.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<CarRealData> getByBranchid(String branchid) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfactory.getConnection();
			String sql = "select tp.projectname as CarNo,cv.ai1 as ai1,cv.ai2 as ai2,cv.ai3 as ai3,cv.ai4 as ai4,cv.updateTime as updateTime,cv.runStatus as runStatus,cv.connectStatus as connectionStatus from tbccrealdata_car cv " +
					"inner join tbccprjtype tp on cv.projectid=tp.projectid " +
					"inner join tbccbranchprjrelation tbpr on cv.projectid=tbpr.projectid where tbpr.branchid="+branchid+" and tp.projecttype=2;" ;
			return runner.query(conn, sql, new BeanListHandler<CarRealData>(CarRealData.class), new Object[]{});	
		} catch (Exception e) {
			logger.error("获取总部分支车载实时信息失败: "+e.getMessage());
		} 
		finally{
			connectionfactory.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<CarRealView_new> getByProject_sy(String projectId) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfactory.getConnection();
			String sql = "select cv.id as id,cv.ai1 as ai1,cv.ai2 as ai2,cv.ai3 as ai3,cv.ai4 as ai4,cv.updateTime as updateTime,cv.runStatus as runStatus,cv.connectStatus as connectionStatus," +
					"cv.latitude as latitude,cv.latitude_dir as latitude_dir,cv.longitude as longitude,cv.longitude_dir as longitude_dir,cv.alarmStatus as alarmStatus," +
					"cv.unloadStatus as unloadStatus,cv.speedStatus as speedStatus,cv.carSpeed as carSpeed,cv.AlarmData as AlarmData,cv.projectId as projectId" +
					" from tbcccarrealview cv where projectId = ?  " ;
			return 	runner.query(conn, sql, new BeanListHandler<CarRealView_new>(CarRealView_new.class), new Object[]{projectId});	
		} catch (Exception e) {
			logger.error("获取车载实时信息失败: "+e.getMessage());
		} 
		finally{
			connectionfactory.closeConn(conn);
		}
		return null;
	}
}
