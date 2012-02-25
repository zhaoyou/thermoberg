package org.fdapservice.entity;

import java.io.Serializable;
/**
 * 车载启停记录实体
 * @author zhoukuanwei
 *
 */
public class CarStartup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int aid1;
	private int aid2;
	private int aid3;
	private String startTime;
	private String endTime;
	private String carrier;
	private int intervalValue;
	
	public CarStartup(){
		super();
	}
	
	public CarStartup(int aid1,int aid2,int aid3,String startTime,String endTime,String carrier,int intervalValue){
		super();
		this.aid1 = aid1;
		this.aid2 = aid2;
		this.aid3 = aid3;
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
