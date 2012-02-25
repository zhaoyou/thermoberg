package org.fdapservice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.fdapservice.dao.RefRealDataDao;
import org.fdapservice.entity.Project;
import org.fdapservice.entity.RefRealData;
import org.fdapservice.util.DBUtil;


/**
 * 冷库实时数据上传数据操作类
 * @author zhaoyou
 *
 */
public class RefRealDataDaoImpl implements RefRealDataDao {

	private Logger logger = Logger.getLogger(RefRealDataDaoImpl.class) ;
	
	@Override
	public List<Object> addRefRealData(String code, String source,String Time) {
		Connection con = null ;
		CallableStatement call = null ;
		String msg = null ;
		Integer result = null ; 
		
		String alarmRefs = "";
		List<Object> list = null;
		try {
			con = DBUtil.getCon() ;
			call = con.prepareCall(RefRealDataDao.UPLOADREF) ;
			call.setString(1, code) ;
			call.setString(2, source) ;
			call.setString(3, Time);
			call.registerOutParameter(4, java.sql.Types.INTEGER);
			call.registerOutParameter(5, java.sql.Types.VARCHAR) ;
			call.registerOutParameter(6, java.sql.Types.VARCHAR) ;
			call.execute() ;
			msg = call.getString(5);
			result = call.getInt(4);
			alarmRefs = call.getString(6);
			list = new ArrayList<Object>();
			list.add(result);
			list.add(alarmRefs);
//			System.out.println(msg);
			return list;
		} catch (Exception e) {
			list = new ArrayList<Object>();
			list.add(2);
			list.add(alarmRefs);
			addLog(2,"调用存储过程失败("+msg+") ："+e.getMessage(),logger);
			return list ;
		}finally{
			DBUtil.closeStatement(call);
			DBUtil.closeCon(con) ;
		}
	}

	@Override
	public List<Long> getAlarmPro(String code) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<Long> list = new ArrayList<Long>() ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = REALALARMPRO+"'"+code.toString()+"'";
			rs = st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					list.add(rs.getLong("projectid"));
				}
			}
		} catch (Exception e) {
			System.out.println("获取该企业下真正报警车载发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}

	@Override
	public Project getProById(Long projectId) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<Project> list = new ArrayList<Project>() ;
		Project pro = null ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement();
			String sql = "select fp.projectId,fp.name,fp.type from fdapproject fp where fp.projectId = "+projectId;
			rs = st.executeQuery(sql);	
			if(rs!=null){
				while(rs.next()){
					pro = new Project();
					pro.setProjectid(rs.getLong("projectid"));
					pro.setName(rs.getString("name"));
					pro.setType(rs.getInt("type"));
					list.add(pro);
				}
			}
		} catch (Exception e) {
			System.out.println("获取工程信息发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list.get(0);
	}

	@Override
	public void addLog(Integer presult, String pmsg, Logger logger) {
		if(!presult.toString().equals("0")){
			logger.error(pmsg);
		}
		
	}

	@Override
	public List<RefRealData> queryRefReal(String code) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<RefRealData> list = new ArrayList<RefRealData>() ;
		RefRealData refreal = null ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = REFREAL+"'"+code+"'";
			rs = st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					refreal = new RefRealData();
					refreal.setId(rs.getInt("reid"));
					refreal.setValue(rs.getDouble("data"));
					refreal.setStatus(rs.getInt("isalarm"));
					refreal.setTime(rs.getString("time"));
					list.add(refreal);
				}
			}
		} catch (Exception e) {
			System.out.println("获取仓库实时数据发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}
}
