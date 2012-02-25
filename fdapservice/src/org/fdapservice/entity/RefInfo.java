package org.fdapservice.entity;

public class RefInfo {
	private static final long serialVersionUID = 1L;
	private Long refId;
	private String name;
	private int floorType;
	private int floorNo;
	private String remark;
	
	public RefInfo(){}
	
	public RefInfo(Long refid,String name,int floorType,int floorNo,String remark){
		super();
		this.refId = refid;
		this.name = name;
		this.floorType = floorType;
		this.floorNo = floorNo;
		this.remark = remark;
	}
	
	public Long getRefId() {
		return refId;
	}
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFloorType() {
		return floorType;
	}
	public void setFloorType(int floorType) {
		this.floorType = floorType;
	}
	public int getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
