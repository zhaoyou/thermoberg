package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.SubOrderMainTrackDao;
import org.fdap.entity.order.PbmSubOrderMainTrack;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SubOrderMainTrackDaoImpl extends HibernateTemplate implements SubOrderMainTrackDao {

	@SuppressWarnings("unchecked")
  @Override
	public List<PbmSubOrderMainTrack> getByOrder(Long orderId) {
		String hql = "from PbmSubOrderMainTrack m where m.orderId = ? " +
				" m.isDelete=0 order by m.bubOrderMId,m.inOutType asc";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, orderId);
		return query.list();
	}

}
