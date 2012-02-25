package org.fdapservice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.dao.CarRealDataDao;
import org.fdapservice.entity.CarRealData;
import org.fdapservice.util.DBUtil;
/**
 * 车载实时数据上传数据访问实现类
 * @author zhoukuanwei
 *
 */
public class CarRealDataDaoImpl implements CarRealDataDao {
	private Logger logger = Logger.getLogger(RefRealDataDaoImpl.class) ;
	
	@Override
	public List<Object> uploadCarReal(String code, String psource) {
		Connection conn = null;
		CallableStatement call = null;
		String msg = "";
		Integer result = 0;
		
		String alarmRefs = "";
		List<Object> list = null;
		try {
			conn = DBUtil.getCon();
			call = conn.prepareCall(CarRealDataDao.UPLOADCAR);
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
			addLog(2, "调用车载实时数据过程失败("+msg+"):"+e.getMessage(), logger);
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
			System.out.println("获取该企业下真正报警车载发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}

	@Override
	public List<CarRealData> queryCarReal(String code) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<CarRealData> list = new ArrayList<CarRealData>() ;
		CarRealData carreal = null;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = CARREAL+"'"+code+"'";
			rs = st.executeQuery(sql);		//现在都是直接查询声光报警，之前是查询冷库报警ALARM
			if(rs!=null){
				while(rs.next()){
					/*aid1,aid2,aid3,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,time,isalarm*/
					carreal = new CarRealData();
					carreal.setAid1(rs.getInt("aid1"));
					carreal.setAid2(rs.getInt("aid2"));
					carreal.setAid3(rs.getInt("aid3"));
					carreal.setT1(rs.getDouble("t1"));
					carreal.setT2(rs.getDouble("t2"));
					carreal.setT3(rs.getDouble("t3"));
					carreal.setLatitude(rs.getDouble("latitude"));
					carreal.setLatitude_dir(rs.getInt("latitude_dir"));
					carreal.setLongitude(rs.getDouble("longitude"));
					carreal.setLongitude_dir(rs.getInt("longitude_dir"));
					carreal.setTime(rs.getString("time"));
					carreal.setIsalarm(rs.getInt("isalarm"));
					list.add(carreal);
				}
			}
		} catch (Exception e) {
			System.out.println("获取该企业下车载实时数据发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}
	
}
