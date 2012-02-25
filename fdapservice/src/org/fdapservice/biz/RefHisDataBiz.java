package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.RefHisData;

public interface RefHisDataBiz {
	/**
	 * 根据企业授权码获取该企业下所有的仓库实时数据
	 * @param code			企业授权码
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param refId			冷库标识ID
	 * return
	 */
	public List<RefHisData> getRefHis(String code,String startTime,String endTime,Long refId);
}
