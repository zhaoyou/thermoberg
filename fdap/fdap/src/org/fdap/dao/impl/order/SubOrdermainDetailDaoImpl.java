package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.SubOrderMainDetailDao;
import org.fdap.entity.order.PbmSubOrderDetailTrack;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SubOrdermainDetailDaoImpl extends HibernateTemplate implements
    SubOrderMainDetailDao {

	@SuppressWarnings("unchecked")
  @Override
	public List<PbmSubOrderDetailTrack> getByOrder(Long orderId) {
		System.out.println("orderId: " + orderId);
		String hql = "from PbmSubOrderDetailTrack d where d.orderId = ? and d.isDelete = 0";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, orderId);
		return query.list();
	}

}
