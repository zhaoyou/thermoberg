package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.BarCodeTrackDao;
import org.fdap.entity.PbmBarcodeTrack;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BarCodeTrackDaoImpl extends HibernateTemplate implements
    BarCodeTrackDao {

	@SuppressWarnings("unchecked")
  @Override
  public List<PbmBarcodeTrack> getBy(Long orderId) {
	  String hql = "from PbmBarcodeTrack t where r.orderId = ?";
	  Query query = this.getSession().createQuery(hql);
	  query.setLong(0, orderId);
	  return query.list();
  }

}
