package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.CarStartup;
import org.fdapservice.entity.HisStartup;

/**
 * 车载启停记录业务逻辑接口
 * @author zhoukuanwei
 */

public interface CarStartupBiz {
	/**
	 * 上传车载启停记录
	 * @param code			企业授权码
	 * @param list			启停记录集合
	 * @return				上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadStartup(String code,List<CarStartup> startuplist);
	
	/**
	 * 根据时间与车载冷库ID，获取对应车载历史启停记录
	 * @param code			企业授权码
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param refId			冷库标识ID
	 * @return
	 */
	public List<HisStartup> getStartUp(String code,String startTime,String endTime,Long refId);
}
