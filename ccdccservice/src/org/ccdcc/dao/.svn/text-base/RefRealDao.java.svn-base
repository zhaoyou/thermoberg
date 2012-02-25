package org.ccdcc.dao;

import java.util.List;

import org.ccdcc.entity.AiInfoView;
import org.ccdcc.entity.RefInfoView;
import org.ccdcc.entity.RefRealView;

/**
 * 冷库实时数据访问接口
 * @author zhaoyou
 *
 */

public interface RefRealDao {
	
	/**
	 * 	根据冷库配置信息，获取冷库对象
	 * @param projectId		工程标示Id
	 * @param realRefId		冷库实际编号
	 * @param refType		冷库类型
	 * @param floorType		冷库楼层类型
	 * @param floorNo		冷库楼层编号
	 * @return				冷库对象
	 */
	public RefInfoView queryRefByInfo(String projectId ,Integer realRefId,Integer refType,Integer floorType,Integer floorNo) ;

	/**
	 * 根据工程的标示，获取该工程下的所有冷库列表
	 * @param projectId		工程标示
	 * @return				冷库列表集合
	 */
	public List<RefInfoView> queryRefByProjectId(String projectId);
	
	
	/**
	 * 	根据冷库的标示Id获取该冷库对应的所有探头
	 * @param rid		冷库标示Id
	 * @return			探头的集合
	 */
	public List<AiInfoView> queryAiInfoByRid(Long rid) ;
	
	/**
	 * 更加工程标示，获取该工程下的所有探头列表
	 * @param projectId			工程标示Id
	 * @return					探头列表
	 */
	public List<AiInfoView>	queryAiInfoByProjectId(String projectId) ;
	
	/**
	 * 	根据工程标示和设备标示或该设备的所有探头实时数据
	 * @param projectId		工程标示Id
	 * @param netId			设备标示Id
	 * @return				设备的实时数据
	 */
	public RefRealView		queryRefReal(String projectId,Integer netId) ;
	
	/**
	 * 根据工程标示获取该工程对应所有设备的实时数据
	 * @param projectId		工程标示Id
	 * @return				 所有设备实时数据列表
	 */
	public List<RefRealView> queryRefReal(String projectId) ;
	
	

}
