package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmSubOrderMainTrack;

/**
 * subOrderMainTrackDao
 * @author zhaoyou
 *
 */
public interface SubOrderMainTrackDao {
	List<PbmSubOrderMainTrack> getByOrder(Long orderid);
	PbmSubOrderMainTrack get(Long subOrderMid);
}
