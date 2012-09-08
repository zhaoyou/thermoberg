package org.tbcc.biz;

import java.util.List;

import org.tbcc.entity.TbccShyyGPSLog;

public interface ShyyGPSLogBiz {
	public List<TbccShyyGPSLog> getByTime(String start, String end, int limit);
}
