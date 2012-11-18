package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmMiReceiver;

/**
 *  receiver dao interface.
 * @author zhaoyou
 *
 */
public interface PbmMiReceiverDao {
	public List<PbmMiReceiver> getByOid(Long oid);
}
