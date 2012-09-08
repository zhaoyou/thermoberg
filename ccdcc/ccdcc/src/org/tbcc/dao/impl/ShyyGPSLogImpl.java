package org.tbcc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.ShyyGPSLogDao;
import org.tbcc.entity.TbccShyyGPSLog;

public class ShyyGPSLogImpl extends HibernateDaoSupport implements ShyyGPSLogDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TbccShyyGPSLog> getByTime(String start, String end, int limit) {
		String hql = "from TbccShyyGPSLog l where l.recordTime >= '" +
			start + "' and l.recordTime <= '" + end + "'";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(limit);
		return q.list();
	}

}
