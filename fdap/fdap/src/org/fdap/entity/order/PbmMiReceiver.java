package org.fdap.entity.order;

/**
 * order receiver entity.
 * @author zhaoyou
 *
 */
public class PbmMiReceiver {
	private Long rid;
	private String fullName;
	private String shortName;
	
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
	
}
