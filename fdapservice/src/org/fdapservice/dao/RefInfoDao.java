package org.fdapservice.dao;



import org.fdapservice.entity.RefInfo;

public interface RefInfoDao {
	/**
	 * 根据冷库标识ID获取冷库信息
	 * @param refId		冷库标识ID
	 * @return
	 */
	public RefInfo getRefById(Long refId);
}
