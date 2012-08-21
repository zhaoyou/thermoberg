package com.thermoberg.service;

import java.util.List;

import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.AlarmSerious;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.entity.TimeOutAlarm;

/**
 * 这是报警服务接口
 * @author Administrator
 *
 */
public interface AlarmService {
		/**
		 * 获取仓库报警列表
		 * @return
		 */
		public List<AlarmInfo>	queryAlarmList() ;
		
		/**
		 * 获取仓库失去连接列表
		 * @return
		 */
		public List<AlarmInfo> queryConnectionList();
		
		
		/**
		 * 获取车载报警列表
		 * @return
		 */
		public List<CarAlarmInfo> queryCarAlarmList();
		
		/**
		 *  通过像授权码，向指定的号码拨号
		 * @param key			授权码
		 * @param phoneNumber	电话号码 
		 * @param phoneCount	拨号次数
		 * @return				0 成功  1 失败
		 */
		public Integer callPhone(String key ,String phoneNumber,Integer phoneCount);
		
		/**
		 * 通过授权码 ，向指定的手机发送短信
		 * @param key			授权码
		 * @param phoneNumber	手机号码 
		 * @param message	 	发送内容
		 * @return				0 成功 1 失败
		 */
		public Integer sendMessage(String key ,String phoneNumber,String message) ;
		
		/**
		 * 向指定的号码，停止拨号
		 * @param key			授权码
		 * @param phoneNumber	电话号码
		 * @return
		 */
		public Integer stopCall(String key ,String phoneNumber) ;
		
		/**
		 * 获取严重报警列表
		 * @return
		 */
		public List<AlarmSerious> queryAlarmSerious();
		
		
		/**
		 * 获取严重报警列表
		 * @return
		 */
		public List<TimeOutAlarm> queryTimeoutAlarm();
		
		/**
		 * 测试链接是否有效，来判断tomcat是否正常，外网是否可以正常访问
		 * @param urlStr		测试的链接
		 * @return
		 */
		public Integer testUrl(String urlStr);
		
		
		
		/**
		 * 获取车载实时数据(暂时运行的，只查询南京医药20E0仓库的数据)
		 * @return
		 */
		public List<HistRefData> getHistRefData();
		
		
		/**
		 * 获取在固定天数内没有更新的车载
		 * @param	天数
		 * @return	
		 */
		public List<CarDataNoChange> queryCar_noChange(Integer day);
}
