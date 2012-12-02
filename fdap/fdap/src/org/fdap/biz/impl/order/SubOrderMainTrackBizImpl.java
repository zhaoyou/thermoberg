package org.fdap.biz.impl.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fdap.biz.order.SubOrderMainTrackBiz;
import org.fdap.dao.order.BarCodeTrackDao;
import org.fdap.dao.order.BarCodeTrackDetailDao;
import org.fdap.dao.order.PbmGoodsFullInfoDao;
import org.fdap.dao.order.SubOrderMainDetailDao;
import org.fdap.dao.order.SubOrderMainTrackDao;
import org.fdap.entity.order.PbmMedicineSummary;
import org.fdap.entity.order.PbmMiBarCodeDetailTrack;
import org.fdap.entity.order.PbmMiBarCodeTrack;
import org.fdap.entity.order.PbmMiGoodsFullInfo;
import org.fdap.entity.order.PbmSubOrderDetailTrack;
import org.fdap.entity.order.PbmSubOrderMainTrack;

public class SubOrderMainTrackBizImpl implements SubOrderMainTrackBiz {

	private SubOrderMainTrackDao subOrderMainDao = null;
	private SubOrderMainDetailDao subOrderMailDetailDao = null;
	private BarCodeTrackDao barCodeTrackDao = null;
	private BarCodeTrackDetailDao barCodeTrackDetailDao = null;
	private PbmGoodsFullInfoDao fullInfoDao = null;
	
	public void setSubOrderMailDetailDao(SubOrderMainDetailDao subOrderMailDetailDao) {
  	this.subOrderMailDetailDao = subOrderMailDetailDao;
  }
	public void setBarCodeTrackDao(BarCodeTrackDao barCodeTrackDao) {
  	this.barCodeTrackDao = barCodeTrackDao;
  }
	public void setBarCodeTrackDetailDao(BarCodeTrackDetailDao barCodeTrackDetailDao) {
  	this.barCodeTrackDetailDao = barCodeTrackDetailDao;
  }
	public void setSubOrderMainDao(SubOrderMainTrackDao subOrderMainDao) {
  	this.subOrderMainDao = subOrderMainDao;
  }

	public void setFullInfoDao(PbmGoodsFullInfoDao fullInfoDao) {
  	this.fullInfoDao = fullInfoDao;
  }
	@Override
	public List<PbmSubOrderMainTrack> getByOrderId(Long orderId) {
		return subOrderMainDao.getByOrder(orderId);
	}

	@Override
  public List<Map<String, String>> getInfo(Long orderId, Long fullId, String goodTypeName,
  		String prodArea, String lotno) {
	  List<PbmSubOrderMainTrack> mainlist = subOrderMainDao.getByOrder(orderId);
	  List<PbmSubOrderDetailTrack> mainDetailList = subOrderMailDetailDao.getByOrder(orderId);
	  
	  List<PbmMiBarCodeTrack> barCodeList = barCodeTrackDao.getBy(orderId);
	  List<PbmMiBarCodeDetailTrack> barCodeDetailList = barCodeTrackDetailDao.getByOrder(orderId);
	  Map<Long, PbmMedicineSummary> ht = new HashMap<Long, PbmMedicineSummary>();
	  PbmMedicineSummary mms = new PbmMedicineSummary();
	  // for each 每一个子订单主记录
	  for (PbmSubOrderMainTrack msomt: mainlist ) {
	  	if (msomt.getInoutType() == 0 && msomt.getInwholeOrder() == 1) { // 完整订单.
	  		// 遍历所有的条码主跟踪记录
	  		for (PbmMiBarCodeTrack mbt: barCodeList) {
	  			// 遍历条码详细记录信息
	  			for (PbmMiBarCodeDetailTrack mbdt: barCodeDetailList) {
	  				if (mbdt.getBcdId() != mbt.getBcId()) { //不属于当前条码
	  					continue;
	  				}
	  				
	  				PbmMiGoodsFullInfo fullInfo = fullInfoDao.get(mbdt.getGoodsFullId());
	  				
	  				// 药品基本信息过滤
	  				if (isProductValid(fullInfo, fullId, goodTypeName, prodArea, lotno) == false) {
	  					if (mbt.getPacketType() == 2) {  //整件
	  						break;  //查找下一条码
	  					} else { // 散件
	  						continue; // 继续查找当前条码其他信息
	  					}
	  				}
	  				
	  				if (ht.containsKey(mbdt.getKid()) == false) {
	  					mms = new PbmMedicineSummary();
	  				} else {
	  					mms = ht.get(mbdt.getKid());
	  				}
	  				
	  				if (mbt.getPacketType() == 2) { //整件
	  					mms.setWholePacketNum(mms.getWholePacketNum() + mbt.getPacketNum());
	  					mms.setWholePacketUnitNum(mbdt.getTotalNum());
	  				} else { // 散件
	  					mms.setLoosePacketNum(mms.getLoosePacketNum() + 1);
	  					mms.setLoosePacketTotalNum(mms.getLoosePacketTotalNum() + mbdt.getTotalNum());
	  				}
	  				mms.setTotalNum(mms.getWholePacketNum() * mms.getWholePacketUnitNum() + mms.getLoosePacketTotalNum());
	  				mms.setKid(mbdt.getKid());
	  				mms.setMsomt(msomt);
	  				mms.setMegfi(fullInfo);
	  				
	  				if (ht.containsKey(mbdt.getKid()) == false) {
	  					ht.put(mbdt.getKid(), mms);
	  				}
	  				
	  				if (mbt.getPacketType() == 2) { //整件
	  					break; // 查找下一条码
	  				} else { // 散件
	  					continue;
	  				}
	  			}
	  		}
	  	} else { // 退换货，订单
	  		// 所有子订单详细记录信息
	  		for (PbmSubOrderDetailTrack msodt: mainDetailList) {
	  			if (msodt.getSubOrderMid() != msomt.getSubOrderMid()) { //不是子订单主记录对应的子订单详细记录
	  				continue;
	  			}
	  			
	  			for (PbmMiBarCodeDetailTrack mbdt: barCodeDetailList) {
	  				if (msodt.getBcdId() != mbdt.getBcdId()) { //不是子订单详细记录对应的详细条码信息
	  					continue;
	  				}
	  				
	  				PbmMiGoodsFullInfo megfi = fullInfoDao.get(mbdt.getGoodsFullId());
	  				
	  				if (isProductValid(megfi, fullId, goodTypeName, prodArea, lotno) == false) {
	  					break;
	  				}
	  				
	  				// 遍历所有条码主跟踪记录
	  				for (PbmMiBarCodeTrack mbt: barCodeList) {
	  					if (mbdt.getBcId() != mbt.getBcId()) { // 不属于当前条码
	  						continue;
	  					}
	  					
	  					if (ht.containsKey(mbdt.getKid()) == false) {
	  						mms = new PbmMedicineSummary();
	  					} else {
	  						mms = ht.get(mbdt.getKid());
	  					}
	  					
	  					if (mbt.getPacketType() == 2) { // 整件
	  						if (msodt.getTotalNum() / mbdt.getTotalNum() > 0){ //存在整件
	  							mms.setWholePacketNum(mms.getWholePacketNum() + msodt.getTotalNum() / mbdt.getTotalNum());
	  							mms.setWholePacketUnitNum(mbdt.getTotalNum());
	  						}
	  						
	  						if (msodt.getTotalNum() % mbdt.getTotalNum() > 0) { // 存在散件
	  							mms.setLoosePacketNum(mms.getLoosePacketNum() + 1); // 散件件数增加
	  							mms.setLoosePacketTotalNum(mms.getLoosePacketTotalNum() + msodt.getTotalNum() % mbdt.getTotalNum()); // 整件散件药品中数量
	  						}
	  					} else { // 散件
	  						mms.setLoosePacketNum(mms.getLoosePacketNum() + 1);
	  						mms.setLoosePacketTotalNum(mms.getLoosePacketTotalNum() + msodt.getTotalNum());
	  					}
	  					
	  					mms.setTotalNum(mms.getWholePacketNum() * mms.getWholePacketUnitNum() + mms.getLoosePacketTotalNum() );
	  					mms.setKid(mbdt.getKid());
	  					mms.setMsomt(msomt);
	  					mms.setMegfi(megfi);
	  					
	  					if (ht.containsKey(mbdt.getKid()) == false) {
	  						ht.put(mbdt.getKid(), mms);
	  					}
	  					break;
	  				}
	  				break;
	  			}
	  		}
	  	}
	  }
	  return null;
  }
	
	private boolean isProductValid(PbmMiGoodsFullInfo info, Long goodId, String typeName,
			String prodArea, String lotno) {
		
		if (info.getBaseInfo().getGoodId().equals(goodId) == false) {
			return false;
		}
		
		if (info.getBaseInfo().getGoodsType().equals(typeName) == false) {
			return false;
		}
		
		if (info.getBaseInfo().getProdarea().endsWith(prodArea) == false) {
			return false;
		}
		
		if (info.getLotno().contains(lotno) == false) {
			return false;
		}
		return true;
	}

}
