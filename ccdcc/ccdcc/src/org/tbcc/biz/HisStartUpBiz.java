package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisStartUp;

/**
 * ��ʷ��ͣҵ������ӿ�
 * @author Administrator
 *
 */
public interface HisStartUpBiz {
	/**
	 * û���ϴ�
	 */
	public static final int NOUP = 0 ;
	
	/**
	 * û�����
	 */
	public static final int NOFINISH =1 ;
	
	/**
	 * �Ѿ����
	 */
	public static final int FINISH = 2 ;
	
	/**
	 * û��ֹͣ
	 */
	public static final int NOSTOP = 3 ;
	
	List<TbccBaseHisStartUp> getStartUpList(String proId,String beginTime,String endTime);
	
	TbccBaseHisStartUp getStartUp(String proId,long id);
	
	/**
	 * ���ݹ��̱�ʶ����ʼʱ���ȡ��ͣ��¼
	 * @param projectId		���̱�ʶ
	 * @param beginTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @return
	 */
	List<TbccBaseHisStartUp> getStartUpListByTime(String projectId,String beginTime,String endTime) ;
	
	/**
	 * ���ݹ��̱�ʶ����ͣ��ʶ��ȡ��ͣ��¼
	 * @param projectId		���̱�ʶ
	 * @param id			��ͣ��ʶ
	 * @return
	 */
	TbccBaseHisStartUp getStartUpById(String projectId,Long id) ;
	
	/**
	 * ���ݿ�ʼʱ���ȡ��ͣ��¼��ʶID(��ĿFDAP�ڵ�ͼ�Ͽ��鿴��ʷ�켣ʱ��Ҫ�õ���)
	 * @param projectId		���̱�ʶID
	 * @param beginTime		��ͣ��¼��ʼʱ��
	 * @return
	 */
	Long getStartupidByBeginTime(String projectId,String beginTime);
	
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
	 * @return integer 						���ص�ǰ��ͣ��������Id���
	*/
	public long getHistBoxMaxStartupId(String tableName);
	
	/** 
	* �ϴ�һ����ͣ��¼����
	 * @param tableName					С������ͣ���ݱ�	
	 * @param devHistStartUp		��Ҫ�ϴ���1����ͣ����
	 * @return Integer 						������ͣ��¼�ϴ������0���ϴ��ɹ���-1���ϴ�ʧ�ܣ�)
	*/	
     public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp);		
		  
// modify by aftermath end
	
}
