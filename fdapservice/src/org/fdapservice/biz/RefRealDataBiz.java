package org.fdapservice.biz;

import java.util.List;

import org.fdapservice.entity.RefRealData;

/**
 * 车载实时数据上传业务操作接口
 * @author zhaoyou
 *
 */
public interface RefRealDataBiz {
			
		
	
			/**
			 * 上传冷库探头的实时数据
			 * @param code			企业授权码
			 * @param list			探头实时数据信息集合
			 * @param Time			数据时刻
			 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
			 */
			public Integer uploadRealData(String code ,List<RefRealData	> list,String Time) ;
			
			/**
			 * 根据企业授权码获取该企业下所有的仓库实时数据
			 * @param code			企业授权码
			 * return
			 */
			public List<RefRealData> getRefReal(String code);
}
