package org.ccdcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.ccdcc.dao.StartUpDao;
import org.ccdcc.entity.StartUpView;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 历史启停记录数据访问实现类
 * @author Administrator
 *
 */
public class StartUpDaoImpl extends HibernateDaoSupport implements StartUpDao {

	@SuppressWarnings("unchecked")
	public List<StartUpView> getStartUpByTime(final String tableName, final String time) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select top 10 s.beginAddress as {ss.beginAddress},s.beginTime as {ss.beginTime},s.carrier as {ss.carrier}," +
					"s.endAddress as {ss.endAddress},s.endTime as {ss.endTime},s.id as {ss.id} ,s.receiver as {ss.receiver}," +
					"s.recordInterval as {ss.recordInterval},s.shipper as {ss.shipper} ,s.updateStatus as {ss.updateStatus},"+
					"s.tlimitType as {ss.tlimitType} from " +tableName+
					" s where begintime > '"+time+"'" ;
			SQLQuery query = session.createSQLQuery(sql) ;	
			return query.addEntity("ss", StartUpView.class).list();
		}});
	}

	public Integer getId(final String tableName,final String time) {
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select id from "+tableName+" where beginTime = '"+time+"'" ;
			return session.createSQLQuery(sql).uniqueResult();
		}}) ;
		
	}

	@SuppressWarnings("unchecked")
	public List<StartUpView> getStartUpById(final String tableName,final  Integer id) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = "select top 5 s.beginAddress as {ss.beginAddress},s.beginTime as {ss.beginTime},s.carrier as {ss.carrier}," +
			"s.endAddress as {ss.endAddress},s.endTime as {ss.endTime},s.id as {ss.id} ,s.receiver as {ss.receiver}," +
			"s.recordInterval as {ss.recordInterval},s.shipper as {ss.shipper} ,s.updateStatus as {ss.updateStatus},"+
			"s.tlimitType as {ss.tlimitType} from " +tableName+
			" s where s.id > "+id ;
			SQLQuery	query = session.createSQLQuery(sql) ;
			return query.addEntity("ss",StartUpView.class).list();
		}});
	}

	public StartUpView get(final String tableName,final Integer id) {
		return (StartUpView)this.getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session arg0)
				throws HibernateException, SQLException {
			String sql = "select  s.beginAddress as {ss.beginAddress},s.beginTime as {ss.beginTime},s.carrier as {ss.carrier}," +
			"s.endAddress as {ss.endAddress},s.endTime as {ss.endTime},s.id as {ss.id} ,s.receiver as {ss.receiver}," +
			"s.recordInterval as {ss.recordInterval},s.shipper as {ss.shipper} ,s.updateStatus as {ss.updateStatus},"+
			"s.tlimitType as {ss.tlimitType} from " +tableName+
			" s where s.id = "+id ;
			SQLQuery query = arg0.createSQLQuery(sql) ;
			return query.addEntity("ss",StartUpView.class).uniqueResult();
		}}) ;
		
	}

}
