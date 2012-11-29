package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmSubOrderDetailTrack;

public interface SubOrderMainDetailDao {
	List<PbmSubOrderDetailTrack> getByOrder(Long orderId);
}
