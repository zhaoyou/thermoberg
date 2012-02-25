package org.fdapservice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.entity.HisStartup;

/**
 * 上传车载启停记录数据访问接口
 * @author zhoukuanwei
 */
public interface CarStartupDao {
	
	public static final String UPLOADSTARTUP = "{call proc_loadStartupData(?,?,?,?)}";
	
	/**
	 * 上传车载启停记录
	 * @param code				企业授权码
	 * @param source			源数据{aid1,aid2,aid3,startTime,endTime,carrier,intervalValue}
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadStartup(String code,String source);
	
	/**
	 * 记录日志
	 * @param result			操作状态值
	 * @param msg				操作信息
	 * @param logger			日志对象
	 */
	public void addLog(Integer result,String msg,Logger logger);
	
	/**
	 * 根据时间与车载冷库ID，获取对应车载历史启停记录
	 * @param tableName		动态表表名
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param refId			冷库标识ID
	 * @return
	 */
	public List<HisStartup> queryCarStartup(String tableName,String startTime,String endTime,Long refId);
}
