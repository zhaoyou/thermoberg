package org.ccdcc.entity;

import java.io.Serializable;
import java.util.Date;

public class CarHisView_new implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id ;
	private Double ai1 ;
	private Double ai2 ;
	private Double ai3 ;
	private Double ai4 ;
	private Integer parentId ;
	private Double latitude ;
	private Integer latitude_dir ;
	private Double longitude ;
	private Integer longitude_dir ;
	private Integer alarmStatus ;
	
	private Integer unloadStatus;

	private Integer histAlarmStorageType ;

	private Integer gpsStorageType ;

	private Integer histDataStorageType;

	private Integer unloadStorageType;

	private Integer histAlarmData;
	
	private Date updateTime ;
	private Integer flag ;
	
	private Date endTime ;		//为了满足webservice调用，该字段表示该历史数据所在的历史启停记录
	
	public CarHisView_new(){super();}
	
	public CarHisView_new(Integer id, Double ai1, Double ai2, Double ai3,
			Double ai4, Integer parentId, Double latitude,
			Integer latitude_dir, Double longitude,
			Integer longitude_dir, Integer alarmStatus,
			Integer unloadStatus,Integer histAlarmStorageType,
			Integer gpsStorageType ,Integer histDataStorageType,
			Integer unloadStorageType,Integer histAlarmData,
			Date updateTime, Integer flag) {
		super();
		this.id = id;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.parentId = parentId;
		this.latitude = latitude;
		this.latitude_dir = latitude_dir;
		this.longitude = longitude;
		this.longitude_dir = longitude_dir;
		this.alarmStatus = alarmStatus;
		this.unloadStatus = unloadStatus;
		this.histAlarmStorageType = histAlarmStorageType;
		this.gpsStorageType = gpsStorageType;
		this.histDataStorageType = histDataStorageType;
		this.unloadStorageType = unloadStorageType;
		this.histAlarmData = histAlarmData;
		this.updateTime = updateTime;
		this.flag = flag;
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

	public Integer getParentId() {
		return parentId;
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

	public Integer getAlarmStatus() {
		return alarmStatus;
	}

	public Integer getUnloadStatus() {
		return unloadStatus;
	}

	public Integer getHistAlarmStorageType() {
		return histAlarmStorageType;
	}

	public Integer getGpsStorageType() {
		return gpsStorageType;
	}

	public Integer getHistDataStorageType() {
		return histDataStorageType;
	}

	public Integer getUnloadStorageType() {
		return unloadStorageType;
	}

	public Integer getHistAlarmData() {
		return histAlarmData;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public Date getEndTime() {
		return endTime;
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

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public void setAlarmStatus(Integer alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public void setUnloadStatus(Integer unloadStatus) {
		this.unloadStatus = unloadStatus;
	}

	public void setHistAlarmStorageType(Integer histAlarmStorageType) {
		this.histAlarmStorageType = histAlarmStorageType;
	}

	public void setGpsStorageType(Integer gpsStorageType) {
		this.gpsStorageType = gpsStorageType;
	}

	public void setHistDataStorageType(Integer histDataStorageType) {
		this.histDataStorageType = histDataStorageType;
	}

	public void setUnloadStorageType(Integer unloadStorageType) {
		this.unloadStorageType = unloadStorageType;
	}

	public void setHistAlarmData(Integer histAlarmData) {
		this.histAlarmData = histAlarmData;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
