package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.TrackRefBiz;
import org.fdap.dao.order.TrackRefDao;
import org.fdap.entity.order.PbmCarInOutTrack;
import org.fdap.entity.order.PbmRefInOutTrack;

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
	@Override
	public List<PbmCarInOutTrack> getCarTrack(Long oid, Long subOrderMid,
			Long orderId) {
		return trackRefDao.getCarTrack(oid, subOrderMid, orderId);
	}
	@Override
	public List<PbmRefInOutTrack> getRefTrack(Long oid, Long subOrderMid,
			Long orderId) {
		return trackRefDao.getRefTrack(oid, subOrderMid, orderId);
	}
	@Override
	public List getCarEnv(Long carId) {
		return trackRefDao.getCarEnv(carId);
	}

}
