package org.fdapservice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.entity.HisCarData;

/**
 * 车载历史数据上传数据访问接口
 * @author zhoukuanwei
 */
public interface CarHisDataDao {
	
	public static final String CARHISDATA = "{call proc_loadCarHisData(?,?,?,?,?)}";
	
	/**
	 * 上传车载历史数据
	 * @param code				企业授权码
	 * @param source			车载历史数据字符串:{aid1,aid2,aid2,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,time,isalarm}
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadCarHisData(String code,String source);
	
	/**
	 * 记录日志
	 * @param result			操作状态值
	 * @param msg				操作信息
	 * @param logger			日志对象
	 */
	public void addlog(Integer result,String msg,Logger logger);
	
	
	/**
	 * 通过车载历史启停记录标识ID获取车载历史数据
	 * @param tableName			动态表表名
	 * @param startupid			车载历史启停记录标识ID
	 * @return
	 */
	public List<HisCarData> queryCarHis(String tableName,Long startupid);
}
