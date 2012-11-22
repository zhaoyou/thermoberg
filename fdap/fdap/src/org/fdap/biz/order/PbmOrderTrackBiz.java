package org.fdap.biz.order;

import java.util.List;

import org.fdap.entity.order.OrderTrackView;

public interface PbmOrderTrackBiz {
	List<OrderTrackView> getOrder(String oid, String s, String e,
      String orderNo, String rid, String prodArea, String lotno,
      String goodsname, String goodsType);
}
