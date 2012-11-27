package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmMiBarCodeTrack;;

public interface BarCodeTrackBiz {
	List<PbmMiBarCodeTrack> getByOrderId(Long orderId);
}
