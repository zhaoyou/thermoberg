package org.fdap.entity;

import java.util.Date;

public class PbmCarInOutTrack {
  private Long cid;
  private Long orderId;
  private Date inTime;
  private Date outTime;
  private Long miCarId;
  private Long miPdaId;
  private Short uploadStatus;
public Long getCid() {
	return cid;
}
public void setCid(Long cid) {
	this.cid = cid;
}
public Long getOrderId() {
	return orderId;
}
public void setOrderId(Long orderId) {
	this.orderId = orderId;
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
public Long getMiCarId() {
	return miCarId;
}
public void setMiCarId(Long miCarId) {
	this.miCarId = miCarId;
}
public Long getMiPdaId() {
	return miPdaId;
}
public void setMiPdaId(Long miPdaId) {
	this.miPdaId = miPdaId;
}
public Short getUploadStatus() {
	return uploadStatus;
}
public void setUploadStatus(Short uploadStatus) {
	this.uploadStatus = uploadStatus;
}
}
