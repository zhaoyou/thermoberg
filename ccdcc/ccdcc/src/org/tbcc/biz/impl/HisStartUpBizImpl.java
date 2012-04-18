package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.dao.HisStartUpDao;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.util.BuildTable;
import org.tbcc.util.ObjectToHistory;

/**
 * 这是历史启停操作业务实现类
 * @author Administrator
 *
 */
public class HisStartUpBizImpl implements HisStartUpBiz{

	/**
	 * 由spring 注入启停数据访问接口
	 */
	private HisStartUpDao startupDao = null ;
	
	private ObjectToHistory objToHis = null ;
	
	public void setStartupDao(HisStartUpDao startupDao) {
		this.startupDao = startupDao;
	}
	
	public void setObjToHis(ObjectToHistory objToHis) {
		this.objToHis = objToHis;
	}

	
	/**
	 * 根据条件获取启停记录
	 */
	public List<TbccBaseHisStartUp> getStartUpList(String proId,
			String startTime, String endTime) {
		
		List list = this.startupDao.getStartUpList(BuildTable.toHisStartUpTable(proId), startTime, endTime);
		if(list!=null && list.size()>0)
			return  objToHis.objectToStartUp(list);
		return null;
		
	}

	/**
	 * 根据项目名、和启停id，获取单条记录
	 */
	public TbccBaseHisStartUp getStartUp(String proId, long id) {
		List list = this.startupDao.getStartUp(BuildTable.toHisStartUpTable(proId), id);
		List<TbccBaseHisStartUp> startupList =	objToHis.objectToStartUp(list);
		if(startupList!=null && startupList.size()>0)
			return startupList.get(0);
		return null;
	}

	
	
	public TbccBaseHisStartUp getStartUpById(String projectId, Long id) {
		if(projectId==null || projectId.equals("") || id==null || id.equals(""))
			return null ;
		List<TbccBaseHisStartUp> list = 
			this.startupDao.getStartUpById(BuildTable.toHisStartUpTable(projectId), id) ;
		
		if(list==null || list.size()==0)
			return null ;
		return list.get(0);
	}

	
	public List<TbccBaseHisStartUp> getStartUpListByTime(String projectId,
			String beginTime, String endTime) {
		
		if(projectId ==null || projectId.equals("") || beginTime==null || beginTime.equals("")
			||	endTime==null || endTime.equals(""))
			return null ;
		
		List<TbccBaseHisStartUp> list = this.startupDao.getStartUpListByTime(BuildTable.toHisStartUpTable(projectId), beginTime, endTime) ;
		
		if(list==null || list.size()==0)
			return null ;
		
		return list;
	}

	
	public Long getStartupidByBeginTime(String projectId,String beginTime) {
		if(projectId ==null || projectId.equals("") || beginTime==null || beginTime.equals(""))
				return null ;
		List<Long> list = startupDao.queryStartupidByBeginTime(BuildTable.toHisStartUpTable(projectId), beginTime);
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

		/** 
	* 根据设备中的最小和最大启动时间，获取数据库中已经上传的此区间的启停记录列表
	 * @param tableName					小批零启停数据表
	 * @param minStartupTime		    最小的启停记录的开始时间
	 * @param maxSatrtupTime			最大的启停记录的开始时间	
	 * @return List                     返回此时间区间内已经上传的启停记录列表
	*/			
	public List<TbccBaseHisStartUp> getHistBoxSsByMinMaxStartTime(String tableName,String minStartupTime,String maxStartupTime){
	   return this.startupDao.getHistBoxSsByMinMaxStartTime(tableName,minStartupTime,maxStartupTime);
	}
	
	/** 
	* 获取当前启停表中最大Id编号
	 * @param tableName					小批零启停数据表	
	 * @return Integer 						返回当前启停表中最大的Id编号
	*/
	public long getHistBoxMaxStartupId(String tableName){
       return this.startupDao.getHistBoxMaxStartupId(tableName);
	}
	
	/** 
	* 上传一条启停记录数据
	 * @param tableName					小批零启停数据表	
	 * @param devHistStartUp		需要上传的1条启停数据
	 * @return updateStatus			返回启停记录上传结果（0：上传成功；-1：上传失败；)
	*/	
     public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp){
	    return this.startupDao.uploadHistBoxStartup(tableName,devHistStartUp);
	 }
	 
	
}
