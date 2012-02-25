package org.ccdcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.ccdcc.dao.CarRealDao;
import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 车载实时数据访问实现类
 * @author Administrator
 *
 */
public class CarRealDaoImpl extends HibernateDaoSupport implements CarRealDao{

	@SuppressWarnings("unchecked")
	public List<CarRealView> getByProject(final String projectId) {		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String hql = "from CarRealView r where r.projectId = ?  " ;
			Query query = session.createQuery(hql) ;
			query.setString(0, projectId);
			return query.list();
		}}) ;	
	}
	
	//这是后来添加的根据总部分支标识hqid，获取车载实时数据，因为访问数据库使用的是jdbc，所以这里没有实现。
	public List<CarRealData> getByHqid(String hqid) {
		// TODO Auto-generated method stub
		return null;
	}

	//这是后来添加的根据分支企业标识hqid，获取车载实时数据，因为访问数据库使用的是jdbc，所以这里没有实现。
	public List<CarRealData> getByBranchid(String branchid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarRealView_new> getByProject_sy(String projectId) {
		// TODO Auto-generated method stub
		return null;
	}
}
