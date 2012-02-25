package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.CarHisData;
import org.fdapservice.entity.HisCarData;
/**
 * 车载历史数据上传业务逻辑接口
 * @author zhoukuanwei
 */
public interface CarHisDataBiz {
	/**
	 * 上传车载历史数据
	 * @param code				企业授权码
	 * @param list				车载历史数据信息集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadCarHisData(String code,List<CarHisData> carHislist);
	
	/**
	 * 通过车载历史启停记录标识ID获取车载历史数据
	 * @param code				企业授权码
	 * @param startupid			车载历史启停记录标识ID
	 * @return
	 */
	public List<HisCarData> getCarHis(String code,Long startupid);
}
