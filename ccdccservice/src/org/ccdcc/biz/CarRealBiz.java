package org.ccdcc.biz;


import java.util.Arrays;
import java.util.List;

import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;

/**
 * 车载实时数据业务接口
 * @author Administrator
 *
 */
public interface CarRealBiz {
	
		/**
		 * 标示错误的xml字符串
		 */
		public final static  String ERROR_XML = "<?xml version='1.0' encoding='utf-8' ?>"+
					"<datas><result_state>0</result_state></datas>" ;
		
		/**
		 * 老版本涉及到的工程编号
		 */
		public final static List<String> EXISTSPRJS = Arrays.asList("3000","1200","1210","1211");
	
		/**
		 * 当前操作的状态码 1	没有相应的实时数据
		 */
		public final static Integer NONE = 1 ;
		
		/**
		 * 当前操作的状态码 0    服务端发生了错误
		 */
		public final static Integer ERROR = 0 ;
		
		/**
		 * 当前操作的状态码 2 	  成功获取了实时数据
		 */
		public final static Integer NORMAL = 2;
	
		/**
		 * 根据项目获取车载的实时数据
		 * @param projectId			移动终端的projectId标示
		 * @return					返回实时数据实体类
		 */
		public CarRealView getRealData(String projectId) ;
		
		/**
		 * 获取车载的实时数据
		 * @param key			车载工程对应的授权码
		 * @param projectId		工程标识Id
		 * @return
		 */
		public CarRealView getRealData(String key,String projectId) ;
		
		/**
		 * 通过项目编号，已经对应的key，获取车载的平均温度
		 * @param key			车载工程对应的key
		 * @param projectId		车载工程编号
		 * @return
		 */
		public String 	getExRealData(String key,String projectId);
		
		/**
		 * 通过总部分支标识hqid获取总部分支对应的所有车载的实时数据
		 * @param hqid			总部分支标识hqid
		 * @return
		 */
		public List<CarRealData> getRealDataByHqid(String hqid);
		
		
		/**
		 * 通过分支ID和分支类型，获取该分支对应的所有车载工程的实时数据
		 * @param  CompanyId	分支标识id
		 * @param  type			分支类型，1代表总部分支，2代表分支企业
		 * @return
		 */
		public List<CarRealData> getRealDataByIdAndType(String companyid,int type);
		
		
		/**
		 * 为上海医药提供的获取车载的实时数据
		 * @param key			车载工程对应的授权码
		 * @param projectId		工程标识Id
		 * @return
		 */
		public CarRealView_new getRealData_sy(String key,String projectId) ;
}
