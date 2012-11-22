package org.fdap.biz.order;

import java.util.List;

import org.fdap.biz.impl.order.BarCodeTrackDetailBiz;
import org.fdap.dao.order.BarCodeTrackDetailDao;
import org.fdap.entity.PbmBarcodeDetailTrack;

/**
 * 
 * @author zhaoyou
 *
 */
public class BarCodeTrackDetailBizImpl implements BarCodeTrackDetailBiz {

	private BarCodeTrackDetailDao detailDao = null;

	public void setDetailDao(BarCodeTrackDetailDao detailDao) {
  	this.detailDao = detailDao;
  }

	@Override
  public List<PbmBarcodeDetailTrack> getByOrderId(Long orderId) {
	  //return detailDao.getByOrder(orderId);
		return null;
  }
	
}
