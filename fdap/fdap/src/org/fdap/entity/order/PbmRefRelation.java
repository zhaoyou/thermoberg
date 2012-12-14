package org.fdap.entity.order;

public class PbmRefRelation {
	private Long id;
	private Long erpRefId;
	private String erpRefName;
	private String ccRefId;
	private String ccRefName;
	private Short isDelete;
	private Long oid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getErpRefId() {
		return erpRefId;
	}
	public void setErpRefId(Long erpRefId) {
		this.erpRefId = erpRefId;
	}
	public String getErpRefName() {
		return erpRefName;
	}
	public void setErpRefName(String erpRefName) {
		this.erpRefName = erpRefName;
	}
	public String getCcRefId() {
		return ccRefId;
	}
	public void setCcRefId(String ccRefId) {
		this.ccRefId = ccRefId;
	}
	public String getCcRefName() {
		return ccRefName;
	}
	public void setCcRefName(String ccRefName) {
		this.ccRefName = ccRefName;
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
