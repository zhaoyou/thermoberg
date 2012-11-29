package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.BarCodeTrackDetailDao;
import org.fdap.entity.order.PbmMiBarCodeDetailTrack;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BarCodeTrackDetailDaoImpl extends HibernateTemplate implements
    BarCodeTrackDetailDao {

	@SuppressWarnings("unchecked")
  @Override
  public List<PbmMiBarCodeDetailTrack> getByOrder(Long orderId) {
	  String hql = "from PbmMiBarCodeDetailTrack d where d.orderId = ? and d.isDelete = 0";
	  Query query = this.getSession().createQuery(hql);
	  query.setLong(0, orderId);
	  return query.list();
  }

}
