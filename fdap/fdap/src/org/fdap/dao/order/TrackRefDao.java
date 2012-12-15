package org.fdap.dao.order;

import java.util.List;

public interface TrackRefDao {
	/**
	 * 
	 * @param erpId
	 * @return string[] [0] 存储环境  [1] 存储类型 [2] 存储载体
	 */
	public List getRefEnv(Long erpId);
}
