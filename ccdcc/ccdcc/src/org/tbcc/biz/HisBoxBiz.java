package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccPrjType;

/**
 * ��ʷС�����ҵ����ʽӿ�
 * @author Administrator
 *
 */
public interface HisBoxBiz {
	
	/**
	 * ������γ
	 */
	public static final int SOUTH = 0 ;
	/**
	 * ����γ
	 */
	public static final int NORTH = 1 ;
	
	/**
	 * ������
	 */
	public static final int EAST = 0 ;
	/**
	 * ��������
	 */
	public static final int WEST =1 ;
	
	/**
	 * ����Ԥ��״̬
	 */
	public static final int PREALARM = 0 ;
	
	/**
	 * ������״̬
	 */
	public static final int ALARM = 1 ;
	/**
	 * ��������״̬
	 */
	public static final int NORMAL =2;
	
	public List<TbccBaseHisBox> getByProperty(String proId,String startTime,String endTime,String type,String value);
	
	public List<TbccPrjType> getBoxPrjByBranchID(Long branchId);
	
	public List<TbccPrjType> getBoxPrjProxy(Long branchId);
	
	public Object[] getCalcValue(List<TbccBaseHisBox> boxList);
	
//modify be aftermath begin	
	/** 
	* �ϴ�һ��С������ʷ��¼����
	 * @param tableName					С������ʷ���ݱ���	
	 * @param devHistData				��Ҫ�ϴ���1��С������ʷ����
	 * @return Integer 				    ������ʷ��¼�ϴ������0���ϴ��ɹ���-1���ϴ�ʧ�ܣ�)
	*/	
	public int uploadHistBoxData(String talbeName,TbccBaseHisBox devHistData);	
//modify be aftermath end	
	
}
