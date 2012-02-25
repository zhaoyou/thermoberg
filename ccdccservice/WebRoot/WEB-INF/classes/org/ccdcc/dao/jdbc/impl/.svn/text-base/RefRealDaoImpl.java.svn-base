package org.ccdcc.dao.jdbc.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.ccdcc.dao.RefRealDao;
import org.ccdcc.entity.AiInfoView;
import org.ccdcc.entity.RefInfoView;
import org.ccdcc.entity.RefRealView;
import org.ccdcc.util.ConnectionProxyFactory;

/**
 * 冷库实时数据访问接口
 * @author zhaoyou
 *
 */
public class RefRealDaoImpl implements RefRealDao {
	
	private static Logger logger = Logger.getRootLogger() ;
	
	private ConnectionProxyFactory connectionfaotory = null;
	
	public void setConnectionfaotory(ConnectionProxyFactory connectionfaotory) {
		this.connectionfaotory = connectionfaotory;
	}

	public RefInfoView queryRefByInfo(String projectId ,Integer realRefId, Integer refType,
			Integer floorType, Integer floorNo) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id,netId,projectId,realRefId,refType,floorType,floorNo " +
					"from tbccrefinfo where projectId = ? and  realrefId = ? and refType= ? and  floorType = ? and floorNo = ?" ;
		return 	runner.query(conn, sql, new BeanHandler<RefInfoView>(RefInfoView.class	), new Object[]{projectId,realRefId,refType,floorType,floorNo});
		
		} catch (Exception e) {
			logger.error("获取冷库信息失败: "+e.getMessage());
		} 
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}
	
	public List<RefInfoView> queryRefByProjectId(String projectId) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id,netId,projectId,realRefId,refType,floorType,floorNo " +
					"from tbccrefinfo where projectId = ?" ;
		return 	runner.query(conn, sql, new BeanListHandler<RefInfoView>(RefInfoView.class	), new Object[]{projectId});	
		} catch (Exception e) {
			logger.error("获取冷库列表信息失败: "+e.getMessage());
		} 
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	
	}
	
	

	public List<AiInfoView> queryAiInfoByRid(Long rid) {
		Connection conn=null;
		try {
			QueryRunner runner =new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id,rid,projectId,portNo,portName,dataType from tbccaiinfo where rid = ?" ;
			return runner.query(conn, sql,
					new BeanListHandler<AiInfoView>(AiInfoView.class), new Object[]{rid});
		} catch (Exception e) {
			logger.error("获取冷库探头失败 ！"+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}
	
	
	public List<AiInfoView> queryAiInfoByProjectId(String projectId) {
		Connection conn=null;
		try {
			QueryRunner runner =new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id,rid,projectId,portNo,portName,dataType from tbccaiinfo where projectId = ?" ;
			return runner.query(conn, sql,
					new BeanListHandler<AiInfoView>(AiInfoView.class), new Object[]{projectId});
		} catch (Exception e) {
			logger.error("获取冷库探头列表失败 ！"+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}
	
	

	public RefRealView queryRefReal(String projectId, Integer netId) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id,netId,projectid,updateTime,ai1,ai2,ai3,ai4,ai5,ai6,ai7,ai8,ai9,ai10,ai11,ai12,"
					+"ai13,ai14,ai15,ai16,ai17,ai18,ai19,ai20,ai21,ai22,ai23,ai24,ai25,ai26,ai27,ai28,ai29,ai30,ai31,ai32 " +
					"from TbccRealData_Ref where projectid = ? and netid = ?" ;
			
			return runner.query(conn, sql,
					new BeanHandler<RefRealView>(RefRealView.class), new Object[]{projectId,netId});
		} catch (Exception e) {
			logger.error("获取设备实时数据失败! "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	
	public List<RefRealView> queryRefReal(String projectId) {
		Connection conn=null;
		try {
			QueryRunner runner = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = "select id,netId,projectid,updateTime,ai1,ai2,ai3,ai4,ai5,ai6,ai7,ai8,ai9,ai10,ai11,ai12,"
					+"ai13,ai14,ai15,ai16,ai17,ai18,ai19,ai20,ai21,ai22,ai23,ai24,ai25,ai26,ai27,ai28,ai29,ai30,ai31,ai32 " +
					"from TbccRealData_Ref where projectid = ?" ;
			
		return 	runner.query(conn, sql,
					new BeanListHandler<RefRealView>(RefRealView.class), new Object[]{projectId});
		} catch (Exception e) {
			logger.error("获取工程设备列表实时数据失败! "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
		return null;
	}

	
	
}
