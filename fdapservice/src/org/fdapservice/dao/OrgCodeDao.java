package org.fdapservice.dao;


public interface OrgCodeDao {
	/**
	 * 根据授权码获取企业ID
	 * @param		code		企业授权码
	 * @return
	 */
	public Long queryOidByCode(String code);
	
}
