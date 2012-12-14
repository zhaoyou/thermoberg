package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmErpRefInOutTrack;

public interface PbmErpRefInOutBiz {
	List<PbmErpRefInOutTrack> getByKid(Long kid, Long oid);
}
