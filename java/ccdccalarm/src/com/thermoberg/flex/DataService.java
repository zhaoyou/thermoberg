package com.thermoberg.flex;

import java.util.List;

import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.AlarmSerious;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.entity.TimeOutAlarm;
import com.thermoberg.service.AlarmService;
import com.thermoberg.service.impl.AlarmServiceImpl;


public class DataService {

	private AlarmService serive = new AlarmServiceImpl();

	public DataService() {
		super();
	}
	
	public List<AlarmInfo> getAlarm(){
		return serive.queryAlarmList() ;
	}
	
	public List<AlarmInfo> getConnection(){
		return serive.queryConnectionList() ;
	}
	
	public List<CarAlarmInfo> getCarAlarm(){
		return serive.queryCarAlarmList(); 
	}
	
	public Integer callPhone(String key ,String phoneNumber ,Integer phoneCount) {
		return serive.callPhone(key, phoneNumber, phoneCount);
	}
	
	public Integer sendMessage(String key ,String phoneNumber,String message){
		return serive.sendMessage(key, phoneNumber, message);
	}
	
	public Integer stopCall(String key ,String phoneNumber){
		return serive.stopCall(key, phoneNumber);
	}
	
	public List<AlarmSerious> getAlarmSerious(){
		return serive.queryAlarmSerious() ;
	}
	
	public List<TimeOutAlarm> getTimeoutAlarm(){
		return serive.queryTimeoutAlarm();
	}
	
	public Integer testTomcat(){
		return serive.testUrl("http://192.168.0.3:8888/ccdcc");
	}
	
	public Integer testOutNet(){
		return serive.testUrl("http://www.thermoberg.com/ccdcc");
	}
	
	public List<HistRefData> getHistRefData(){
		return serive.getHistRefData();
	}
	
	public List<CarDataNoChange> getCar_noChange(Integer day){
		return serive.queryCar_noChange(day);
	}
}
