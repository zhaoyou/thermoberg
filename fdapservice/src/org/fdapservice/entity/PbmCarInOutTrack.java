package org.fdapservice.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PbmCarInOutTrack {
	private Long cdId;
	private Long orderId;
	private Long pid;
	private Date inTime;
	private Date outTime;
	private Date demandTime;
	private Long inPdaId;
	private Long outPdaId;
	private Long miCarId;
	private Long subOrderMid;
	private Short inOutStatus;
	private Short isDelete;
	private Short uploadStatus;
	private Long oid;
	
	// for page show.
	private String storeEnv;
	private String storeType;
	private String storeName;
	
	private Long storeId;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
	public Long getCdId() {
		return cdId;
	}
	public void setCdId(Long cdId) {
		this.cdId = cdId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
	public Date getDemandTime() {
		return demandTime;
	}
	public void setDemandTime(Date demandTime) {
		this.demandTime = demandTime;
	}
	public Long getInPdaId() {
		return inPdaId;
	}
	public void setInPdaId(Long inPdaId) {
		this.inPdaId = inPdaId;
	}
	public Long getOutPdaId() {
		return outPdaId;
	}
	public void setOutPdaId(Long outPdaId) {
		this.outPdaId = outPdaId;
	}
	public Long getMiCarId() {
		return miCarId;
	}
	public void setMiCarId(Long miCarId) {
		this.miCarId = miCarId;
	}
	public Long getSubOrderMid() {
		return subOrderMid;
	}
	public void setSubOrderMid(Long subOrderMid) {
		this.subOrderMid = subOrderMid;
	}
	public Short getInOutStatus() {
		return inOutStatus;
	}
	public void setInOutStatus(Short inOutStatus) {
		this.inOutStatus = inOutStatus;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
}
