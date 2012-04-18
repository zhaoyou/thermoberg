package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.dao.HisStartUpDao;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.util.BuildTable;
import org.tbcc.util.ObjectToHistory;

/**
 * ������ʷ��ͣ����ҵ��ʵ����
 * @author Administrator
 *
 */
public class HisStartUpBizImpl implements HisStartUpBiz{

	/**
	 * ��spring ע����ͣ���ݷ��ʽӿ�
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
	 * ����������ȡ��ͣ��¼
	 */
	public List<TbccBaseHisStartUp> getStartUpList(String proId,
			String startTime, String endTime) {
		
		List list = this.startupDao.getStartUpList(BuildTable.toHisStartUpTable(proId), startTime, endTime);
		if(list!=null && list.size()>0)
			return  objToHis.objectToStartUp(list);
		return null;
		
	}

	/**
	 * ������Ŀ��������ͣid����ȡ������¼
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
	* �����豸�е���С���������ʱ�䣬��ȡ���ݿ����Ѿ��ϴ��Ĵ��������ͣ��¼�б�
	 * @param tableName					С������ͣ���ݱ�
	 * @param minStartupTime		    ��С����ͣ��¼�Ŀ�ʼʱ��
	 * @param maxSatrtupTime			������ͣ��¼�Ŀ�ʼʱ��	
	 * @return List                     ���ش�ʱ���������Ѿ��ϴ�����ͣ��¼�б�
	*/			
	public List<TbccBaseHisStartUp> getHistBoxSsByMinMaxStartTime(String tableName,String minStartupTime,String maxStartupTime){
	   return this.startupDao.getHistBoxSsByMinMaxStartTime(tableName,minStartupTime,maxStartupTime);
	}
	
	/** 
	* ��ȡ��ǰ��ͣ�������Id���
	 * @param tableName					С������ͣ���ݱ�	
	 * @return Integer 						���ص�ǰ��ͣ��������Id���
	*/
	public long getHistBoxMaxStartupId(String tableName){
       return this.startupDao.getHistBoxMaxStartupId(tableName);
	}
	
	/** 
	* �ϴ�һ����ͣ��¼����
	 * @param tableName					С������ͣ���ݱ�	
	 * @param devHistStartUp		��Ҫ�ϴ���1����ͣ����
	 * @return updateStatus			������ͣ��¼�ϴ������0���ϴ��ɹ���-1���ϴ�ʧ�ܣ�)
	*/	
     public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp){
	    return this.startupDao.uploadHistBoxStartup(tableName,devHistStartUp);
	 }
	 
	
}
