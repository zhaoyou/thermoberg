package org.fdapservice.dao;

import java.util.List;

import org.fdapservice.entity.CallPhone;

public interface CallPhoneDao {
	/**
	 * 获取包括拨号号码与短信号码的电话信息
	 * @return
	 */
	public List<CallPhone> getPhoneCall();
}
