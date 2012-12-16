package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.TrackRefDao;
import org.fdap.entity.order.PbmCarInOutTrack;
import org.fdap.entity.order.PbmRefInOutTrack;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class TrackRefDaoImpl extends HibernateTemplate implements TrackRefDao {

	@Override
	public List getRefEnv(Long erpId) {
		String sql = "SELECT     fdapstoretype.name as storeName, " +
				"fdapref.name AS refName, fdapstoretype.temphighlimit as tEnd, " +
				"fdapstoretype.templowerlimit as tStart, fdapref.refId as refId  " +
				"FROM    fdapref INNER JOIN " +
                      "pbmRefRelation ON fdapref.refId = pbmRefRelation.ccRefId INNER JOIN " +
                      "fdapstoretype ON fdapref.storeid = fdapstoretype.storeid where pbmRefRelation.ErpRefId = ? ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setLong(0, erpId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PbmCarInOutTrack> getCarTrack(Long oid, Long subOrderMid,
			Long orderId) {
		String hql = "from PbmCarInOutTrack c where c.oid = ? and c.subOrderMid = ? and c.orderId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, oid);
		query.setLong(1, subOrderMid);
		query.setLong(2, orderId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PbmRefInOutTrack> getRefTrack(Long oid, Long subOrderMid,
			Long orderId) {
		String hql = "from PbmRefInOutTrack r where r.oid = ? and r.subOrderMid = ? and r.orderId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setLong(0, oid);
		query.setLong(1, subOrderMid);
		query.setLong(2, orderId);
		return query.list();
	}

	@Override
	public List getCarEnv(Long carId) {
		String sql = "SELECT     fdapstoretype.name as storeName, " +
		"fdapref.name AS refName, fdapstoretype.temphighlimit as tEnd, " +
		"fdapstoretype.templowerlimit as tStart , fdapref.refId as refId " +
		"FROM    fdapref INNER JOIN " +
              "pbmCarRelation ON fdapref.refId = pbmCarRelation.CcCarid INNER JOIN " +
              "fdapstoretype ON fdapref.storeid = fdapstoretype.storeid where pbmCarRelation.MiCarId = ? ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setLong(0, carId);
		return query.list();
	}

}
