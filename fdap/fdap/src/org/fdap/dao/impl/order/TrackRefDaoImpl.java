package org.fdap.dao.impl.order;

import java.util.List;

import org.fdap.dao.order.TrackRefDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class TrackRefDaoImpl extends HibernateTemplate implements TrackRefDao {

	@Override
	public List getRefEnv(Long erpId) {
		String sql = "SELECT     fdapstoretype.name as storeName, " +
				"fdapref.name AS refName, fdapstoretype.temphighlimit as tEnd, " +
				"fdapstoretype.templowerlimit as tStart " +
				"FROM    fdapref INNER JOIN " +
                      "pbmRefRelation ON fdapref.refId = pbmRefRelation.ccRefId INNER JOIN " +
                      "fdapstoretype ON fdapref.storeid = fdapstoretype.storeid where pbmRefRelation.ErpRefId = ? ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setLong(0, erpId);
		return query.list();
	}

}
