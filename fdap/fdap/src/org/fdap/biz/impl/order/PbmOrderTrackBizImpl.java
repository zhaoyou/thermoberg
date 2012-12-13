package org.fdap.biz.impl.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fdap.biz.order.PbmOrderTrackBiz;
import org.fdap.dao.order.PbmOrderTrackDao;
import org.fdap.entity.order.OrderTrackView;
import org.fdap.entity.order.OrderView;

public class PbmOrderTrackBizImpl implements PbmOrderTrackBiz {
	
	private PbmOrderTrackDao orderTrackDao = null;
	
	public PbmOrderTrackDao getOrderTrackDao() {
  	return orderTrackDao;
  }
	public void setOrderTrackDao(PbmOrderTrackDao orderTrackDao) {
  	this.orderTrackDao = orderTrackDao;
  }

	@Override
  public List<OrderTrackView> getOrder(String oid, String s, String e,
      String orderNo, String rid, String prodArea, String lotno,
      String goodsname, String goodsType) {
	  Long oid_long = null;
	  Long rid_long = null;
	  if (oid != null && !"-1".equals(oid)) oid_long = Long.parseLong(oid);
	  if (rid != null && !"-1".equals(rid)) rid_long = Long.parseLong(rid);
	  List list = orderTrackDao.queryOrder(oid_long, s, e, orderNo,
	  		rid_long, prodArea, lotno, goodsname, goodsType);
	  
	  Map<String, List<OrderView>> map = new HashMap<String, List<OrderView>>();
	  // get recevier to order map.
	  for (int i = 0; i < list.size(); i++) {
	  	Object[] obj = (Object[])list.get(i);
	  	OrderView view = new OrderView();
  		view.setOid(Long.parseLong(obj[1].toString()));
  		view.setOrderNo(obj[2].toString());
	  	if (map.containsKey(obj[0].toString())) {
	      // 相同的订单
	  	  if (!map.get(obj[0].toString()).contains(view)) {
	  	    map.get(obj[0].toString()).add(view);
	  	  }
	  	} else {
	  		 List<OrderView> views = new ArrayList<OrderView>();
	  		 views.add(view);
	  		 map.put(obj[0].toString(), views);
	  	}
	  }
	  
	  // get result list.s
	  List<OrderTrackView> resultList = new ArrayList<OrderTrackView>();
	  for (String obj: map.keySet()) {
	  	OrderTrackView tview = new OrderTrackView();
	  	tview.setReceiver(obj);
	  	tview.setList(map.get(obj));
	  	resultList.add(tview);
	  }
	  
	  return resultList;
  }
	

}
