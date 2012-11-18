package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.PbmGoodsBaseInfoDao;
import org.fdap.entity.order.PbmMiGoodsBaseInfo;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PbmGoodsBaseInfoDaoImpl extends HibernateTemplate implements PbmGoodsBaseInfoDao {

	@SuppressWarnings("unchecked")
  @Override
	public List<PbmMiGoodsBaseInfo> getGoodsBaseInfoList(Long oid) {
		Query query = this.getSession().createQuery("from PbmMiGoodsBaseInfo g where g.oid = ?");
		query.setLong(0, oid);
		return query.list();
	}

}
