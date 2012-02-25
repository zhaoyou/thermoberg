package org.ccdcc.dao;

import java.util.List;

import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;


/**
 * 车载历史数据访问接口
 * @author Administrator
 *
 */
public interface CarHisDao {
	
		/**
		 * 根据表名、启停记录的标识、启停记录的某个时间点
		 * @param tableName		表名
		 * @param sid			启停记录的标示
		 * @param time			启停记录的某个时间点
		 * @return
		 */
		public List<CarHisView> getCarhis(String tableName ,Integer sid ,String time) ;
		
		/**
		 * 根据表名、启停记录的标识、启停记录中某个时间点
		 * @param tableName			启停记录的表名
		 * @param sid				启停记录的标识
		 * @param time				启停记录中的某个时间点
		 * @return
		 */
		public List<CarHisView> getCarhis2(String tableName,Integer sid ,String time);
		
		
		/**
		 * 为上海医药提供的方法，根据表名、启停记录的标识、启停记录的某个时间点
		 * @param tableName		表名
		 * @param sid			启停记录的标示
		 * @param time			启停记录的某个时间点
		 * @return
		 */
		public List<CarHisView_new> getCarhis_sy(String tableName ,Integer sid ,String time);
}
