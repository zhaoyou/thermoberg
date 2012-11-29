package org.fdap.biz.impl.order;

import java.util.List;
import java.util.Map;

import org.fdap.biz.order.SubOrderMainTrackBiz;
import org.fdap.dao.order.BarCodeTrackDao;
import org.fdap.dao.order.BarCodeTrackDetailDao;
import org.fdap.dao.order.SubOrderMainDetailDao;
import org.fdap.dao.order.SubOrderMainTrackDao;
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

	@Override
	public List<PbmSubOrderMainTrack> getByOrderId(Long orderId) {
		return subOrderMainDao.getByOrder(orderId);
	}

	@Override
  public List<Map<String, String>> getInfo(Long orderId) {
	  List<PbmSubOrderMainTrack> mainlist = subOrderMainDao.getByOrder(orderId);
	  List<PbmSubOrderDetailTrack> mainDetailList = subOrderMailDetailDao.getByOrder(orderId);
	  List<PbmMiBarCodeTrack> barCodeList = barCodeTrackDao.getBy(orderId);
	  List<PbmMiBarCodeDetailTrack> barCodeDetailList = barCodeTrackDetailDao.getByOrder(orderId);
	  
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
	  				
	  			}
	  		}
	  	} else { // 退换货，订单
	  		
	  	}
	  }
	  return null;
  }
	
	private boolean isProductValid(PbmMiGoodsFullInfo info) {
		return false;
	}

}
