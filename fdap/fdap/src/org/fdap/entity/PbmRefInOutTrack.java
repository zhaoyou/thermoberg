package org.fdap.entity;

import java.util.Date;

public class PbmRefInOutTrack {
	private Long kid;
	private Long orderNo;
	private Date inTime;
	private Date outTime;
	private Long goodFullId;
	private int totalNum;
	private String erpRefid;
	private String erpPosId;
	private Short uploadStatus;
	public Long getKid() {
		return kid;
	}
	public void setKid(Long kid) {
		this.kid = kid;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Long getGoodFullId() {
		return goodFullId;
	}
	public void setGoodFullId(Long goodFullId) {
		this.goodFullId = goodFullId;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getErpRefid() {
		return erpRefid;
	}
	public void setErpRefid(String erpRefid) {
		this.erpRefid = erpRefid;
	}
	public String getErpPosId() {
		return erpPosId;
	}
	public void setErpPosId(String erpPosId) {
		this.erpPosId = erpPosId;
	}
	public Short getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(Short uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
	
}
