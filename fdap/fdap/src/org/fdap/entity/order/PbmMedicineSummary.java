package org.fdap.entity.order;

public class PbmMedicineSummary {
	private int wholePacketNum;
	private int wholePacketUnitNum;
	private int loosePacketNum;
	private int loosePacketTotalNum;
	private int totalNum;
	private long kid;
	private PbmSubOrderMainTrack msomt;
	private PbmMiGoodsFullInfo megfi;
	public int getWholePacketNum() {
  	return wholePacketNum;
  }
	public void setWholePacketNum(int wholePacketNum) {
  	this.wholePacketNum = wholePacketNum;
  }
	public int getWholePacketUnitNum() {
  	return wholePacketUnitNum;
  }
	public void setWholePacketUnitNum(int wholePacketUnitNum) {
  	this.wholePacketUnitNum = wholePacketUnitNum;
  }
	public int getLoosePacketNum() {
  	return loosePacketNum;
  }
	public void setLoosePacketNum(int loosePacketNum) {
  	this.loosePacketNum = loosePacketNum;
  }
	public int getLoosePacketTotalNum() {
  	return loosePacketTotalNum;
  }
	public void setLoosePacketTotalNum(int loosePacketTotalNum) {
  	this.loosePacketTotalNum = loosePacketTotalNum;
  }
	public int getTotalNum() {
  	return totalNum;
  }
	public void setTotalNum(int totalNum) {
  	this.totalNum = totalNum;
  }
	public long getKid() {
  	return kid;
  }
	public void setKid(long kid) {
  	this.kid = kid;
  }
	public PbmSubOrderMainTrack getMsomt() {
  	return msomt;
  }
	public void setMsomt(PbmSubOrderMainTrack msomt) {
  	this.msomt = msomt;
  }
	public PbmMiGoodsFullInfo getMegfi() {
  	return megfi;
  }
	public void setMegfi(PbmMiGoodsFullInfo megfi) {
  	this.megfi = megfi;
  }
	
	
}
