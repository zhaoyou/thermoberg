package org.fdapservice.entity;

/**
 * subOrder detail track entity.
 * @author zhaoyou
 *
 */
public class PbmSubOrderDetailTrack {
	private Long subOrderDetailId;
	private Long subOrderMid;
	private Long orderId;
	private Long bcdId;
	private Integer totalNum;
	private Short isDelete;
	private Long oid;
	public Long getSubOrderDetailId() {
  	return subOrderDetailId;
  }
	public void setSubOrderDetailId(Long subOrderDetailId) {
  	this.subOrderDetailId = subOrderDetailId;
  }
	public Long getSubOrderMid() {
  	return subOrderMid;
  }
	public void setSubOrderMid(Long subOrderMid) {
  	this.subOrderMid = subOrderMid;
  }
	public Long getBcdId() {
  	return bcdId;
  }
	public void setBcdId(Long bcdId) {
  	this.bcdId = bcdId;
  }
	public Integer getTotalNum() {
  	return totalNum;
  }
	public void setTotalNum(Integer totalNum) {
  	this.totalNum = totalNum;
  }
	public Short getIsDelete() {
  	return isDelete;
  }
	public void setIsDelete(Short isDelete) {
  	this.isDelete = isDelete;
  }
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
