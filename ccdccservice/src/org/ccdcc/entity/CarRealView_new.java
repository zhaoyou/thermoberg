package org.ccdcc.entity;

import java.io.Serializable;
import java.util.Date;

public class CarRealView_new implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id ;		
	private Double ai1 ;
	private Double ai2 ;
	private Double ai3 ;
	private Double ai4 ;
	
	private Double latitude ;
	private Integer latitude_dir ;
	private Double longitude ;
	private Integer longitude_dir ;
	
	private Date	updateTime ;
	private Integer	runStatus ;
	private Integer	alarmStatus ;
	private Integer connectionStatus ;
	
	private Integer unloadStatus;
	private Integer speedStatus;
	private Double carSpeed;
	private Integer AlarmData;
	
	private Integer flag ;
	
	private String projectId ;
	
	public CarRealView_new(){super();}
	
	public CarRealView_new(Integer id, Double ai1, Double ai2, Double ai3,
			Double ai4, Double latitude, Integer latitude_dir,
			Double longitude, Integer longitude_dir, Date updateTime,
			Integer runStatus, Integer alarmStatus,
			Integer connectionStatus,Integer unloadStatus,Integer speedStatus,
			Double carSpeed,Integer AlarmData,Integer flag) {
		super();
		this.id = id;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.latitude = latitude;
		this.latitude_dir = latitude_dir;
		this.longitude = longitude;
		this.longitude_dir = longitude_dir;
		this.updateTime = updateTime;
		this.runStatus = runStatus;
		this.alarmStatus = alarmStatus;
		this.connectionStatus = connectionStatus;
		this.unloadStatus = unloadStatus;
		this.speedStatus = speedStatus;
		this.carSpeed = carSpeed;
		this.AlarmData = AlarmData;
		this.flag = flag;
	}
	
	public CarRealView_new(Integer id, Double ai1, Double ai2, Double ai3,
			Double ai4, Double latitude, Integer latitude_dir,
			Double longitude, Integer longitude_dir, Date updateTime,
			Integer runStatus, Integer alarmStatus,
			Integer connectionStatus,Integer unloadStatus,Integer speedStatus,
			Double carSpeed,Integer AlarmData,Integer flag,String projectId) {
		super();
		this.id = id;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.latitude = latitude;
		this.latitude_dir = latitude_dir;
		this.longitude = longitude;
		this.longitude_dir = longitude_dir;
		this.updateTime = updateTime;
		this.runStatus = runStatus;
		this.alarmStatus = alarmStatus;
		this.connectionStatus = connectionStatus;
		this.unloadStatus = unloadStatus;
		this.speedStatus = speedStatus;
		this.carSpeed = carSpeed;
		this.AlarmData = AlarmData;
		this.flag = flag;
		this.projectId = projectId;
	}
	
	

	public Integer getId() {
		return id;
	}

	public Double getAi1() {
		return ai1;
	}

	public Double getAi2() {
		return ai2;
	}

	public Double getAi3() {
		return ai3;
	}

	public Double getAi4() {
		return ai4;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Integer getLatitude_dir() {
		return latitude_dir;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Integer getLongitude_dir() {
		return longitude_dir;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getRunStatus() {
		return runStatus;
	}

	public Integer getAlarmStatus() {
		return alarmStatus;
	}

	public Integer getConnectionStatus() {
		return connectionStatus;
	}

	public Integer getUnloadStatus() {
		return unloadStatus;
	}

	public Integer getSpeedStatus() {
		return speedStatus;
	}

	public Double getCarSpeed() {
		return carSpeed;
	}

	public Integer getAlarmData() {
		return AlarmData;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAi1(Double ai1) {
		this.ai1 = ai1;
	}

	public void setAi2(Double ai2) {
		this.ai2 = ai2;
	}

	public void setAi3(Double ai3) {
		this.ai3 = ai3;
	}

	public void setAi4(Double ai4) {
		this.ai4 = ai4;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLatitude_dir(Integer latitude_dir) {
		this.latitude_dir = latitude_dir;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setLongitude_dir(Integer longitude_dir) {
		this.longitude_dir = longitude_dir;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}

	public void setAlarmStatus(Integer alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public void setConnectionStatus(Integer connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public void setUnloadStatus(Integer unloadStatus) {
		this.unloadStatus = unloadStatus;
	}

	public void setSpeedStatus(Integer speedStatus) {
		this.speedStatus = speedStatus;
	}

	public void setCarSpeed(Double carSpeed) {
		this.carSpeed = carSpeed;
	}

	public void setAlarmData(Integer alarmData) {
		AlarmData = alarmData;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
