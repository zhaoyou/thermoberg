package org.fdap.entity.order;

public class PbmCarRelation {
	private Long id;
	private Long miCarId;
	private String miCarName;
	private String ccCarId;
	private String ccCarName;
	private Short isDelete;
	private Long oid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMiCarId() {
		return miCarId;
	}
	public void setMiCarId(Long miCarId) {
		this.miCarId = miCarId;
	}
	public String getMiCarName() {
		return miCarName;
	}
	public void setMiCarName(String miCarName) {
		this.miCarName = miCarName;
	}
	public String getCcCarId() {
		return ccCarId;
	}
	public void setCcCarId(String ccCarId) {
		this.ccCarId = ccCarId;
	}
	public String getCcCarName() {
		return ccCarName;
	}
	public void setCcCarName(String ccCarName) {
		this.ccCarName = ccCarName;
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
