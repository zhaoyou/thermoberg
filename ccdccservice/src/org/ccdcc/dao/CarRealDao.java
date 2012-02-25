package org.ccdcc.dao;

import java.util.List;

import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;

/**
 * 车载实时数据访问接口
 * @author Administrator
 *
 */
public interface CarRealDao {
	
	
		/**
		 * 根据移动终端的projectId 获取移动终端实时数据
		 * @param projectId		移动终端projectId 
		 * @return				返回移动终端实时数据
		 */
		public List<CarRealView> getByProject(String projectId) ;
		
		
		
		/**
		 * 根据总部分支标识hqid，获取对应的所有移动车载实时数据
		 * @param hqid			总部分支标识hqid
		 * @return
		 */
		public List<CarRealData> getByHqid(String hqid);
		
		
		/**
		 * 根据分支企业标识branchid，获取对应的所有移动车载实时数据
		 * @param branchid			分支企业标识branchid
		 * @return
		 */
		public List<CarRealData> getByBranchid(String branchid);
		
		
		/**
		 * 为上海医药提供的根据移动终端的projectId 获取移动终端实时数据
		 * @param projectId		移动终端projectId 
		 * @return				返回移动终端实时数据
		 */
		public List<CarRealView_new> getByProject_sy(String projectId) ;
}
