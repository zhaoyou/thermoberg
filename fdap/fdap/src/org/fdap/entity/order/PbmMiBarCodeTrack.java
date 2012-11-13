package org.fdap.entity.order;

/**
 * bar code track.
 * @author zhaoyou
 *
 */
public class PbmMiBarCodeTrack {
	private Long bcId;
	private Long orderId;
	private String barCode;
	private Short barCodeType;
	private Integer packetType;
	private Integer packetNum;
	private Short isDelete;
	private Long oid;
	public Long getBcId() {
  	return bcId;
  }
	public void setBcId(Long bcId) {
  	this.bcId = bcId;
  }
	public Long getOrderId() {
  	return orderId;
  }
	public void setOrderId(Long orderId) {
  	this.orderId = orderId;
  }
	public String getBarCode() {
  	return barCode;
  }
	public void setBarCode(String barCode) {
  	this.barCode = barCode;
  }
	public Short getBarCodeType() {
  	return barCodeType;
  }
	public void setBarCodeType(Short barCodeType) {
  	this.barCodeType = barCodeType;
  }
	public Integer getPacketType() {
  	return packetType;
  }
	public void setPacketType(Integer packetType) {
  	this.packetType = packetType;
  }
	public Integer getPacketNum() {
  	return packetNum;
  }
	public void setPacketNum(Integer packetNum) {
  	this.packetNum = packetNum;
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
