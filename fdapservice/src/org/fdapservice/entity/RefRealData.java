package org.fdapservice.entity;

import java.io.Serializable;

/**
 * 冷库实时数据实体
 * @author zhaoyou
 *
 */
public class RefRealData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id ;
	
	private Double value ;
	
	private String time ;
	
	private Integer status ;

	
	
	public RefRealData() {
		super();
	}


	public RefRealData(Integer id, Double value, String time, Integer status) {
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
