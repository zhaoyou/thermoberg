package org.fdapservice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.dao.RetailRealDataDao;
import org.fdapservice.entity.RetailRealData;
import org.fdapservice.util.DBUtil;

/**
 * 小批零实时数据上传数据访问实现类
 * @author wjt
 *
 */
public class RetailRealDataDaoImpl implements RetailRealDataDao {
	private Logger logger = Logger.getLogger(RefRealDataDaoImpl.class) ;
	
	@Override
	public List<Object> uploadRetailReal(String code, String psource) {
		Connection conn = null;
		CallableStatement call = null;
		String msg = "";
		Integer result = 0;
		
		String alarmRefs = "";
		List<Object> list = null;
		try {
			conn = DBUtil.getCon();
			call = conn.prepareCall(RetailRealDataDao.UPLOADRETAIL);
			call.setString(1, code);
			call.setString(2, psource);
			call.registerOutParameter(3, java.sql.Types.INTEGER);
			call.registerOutParameter(4, java.sql.Types.VARCHAR);
			call.registerOutParameter(5, java.sql.Types.VARCHAR);
			call.execute();
			msg = call.getString(4);
			result = call.getInt(3);
			alarmRefs = call.getString(5);
			addLog(result, msg, logger);
			list = new ArrayList<Object>();
			list.add(result);
			list.add(alarmRefs);
//			System.out.println(msg);
			return list;
		} catch (Exception e) {
			list = new ArrayList<Object>();
			list.add(2);
			list.add("");
			addLog(2, "调用小批零实时数据过程失败("+msg+"):"+e.getMessage(), logger);
			return list;
		}finally{
			DBUtil.closeStatement(call);
			DBUtil.closeCon(conn) ;
		}
	}

	@Override
	public void addLog(Integer result, String msg, Logger log) {
		if(!result.toString().equals('0')){
			log.error(msg);
		}
	}

	@Override
	public List<Long> getAlarmRef(String code) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<Long> list = new ArrayList<Long>() ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = REALALARMREF+"'"+code.toString()+"'";
			rs = st.executeQuery(sql);		//现在都是直接查询声光报警，之前是查询冷库报警ALARM
			if(rs!=null){
				while(rs.next()){
					list.add(rs.getLong("refid"));
				}
			}
		} catch (Exception e) {
			System.out.println("获取该企业下真正报警小批零发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}

	@Override
	public List<RetailRealData> queryRetailReal(String code) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<RetailRealData> list = new ArrayList<RetailRealData>() ;
		RetailRealData retailreal = null;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = RETAILREAL+"'"+code+"'";
			rs = st.executeQuery(sql);		//现在都是直接查询声光报警，之前是查询冷库报警ALARM
			if(rs!=null){
				while(rs.next()){
					/*aid1,aid2,aid3,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,time,isalarm*/
					retailreal = new RetailRealData();
					retailreal.setAid1(rs.getInt("aid1"));
				
					retailreal.setT1(rs.getDouble("t1"));
				
					retailreal.setLatitude(rs.getDouble("latitude"));
					retailreal.setLatitude_dir(rs.getInt("latitude_dir"));
					retailreal.setLongitude(rs.getDouble("longitude"));
					retailreal.setLongitude_dir(rs.getInt("longitude_dir"));
					retailreal.setTime(rs.getString("time"));
					retailreal.setIsalarm(rs.getInt("isalarm"));
					list.add(retailreal);
				}
			}
		} catch (Exception e) {
			System.out.println("获取该企业下小批零实时数据发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}
	
}
