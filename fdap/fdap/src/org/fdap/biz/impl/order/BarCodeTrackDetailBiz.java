package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.entity.PbmBarcodeDetailTrack;

public interface BarCodeTrackDetailBiz {
  List<PbmBarcodeDetailTrack> getByOrderId(Long orderId);
}
