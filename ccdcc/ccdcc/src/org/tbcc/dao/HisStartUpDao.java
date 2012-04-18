package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * �������ͣ���������ݽӿ�
 * @author Administrator
 *
 */
public interface HisStartUpDao {
	/**
	 * �����ƶ��ն���ʷ��ͣ����ʼʱ�䡢����ʱ�䣬��ȡ��ͣ��¼
	 * @param tableName		�ն���ʷ��ͣ��
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @return
	 */
	public List getStartUpList(String tableName,String startTime,String endTime);
	
	/**
	 * �����ƶ��ն˵���ʷ��ͣ���ͱ�ʶ������ȡ��ͣ��¼
	 * @param tableName		�ն���ʷ��ͣ��
	 * @param id			��ͣ��¼�ı�ʶid
	 * @return
	 */
	public List getStartUp(String tableName,long id) ;
	
	
	/**
	 * �����ƶ��ն˵ı���������ʼʱ���ȡ��ͣ��¼ version 2
	 * @param tableName		�ƶ��ն˱���
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpListByTime(String tableName,String startTime,String endTime) ;
	
	/**
	 * �����ƶ��ն˵ı��� ������ͣ��¼�ı�ʶId��ȡ��ͣ��¼
	 * @param tableName
	 * @param id
	 * @return
	 */
	public List<TbccBaseHisStartUp> getStartUpById(String tableName,long id) ;
	
	
	/**
	 * ���ݿ�ʼʱ���ȡ��ͣ��¼��ʶID(��ĿFDAP�ڵ�ͼ�Ͽ��鿴��ʷ�켣ʱ��Ҫ�õ���)
	 * @param tableName			�����ƶ��ն˱�����
	 * @param beginTime			��ͣ��¼��ʼʱ��
	 * @return
	 */
	public List<Long> queryStartupidByBeginTime(String tableName,String beginTime);
	
	//modify by aftermath begin
	/** 
	* �����豸�е���С���������ʱ�䣬��ȡ���ݿ����Ѿ��ϴ��Ĵ��������ͣ��¼�б�
	 * @param tableName					С������ͣ���ݱ�
	 * @param minStartupTime		    ��С����ͣ��¼�Ŀ�ʼʱ��
	 * @param maxSatrtupTime			������ͣ��¼�Ŀ�ʼʱ��	
	 * @return List                     ���ش�ʱ���������Ѿ��ϴ�����ͣ��¼�б�
	*/
	public List<TbccBaseHisStartUp> getHistBoxSsByMinMaxStartTime(String tableName,String minStartupTime,String maxStartupTime);
	
	/** 
	* ��ȡ��ǰ��ͣ�������Id���
	 * @param tableName					С������ͣ���ݱ�	
	 * @return Integer 				    ���ص�ǰ��ͣ��������Id���(���ڲ�������ͣ��¼�޷���ѯ�����ݵ����������0
	*/
	public long getHistBoxMaxStartupId(String tableName);
	
	/** 
	* �ϴ�һ����ͣ��¼����
	 * @param tableName					С������ͣ���ݱ�	
	 * @param devHistStartUp		��Ҫ�ϴ���1����ͣ����
	 * @return integer						������ͣ��¼�ϴ������0���ϴ��ɹ���-1���ϴ�ʧ�ܣ�)
	*/	
     public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp);		

		  
		// modify by aftermath end	
}
