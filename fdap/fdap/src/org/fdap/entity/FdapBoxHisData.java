package org.fdap.entity;

import java.util.Date;

public class FdapBoxHisData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private Double t1 ;
	private Long startUpId ;
	private Double latitude ;
	private Integer latitude_dir ;
	private Double longitude ;
	private Integer longitude_dir ;
	private Date	time ;
	private	 Integer isAlarm ;
	
	
	
	
	public FdapBoxHisData(Long id, Double t1,
			Long startUpId, Double latitude, Integer latitude_dir,
			Double longitude, Integer longitude_dir, Date time, Integer isAlarm) {
		super();
		this.id = id;
		this.t1 = t1;
		this.startUpId = startUpId;
		this.latitude = latitude;
		this.latitude_dir = latitude_dir;
		this.longitude = longitude;
		this.longitude_dir = longitude_dir;
		this.time = time;
		this.isAlarm = isAlarm;
	}
	
	
	
	public FdapBoxHisData() {
		super();
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getT1() {
		return t1;
	}
	public void setT1(Double t1) {
		this.t1 = t1;
	}
	public Long getStartUpId() {
		return startUpId;
	}
	public void setStartUpId(Long startUpId) {
		this.startUpId = startUpId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getLatitude_dir() {
		return latitude_dir;
	}
	public void setLatitude_dir(Integer latitude_dir) {
		this.latitude_dir = latitude_dir;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getLongitude_dir() {
		return longitude_dir;
	}
	public void setLongitude_dir(Integer longitude_dir) {
		this.longitude_dir = longitude_dir;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getIsAlarm() {
		return isAlarm;
	}
	public void setIsAlarm(Integer isAlarm) {
		this.isAlarm = isAlarm;
	}
	
	
	

}
