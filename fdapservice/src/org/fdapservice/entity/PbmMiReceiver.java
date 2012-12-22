package org.fdapservice.entity;

/**
 * order receiver entity.
 * @author zhaoyou
 *
 */
public class PbmMiReceiver {
	private Long rid;
	private String fullName;
	private String shortName;
	private Long oid;
	private Short isDelete;
	
	public Long getRid() {
  	return rid;
  }
	public void setRid(Long rid) {
  	this.rid = rid;
  }
	public String getFullName() {
  	return fullName;
  }
	public void setFullName(String fullName) {
  	this.fullName = fullName;
  }
	public String getShortName() {
  	return shortName;
  }
	public void setShortName(String shortName) {
  	this.shortName = shortName;
  }
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
	public Short getIsDelete() {
  	return isDelete;
  }
	public void setIsDelete(Short isDelete) {
  	this.isDelete = isDelete;
  }
	
}
