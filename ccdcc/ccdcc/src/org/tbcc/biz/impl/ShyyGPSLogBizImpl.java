package org.tbcc.biz.impl;

import java.util.List;

import org.tbcc.biz.ShyyGPSLogBiz;
import org.tbcc.dao.ShyyGPSLogDao;
import org.tbcc.entity.TbccShyyGPSLog;

public class ShyyGPSLogBizImpl implements ShyyGPSLogBiz {

	private ShyyGPSLogDao dao = null;

	public void setDao(ShyyGPSLogDao dao) {
		this.dao = dao;
	}

	@Override
	public List<TbccShyyGPSLog> getByTime(String start, String end, int limit) {
		return dao.getByTime(start, end, limit);
	}

}
