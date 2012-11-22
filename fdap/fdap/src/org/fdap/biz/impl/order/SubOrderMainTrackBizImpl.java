package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.SubOrderMainTrackBiz;
import org.fdap.dao.order.SubOrderMainTrackDao;
import org.fdap.entity.order.PbmSubOrderMainTrack;

public class SubOrderMainTrackBizImpl implements SubOrderMainTrackBiz {

	private SubOrderMainTrackDao subOrderMainDao = null;
	
	public void setSubOrderMainDao(SubOrderMainTrackDao subOrderMainDao) {
  	this.subOrderMainDao = subOrderMainDao;
  }

	@Override
	public List<PbmSubOrderMainTrack> getByOrderId(Long orderId) {
		return subOrderMainDao.getByOrder(orderId);
	}

}
