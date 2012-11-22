package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.entity.PbmBarcodeTrack;

public interface BarCodeTrackBiz {
	List<PbmBarcodeTrack> getByOrderId(Long orderId);
}
