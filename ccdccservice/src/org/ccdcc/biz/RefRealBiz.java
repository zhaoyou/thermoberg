package org.ccdcc.biz;

/**
 * 冷库实时数据业务访问接口
 * @author zhaoyou
 *
 */
public interface RefRealBiz {
	
		/**
		 * 标示错误的xml字符串
		 */
		public final static  String ERROR_XML = 
			"<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>" ;
	
		/**
		 * 获取当个冷库的实时数据xml字符串
		 * @param key		project对应的授权码
		 * @param projectId	工程标示Id
		 * @param realRefId	冷库实际编号
		 * @param refType	冷库类型
		 * @param floorType	楼层类型
		 * @param floorNo	楼层编号
		 * @return			包含结果的xml字符串 eg: <datas><refReal>....</refReal></datas>
		 */
		public String getRefReal(String key ,String projectId,Integer realRefId ,Integer refType,Integer floorType,Integer floorNo) ;
		
		/**
		 * 获取某个工程下的所有冷库实时数据xml字符串
		 * @param key			projectId对应的授权码
		 * @param projectId		工程标示Id
		 * @return				包含结果的xml字符串 eg:<datas><refReal>....</refReal></datas>
		 */
		public String getRefReal(String key ,String projectId) ;
}
