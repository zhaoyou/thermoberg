package org.tbcc.dao;

import java.util.List;

import org.tbcc.entity.TbccShyyGPSLog;

public interface ShyyGPSLogDao {
	public List<TbccShyyGPSLog> getByTime(String start, String end, int limit);
}
