package org.fdapservice.entity;

public class RefHisData {
	private static final long serialVersionUID = 1L;
	
	private Integer id ;
	
	private Double value ;
	
	private String time ;
	
	private Integer status ;

	public RefHisData(){super();}
	
	public RefHisData(Integer id, Double value, String time, Integer status) {
		super();
		this.id = id;
		this.value = value;
		this.time = time;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
