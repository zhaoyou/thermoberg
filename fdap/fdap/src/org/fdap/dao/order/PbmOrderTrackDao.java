package org.fdap.dao.order;

import java.util.List;

public interface PbmOrderTrackDao {
	public List queryOrder(Long oid,
			String s, String e, String orderNo, Long rid,
			String prodArea, String lotno, String goodsname, String goodsType);
}
