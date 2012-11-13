package org.fdap.entity.order;

/**
 * barcode detail track.
 * @author zhaoyou
 *
 */
public class PbmMiBarCodeDetailTrack {
	private Long bcdId;
	private Long bcId;
	private Long orderId;
	private Long goodsFullId;
	private Integer totalNum;
	private Long kid;
	private Short isDelete;
	private Long oid;
	public Long getBcdId() {
  	return bcdId;
  }
	public void setBcdId(Long bcdId) {
  	this.bcdId = bcdId;
  }
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
	public Long getGoodsFullId() {
  	return goodsFullId;
  }
	public void setGoodsFullId(Long goodsFullId) {
  	this.goodsFullId = goodsFullId;
  }
	public Integer getTotalNum() {
  	return totalNum;
  }
	public void setTotalNum(Integer totalNum) {
  	this.totalNum = totalNum;
  }
	public Long getKid() {
  	return kid;
  }
	public void setKid(Long kid) {
  	this.kid = kid;
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
