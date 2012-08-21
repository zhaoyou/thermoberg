package com.thermoberg.entity;

import java.io.Serializable;

public class CarDataNoChange implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String projectName;
	private String branchName;
	private	Integer timediff;
	private String namestr;
	
	public CarDataNoChange(){super();}
	
	public CarDataNoChange(String projectName,String branchName,Integer timediff){
		super();
		this.projectName = projectName;
		this.branchName = branchName;
		this.timediff = timediff;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getBranchName() {
		return branchName;
	}

	public Integer getTimediff() {
		return timediff;
	}

	public String getNamestr() {
		return this.branchName+"("+this.projectName+")";
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setTimediff(Integer timediff) {
		this.timediff = timediff;
	}

	public void setNamestr(String namestr) {
		this.namestr = namestr;
	}
	
}
