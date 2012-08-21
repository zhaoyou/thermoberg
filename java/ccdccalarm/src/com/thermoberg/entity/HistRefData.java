package com.thermoberg.entity;

import com.thermoberg.util.DateUtil;


public class HistRefData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String projectId  ;
	private Integer neiId  ;
	private Double ai1 ;
	private Double ai2 ;
	private Double ai3 ;
	private Double ai4 ;
	private Double ai5 ;
	private Double ai6 ;
	private Double ai7 ;
	private Double ai8 ;
	private Double ai9 ;
	private Double ai10 ;
	private Double ai11 ;
	private Double ai12 ;
	
	/**
	 * 后面是为了扩展兼容模块，实时数据的探头由原来的12个扩展到了32探头
	 * 如果是原来的设备类型 12(1-9温度探头、10-12为湿度探头)
	 * 			   兼容类型 32(1-16为温度探头、17-32为湿度探头)
	 */
	private Double ai13 ;
	private Double ai14 ;
	private Double ai15 ;
	private Double ai16 ;
	private Double ai17 ;
	private Double ai18 ;
	private Double ai19 ;
	private Double ai20 ;
	private Double ai21 ;
	private Double ai22 ;
	private Double ai23 ;
	private Double ai24 ;
	private Double ai25 ;
	private Double ai26 ;
	private Double ai27 ;
	private Double ai28 ;
	private Double ai29 ;
	private Double ai30 ;
	private Double ai31 ;
	private Double ai32 ;

	private String updateTime ;
	private Integer runStatus_Ref1;
	private Integer runStatus_Ref2 ;
	private Integer runStatus_Ref3;
	private Integer runStatus_Ref4;
	
	private Integer alarmStatus_Ref1;
	private Integer alarmStatus_Ref2;
	private Integer alarmStatus_Ref3;
	private Integer alarmStatus_Ref4;
	
	private Integer connectStatus;
	
	private Integer alarmStatus_Door1 ;
	private Integer alarmStatus_Door2 ;
	private Integer alarmStatus_Door3 ;
	private Integer alarmStatus_Door4 ;
	
	private Integer alarmStatus_LostPower1 ;
	private Integer alarmStatus_LostPower2 ;
	private Integer alarmStatus_LostPower3 ;
	private Integer alarmStatus_LostPower4 ;
	
	private Integer alarmStatus_Sound1 ;
	private Integer alarmStatus_Sound2 ;
	private Integer alarmStatus_Sound3 ;
	private Integer alarmStatus_Sound4 ;
	
	private String timestr;
	
	public HistRefData(){super();};
	
	public HistRefData( String projectId, Integer neiId,
			Double ai1, Double ai2, Double ai3, Double ai4, Double ai5,
			Double ai6, Double ai7, Double ai8, Double ai9, Double ai10,
			Double ai11, Double ai12, Double ai13, Double ai14, Double ai15, Double ai16, 
			Double ai17, Double ai18, Double ai19, Double ai20, Double ai21, Double ai22, 
			Double ai23, Double ai24, Double ai25, Double ai26, Double ai27, Double ai28, 
			Double ai29, Double ai30, Double ai31, Double ai32,
			String updateTime, Integer runStatus_Ref1,
			Integer runStatus_Ref2, Integer runStatus_Ref3,
			Integer runStatus_Ref4,  Integer alarmStatus_Ref1,
			Integer alarmStatus_Ref2, Integer alarmStatus_Ref3,
			Integer alarmStatus_Ref4, Integer connectStatus,Integer alarmStatus_Door1 ,
			Integer alarmStatus_Door2 ,Integer alarmStatus_Door3 ,Integer alarmStatus_Door4,
			Integer alarmStatus_LostPower1 ,Integer alarmStatus_LostPower2,Integer alarmStatus_LostPower3,
			Integer alarmStatus_LostPower4 ,Integer alarmStatus_Sound1,Integer alarmStatus_Sound2,
			Integer alarmStatus_Sound3,Integer alarmStatus_Sound4 ) {
		super();
		this.projectId = projectId;
		this.neiId = neiId;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.ai5 = ai5;
		this.ai6 = ai6;
		this.ai7 = ai7;
		this.ai8 = ai8;
		this.ai9 = ai9;
		this.ai10 = ai10;
		this.ai11 = ai11;
		this.ai12 = ai12;
		this.ai13 = ai13;
		this.ai14 = ai14;
		this.ai15 = ai15;
		this.ai16 = ai16;
		this.ai17 = ai17;
		this.ai18 = ai18;
		this.ai19 = ai19;
		this.ai20 = ai20;
		this.ai21 = ai21;
		this.ai22 = ai22;
		this.ai23 = ai23;
		this.ai24 = ai24;
		this.ai25 = ai25;
		this.ai26 = ai26;
		this.ai27 = ai27;
		this.ai28 = ai28;
		this.ai29 = ai29;
		this.ai30 = ai30;
		this.ai31 = ai31;
		this.ai32 = ai32;
		this.updateTime = updateTime;
		this.runStatus_Ref1 = runStatus_Ref1;
		this.runStatus_Ref2 = runStatus_Ref2;
		this.runStatus_Ref3 = runStatus_Ref3;
		this.runStatus_Ref4 = runStatus_Ref4;
		
		this.alarmStatus_Ref1 = alarmStatus_Ref1;
		this.alarmStatus_Ref2 = alarmStatus_Ref2;
		this.alarmStatus_Ref3 = alarmStatus_Ref3;
		this.alarmStatus_Ref4 = alarmStatus_Ref4;
		
		this.alarmStatus_Door1 = alarmStatus_Door1;
		this.alarmStatus_Door2 = alarmStatus_Door2;
		this.alarmStatus_Door3 = alarmStatus_Door3;
		this.alarmStatus_Door4 = alarmStatus_Door4;
		this.alarmStatus_LostPower1 = alarmStatus_LostPower1;
		this.alarmStatus_LostPower2 = alarmStatus_LostPower2;
		this.alarmStatus_LostPower3 = alarmStatus_LostPower3;
		this.alarmStatus_LostPower4 = alarmStatus_LostPower4;
		this.alarmStatus_Sound1 = alarmStatus_Sound1;
		this.alarmStatus_Sound2 = alarmStatus_Sound2;
		this.alarmStatus_Sound3 = alarmStatus_Sound3;
		this.alarmStatus_Sound4 = alarmStatus_Sound4;
		this.connectStatus = connectStatus;
	}

	public String getProjectId() {
		return projectId;
	}


	public Integer getNeiId() {
		return neiId;
	}


	public Double getAi1() {
		return ai1;
	}


	public Double getAi2() {
		return ai2;
	}


	public Double getAi3() {
		return ai3;
	}


	public Double getAi4() {
		return ai4;
	}


	public Double getAi5() {
		return ai5;
	}


	public Double getAi6() {
		return ai6;
	}


	public Double getAi7() {
		return ai7;
	}


	public Double getAi8() {
		return ai8;
	}


	public Double getAi9() {
		return ai9;
	}


	public Double getAi10() {
		return ai10;
	}


	public Double getAi11() {
		return ai11;
	}


	public Double getAi12() {
		return ai12;
	}


	public Double getAi13() {
		return ai13;
	}


	public Double getAi14() {
		return ai14;
	}


	public Double getAi15() {
		return ai15;
	}


	public Double getAi16() {
		return ai16;
	}


	public Double getAi17() {
		return ai17;
	}


	public Double getAi18() {
		return ai18;
	}


	public Double getAi19() {
		return ai19;
	}


	public Double getAi20() {
		return ai20;
	}


	public Double getAi21() {
		return ai21;
	}


	public Double getAi22() {
		return ai22;
	}


	public Double getAi23() {
		return ai23;
	}


	public Double getAi24() {
		return ai24;
	}


	public Double getAi25() {
		return ai25;
	}


	public Double getAi26() {
		return ai26;
	}


	public Double getAi27() {
		return ai27;
	}


	public Double getAi28() {
		return ai28;
	}


	public Double getAi29() {
		return ai29;
	}


	public Double getAi30() {
		return ai30;
	}


	public Double getAi31() {
		return ai31;
	}


	public Double getAi32() {
		return ai32;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public Integer getRunStatus_Ref1() {
		return runStatus_Ref1;
	}


	public Integer getRunStatus_Ref2() {
		return runStatus_Ref2;
	}


	public Integer getRunStatus_Ref3() {
		return runStatus_Ref3;
	}


	public Integer getRunStatus_Ref4() {
		return runStatus_Ref4;
	}


	public Integer getAlarmStatus_Ref1() {
		return alarmStatus_Ref1;
	}


	public Integer getAlarmStatus_Ref2() {
		return alarmStatus_Ref2;
	}


	public Integer getAlarmStatus_Ref3() {
		return alarmStatus_Ref3;
	}


	public Integer getAlarmStatus_Ref4() {
		return alarmStatus_Ref4;
	}


	public Integer getConnectStatus() {
		return connectStatus;
	}


	public Integer getAlarmStatus_Door1() {
		return alarmStatus_Door1;
	}


	public Integer getAlarmStatus_Door2() {
		return alarmStatus_Door2;
	}


	public Integer getAlarmStatus_Door3() {
		return alarmStatus_Door3;
	}


	public Integer getAlarmStatus_Door4() {
		return alarmStatus_Door4;
	}


	public Integer getAlarmStatus_LostPower1() {
		return alarmStatus_LostPower1;
	}


	public Integer getAlarmStatus_LostPower2() {
		return alarmStatus_LostPower2;
	}


	public Integer getAlarmStatus_LostPower3() {
		return alarmStatus_LostPower3;
	}


	public Integer getAlarmStatus_LostPower4() {
		return alarmStatus_LostPower4;
	}


	public Integer getAlarmStatus_Sound1() {
		return alarmStatus_Sound1;
	}


	public Integer getAlarmStatus_Sound2() {
		return alarmStatus_Sound2;
	}


	public Integer getAlarmStatus_Sound3() {
		return alarmStatus_Sound3;
	}


	public Integer getAlarmStatus_Sound4() {
		return alarmStatus_Sound4;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public void setNeiId(Integer neiId) {
		this.neiId = neiId;
	}


	public void setAi1(Double ai1) {
		this.ai1 = ai1;
	}


	public void setAi2(Double ai2) {
		this.ai2 = ai2;
	}


	public void setAi3(Double ai3) {
		this.ai3 = ai3;
	}


	public void setAi4(Double ai4) {
		this.ai4 = ai4;
	}


	public void setAi5(Double ai5) {
		this.ai5 = ai5;
	}


	public void setAi6(Double ai6) {
		this.ai6 = ai6;
	}


	public void setAi7(Double ai7) {
		this.ai7 = ai7;
	}


	public void setAi8(Double ai8) {
		this.ai8 = ai8;
	}


	public void setAi9(Double ai9) {
		this.ai9 = ai9;
	}


	public void setAi10(Double ai10) {
		this.ai10 = ai10;
	}


	public void setAi11(Double ai11) {
		this.ai11 = ai11;
	}


	public void setAi12(Double ai12) {
		this.ai12 = ai12;
	}


	public void setAi13(Double ai13) {
		this.ai13 = ai13;
	}


	public void setAi14(Double ai14) {
		this.ai14 = ai14;
	}


	public void setAi15(Double ai15) {
		this.ai15 = ai15;
	}


	public void setAi16(Double ai16) {
		this.ai16 = ai16;
	}


	public void setAi17(Double ai17) {
		this.ai17 = ai17;
	}


	public void setAi18(Double ai18) {
		this.ai18 = ai18;
	}


	public void setAi19(Double ai19) {
		this.ai19 = ai19;
	}


	public void setAi20(Double ai20) {
		this.ai20 = ai20;
	}


	public void setAi21(Double ai21) {
		this.ai21 = ai21;
	}


	public void setAi22(Double ai22) {
		this.ai22 = ai22;
	}


	public void setAi23(Double ai23) {
		this.ai23 = ai23;
	}


	public void setAi24(Double ai24) {
		this.ai24 = ai24;
	}


	public void setAi25(Double ai25) {
		this.ai25 = ai25;
	}


	public void setAi26(Double ai26) {
		this.ai26 = ai26;
	}


	public void setAi27(Double ai27) {
		this.ai27 = ai27;
	}


	public void setAi28(Double ai28) {
		this.ai28 = ai28;
	}


	public void setAi29(Double ai29) {
		this.ai29 = ai29;
	}


	public void setAi30(Double ai30) {
		this.ai30 = ai30;
	}


	public void setAi31(Double ai31) {
		this.ai31 = ai31;
	}


	public void setAi32(Double ai32) {
		this.ai32 = ai32;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public void setRunStatus_Ref1(Integer runStatus_Ref1) {
		this.runStatus_Ref1 = runStatus_Ref1;
	}


	public void setRunStatus_Ref2(Integer runStatus_Ref2) {
		this.runStatus_Ref2 = runStatus_Ref2;
	}


	public void setRunStatus_Ref3(Integer runStatus_Ref3) {
		this.runStatus_Ref3 = runStatus_Ref3;
	}


	public void setRunStatus_Ref4(Integer runStatus_Ref4) {
		this.runStatus_Ref4 = runStatus_Ref4;
	}


	public void setAlarmStatus_Ref1(Integer alarmStatus_Ref1) {
		this.alarmStatus_Ref1 = alarmStatus_Ref1;
	}


	public void setAlarmStatus_Ref2(Integer alarmStatus_Ref2) {
		this.alarmStatus_Ref2 = alarmStatus_Ref2;
	}


	public void setAlarmStatus_Ref3(Integer alarmStatus_Ref3) {
		this.alarmStatus_Ref3 = alarmStatus_Ref3;
	}


	public void setAlarmStatus_Ref4(Integer alarmStatus_Ref4) {
		this.alarmStatus_Ref4 = alarmStatus_Ref4;
	}


	public void setConnectStatus(Integer connectStatus) {
		this.connectStatus = connectStatus;
	}


	public void setAlarmStatus_Door1(Integer alarmStatus_Door1) {
		this.alarmStatus_Door1 = alarmStatus_Door1;
	}


	public void setAlarmStatus_Door2(Integer alarmStatus_Door2) {
		this.alarmStatus_Door2 = alarmStatus_Door2;
	}


	public void setAlarmStatus_Door3(Integer alarmStatus_Door3) {
		this.alarmStatus_Door3 = alarmStatus_Door3;
	}


	public void setAlarmStatus_Door4(Integer alarmStatus_Door4) {
		this.alarmStatus_Door4 = alarmStatus_Door4;
	}


	public void setAlarmStatus_LostPower1(Integer alarmStatus_LostPower1) {
		this.alarmStatus_LostPower1 = alarmStatus_LostPower1;
	}


	public void setAlarmStatus_LostPower2(Integer alarmStatus_LostPower2) {
		this.alarmStatus_LostPower2 = alarmStatus_LostPower2;
	}


	public void setAlarmStatus_LostPower3(Integer alarmStatus_LostPower3) {
		this.alarmStatus_LostPower3 = alarmStatus_LostPower3;
	}


	public void setAlarmStatus_LostPower4(Integer alarmStatus_LostPower4) {
		this.alarmStatus_LostPower4 = alarmStatus_LostPower4;
	}


	public void setAlarmStatus_Sound1(Integer alarmStatus_Sound1) {
		this.alarmStatus_Sound1 = alarmStatus_Sound1;
	}


	public void setAlarmStatus_Sound2(Integer alarmStatus_Sound2) {
		this.alarmStatus_Sound2 = alarmStatus_Sound2;
	}


	public void setAlarmStatus_Sound3(Integer alarmStatus_Sound3) {
		this.alarmStatus_Sound3 = alarmStatus_Sound3;
	}


	public void setAlarmStatus_Sound4(Integer alarmStatus_Sound4) {
		this.alarmStatus_Sound4 = alarmStatus_Sound4;
	}

	public String getTimestr() {
		return DateUtil.dateToString(DateUtil.stringToDate(updateTime));
	}

	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}
}
