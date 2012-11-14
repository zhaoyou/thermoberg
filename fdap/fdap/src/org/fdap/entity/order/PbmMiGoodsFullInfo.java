package org.fdap.entity.order;

import java.util.Date;

/**
 * PmbGoods FullInfo entity.
 * @author zhaoyou
 *
 */
public class PbmMiGoodsFullInfo extends PbmMiGoodsBaseInfo {
	private Long goodFullId;
	private String lotno;
	private Date invaliDate;
	private Long goodId;
	private Long oid;
	public Long getGoodFullId() {
  	return goodFullId;
  }
	public void setGoodFullId(Long goodFullId) {
  	this.goodFullId = goodFullId;
  }
	public String getLotno() {
  	return lotno;
  }
	public void setLotno(String lotno) {
  	this.lotno = lotno;
  }
	public Date getInvaliDate() {
  	return invaliDate;
  }
	public void setInvaliDate(Date invaliDate) {
  	this.invaliDate = invaliDate;
  }
	public Long getGoodId() {
  	return goodId;
  }
	public void setGoodId(Long goodId) {
  	this.goodId = goodId;
  }
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
	
}
