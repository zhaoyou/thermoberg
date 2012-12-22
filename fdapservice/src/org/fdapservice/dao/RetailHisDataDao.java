package org.fdapservice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.entity.HisCarData;
import org.fdapservice.entity.HisRetailData;

/**
 * 小批零历史数据上传数据访问接口
 * @author wjt
 */
public interface RetailHisDataDao {
	
	public static final String RETAILHISDATA = "{call proc_loadRetailHisData(?,?,?,?,?)}";
	
	/**
	 * 上传小批零历史数据
	 * @param code				企业授权码
	 * @param source			小批零历史数据字符串:{aid1,t1,latitude,latitude_dir,longitude,longitude_dir,time,isalarm}
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadRetailHisData(String code,String source);
	
	/**
	 * 记录日志
	 * @param result			操作状态值
	 * @param msg				操作信息
	 * @param logger			日志对象
	 */
	public void addlog(Integer result,String msg,Logger logger);
	
	
	/**
	 * 通过小批零历史启停记录标识ID获取小批零历史数据
	 * @param tableName			动态表表名
	 * @param startupid			小批零历史启停记录标识ID
	 * @return
	 */
	public List<HisRetailData> queryRetailHis(String tableName,Long startupid);
}
