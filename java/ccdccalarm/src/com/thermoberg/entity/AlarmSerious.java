package com.thermoberg.entity;

import java.util.Date;

import com.thermoberg.util.DateUtil;

/**
 * 严重报警的实体类
 * @author zhaoyou
 *
 */
public class AlarmSerious implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long branchId ;
	private String branchName ;
	private Date	updateTime ;
	
	private String 	timestr ;
	private Integer alarmType ;
	
	
	private Integer delayValue ;
	
	private Integer isConfirm ;		//默认没有确认 0
	
	private Integer isSend ;		//默认没有发送短信 0
	private Integer isCall ;		//默认没有拨打电话 0
	
	
	public AlarmSerious(){
		super();
	}
	
	
	public AlarmSerious(Long branchId, String branchName, Date updateTime,
			String timestr, Integer alarmType, Integer delayValue,
			Integer isConfirm, Integer isSend, Integer isCall) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.updateTime = updateTime;
		this.timestr = timestr;
		this.alarmType = alarmType;
		this.delayValue = delayValue;
		this.isConfirm = isConfirm;
		this.isSend = isSend;
		this.isCall = isCall;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	public String getTimestr() {
		return DateUtil.dateToString(this.updateTime);
	}
	
	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}
	
	
	public Integer getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	
	
	public Integer getDelayValue() {
		return 10;
	}
	public void setDelayValue(Integer delayValue) {
		this.delayValue = delayValue;
	}
	
	
	
	public Integer getIsConfirm() {
		return 0;
	}
	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}
	
	
	
	public Integer getIsSend() {
		return 0;
	}
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	
	
	
	public Integer getIsCall() {
		return 0;
	}
	public void setIsCall(Integer isCall) {
		this.isCall = isCall;
	}
	
	

}
