package org.ccdcc.service;

import java.util.List;

import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;
import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;
import org.ccdcc.entity.RefHisData;
import org.ccdcc.entity.StartUpView;

public interface AllService {
	
	
	/**
	 * 获取车载实时数据
	 * @param projectId	车载工程标示
	 * @return
	 */
	public CarRealView getCarRealData(String projectId);
	
	/**
	 * 获取车载实时数据，通过授权码认证
	 * @param key			授权码
	 * @param projectId		工程编号
	 * @return
	 */
	public CarRealView getCarRealData_auth(String key,String projectId);
	
	/**
	 * 获取车载历史数据
	 * @param projectId	车载工程标示
	 * @param parentId		启停的启动时间
	 * @param afterTime	启停过程中的某个时间点
	 * @return			车载历史数据集合
	 */
	public List<CarHisView> getCarHis(String projectId, Integer parentId,String afterTime) ;
	
	/**
	 * 获取车载历史数据
	 * @param key			车载工程对应的授权码
	 * @param projectId		车载工程标识
	 * @param parentId		车载的起停记录标识id
	 * @param afterTime		起停中的某个时刻
	 * @return
	 */
	public List<CarHisView> getCarHis_auth(String key,String projectId,Integer parentId,String afterTime);
	
	
	/**
	 * 获取车载的启停记录
	 * @param projectId	车载的工程标示
	 * @param time		获取某个启停标识Id后的10条启停记录
	 * @return			车载起停记录集合
	 */
	public List<StartUpView> getStartUpList(String projectId,Integer id) ;
	
	
	/**
	 * 获取车载的起停记录标识
	 * @param key		工程对应的授权码
	 * @param projectId工程编号
	 * @param id		起停Id
	 * @return
	 */
	public List<StartUpView> getStartUpList_auth(String key,String projectId,Integer id) ;
	
	/**
	 * 获取车载的起停记录(包括上下限范围)
	 * @param key			工程对应的授权码
	 * @param projectId	工程标识
	 * @param id				起停的id
	 * @return
	 */
	public List<StartUpView> getStartUpList_auth_tupdw(String key,String projectId,Integer id);
	
	/**
	 * 获取单个冷库的实时数据
	 * @param key			冷库对应的工程授权码
	 * @param projectId		工程标示
	 * @param realRefId		实际冷库编号
	 * @param refType		冷库类型
	 * @param floorType		冷库所处的楼层类型
	 * @param floorNo		楼层编号
	 * @return				包含单个冷库实时数据的xml字符串
	 */
	public String getRefRealData(String key,String projectId,Integer realRefId,Integer refType,Integer floorType,Integer floorNo);
	
	/**
	 * 获取某个工程下的所有冷库实时数据
	 * @param key			工程对应的授权码
	 * @param projectId		工程标示Id
	 * @return
	 */
	public String getAllRefRealData(String key,String projectId);
	
	/**
	 * 通过车载工程编号、已经车载工程对应的key，获取车载的平均温度
	 * @param key			车载工程对应的key
	 * @param projectId		车载工程编号
	 * @return
	 */
	public String getExCarRealData(String key,String projectId);
	
	
	
	/**
	 * 通过总部分支ID，获取该总部分支对应的所有车载工程的实时数据
	 * @param  CompanyId	总部分支企业标识id
	 * @return
	 */
	public List<CarRealData> getCarRealData_company(String CompanyId);
	
	
	
	/**
	 * 通过分支ID和分支类型，获取该分支对应的所有车载工程的实时数据
	 * @param  CompanyId	分支标识id
	 * @param  type			分支类型，1代表总部分支，2代表分支企业
	 * @return
	 */
	public List<CarRealData> getCarRealData_companytype(String CompanyId,int type);
	
	/**
	 * 提供给上海医药的获取车载实时数据，通过授权码认证
	 * @param key			授权码
	 * @param projectId		工程编号
	 * @return
	 */
	public CarRealView_new	getCarRealData_sy   (String key, String projectId);
	
	/**
	 * 提供给上海医药的获取车载历史数据方法
	 * @param key			车载工程对应的授权码
	 * @param projectId		车载工程标识
	 * @param parentId		车载的起停记录标识id
	 * @param afterTime		起停中的某个时刻
	 * @return
	 */
	public List<CarHisView_new>	getCarHis_sy 	(String key ,String projectId ,Integer parentId, String  afterTime);
	
	/**
	 * 上传冷库历史数据。
	 * @param projectId 冷库工程编号
	 * @param devId		冷库设备编号
	 * @param devType	冷库的设备类型 
	 * @param list		冷库的历史数据集合
	 */
	public void uploadRefHisData(String projectId, String devId, String devType, List<RefHisData> list);
}
