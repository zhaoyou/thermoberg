package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.CarRealData;

/**
 * 车载实时数据上传业务逻辑接口
 * @author zhoukuanwei
 */

public interface CarRealDataBiz {
	
	/**
	 * 车载实时数据上传
	 * @param code			企业授权码
	 * @param list			车载实时数据集合
	 * @return				上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadCarReal(String code,List<CarRealData> carReallist);
	
	
	/**
	 * 根据企业授权码获取该企业下所有车载的实时数据
	 * @param code			企业授权码
	 * @return
	 */
	public List<CarRealData> getCarReal(String code);
}
