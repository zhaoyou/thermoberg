package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.BarCodeTrackBiz;
import org.fdap.dao.order.BarCodeTrackDao;
import org.fdap.entity.order.PbmMiBarCodeTrack;;;

/**
 * 
 * @author zhaoyou
 *
 */
public class BarCodeTrackBizImpl implements BarCodeTrackBiz {
	private BarCodeTrackDao dao = null;

	public void setDao(BarCodeTrackDao dao) {
  	this.dao = dao;
  }

	@Override
  public List<PbmMiBarCodeTrack> getByOrderId(Long orderId) {
	  return dao.getBy(orderId);
  }
	
}
