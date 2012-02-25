package org.ccdcc.entity;

import java.io.Serializable;
import java.util.Date;

public class CarRealData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String CarNo;
	
	private Double ai1;
	private Double ai2;
	private Double ai3;
	private Double ai4;
	
	private Date updateTime;
	
	private Integer runStatus;
	
	private Integer connectionStatus;
	
	
	public CarRealData(){super();}
	
	
	public CarRealData(String CarNo,Double ai1,Double ai2,Double ai3,Double ai4,Date updateTime,Integer runStatus,Integer connectionStatus){
		super();
		this.CarNo = CarNo;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.updateTime = updateTime;
		this.runStatus = runStatus;
		this.connectionStatus = connectionStatus;
	}


	public String getCarNo() {
		return CarNo;
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


	public Date getUpdateTime() {
		return updateTime;
	}


	public Integer getRunStatus() {
		return runStatus;
	}


	public Integer getConnectionStatus() {
		return connectionStatus;
	}


	public void setCarNo(String carNo) {
		CarNo = carNo;
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


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}


	public void setConnectionStatus(Integer connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
}
