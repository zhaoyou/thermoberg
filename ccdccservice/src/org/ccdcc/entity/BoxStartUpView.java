package org.ccdcc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 小批零移动终端历史启停实体视图
 * @author Administrator
 *
 */
public class BoxStartUpView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private	Integer id ;
		private Date beginTime ;
		private Date endTime ;
		private Integer recordInterval ;
		private String beginAddress ;
		private String endAddress ;
		private String shipper ;
		private String carrier ;
		private String receiver ;
		private Integer updateStatus ;
		private Integer flag ;
		private Integer tlimitType;
		
		private Integer tuplimit ;
		
		private Integer tdwlimit ;
		
		
		
		public Integer getTuplimit() {
			return tuplimit;
		}



		public void setTuplimit(Integer tuplimit) {
			this.tuplimit = tuplimit;
		}



		public Integer getTdwlimit() {
			return tdwlimit;
		}



		public void setTdwlimit(Integer tdwlimit) {
			this.tdwlimit = tdwlimit;
		}



		public BoxStartUpView(){}
		
		
		
		public BoxStartUpView(Integer id, Date beginTime, Date endTime,
				Integer recordInterval, String beginAddress, String endAddress,
				String shipper, String carrier, String receiver,
				Integer updateStatus, Integer flag,Integer tlimitType) {
			super();
			this.id = id;
			this.beginTime = beginTime;
			this.endTime = endTime;
			this.recordInterval = recordInterval;
			this.beginAddress = beginAddress;
			this.endAddress = endAddress;
			this.shipper = shipper;
			this.carrier = carrier;
			this.receiver = receiver;
			this.updateStatus = updateStatus;
			this.flag = flag;
			this.tlimitType=tlimitType;
		}



		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getBeginTime() {
			return beginTime;
		}
		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public Integer getRecordInterval() {
			return recordInterval;
		}
		public void setRecordInterval(Integer recordInterval) {
			this.recordInterval = recordInterval;
		}
		public String getBeginAddress() {
			return beginAddress;
		}
		public void setBeginAddress(String beginAddress) {
			this.beginAddress = beginAddress;
		}
		public String getEndAddress() {
			return endAddress;
		}
		public void setEndAddress(String endAddress) {
			this.endAddress = endAddress;
		}
		public String getShipper() {
			return shipper;
		}
		public void setShipper(String shipper) {
			this.shipper = shipper;
		}
		public String getCarrier() {
			return carrier;
		}
		public void setCarrier(String carrier) {
			this.carrier = carrier;
		}
		public String getReceiver() {
			return receiver;
		}
		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}
		public Integer getUpdateStatus() {
			return updateStatus;
		}
		public void setUpdateStatus(Integer updateStatus) {
			this.updateStatus = updateStatus;
		}
		public Integer getFlag() {
			return flag;
		}
		public void setFlag(Integer flag) {
			this.flag = flag;
		}



		public Integer getTlimitType() {
			return tlimitType;
		}



		public void setTlimitType(Integer tlimitType) {
			this.tlimitType = tlimitType;
		}
		
		
		
}
