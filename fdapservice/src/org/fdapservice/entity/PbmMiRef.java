package org.fdapservice.entity;

/**
 * Ref entity
 * @author zhaoyou
 *
 */
public class PbmMiRef {
	private Long mirefId;
	private String mirefName;
	private Long oid;
	private Short isDelete;
	
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
	public Long getMirefId() {
		return mirefId;
	}
	public void setMirefId(Long mirefId) {
		this.mirefId = mirefId;
	}
	public String getMirefName() {
		return mirefName;
	}
	public void setMirefName(String mirefName) {
		this.mirefName = mirefName;
	}
	public Short getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
}
