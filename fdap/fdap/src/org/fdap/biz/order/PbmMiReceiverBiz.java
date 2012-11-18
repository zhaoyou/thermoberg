package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmMiReceiver;

/**
 * receiver biz.
 * @author zhaoyou
 *
 */
public interface PbmMiReceiverBiz {
	List<PbmMiReceiver> getList(Long oid);
}
