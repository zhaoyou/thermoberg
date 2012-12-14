package org.fdap.entity.order;

public class PbmRecorderRelation {
	private Long id;
	private Long miRecorderId;
	private String miRecorderName;
	private String ccRecorderId;
	private String ccRecorderName;
	private Short isDelete;
	private Long oid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMiRecorderId() {
		return miRecorderId;
	}
	public void setMiRecorderId(Long miRecorderId) {
		this.miRecorderId = miRecorderId;
	}
	public String getMiRecorderName() {
		return miRecorderName;
	}
	public void setMiRecorderName(String miRecorderName) {
		this.miRecorderName = miRecorderName;
	}
	public String getCcRecorderId() {
		return ccRecorderId;
	}
	public void setCcRecorderId(String ccRecorderId) {
		this.ccRecorderId = ccRecorderId;
	}
	public String getCcRecorderName() {
		return ccRecorderName;
	}
	public void setCcRecorderName(String ccRecorderName) {
		this.ccRecorderName = ccRecorderName;
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
