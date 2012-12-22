package org.fdapservice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.dao.RetailStartupDao;
import org.fdapservice.entity.HisRetailStartup;
import org.fdapservice.util.DBUtil;


/**
 * 小批零启停记录上传数据访问实现类
 * @author wjt
 */
public class RetailStartupDaoImpl implements RetailStartupDao {
	private Logger logger = Logger.getLogger(RetailStartupDaoImpl.class);
	
	
	@Override
	public void addLog(Integer result, String msg, Logger logger) {
		if(!result.toString().equals("0")){
			logger.error(msg);
		}
	}

	@Override
	public Integer uploadRetailStartup(String code, String source) {
		Connection conn = null;
		CallableStatement call = null;
		Integer result = null;
		String msg = "";
		try {
			conn = DBUtil.getCon();
			call = conn.prepareCall(RetailStartupDao.UPLOADRETAILSTARTUP);
			call.setString(1, code);
			call.setString(2, source);
			call.registerOutParameter(3, java.sql.Types.INTEGER);
			call.registerOutParameter(4, java.sql.Types.VARCHAR);
			call.execute();
			result = call.getInt(3);
			msg = call.getString(4);
			addLog(result, msg, logger);
//			System.out.println(msg);
			return result;
		} catch (Exception e) {
			addLog(2, "调用小批零启停记录过程失败("+msg+"):"+e.getMessage(), logger);
			return 2;
		}finally{
			DBUtil.closeStatement(call);
			DBUtil.closeCon(conn);
		}
	}

	@Override
	public List<HisRetailStartup> queryRetailStartup(String tableName, String startTime,
			String endTime, Long refId) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<HisRetailStartup> list = new ArrayList<HisRetailStartup>() ;
		HisRetailStartup retailstartup = null ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement();
			String sql = "select startupid,refId,startTime,endTime,carrier,intervalValue from "+tableName+
					" where startTime>='"+startTime+"' and endTime<='"+endTime+"' and refid="+refId;
			rs = st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					retailstartup = new HisRetailStartup();
					retailstartup.setStartupid(rs.getLong("startupid"));
					retailstartup.setRefId(rs.getLong("refId"));
					retailstartup.setStartTime(rs.getString("startTime"));
					retailstartup.setEndTime(rs.getString("endTime"));
					retailstartup.setCarrier(rs.getString("carrier"));
					retailstartup.setIntervalValue(rs.getInt("intervalValue"));
					list.add(retailstartup);
				}
			}
		} catch (Exception e) {
			System.out.println("获取小批零历史启停记录发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}

}
