package org.fdap.entity.order;

import java.util.Date;

/**
 * order track entity.
 * @author zhaoyou
 *
 */
public class PbmMiOrderTrack {
	private Long orderId;
	private Long orderNo;
	private Long receiverId;
	private Date orderTime;
	private Short isDelete;
	private Short uploadStatus;
	private Long oid;
	
	public Long getOrderId() {
  	return orderId;
  }
	public void setOrderId(Long orderId) {
  	this.orderId = orderId;
  }
	public Long getOrderNo() {
  	return orderNo;
  }
	public void setOrderNo(Long orderNo) {
  	this.orderNo = orderNo;
  }
	public Long getReceiverId() {
  	return receiverId;
  }
	public void setReceiverId(Long receiverId) {
  	this.receiverId = receiverId;
  }
	public Date getOrderTime() {
  	return orderTime;
  }
	public void setOrderTime(Date orderTime) {
  	this.orderTime = orderTime;
  }
	public Short getIsDelete() {
  	return isDelete;
  }
	public void setIsDelete(Short isDelete) {
  	this.isDelete = isDelete;
  }
	public Short getUploadStatus() {
  	return uploadStatus;
  }
	public void setUploadStatus(Short uploadStatus) {
  	this.uploadStatus = uploadStatus;
  }
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
	
}
