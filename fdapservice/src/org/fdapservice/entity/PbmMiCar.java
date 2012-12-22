package org.fdapservice.entity;

/**
 * Car entity.
 * @author zhaoyou
 *
 */
public class PbmMiCar {
	private Long micarId;
	private String micarName;
	private Long oid;
	private Short isdelete;
	
	public Long getMicarId() {
		return micarId;
	}
	public void setMicarId(Long micarId) {
		this.micarId = micarId;
	}
	public String getMicarName() {
		return micarName;
	}
	public void setMicarName(String micarName) {
		this.micarName = micarName;
	}
	public Short getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Short isdelete) {
		this.isdelete = isdelete;
	}
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
}
