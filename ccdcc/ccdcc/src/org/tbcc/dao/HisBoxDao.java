package org.tbcc.dao;

import java.util.List;
import org.tbcc.entity.TbccBaseHisBox;
/**
 * 小批零的数据访问接口
 * @author Administrator
 *
 */
public interface HisBoxDao {
	/**
	 * 根据小批零的历史数据表、启停的开始时间、结束时间、以及查询间隔获取小批零历史数据
	 * @param tableName			小批零历史数据表
	 * @param startTime			启停记录的开始时间
	 * @param endTime			启停记录的结束时间
	 * @param value				查询的间隔
	 * @return
	 */

	public List<TbccBaseHisBox> getHisBoxData(String tableName ,String startTime,String endTime,int value ) ;
	
	//modify by aftermath begin
	/** 
	*上传一条历史记录数据
	 * @param tableName					小批零历史数据表
	 * @param devHistData		        需要上传的1条历史数据 
	 * @return integer                     返回历史记录上传结果（0：上传成功；-1：上传失败；)
	*/
    int uploadHistBoxData(String tableName,TbccBaseHisBox devHistData);
		// modify by aftermath end	
		

	
	/**
	 * 获取指定时间内的第一条时间
	 * @param tableName
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Object getFirstDataTime(String tableName, String startTime, String endTime);

}
