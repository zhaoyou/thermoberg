package com.thermoberg.service.impl;

//import java.util.Arrays;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.thermoberg.dao.AlarmDao;
import com.thermoberg.dao.impl.AlarmDaoImpl;
import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.AlarmSerious;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.entity.TimeOutAlarm;
import com.thermoberg.phone.ITelephone;
import com.thermoberg.phone.PhoneCaller;
import com.thermoberg.service.AlarmService;

/**
 * 报警的业务服务实现类
 * @author Administrator
 *
 */
public class AlarmServiceImpl implements AlarmService {
	
	private AlarmDao alarmDao = new AlarmDaoImpl();
	
	public static ITelephone telphone = null;

	static{
		try {
			telphone = new PhoneCaller().getBasicHttpBindingITelephone();
		} catch (Exception e) {
			System.out.println("创建ITelephone失败"+e.getStackTrace());
		}
	}
	
	public List<AlarmInfo> queryAlarmList() {
		List<AlarmInfo> list = alarmDao.getAlarmList() ;
		distinctData(list) ;
		return list;
	}

	public List<AlarmInfo> queryConnectionList() {	
		List<AlarmInfo> list = alarmDao.getLostConnection() ;
		distinctData(list);
		return list;
	}
	
	public List<CarAlarmInfo> queryCarAlarmList(){
		List<CarAlarmInfo> list = alarmDao.getCarAlarmList() ;
		distinctCarData(list);		
		return list ;
	}
	
	
	/**
	 * 过滤数据
	 * @param list
	 */
	private void distinctData(List<AlarmInfo> list){
		if(list==null || list.size()==0)
			return  ;
		
		Map<Long,AlarmInfo> tempMap = new HashMap<Long, AlarmInfo>();
		
		//循环处理集合重复值
		for (Iterator<AlarmInfo> iterator = list.iterator(); iterator.hasNext();) {
			AlarmInfo alarmInfo =  iterator.next();
			if(tempMap.containsKey(alarmInfo.getBranchId()))
				iterator.remove();
			else 
				tempMap.put(alarmInfo.getBranchId(), alarmInfo);
		}
	}
	
	/**
	 * 过滤车载报警数据
	 * @param	list
	 */
	private void distinctCarData(List<CarAlarmInfo> list){
		
		if(list==null || list.size()==0)
				return ;
		
		Map<String, CarAlarmInfo> map = new HashMap<String, CarAlarmInfo>();
		
		
		//循环遍历集合去除重复的值
		for (Iterator<CarAlarmInfo> iterator = list.iterator(); iterator.hasNext();) {
			CarAlarmInfo alarmInfo  =  iterator.next();
			
			if(map.containsKey(alarmInfo.getProjectId()))
				iterator.remove();
			else
				map.put(alarmInfo.getProjectId(), alarmInfo);
			
		}
		
	}

	@Override
	public Integer callPhone(String key, String phoneNumber, Integer phoneCount) {	
		if(key==null || phoneNumber==null || phoneCount==null || key.equals("")|| 
				phoneNumber.equals("")|| phoneCount.equals("")){
			return 1 ;
		}
		try {
			telphone.call(key, phoneNumber, phoneCount);
		} catch (Exception e) {
			return 1 ;
		}
		return 0;
	}

	@Override
	public Integer sendMessage(String key, String phoneNumber,
			String message) {
		if(key==null || message==null || phoneNumber==null || key.equals("")||
				message.equals("")||phoneNumber.equals("")){
			return 1 ;
		}
		
		try {
			telphone.sendMessage(key, phoneNumber, message) ;
		} catch (Exception e) {
			return 1 ;
		}
		return 0;
	}

	@Override
	public Integer stopCall(String key, String phoneNumber) {
		
		if(key==null || key.equals("") || phoneNumber==null || phoneNumber.equals("")){
			return 1 ;
		}
		try {
			telphone.stopCall(key, phoneNumber);
		} catch (Exception e) {
			return 1 ;
		}
		return 0;
	}

	@Override
	public List<AlarmSerious> queryAlarmSerious() {
		
		List<AlarmSerious> allList = new java.util.ArrayList<AlarmSerious>();
		
		//设备为net100以上
		List<AlarmSerious> refList = this.alarmDao.getAlarmSeriousList(AlarmDao.ALARM_IMPORTTANT) ;
		for (AlarmSerious alarmSerious : refList) {
			alarmSerious.setAlarmType(0) ;		//设置报警类型为仓库
			allList.add(alarmSerious) ;
		}
		
		//设备为net100以内
		List<AlarmSerious> refList2 = this.alarmDao.getAlarmSeriousList(AlarmDao.ALARM_IMPORTTANT2) ;
		for (AlarmSerious alarmSerious3 : refList2) {
			alarmSerious3.setAlarmType(0) ;
			allList.add(alarmSerious3) ;
		}
		
		List<AlarmSerious> LostPowerList = this.alarmDao.getLostPower() ;
		for (AlarmSerious alarmSerious4 : LostPowerList) {
			alarmSerious4.setAlarmType(2) ;		//设置报警类型为断电报警
			allList.add(alarmSerious4) ;
		}
		
		List<AlarmSerious> carList = this.alarmDao.getAlarmSeriousList(AlarmDao.CARALARM_IMPORTTANT) ;
		for (AlarmSerious alarmSerious2 : carList) {
			alarmSerious2.setAlarmType(1) ;		//设置报警类型为车载
			allList.add(alarmSerious2) ;
		}
		
		distinctAlarmSeriousData(allList) ;
		
		return allList;
	}
	

	public List<TimeOutAlarm> queryTimeoutAlarm() {
		List<TimeOutAlarm> list = this.alarmDao.getTimeoutAlarmList();
		return list;
	}
	
	
	public Integer testUrl(String urlStr){
		HttpURLConnection connt = null;
		try{
			//URL url = new URL("http://192.168.1.119:8888/fdap");
			URL url = new URL(urlStr);
			connt = (HttpURLConnection)url.openConnection();
			connt.setRequestMethod("HEAD");
			String strMessage = connt.getResponseMessage();
			if (strMessage.compareTo("OK") == 0) {
				return 1;
			}
			else{
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		finally{
			connt.disconnect();
		}
		
	}
	
	public List<HistRefData> getHistRefData() {
		List<HistRefData> hisRefList = alarmDao.getHistRefData();
		return hisRefList;
	}

		/**
		 * 除去严重报警重复分支数据
		 * @param list
		 */
		private void distinctAlarmSeriousData(List<AlarmSerious> list){
		
		if(list==null || list.size()==0)
				return ;
		
		Map<Long, AlarmSerious> map = new HashMap<Long, AlarmSerious>();
		
		
		//循环遍历集合去除重复的值
		for (Iterator<AlarmSerious> iterator = list.iterator(); iterator.hasNext();) {
			AlarmSerious alarmSerious  =  iterator.next();
			
			if(map.containsKey(alarmSerious.getBranchId()))
				iterator.remove();
			else
				map.put(alarmSerious.getBranchId(), alarmSerious);
			
		}
		
	}

		public List<CarDataNoChange> queryCar_noChange(Integer day) {
			return alarmDao.getCar_noChange(day);
		}
	
	
}
