package org.fdap.entity.order;

/**
 * SubOrder main track entity.
 * @author zhaoyou
 *
 */
public class PbmSubOrderMainTrack {
	private Long subOrderParentMid;
	private Long subOrderMid;
	private String subOrderName;
	private Long orderId;
	private Short inoutType;
	private Short inwholeOrder;
	private Short orderStatus;
	private Short planType;
	private Short planStatus;
	private Short isDelete;
	private Long oid;
	
	private Integer wholePacketNum;
	private Integer loosePacketNum;
	
	public Integer getWholePacketNum() {
		return wholePacketNum;
	}
	public void setWholePacketNum(Integer wholePacketNum) {
		this.wholePacketNum = wholePacketNum;
	}
	public Integer getLoosePacketNum() {
		return loosePacketNum;
	}
	public void setLoosePacketNum(Integer loosePacketNum) {
		this.loosePacketNum = loosePacketNum;
	}
	public Long getSubOrderParentMid() {
  	return subOrderParentMid;
  }
	public void setSubOrderParentMid(Long subOrderParentMid) {
  	this.subOrderParentMid = subOrderParentMid;
  }
	public Long getSubOrderMid() {
  	return subOrderMid;
  }
	public void setSubOrderMid(Long subOrderMid) {
  	this.subOrderMid = subOrderMid;
  }
	public String getSubOrderName() {
  	return subOrderName;
  }
	public void setSubOrderName(String subOrderName) {
  	this.subOrderName = subOrderName;
  }
	public Long getOrderId() {
  	return orderId;
  }
	public void setOrderId(Long orderId) {
  	this.orderId = orderId;
  }
	public Short getInoutType() {
  	return inoutType;
  }
	public void setInoutType(Short inoutType) {
  	this.inoutType = inoutType;
  }
	public Short getInwholeOrder() {
  	return inwholeOrder;
  }
	public void setInwholeOrder(Short inwholeOrder) {
  	this.inwholeOrder = inwholeOrder;
  }
	public Short getOrderStatus() {
  	return orderStatus;
  }
	public void setOrderStatus(Short orderStatus) {
  	this.orderStatus = orderStatus;
  }
	public Short getPlanType() {
  	return planType;
  }
	public void setPlanType(Short planType) {
  	this.planType = planType;
  }
	public Short getPlanStatus() {
  	return planStatus;
  }
	public void setPlanStatus(Short planStatus) {
  	this.planStatus = planStatus;
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
	
	
}
