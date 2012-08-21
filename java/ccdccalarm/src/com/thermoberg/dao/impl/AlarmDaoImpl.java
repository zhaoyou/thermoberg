package com.thermoberg.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thermoberg.dao.AlarmDao;
import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.AlarmSerious;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.entity.TimeOutAlarm;
import com.thermoberg.util.DBUtil;
import com.thermoberg.util.DateUtil;

/**
 * 这是报警数据访问的实现类
 * @author Administrator
 *
 */
public class AlarmDaoImpl implements AlarmDao {

	public List<AlarmInfo> getAlarmList() {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<AlarmInfo> list = new ArrayList<AlarmInfo>() ;
		AlarmInfo info = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(ALARM_SOUND);		//现在都是直接查询声光报警，之前是查询冷库报警ALARM
			if(rs!=null){
				while(rs.next()){
					info = new AlarmInfo();
					info.setBranchId(rs.getLong("branchId"));
					info.setBranchName(rs.getString("branchName"));
					info.setUpdatetime(DateUtil.stringToDate(rs.getString("updatetime")));
					list.add(info);
				}
			}
		} catch (Exception e) {
			System.out.println("获取报警信息发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}
	
	

	public List<AlarmInfo> getLostConnection() {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<AlarmInfo> list = new ArrayList<AlarmInfo>() ;
		AlarmInfo info = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(CONNECTION);
			if(rs!=null){
				while(rs.next()){
					info = new AlarmInfo();
					info.setBranchId(rs.getLong("branchId"));
					info.setBranchName(rs.getString("branchName"));
					info.setUpdatetime(DateUtil.stringToDate(rs.getString("updatetime")));
					list.add(info);
				}
			}
		} catch (Exception e) {
			System.out.println("获取断开连接发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}



	public List<CarAlarmInfo> getCarAlarmList() {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<CarAlarmInfo> list = new ArrayList<CarAlarmInfo>();
		CarAlarmInfo info = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(CARALARM);
			
			if(rs!=null){
				while(rs.next()){
					info  = new CarAlarmInfo();
					info.setProjectId(rs.getString("projectId"));
					info.setBranchName(rs.getString("branchName"));
					info.setProjectName(rs.getString("projectName"));
					info.setUpdateTime(DateUtil.stringToDate(rs.getString("updateTime")));
					
					list.add(info);
				}
			}
		}catch (Exception e) {
			System.out.println("获取车载报警信息错误: "+e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}



	@Override
	public List<AlarmSerious> getAlarmSeriousList(String sql) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<AlarmSerious> list = new ArrayList<AlarmSerious>();
		AlarmSerious info = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(sql) ;
			
			
			
			if(rs!=null){
				while(rs.next()){
					info  = new AlarmSerious();
					info.setBranchId(rs.getLong("branchId")) ;
					info.setBranchName(rs.getString("branchName")) ;
					info.setUpdateTime(DateUtil.stringToDate(rs.getString("updatetime"))) ;
					list.add(info);
				}
			}
			
			
		}catch (Exception e) {
			System.out.println("获取严重报警信息错误: "+e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}



	@Override
	public List<AlarmSerious> getLostPower() {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<AlarmSerious> list = new ArrayList<AlarmSerious>() ;
		AlarmSerious info = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(ALARM_LOSTPOWER);
			if(rs!=null){
				while(rs.next()){
					info = new AlarmSerious();
					info.setBranchId(rs.getLong("branchId"));
					info.setBranchName(rs.getString("branchName")+"(断电)");
					info.setUpdateTime(DateUtil.stringToDate(rs.getString("updatetime")));
					list.add(info);
				}
			}
		} catch (Exception e) {
			System.out.println("获取断电发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}

	public List<TimeOutAlarm> getTimeoutAlarmList() {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<TimeOutAlarm> list = new ArrayList<TimeOutAlarm>() ;
		TimeOutAlarm timeout = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(ALARM_TIMEOUT);
			if(rs!=null){
				while(rs.next()){
					timeout = new TimeOutAlarm();
					timeout.setProjectid(rs.getString("projectId"));
					timeout.setBranchName(rs.getString("branchName"));
					timeout.setProjectName(rs.getString("projectName"));
					timeout.setStartTime(DateUtil.stringToDate(rs.getString("startTime")));
					timeout.setNetid(rs.getInt("netId"));
					list.add(timeout);
				}
			}
		} catch (Exception e) {
			System.out.println("获取持续不变超过10分钟发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}
	
	public List<HistRefData> getHistRefData(){
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<HistRefData> list = new ArrayList<HistRefData>() ;
		HistRefData histRef = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(REF_REAL);
			if(rs!=null){
				while(rs.next()){
					histRef = new HistRefData();
					histRef.setProjectId(rs.getString("projectId"));
					histRef.setNeiId(Integer.parseInt(rs.getString("NetId")));
					histRef.setUpdateTime(rs.getString("UpdateTime"));
					histRef.setConnectStatus(Integer.parseInt(rs.getString("ConnectStatus")));
					histRef.setAi1(Double.valueOf(rs.getString("AI1")));
					histRef.setAi2(Double.valueOf(rs.getString("AI2")));
					histRef.setAi3(Double.valueOf(rs.getString("AI3")));
					histRef.setAi4(Double.valueOf(rs.getString("AI4")));
					histRef.setAi5(Double.valueOf(rs.getString("AI5")));
					histRef.setAi6(Double.valueOf(rs.getString("AI6")));
					histRef.setAi7(Double.valueOf(rs.getString("AI7")));
					histRef.setAi8(Double.valueOf(rs.getString("AI8")));
					histRef.setAi9(Double.valueOf(rs.getString("AI9")));
					histRef.setAi10(Double.valueOf(rs.getString("AI10")));
					histRef.setAi11(Double.valueOf(rs.getString("AI11")));
					histRef.setAi12(Double.valueOf(rs.getString("AI12")));
					histRef.setAi13(Double.valueOf(rs.getString("AI13")));
					histRef.setAi14(Double.valueOf(rs.getString("AI14")));
					histRef.setAi15(Double.valueOf(rs.getString("AI15")));
					histRef.setAi16(Double.valueOf(rs.getString("AI16")));
					histRef.setAi17(Double.valueOf(rs.getString("AI17")));
					histRef.setAi18(Double.valueOf(rs.getString("AI18")));
					histRef.setAi19(Double.valueOf(rs.getString("AI19")));
					histRef.setAi20(Double.valueOf(rs.getString("AI20")));
					histRef.setAi21(Double.valueOf(rs.getString("AI21")));
					histRef.setAi22(Double.valueOf(rs.getString("AI22")));
					histRef.setAi23(Double.valueOf(rs.getString("AI23")));
					histRef.setAi24(Double.valueOf(rs.getString("AI24")));
					histRef.setAi25(Double.valueOf(rs.getString("AI25")));
					histRef.setAi26(Double.valueOf(rs.getString("AI26")));
					histRef.setAi27(Double.valueOf(rs.getString("AI27")));
					histRef.setAi28(Double.valueOf(rs.getString("AI28")));
					histRef.setAi29(Double.valueOf(rs.getString("AI29")));
					histRef.setAi30(Double.valueOf(rs.getString("AI30")));
					histRef.setAi31(Double.valueOf(rs.getString("AI31")));
					histRef.setAi32(Double.valueOf(rs.getString("AI32")));
					list.add(histRef);
				}
			}
		} catch (Exception e) {
			System.out.println("获取持续不变超过10分钟发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}
	
	public List<CarDataNoChange> getCar_noChange(Integer day) {
		String CAR_NOCHANGE="select pt.projectname as projectname,bt.branchname as branchname,datediff(day,realcar.updatetime,getdate()) as timediff from tbccrealdata_car realcar inner join tbccprjtype pt on pt.projectid=realcar.projectid " +
		" inner join tbccbranchprjrelation bpr on bpr.projectid=pt.projectid inner join tbccbranchtype bt on" +
		" bt.branchid = bpr.branchid where datediff(second,dateadd(day,"+day+",realcar.updatetime),getdate())>=0" +
				" and bt.branchid!=29 and bt.branchId !=36 and bt.branchId !=31 and bt.branchId !=34 order by realcar.projectid";
		
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<CarDataNoChange> list = new ArrayList<CarDataNoChange>() ;
		CarDataNoChange car_nc = null ;
		try {
			conn = DBUtil.getConn() ;
			st = conn.createStatement() ;
			rs = st.executeQuery(CAR_NOCHANGE);
			if(rs!=null){
				while(rs.next()){
					car_nc = new CarDataNoChange();
					car_nc.setBranchName(rs.getString("branchName"));
					car_nc.setProjectName(rs.getString("projectName"));
					car_nc.setTimediff(Integer.parseInt(rs.getString("timediff")));
					list.add(car_nc);
				}
			}
		} catch (Exception e) {
			System.out.println("获取在固定时间内不变的车载数据发生错误:" +e.getMessage());
			return list ;
		}finally{
			DBUtil.closeRs(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeConn(conn);
		}
		return list;
	}
}


