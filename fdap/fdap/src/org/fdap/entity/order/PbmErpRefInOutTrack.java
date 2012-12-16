package org.fdap.entity.order;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * order goods Erp refInOut track.
 * @author zhaoyou
 *
 */
public class PbmErpRefInOutTrack {
	private Long kdId;
	private Long kid;
	private Long orderId;
	private Date inTime;
	private Date outTime;
	private Long goodFullId;
	private Integer totalNum;
	private Long erpRefId;
	private Long erpPosId;
	private Short isPacketRec;
	private Short inoutType;
	private Long subOrderMid;
	private Short inoutStatus;
	private Short isDelete;
	private Long oid;
	
	// for page show.
	private String storeEnv;
	private String storeType;
	private String storeName;
	private Long storeId;
	
	
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getInTimeStr() {
		return sf.format(this.getInTime());
	}
	
	public String getOutTimeStr() {
		return sf.format(this.getOutTime());
	}
	
	public String getStoreEnv() {
		return storeEnv;
	}
	public void setStoreEnv(String storeEnv) {
		this.storeEnv = storeEnv;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getKdId() {
  	return kdId;
  }
	public void setKdId(Long kdId) {
  	this.kdId = kdId;
  }
	public Long getKid() {
  	return kid;
  }
	public void setKid(Long kid) {
  	this.kid = kid;
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
	public Long getGoodFullId() {
  	return goodFullId;
  }
	public void setGoodFullId(Long goodFullId) {
  	this.goodFullId = goodFullId;
  }
	public Integer getTotalNum() {
  	return totalNum;
  }
	public void setTotalNum(Integer totalNum) {
  	this.totalNum = totalNum;
  }
	public Long getErpRefId() {
  	return erpRefId;
  }
	public void setErpRefId(Long erpRefId) {
  	this.erpRefId = erpRefId;
  }
	public Long getErpPosId() {
  	return erpPosId;
  }
	public void setErpPosId(Long erpPosId) {
  	this.erpPosId = erpPosId;
  }
	public Short getIsPacketRec() {
  	return isPacketRec;
  }
	public void setIsPacketRec(Short isPacketRec) {
  	this.isPacketRec = isPacketRec;
  }
	public Short getInoutType() {
  	return inoutType;
  }
	public void setInoutType(Short inoutType) {
  	this.inoutType = inoutType;
  }
	public Long getSubOrderMid() {
  	return subOrderMid;
  }
	public void setSubOrderMid(Long subOrderMid) {
  	this.subOrderMid = subOrderMid;
  }
	public Short getInoutStatus() {
  	return inoutStatus;
  }
	public void setInoutStatus(Short inoutStatus) {
  	this.inoutStatus = inoutStatus;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	
}
