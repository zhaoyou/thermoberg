package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * 历史启停业务操作接口
 * @author Administrator
 *
 */
public interface HisStartUpBiz {
	/**
	 * 没有上传
	 */
	public static final int NOUP = 0 ;
	
	/**
	 * 没有完成
	 */
	public static final int NOFINISH =1 ;
	
	/**
	 * 已经完成
	 */
	public static final int FINISH = 2 ;
	
	/**
	 * 没有停止
	 */
	public static final int NOSTOP = 3 ;
	
	List<TbccBaseHisStartUp> getStartUpList(String proId,String beginTime,String endTime);
	
	TbccBaseHisStartUp getStartUp(String proId,long id);
	
	/**
	 * 根据工程标识、起始时间获取起停记录
	 * @param projectId		工程标识
	 * @param beginTime		开始时间
	 * @param endTime		结束时间
	 * @return
	 */
	List<TbccBaseHisStartUp> getStartUpListByTime(String projectId,String beginTime,String endTime) ;
	
	/**
	 * 根据工程标识，起停标识获取起停记录
	 * @param projectId		工程标识
	 * @param id			起停标识
	 * @return
	 */
	TbccBaseHisStartUp getStartUpById(String projectId,Long id) ;
	
	/**
	 * 根据开始时间获取启停记录标识ID(项目FDAP在地图上看查看历史轨迹时需要用到的)
	 * @param projectId		工程标识ID
	 * @param beginTime		启停记录开始时间
	 * @return
	 */
	Long getStartupidByBeginTime(String projectId,String beginTime);
	
//modify by aftermath begin
	/** 
	* 根据设备中的最小和最大启动时间，获取数据库中已经上传的此区间的启停记录列表
	 * @param tableName					小批零启停数据表
	 * @param minStartupTime		    最小的启停记录的开始时间
	 * @param maxSatrtupTime			最大的启停记录的开始时间	
	 * @return List                     返回此时间区间内已经上传的启停记录列表
	*/			
	public List<TbccBaseHisStartUp> getHistBoxSsByMinMaxStartTime(String tableName,String minStartupTime,String maxStartupTime);
	
	/** 
	* 获取当前启停表中最大Id编号
	 * @param tableName					小批零启停数据表	
	 * @return integer 						返回当前启停表中最大的Id编号
	*/
	public long getHistBoxMaxStartupId(String tableName);
	
	/** 
	* 上传一条启停记录数据
	 * @param tableName					小批零启停数据表	
	 * @param devHistStartUp		需要上传的1条启停数据
	 * @return Integer 						返回启停记录上传结果（0：上传成功；-1：上传失败；)
	*/	
     public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp);		
		  
// modify by aftermath end
	
}
