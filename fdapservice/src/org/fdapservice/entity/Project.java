package org.fdapservice.entity;

public class Project {
	private static final long serialVersionUID = 1L;
	private Long projectid;
	private String name;
	private Integer type;
	public Project(){}
	public Project(Long projectid,String name,Integer type){
		super();
		this.projectid = projectid;
		this.name = name;
		this.type = type;
	}
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
