package org.tbcc.dao;

import java.util.List;
import org.tbcc.entity.TbccBaseHisBox;
/**
 * С��������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface HisBoxDao {
	/**
	 * ����С�������ʷ���ݱ���ͣ�Ŀ�ʼʱ�䡢����ʱ�䡢�Լ���ѯ�����ȡС������ʷ����
	 * @param tableName			С������ʷ���ݱ�
	 * @param startTime			��ͣ��¼�Ŀ�ʼʱ��
	 * @param endTime			��ͣ��¼�Ľ���ʱ��
	 * @param value				��ѯ�ļ��
	 * @return
	 */

	public List<TbccBaseHisBox> getHisBoxData(String tableName ,String startTime,String endTime,int value ) ;
	
	//modify by aftermath begin
	/** 
	*�ϴ�һ����ʷ��¼����
	 * @param tableName					С������ʷ���ݱ�
	 * @param devHistData		        ��Ҫ�ϴ���1����ʷ���� 
	 * @return integer                     ������ʷ��¼�ϴ������0���ϴ��ɹ���-1���ϴ�ʧ�ܣ�)
	*/
    int uploadHistBoxData(String tableName,TbccBaseHisBox devHistData);
		// modify by aftermath end	
		

	
	/**
	 * ��ȡָ��ʱ���ڵĵ�һ��ʱ��
	 * @param tableName
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Object getFirstDataTime(String tableName, String startTime, String endTime);

}
