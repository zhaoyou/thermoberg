package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmMiBarCodeDetailTrack;;

public interface BarCodeTrackDetailBiz {
  List<PbmMiBarCodeDetailTrack> getByOrderId(Long orderId);
}
