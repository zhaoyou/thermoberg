package com.thermoberg.service;

import java.util.List;

import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.AlarmSerious;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.entity.TimeOutAlarm;

/**
 * ���Ǳ�������ӿ�
 * @author Administrator
 *
 */
public interface AlarmService {
		/**
		 * ��ȡ�ֿⱨ���б�
		 * @return
		 */
		public List<AlarmInfo>	queryAlarmList() ;
		
		/**
		 * ��ȡ�ֿ�ʧȥ�����б�
		 * @return
		 */
		public List<AlarmInfo> queryConnectionList();
		
		
		/**
		 * ��ȡ���ر����б�
		 * @return
		 */
		public List<CarAlarmInfo> queryCarAlarmList();
		
		/**
		 *  ͨ������Ȩ�룬��ָ���ĺ��벦��
		 * @param key			��Ȩ��
		 * @param phoneNumber	�绰���� 
		 * @param phoneCount	���Ŵ���
		 * @return				0 �ɹ�  1 ʧ��
		 */
		public Integer callPhone(String key ,String phoneNumber,Integer phoneCount);
		
		/**
		 * ͨ����Ȩ�� ����ָ�����ֻ����Ͷ���
		 * @param key			��Ȩ��
		 * @param phoneNumber	�ֻ����� 
		 * @param message	 	��������
		 * @return				0 �ɹ� 1 ʧ��
		 */
		public Integer sendMessage(String key ,String phoneNumber,String message) ;
		
		/**
		 * ��ָ���ĺ��룬ֹͣ����
		 * @param key			��Ȩ��
		 * @param phoneNumber	�绰����
		 * @return
		 */
		public Integer stopCall(String key ,String phoneNumber) ;
		
		/**
		 * ��ȡ���ر����б�
		 * @return
		 */
		public List<AlarmSerious> queryAlarmSerious();
		
		
		/**
		 * ��ȡ���ر����б�
		 * @return
		 */
		public List<TimeOutAlarm> queryTimeoutAlarm();
		
		/**
		 * ���������Ƿ���Ч�����ж�tomcat�Ƿ������������Ƿ������������
		 * @param urlStr		���Ե�����
		 * @return
		 */
		public Integer testUrl(String urlStr);
		
		
		
		/**
		 * ��ȡ����ʵʱ����(��ʱ���еģ�ֻ��ѯ�Ͼ�ҽҩ20E0�ֿ������)
		 * @return
		 */
		public List<HistRefData> getHistRefData();
		
		
		/**
		 * ��ȡ�ڹ̶�������û�и��µĳ���
		 * @param	����
		 * @return	
		 */
		public List<CarDataNoChange> queryCar_noChange(Integer day);
}
