package org.ccdcc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.ccdcc.dao.CarHisDao;
import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 车载历史数据访问实现类
 * @author Administrator
 *
 */
public class CarHisDaoImpl extends HibernateDaoSupport implements CarHisDao {

	@SuppressWarnings("unchecked")
	public List<CarHisView> getCarhis(final String tableName,final  Integer sid,final String time) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			String sql = " select top 50 his.id as {car.id} ,his.ai1 as {car.ai1},his.ai2 as {car.ai2},his.ai3 as {car.ai3" +
					"},his.ai4 as {car.ai4},his.alarmStatus as {car.alarmStatus},his.latitude as {car.latitude},his.latitude_dir as {car.latitude_dir" +
					"},his.longitude as {car.longitude},his.longitude_dir as {car.longitude_dir},his.parentId as {car.parentId},his.updateTime as {car.updateTime" +
					"} from  "+tableName+" his where his.parentId = "+sid+" and updateTime >'"+time+"' and HistDataStorageType = 0" ;
			return session.createSQLQuery(sql).addEntity("car", CarHisView.class).list();
		}});
	}



	@SuppressWarnings("unchecked")
	public List<CarHisView> getCarhis2(final String tableName,final Integer sid,
			final String time) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){public Object doInHibernate(Session session)
		throws HibernateException, SQLException {
	String sql = " select top 50 his.id as {car.id} ,his.ai1 as {car.ai1},his.ai2 as {car.ai2},his.ai3 as {car.ai3" +
			"},his.ai4 as {car.ai4},his.alarmStatus as {car.alarmStatus},his.latitude as {car.latitude},his.latitude_dir as {car.latitude_dir" +
			"},his.longitude as {car.longitude},his.longitude_dir as {car.longitude_dir},his.parentId as {car.parentId},his.updateTime as {car.updateTime" +
			"} from  "+tableName+" his where his.parentId = "+sid+" and updateTime >'"+time+"' and HistDataStorageType = 0 order by updateTime" ;
	return session.createSQLQuery(sql).addEntity("car", CarHisView.class).list();
}});
	}



	@Override
	public List<CarHisView_new> getCarhis_sy(String tableName, Integer sid,
			String time) {
		// TODO Auto-generated method stub
		return null;
	}

}
