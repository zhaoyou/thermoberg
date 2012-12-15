package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.TrackRefBiz;
import org.fdap.dao.order.TrackRefDao;

public class TrackRefBizImpl implements TrackRefBiz {

	private TrackRefDao trackRefDao = null;

	public TrackRefDao getTrackRefDao() {
		return trackRefDao;
	}
	public void setTrackRefDao(TrackRefDao trackRefDao) {
		this.trackRefDao = trackRefDao;
	}

	@Override
	public List getRefEnv(Long erpId) {
		return trackRefDao.getRefEnv(erpId);
	}

}
