package org.fdap.biz.order;

import java.util.List;

import org.fdap.biz.impl.order.BarCodeTrackBiz;
import org.fdap.dao.order.BarCodeTrackDao;
import org.fdap.entity.PbmBarcodeTrack;

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
  public List<PbmBarcodeTrack> getByOrderId(Long orderId) {
	  // TODO Auto-generated method stub
	  return null;
  }
	
}
