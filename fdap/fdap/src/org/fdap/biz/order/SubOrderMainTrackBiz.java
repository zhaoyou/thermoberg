package org.fdap.biz.order;

import java.util.List;
import org.fdap.entity.order.PbmMedicineSummary;
import org.fdap.entity.order.PbmSubOrderMainTrack;

public interface SubOrderMainTrackBiz {
	List<PbmSubOrderMainTrack> getByOrderId(Long orderId);
	
	List<PbmMedicineSummary>	getInfo(Long orderId, String goodsName, String goodTypeName,
			String prodArea, String logno);
	
	PbmSubOrderMainTrack get(Long subOrderMid);
}
