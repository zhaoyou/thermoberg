package com.thermoberg.entity;

import java.util.Date;

import com.thermoberg.util.DateUtil;

/**
 * 车载实时报警信息
 * @author zhaoyou
 *
 */
public class CarAlarmInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String projectId ;
	private String 	branchName ;
	private Date 	updateTime ;
	private String timestr ;
	private Integer delayValue ;
	private String projectName ;
	private String displayName ;
	
	private Integer isConfirm  ;				//默认没有确认 0 
	
	private Integer isCall 		 ;				//默认没有关闭打电话 0
	
	private Integer isSend 		 ;				//默认没有发送出去 0
	
	
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



	public Integer getIsConfirm() {
		return 0;
	}



	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}



	public CarAlarmInfo(){
		super();
	}
	
	

	public CarAlarmInfo( String branchName, Date updateTime,
			String timestr, Integer delayValue, String projectName) {
		super();
		this.branchName = branchName;
		this.updateTime = updateTime;
		this.timestr = timestr;
		this.delayValue = delayValue;
		this.projectName = projectName;
	}


	public String getDisplayName(){
		return this.getProjectName()+"("+this.getBranchName()+")";
	}



	public String getProjectId() {
		return projectId;
	}



	public void setProjectId(String projectId) {
		this.projectId = projectId;
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



	public Integer getDelayValue() {
		return 10;
	}



	public void setDelayValue(Integer delayValue) {
		this.delayValue = delayValue;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
	
	
	
}
