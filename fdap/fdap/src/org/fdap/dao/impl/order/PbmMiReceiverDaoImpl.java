package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.PbmMiReceiverDao;
import org.fdap.entity.order.PbmMiReceiver;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PbmMiReceiverDaoImpl extends HibernateDaoSupport  implements PbmMiReceiverDao {

	@SuppressWarnings("unchecked")
  @Override
	public List<PbmMiReceiver> getByOid(Long oid) {
		String hql = "from PbmMiReceiver r where r.oid = ? and r.isDelete = 0";
		org.hibernate.Query query = this.getSession().createQuery(hql);
		query.setLong(0, oid);
		return query.list();
	}

}
