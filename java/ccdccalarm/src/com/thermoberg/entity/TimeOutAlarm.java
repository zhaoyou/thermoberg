package com.thermoberg.entity;

import java.io.Serializable;
import java.util.Date;

import com.thermoberg.util.DateUtil;

public class TimeOutAlarm implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String projectid ;
		private String branchName;
		private String projectName;
		private Integer netid ;
		
		private Date startTime;
		
		private String timestr;
		
		private Integer delayValue;
		
		private Integer isConfirm ;			//默认没有确认 0 
		
		private Integer isSend ;			//默认没有发送短信 0 ;
		
		private String nameStr;

		public TimeOutAlarm(){super();}
		
		public TimeOutAlarm(String projectid,String branchName,String projectName,Integer netid,Integer isSend,Date startTime ){
			super();
			this.projectid = projectid;
			this.branchName = branchName;
			this.projectName = projectName;
			this.netid = netid;
			this.startTime = startTime;
		}

		public String getProjectid() {
			return projectid;
		}

		public String getBranchName() {
			return branchName;
		}

		public String getProjectName() {
			return projectName;
		}

		public Integer getNetid() {
			return netid;
		}

		public Integer getIsSend() {
			return 0;
		}

		public void setProjectid(String projectid) {
			this.projectid = projectid;
		}

		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public void setNetid(Integer netid) {
			this.netid = netid;
		}

		public void setIsSend(Integer isSend) {
			this.isSend = isSend;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public String getTimestr() {
			return DateUtil.dateToString(this.startTime);
		}

		public Integer getDelayValue() {
			return 10;
		}

		public void setDelayValue(Integer delayValue) {
			this.delayValue = delayValue;
		}

		public void setTimestr(String timestr) {
			this.timestr = timestr;
		}

		public Integer getIsConfirm() {
			return 0;
		}

		public void setIsConfirm(Integer isConfirm) {
			this.isConfirm = isConfirm;
		}

		public String getNameStr() {
			return this.branchName+"("+this.projectName+"的"+this.netid+"#设备)";
		}

		public void setNameStr(String nameStr) {
			this.nameStr = nameStr;
		}
	
}
