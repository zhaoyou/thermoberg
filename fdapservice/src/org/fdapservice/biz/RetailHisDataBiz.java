package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.CarHisData;
import org.fdapservice.entity.HisCarData;
import org.fdapservice.entity.HisRetailData;
import org.fdapservice.entity.RetailHisData;
/**
 * 小批零历史数据上传业务逻辑接口
 * @author wjt
 */
public interface RetailHisDataBiz {
	/**
	 * 上传小批零历史数据
	 * @param code				企业授权码
	 * @param list				小批零历史数据信息集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadRetailHisData(String code,List<RetailHisData> retailHislist);
	
	/**
	 * 通过小批零历史启停记录标识ID获取小批零历史数据
	 * @param code				企业授权码
	 * @param startupid			小批零历史启停记录标识ID
	 * @return
	 */
	public List<HisRetailData> getRetailHis(String code,Long startupid);
}
