package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * 这个事启停操作的数据接口
 * @author Administrator
 *
 */
public interface HisStartUpDao {
	/**
	 * 根据移动终端历史启停表、开始时间、结束时间，获取启停记录
	 * @param tableName		终端历史启停表
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @return
	 */
	public List getStartUpList(String tableName,String startTime,String endTime);
	
	/**
	 * 根据移动终端的历史启停表、和标识主键获取启停记录
	 * @param tableName		终端历史启停表
	 * @param id			启停记录的标识id
	 * @return
	 */
	public List getStartUp(String tableName,long id) ;
	
	
	/**
	 * 根据移动终端的表名，和起始时间获取起停记录 version 2
	 * @param tableName		移动终端表名
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpListByTime(String tableName,String startTime,String endTime) ;
	
	/**
	 * 根据移动终端的表名 ，和起停记录的标识Id获取起停记录
	 * @param tableName
	 * @param id
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpById(String tableName,long id) ;
	
	
	/**
	 * 根据开始时间获取启停记录标识ID(项目FDAP在地图上看查看历史轨迹时需要用到的)
	 * @param tableName			根据移动终端表名和
	 * @param beginTime			启停记录开始时间
	 * @return
	 */
	public List<Long> queryStartupidByBeginTime(String tableName,String beginTime);
	
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
	 * @return Integer 				    返回当前启停表中最大的Id编号(对于不存在启停记录无法查询到数据的情况，返回0
	*/
	public long getHistBoxMaxStartupId(String tableName);
	
	/** 
	* 上传一条启停记录数据
	 * @param tableName					小批零启停数据表	
	 * @param devHistStartUp		需要上传的1条启停数据
	 * @return integer						返回启停记录上传结果（0：上传成功；-1：上传失败；)
	*/	
     public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp);		

		  
		// modify by aftermath end	
}
