package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.BarCodeTrackDetailBiz;
import org.fdap.dao.order.BarCodeTrackDetailDao;
import org.fdap.entity.order.PbmMiBarCodeDetailTrack;

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
  public List<PbmMiBarCodeDetailTrack> getByOrderId(Long orderId) {
	  return detailDao.getByOrder(orderId);
  }
	
}
