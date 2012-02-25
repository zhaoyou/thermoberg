package org.fdapservice.entity;

public class HisStartup {
	private static final long serialVersionUID = 1L;
	
	private Long startupid;
	private Long refId;
	private String startTime;
	private String endTime;
	private String carrier;
	private int intervalValue;
	
	public HisStartup(){super();}
	
	public HisStartup(Long startupid,Long refId,String startTime,String endTime,String carrier,int intervalValue){
		super();
		this.startupid = startupid;
		this.refId = refId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.carrier = carrier;
		this.intervalValue = intervalValue;
	}

	public Long getStartupid() {
		return startupid;
	}

	public void setStartupid(Long startupid) {
		this.startupid = startupid;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getIntervalValue() {
		return intervalValue;
	}

	public void setIntervalValue(int intervalValue) {
		this.intervalValue = intervalValue;
	}
	
}
