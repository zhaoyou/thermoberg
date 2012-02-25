package org.fdapservice.entity;

import java.io.Serializable;
/**
 * 车载实时数据实体
 * @author zhoukuanwei
 */
public class CarRealData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int aid1;
	private int aid2;
	private int aid3;
	
	private Double t1;
	private Double t2;
	private Double t3;
	
	private Double latitude;
	private int latitude_dir;
	private Double longitude;
	private int longitude_dir;
	
	private String time;
	
	private Integer runstatus;
	
	private int isalarm;

	public CarRealData(){
		super();
	}
	
	public CarRealData(int aid1,int aid2,int aid3,Double t1,Double t2,Double t3,Double latitude,
			int latitude_dir,Double longitude,int longitude_dir,String time,Integer runstatus){
		super();
		this.aid1 = aid1;
		this.aid2 = aid2;
		this.aid3 = aid3;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.latitude = latitude;
		this.latitude_dir = latitude_dir;
		this.longitude = longitude;
		this.longitude_dir = longitude_dir;
		this.time = time;
		this.runstatus = runstatus;
	}
	
	
	public CarRealData(int aid1,int aid2,int aid3,Double t1,Double t2,Double t3,Double latitude,
			int latitude_dir,Double longitude,int longitude_dir,String time,int isalarm){
		super();
		this.aid1 = aid1;
		this.aid2 = aid2;
		this.aid3 = aid3;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.latitude = latitude;
		this.latitude_dir = latitude_dir;
		this.longitude = longitude;
		this.longitude_dir = longitude_dir;
		this.time = time;
		this.isalarm = isalarm;
	}
	
	public int getAid1() {
		return aid1;
	}

	public void setAid1(int aid1) {
		this.aid1 = aid1;
	}

	public int getAid2() {
		return aid2;
	}

	public void setAid2(int aid2) {
		this.aid2 = aid2;
	}

	public int getAid3() {
		return aid3;
	}

	public void setAid3(int aid3) {
		this.aid3 = aid3;
	}

	public Double getT1() {
		return t1;
	}

	public void setT1(Double t1) {
		this.t1 = t1;
	}

	public Double getT2() {
		return t2;
	}

	public void setT2(Double t2) {
		this.t2 = t2;
	}

	public Double getT3() {
		return t3;
	}

	public void setT3(Double t3) {
		this.t3 = t3;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public int getLatitude_dir() {
		return latitude_dir;
	}

	public void setLatitude_dir(int latitude_dir) {
		this.latitude_dir = latitude_dir;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getLongitude_dir() {
		return longitude_dir;
	}

	public void setLongitude_dir(int longitude_dir) {
		this.longitude_dir = longitude_dir;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIsalarm() {
		return isalarm;
	}

	public void setIsalarm(int isalarm) {
		this.isalarm = isalarm;
	}

	public Integer getRunstatus() {
		return runstatus;
	}

	public void setRunstatus(Integer runstatus) {
		this.runstatus = runstatus;
	}
}
