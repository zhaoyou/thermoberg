package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmCarInOutTrack;
import org.fdap.entity.order.PbmRefInOutTrack;

public interface TrackRefBiz {
	public List getRefEnv(Long erpId);
	public List getCarEnv(Long carId);
	public List<PbmRefInOutTrack> getRefTrack(Long oid, Long subOrderMid, Long orderId);
	public List<PbmCarInOutTrack> getCarTrack(Long oid, Long subOrderMid, Long orderId);
}
