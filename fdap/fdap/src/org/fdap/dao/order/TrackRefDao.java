package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmCarInOutTrack;
import org.fdap.entity.order.PbmRefInOutTrack;

public interface TrackRefDao {
	/**
	 * 
	 * @param erpId
	 * @return string[] [0] 存储环境  [1] 存储类型 [2] 存储载体
	 */
	public List getRefEnv(Long erpId);
	
	public List getCarEnv(Long carId);
	
	public List<PbmCarInOutTrack> getCarTrack(Long oid, Long subOrderMid, Long orderId);
	
	public List<PbmRefInOutTrack> getRefTrack(Long oid, Long subOrderMid, Long orderId);
}
