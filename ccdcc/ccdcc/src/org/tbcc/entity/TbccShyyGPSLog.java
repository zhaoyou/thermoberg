package org.tbcc.entity;

import java.util.Date;

/**
 * TbccShyyGPSLOg.
 * @author zhaoyou
 *
 */
public class TbccShyyGPSLog implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date recordTime;
	private String carNO;
	private int returnFlag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getrecordTime() {
		return recordTime;
	}
	public void setrecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getCarNO() {
		return carNO;
	}
	public void setCarNO(String carNO) {
		this.carNO = carNO;
	}
	public int getReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(int returnFlag) {
		this.returnFlag = returnFlag;
	}
}
