package org.fdap.biz.order;

import java.util.List;
import java.util.Map;

import org.fdap.entity.order.PbmSubOrderMainTrack;

public interface SubOrderMainTrackBiz {
	List<PbmSubOrderMainTrack> getByOrderId(Long orderId);
	List<Map<String, String>>	getInfo(Long orderId, Long fullId, String goodTypeName,
			String prodArea, String logno);
}
