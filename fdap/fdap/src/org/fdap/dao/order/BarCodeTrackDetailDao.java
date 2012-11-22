package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmMiBarCodeDetailTrack;

public interface BarCodeTrackDetailDao {
	List<PbmMiBarCodeDetailTrack> getByOrder(Long orderId);
}
