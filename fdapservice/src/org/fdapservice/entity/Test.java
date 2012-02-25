package org.fdapservice.entity;

import java.io.Serializable;

public class Test implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;
	private Double tt;
	public Test(){super();}
	public Test(String name,Integer id,Double tt){
		super();
		this.name = name;
		this.id = id;
		this.tt = tt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getTt() {
		return tt;
	}
	public void setTt(Double tt) {
		this.tt = tt;
	}
	
}
