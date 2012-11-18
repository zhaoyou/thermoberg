package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmMiGoodsBaseInfo;


/**
 * goods baseinfo dao.
 * @author zhaoyou
 *
 */
public interface PbmGoodsBaseInfoDao {
	public List<PbmMiGoodsBaseInfo> getGoodsBaseInfoList(Long oid);
}
