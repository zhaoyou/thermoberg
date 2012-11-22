package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmSubOrderMainTrack;

public interface SubOrderMainTrackBiz {
	List<PbmSubOrderMainTrack> getByOrderId(Long orderId);
}
