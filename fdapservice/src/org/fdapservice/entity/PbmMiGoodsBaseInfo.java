package org.fdapservice.entity;

/**
 * Goods Baseinfo entity.
 * @author zhaoyou
 *
 */
public class PbmMiGoodsBaseInfo {
	private Long goodId;
	private String goodsName;
	private String goodsType;
	private String goodsUnit;
	private String typeName;
	private String prodarea;
	private String storageEnv;
	private Long oid;
	private Short isDelete;
	
	
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
	public Long getGoodId() {
  	return goodId;
  }
	public void setGoodId(Long goodId) {
  	this.goodId = goodId;
  }
	public String getGoodsName() {
  	return goodsName;
  }
	public void setGoodsName(String goodsName) {
  	this.goodsName = goodsName;
  }
	public String getGoodsType() {
  	return goodsType;
  }
	public void setGoodsType(String goodsType) {
  	this.goodsType = goodsType;
  }
	public String getGoodsUnit() {
  	return goodsUnit;
  }
	public void setGoodsUnit(String goodsUnit) {
  	this.goodsUnit = goodsUnit;
  }
	public String getTypeName() {
  	return typeName;
  }
	public void setTypeName(String typeName) {
  	this.typeName = typeName;
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
	
}
