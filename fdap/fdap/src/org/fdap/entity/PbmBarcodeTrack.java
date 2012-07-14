package org.fdap.entity;

public class PbmBarcodeTrack {
  private Long bcId;
  private Long orderId;
  private String barcode;
  private Integer packetTypeId;
  private Short uploadStatus;
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
public String getBarcode() {
	return barcode;
}
public void setBarcode(String barcode) {
	this.barcode = barcode;
}
public Integer getPacketTypeId() {
	return packetTypeId;
}
public void setPacketTypeId(Integer packetTypeId) {
	this.packetTypeId = packetTypeId;
}
public Short getUploadStatus() {
	return uploadStatus;
}
public void setUploadStatus(Short uploadStatus) {
	this.uploadStatus = uploadStatus;
}
  
  
}
