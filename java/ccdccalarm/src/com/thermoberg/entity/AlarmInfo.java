package com.thermoberg.entity;

import java.io.Serializable;
import java.util.Date;

import com.thermoberg.util.DateUtil;

/**
 * ����һ���������汨����Ϣ��ʵ����
 * @author Administrator
 *
 */
public class AlarmInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long branchId ;
	private String branchName ;
	private Date updatetime ;
	
	private String timestr ;
	
	private Integer delayValue ;
	
	private Integer isConfirm ;			//Ĭ��û��ȷ�� 0 
	
	private Integer isCall ;			//Ĭ��û�йرմ�绰 0 ;
	
	private Integer isSend ;			//Ĭ��û�з��Ͷ��� 0 ;

	
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
		
		return 0 ;
	}


	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}


	public Integer getDelayValue() {
		return 10;
	}


	public void setDelayValue(Integer delayValue) {
		this.delayValue = delayValue;
	}


	public String getTimestr() {
		return DateUtil.dateToString(this.updatetime);
	}


	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}


	public AlarmInfo(){
		super() ;
	}
	
	
	public AlarmInfo(Long branchId, String branchName, Date updatetime) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.updatetime = updatetime;
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
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}



	

}
