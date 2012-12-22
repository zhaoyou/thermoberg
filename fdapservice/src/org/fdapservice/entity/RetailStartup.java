package org.fdapservice.entity;

import java.io.Serializable;
/**
 * 小批零启停记录实体
 * @author wjt
 *
 */
public class RetailStartup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int aid1;	
	private String startTime;
	private String endTime;
	private String carrier;
	private int intervalValue;
	
	public RetailStartup(){
		super();
	}
	
	public RetailStartup(int aid1,String startTime,String endTime,String carrier,int intervalValue){
		super();
		this.aid1 = aid1;
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.carrier = carrier;
		this.intervalValue = intervalValue;
	}
	
	public int getAid1() {
		return aid1;
	}
	public void setAid1(int aid1) {
		this.aid1 = aid1;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getIntervalValue() {
		return intervalValue;
	}
	public void setIntervalValue(int intervalValue) {
		this.intervalValue = intervalValue;
	}
}
