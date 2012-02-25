package org.ccdcc.dao;

import java.util.List;

import org.ccdcc.entity.StartUpView;

/**
 * 历史启停记录数据访问接口
 * @author Administrator
 *
 */
public interface StartUpDao {
	
		/**
		 * 根据启停记录表名，以及某个启停记录的启动时间，获取该启动时间后的10条启停记录
		 * @param tableName		车载启停记录表
		 * @param time			某个启停记录的启动时间
		 * @return				启停记录的集合
		 */
		public List<StartUpView> getStartUpByTime (String tableName,String time)   ;
		
		
		/**
		 * 根据车载启停的数据表、启停记录的启动时间
		 * @param tableName	启停表名
		 * @param time		启停记录的启动时间
		 * @return			启停记录的标识Id
		 */
		public Integer getId(String tableName ,String time) ;
		
		/**
		 * 根据移动终端工程编号，获取启停记录的集合
		 * @param projectId	工程编号
		 * @param id		某个启停记录标识
		 * @return			大于标示id后的启停记录的集合
		 */
		public List<StartUpView> getStartUpById(String tableName ,Integer id) ;
		
		/**
		 * 根据启停标识Id，获取启停记录的对象
		 * @param id		启停标识Id
		 * @return			启停记录对象
		 */
		public StartUpView get(String tableName,Integer id);
}
