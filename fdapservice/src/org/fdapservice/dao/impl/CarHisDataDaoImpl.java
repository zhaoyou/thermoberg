package org.fdapservice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.dao.CarHisDataDao;
import org.fdapservice.entity.HisCarData;
//import org.fdapservice.entity.RefInfo;
import org.fdapservice.util.DBUtil;
import org.fdapservice.util.DealCarhis;
/**
 * 车载历史数据上传数据访问实现类
 * @author zhoukuanwei
 */
public class CarHisDataDaoImpl implements CarHisDataDao{
	private Logger logger = Logger.getLogger(CarHisDataDaoImpl.class);
	
	
	@Override
	public Integer uploadCarHisData(String code, String source) {
		Connection conn = null;
		CallableStatement call = null;
		Integer result = null;
		String msg = "";
		/*dealhisMsg字符串格式为：{oid;aid1,aid2,aid3,startupid;aid1,aid2,aid3,startupid;。。。}*/
		String dealhisMsg = "";
		try {
			conn = DBUtil.getCon();
			call = conn.prepareCall(CarHisDataDao.CARHISDATA);
			call.setString(1, code);
			call.setString(2, source);
			call.registerOutParameter(3, java.sql.Types.INTEGER);
			call.registerOutParameter(4, java.sql.Types.VARCHAR);
			call.registerOutParameter(5, java.sql.Types.VARCHAR);
			call.execute();
			result = call.getInt(3);
			msg = call.getString(4);
			dealhisMsg = call.getString(5);
			if(result==0){
				new Thread(new DealCarhis(dealhisMsg)).start();
			}
			addlog(result, msg, logger);
//			System.out.println("结束车载历史");
			return result;
		} catch (Exception e) {
			addlog(2, "调用车载历史数据过程失败("+msg+"):"+e.getMessage(), logger);
//			System.out.println("出错了，确认是否存在历史数据对应的启停记录");
			return 2;
		}finally{
			DBUtil.closeStatement(call);
			DBUtil.closeCon(conn);

		}
	}
	
	@Override
	public List<HisCarData> queryCarHis(String tableName, Long startupid) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<HisCarData> list = new ArrayList<HisCarData>() ;
		HisCarData carHis = null ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement();
			String sql = "select * from "+tableName+" where startupid="+startupid.toString();
			rs = st.executeQuery(sql);	
			if(rs!=null){
				while(rs.next()){
					carHis = new HisCarData();
					carHis.setStartupid(rs.getLong("startupid"));
					carHis.setT1(rs.getDouble("t1"));
					carHis.setT2(rs.getDouble("t2"));
					carHis.setT3(rs.getDouble("t3"));
					carHis.setLatitude(rs.getDouble("latitude"));
					carHis.setLatitude_dir(rs.getInt("Latitude_dir"));
					carHis.setLongitude(rs.getDouble("longitude"));
					carHis.setLongitude_dir(rs.getInt("longitude_dir"));
					carHis.setTime(rs.getString("time"));
					carHis.setIsalarm(rs.getInt("isalarm"));
					list.add(carHis);
				}
			}
		} catch (Exception e) {
			System.out.println("获取车载历史数据发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}

	@Override
	public void addlog(Integer result, String msg, Logger logger) {
		if(!result.toString().equals("0")){
			logger.error(msg);
		}
	}
}
