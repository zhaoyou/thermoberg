package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.PbmErpRefInOutDao;
import org.fdap.entity.order.PbmErpRefInOutTrack;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PbmErpRefInOutDaoImpl extends HibernateTemplate implements
		PbmErpRefInOutDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PbmErpRefInOutTrack> getByKid(Long kid, Long oid) {
		String hql = "from PbmErpRefInOutTrack e where e.kid = ? and e.oid = ?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, kid);
		query.setLong(1, oid);
		return query.list();
	}

}
