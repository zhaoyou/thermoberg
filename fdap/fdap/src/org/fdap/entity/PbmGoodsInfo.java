package org.fdap.entity;

public class PbmGoodsInfo {
  private Long goodId;
  private String goodsname;
  private String goodstype;
  private String goodsunit;
  private String typename;
  private String prodarea;
  private String storageEnv;
  private Short uploadStatus;
public Long getGoodId() {
	return goodId;
}
public void setGoodId(Long goodId) {
	this.goodId = goodId;
}
public String getGoodsname() {
	return goodsname;
}
public void setGoodsname(String goodsname) {
	this.goodsname = goodsname;
}
public String getGoodstype() {
	return goodstype;
}
public void setGoodstype(String goodstype) {
	this.goodstype = goodstype;
}
public String getGoodsunit() {
	return goodsunit;
}
public void setGoodsunit(String goodsunit) {
	this.goodsunit = goodsunit;
}
public String getTypename() {
	return typename;
}
public void setTypename(String typename) {
	this.typename = typename;
}
public String getProdarea() {
	return prodarea;
}
public void setProdarea(String prodarea) {
	this.prodarea = prodarea;
}
public String getStorageEnv() {
	return storageEnv;
}
public void setStorageEnv(String storageEnv) {
	this.storageEnv = storageEnv;
}
public Short getUploadStatus() {
	return uploadStatus;
}
public void setUploadStatus(Short uploadStatus) {
	this.uploadStatus = uploadStatus;
}
}
