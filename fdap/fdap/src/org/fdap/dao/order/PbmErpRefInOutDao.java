package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmErpRefInOutTrack;

/**
 * erp ref inout dao
 * @author zhaoyou
 *
 */
public interface PbmErpRefInOutDao {
	List<PbmErpRefInOutTrack> getByKid(Long kid, Long oid);
}
