package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.RetailRealData;



/**
 * 小批零实时数据上传业务逻辑接口
 * @author wjt
 */

public interface RetailRealDataBiz {
	
	/**
	 * 小批零实时数据上传
	 * @param code			企业授权码
	 * @param list			小批零实时数据集合
	 * @return				上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadRetailReal(String code,List<RetailRealData> retailReallist);
	
	
	/**
	 * 根据企业授权码获取该企业下所有小批零的实时数据
	 * @param code			企业授权码
	 * @return
	 */
	public List<RetailRealData> getRetailReal(String code);
}
