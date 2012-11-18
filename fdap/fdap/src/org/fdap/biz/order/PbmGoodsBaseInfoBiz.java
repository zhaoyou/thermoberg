package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.PbmMiGoodsBaseInfo;

public interface PbmGoodsBaseInfoBiz {
	public List<PbmMiGoodsBaseInfo> getList(Long oid);
}
