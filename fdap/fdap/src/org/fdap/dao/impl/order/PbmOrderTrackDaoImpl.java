package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.PbmOrderTrackDao;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PbmOrderTrackDaoImpl extends HibernateTemplate implements PbmOrderTrackDao {

	@Override
  public List queryOrder(Long oid, String s, String e,
      String orderNo, Long rid, String prodArea, String lotno,
      String goodsname, String goodsType) {
		StringBuffer sb = new StringBuffer();
		String sql = 
			"SELECT pbmMiReceiver.fullName, pbmOrderTrack.orderId, pbmOrderTrack.OrderNo " +
			"FROM  pbmOrderTrack INNER JOIN " +
			 "   pbmMiReceiver ON pbmOrderTrack.Receiverid = pbmMiReceiver.rid INNER JOIN " +
			 "   pbmBarcodeDetailTrack ON pbmOrderTrack.OrderId = pbmBarcodeDetailTrack.orderId INNER JOIN " +
			 "   pbmGoodsFullInfo ON pbmBarcodeDetailTrack.GoodsFullId = pbmGoodsFullInfo.GoodFullId INNER JOIN " +
			 "   pbmGoodsBasicInfo ON pbmGoodsFullInfo.GoodId = pbmGoodsBasicInfo.GoodId  where pbmOrderTrack.oid = ? " +
			 " and pbmOrderTrack.isDelete = 0 and pbmMiReceiver.isDelete = 0 and pbmBarcodeDetailTrack.isDelete = 0 and " +
			 " and pbmGoodsFullInfo.isDelete = 0 and pbmGoodsBasicInfo.isDelete = 0 ";
	 
	  
	  sb.append(sql);
	  
	  if (s != null && !"".equals(s) && e != null && !"".equals(e))
	  	sb.append(" and pbmOrderTrack.orderTime > '" + s +
	  			"' and pbmOrderTrack.orderTime < '" + e + "' ");
	  
	  if ( orderNo != null && !"".equals(orderNo)) {
	  	sb.append(" and pbmOrderTrack.OrderNo like '%" + orderNo + "%'");
	  }
	  
	  if (rid != null) {
	  	sb.append(" and pbmOrderTrack.ReceiverId = " + rid);
	  }
	  
	  if (prodArea != null && !"".equals(prodArea)) {
	  	sb.append(" and pbmGoodsBasicInfo.prodarea  like '%" + prodArea + "%'");
	  }
	  
	  if (lotno != null && !"".equals(lotno)) {
	  	sb.append(" and pbmGoodsFullInfo.lotno like'%" + lotno + "%'");
	  }
	  
	  if (goodsname != null && !"".equals(goodsname)) {
	  	sb.append(" and pbmGoodsBasicInfo.goodsname like '%" + goodsname + "%'");
	  }
	  
	  if (goodsType != null && !"".equals(goodsType)) {
	  	sb.append(" and pbmGoodsBasicInfo.goodsType like '%" + goodsType + "%'");
	  }
	  
	  SQLQuery query = this.getSession().createSQLQuery(sql);
	  query.setLong(0, oid);
	  return query.list();
  }
}
